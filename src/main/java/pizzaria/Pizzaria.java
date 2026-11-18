/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package pizzaria;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import pizzaria.controller.ClientController;
import pizzaria.controller.FlavourController;
import pizzaria.controller.OrderController;
import pizzaria.controller.PriceTableController;
import pizzaria.model.Client;
import pizzaria.model.Flavour;
import pizzaria.model.Order;
import pizzaria.model.Pizza;
import pizzaria.model.PriceTable;
import pizzaria.model.dao.DatabaseMigration;
import pizzaria.view.Home;

/**
 *
 * @author letic
 * @author Vinicius Dias
 */
public class Pizzaria {


    private final ClientController clientController;
    private final FlavourController flavourController;
    private final OrderController orderController;
    private final PriceTableController priceTableController;

    public Pizzaria() {
        this.clientController = new ClientController();
        this.flavourController = new FlavourController();
        this.orderController = new OrderController();
        this.priceTableController = new PriceTableController();
    }
    
    public ClientController getClientController() {
        return clientController;
    }

    public FlavourController getFlavourController() {
        return flavourController;
    }

    public OrderController getOrderController() {
        return orderController;
    }

    public PriceTableController getPriceTableController() {
        return priceTableController;
    }

    // --- Métodos delegados aos Controllers (mantém todas as Views funcionando) ---
    public ArrayList<Client> getClients() {
        return new ArrayList<>(clientController.getAllClients());
    }

    public ArrayList<Order> getOrdersList() {
        return new ArrayList<>(orderController.getAllOrders());
    }

    public PriceTable getPriceTable() {
        return priceTableController.getPriceTable();
    }

    public ArrayList<Flavour> getFlavoursList() {
        return new ArrayList<>(flavourController.getAllFlavours());
    }

    public void newOrder(Client client, ArrayList<Pizza> pizzas, double subtotal) {
        orderController.createOrder(client, pizzas);
    }

    public void cancelOrder(Order order) {
        orderController.cancelOrder(order);
    }

    public Client findClientByTelephone(String telephone) {
        return clientController.findClientByTelephone(telephone);
    }

    public Order findActiveOrderByClient(Client client) {
        return orderController.findActiveOrderByClient(client);
    }

    public void deleteClientWithOrders(Client client) {
        clientController.deleteClient(client);
    }

    public void deleteFlavour(Flavour flavour) {
        flavourController.deleteFlavour(flavour);
    }
    
    public static void main(String[] args) {
        try {
            DatabaseMigration.migrate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Database initialization error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ClientController clientController = new ClientController();
        FlavourController flavourController = new FlavourController();
        OrderController orderController = new OrderController();
        PriceTableController priceTableController = new PriceTableController();

        JFrame screen = new JFrame("Pizzaria - Sistema de Cadastro");
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setSize(800, 600);
        screen.setLocationRelativeTo(null);

        Home homeScreen = new Home(clientController, flavourController, orderController, priceTableController);
        screen.add(homeScreen);
        screen.setVisible(true);
    }
}