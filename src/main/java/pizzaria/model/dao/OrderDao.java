/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pizzaria.model.dao;

import pizzaria.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pizzaria.forma.Circle;
import pizzaria.forma.Forma;
import pizzaria.forma.Square;
import pizzaria.forma.Triangle;
import pizzaria.model.Client;
import pizzaria.model.Flavour;
import pizzaria.model.Order;
import pizzaria.model.Pizza;
import pizzaria.model.PizzaType;
import pizzaria.model.PriceTable;

/**
 *
 * @author dias
 */
public class OrderDao implements Dao<Order> {

    private final ClientDao clientDao = new ClientDao();
    private final PriceTableDao priceTableDao = new PriceTableDao();

    @Override
    public void add(Order order) throws Exception {
        String sqlOrder = "INSERT INTO orders (client_id, subtotal, order_status) VALUES (?, ?, ?)";
        String sqlPizza = "INSERT INTO pizza (order_id, shape_type, dimension, price) VALUES (?, ?, ?, ?)";
        String sqlPizzaFlavour = "INSERT INTO pizza_flavour (pizza_id, flavour_id) VALUES (?, ?)";

        PriceTable pt = priceTableDao.get();

        try (Connection conn = ConnectionFactory.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement stmtOrder = conn.prepareStatement(sqlOrder, Statement.RETURN_GENERATED_KEYS)) {
                stmtOrder.setLong(1, order.getClient().getId());
                stmtOrder.setDouble(2, order.getPrice(pt));
                stmtOrder.setString(3, order.getOrderStatus());
                stmtOrder.executeUpdate();

                long orderId;
                try (ResultSet rsOrder = stmtOrder.getGeneratedKeys()) {
                    if (!rsOrder.next()) throw new Exception("Falha ao gerar ID do pedido");
                    orderId = rsOrder.getLong(1);
                    order.setId(orderId);
                }

                for (Pizza pizza : order.getPizzas()) {
                    try (PreparedStatement stmtPizza = conn.prepareStatement(sqlPizza, Statement.RETURN_GENERATED_KEYS)) {
                        Forma forma = pizza.getForma();
                        stmtPizza.setLong(1, orderId);
                        stmtPizza.setString(2, forma.getClass().getSimpleName().toUpperCase());
                        stmtPizza.setDouble(3, extractDimension(forma));
                        stmtPizza.setDouble(4, pizza.getPrice(pt));
                        stmtPizza.executeUpdate();

                        long pizzaId;
                        try (ResultSet rsPizza = stmtPizza.getGeneratedKeys()) {
                            if (!rsPizza.next()) throw new Exception("Falha ao gerar ID da pizza");
                            pizzaId = rsPizza.getLong(1);
                        }
                        //relationship pizza and flavour
                        for (Flavour f : pizza.getFlavours()) {
                            if (f != null) {
                                try (PreparedStatement stmtPf = conn.prepareStatement(sqlPizzaFlavour)) {
                                    stmtPf.setLong(1, pizzaId);
                                    stmtPf.setLong(2, f.getId());
                                    stmtPf.executeUpdate();
                                }
                            }
                        }
                    }
                }

                conn.commit(); // Confirma tudo no banco
            } catch (Exception e) {
                conn.rollback(); // Desfaz tudo se algo falhar
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        }
    }

    @Override
    public List<Order> getAll() throws Exception {
        String sql = "SELECT * FROM orders";
        List<Order> orders = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                long orderId = rs.getLong("id");
                Client client = clientDao.getById(rs.getLong("client_id"));
                double subtotal = rs.getDouble("subtotal");
                String status = rs.getString("order_status");

                ArrayList<Pizza> pizzas = getPizzasByOrderId(conn, orderId);
                Order order = new Order(client, pizzas, subtotal, orderId);
                order.setOrderStatus(status);
                orders.add(order);
            }
        }
        return orders;
    }

    private ArrayList<Pizza> getPizzasByOrderId(Connection conn, long orderId) throws Exception {
        String sql = "SELECT * FROM pizza WHERE order_id = ?";
        ArrayList<Pizza> pizzas = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, orderId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    long pizzaId = rs.getLong("id");
                    String shapeType = rs.getString("shape_type");
                    double dimension = rs.getDouble("dimension");

                    Pizza p = new Pizza();
                    p.setForma(reconstruirForma(shapeType, dimension));
                    p.setFlavours(getFlavoursByPizzaId(conn, pizzaId));
                    pizzas.add(p);
                }
            }
        }
        return pizzas;
    }

    private Flavour[] getFlavoursByPizzaId(Connection conn, long pizzaId) throws Exception {
        String sql = """
            SELECT f.* FROM flavour f
            INNER JOIN pizza_flavour pf ON f.id = pf.flavour_id
            WHERE pf.pizza_id = ?
            """;
        Flavour[] flavours = new Flavour[2];
        int index = 0;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, pizzaId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next() && index < 2) {
                    Flavour f = new Flavour(
                        PizzaType.valueOf(rs.getString("pizza_type")),
                        rs.getString("name")
                    );
                    f.setId(rs.getLong("id"));
                    flavours[index++] = f;
                }
            }
        }
        return flavours;
    }

    @Override
    public void update(Order order) throws Exception {
        String sql = "UPDATE orders SET order_status = ?, subtotal = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, order.getOrderStatus());
            stmt.setDouble(2, order.getSubtotal());
            stmt.setLong(3, order.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public Order getById(long id) throws Exception {
        return getAll().stream().filter(o -> o.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void delete(Order order) throws Exception {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM orders WHERE id = ?")) {
            stmt.setLong(1, order.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteAll() throws Exception {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM orders")) {
            stmt.executeUpdate();
        }
    }

    private double extractDimension(Forma forma) {
        return forma != null ? forma.getDimensao() : 0.0;
    }

    private Forma reconstruirForma(String type, double dimension) {
        return switch (type) {
            case "CIRCLE" -> new Circle(dimension);
            case "SQUARE" -> new Square(dimension);
            case "TRIANGLE" -> new Triangle(dimension);
            default -> throw new IllegalArgumentException("Forma desconhecida: " + type);
        };
    }
}