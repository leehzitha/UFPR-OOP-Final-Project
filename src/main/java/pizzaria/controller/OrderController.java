/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pizzaria.controller;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import pizzaria.model.Client;
import pizzaria.model.Order;
import pizzaria.model.Pizza;
import pizzaria.model.PriceTable;
import pizzaria.model.dao.OrderDao;
import pizzaria.model.dao.PriceTableDao;

/**
 *
 * @author dias
 */
public class OrderController {

    private final OrderDao orderDao;
    private final PriceTableDao priceTableDao;

    public OrderController() {
        this.orderDao = new OrderDao();
        this.priceTableDao = new PriceTableDao();
    }

    public boolean createOrder(Client client, ArrayList<Pizza> pizzas) {
        if (client == null || client.getId() == null) {
            JOptionPane.showMessageDialog(null, "Selecione um cliente.", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (pizzas == null || pizzas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Adicione ao menos uma pizza ao pedido.", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        try {
            PriceTable priceTable = priceTableDao.get();
            double subtotal = 0.0;
            for (Pizza pizza : pizzas) {
                subtotal += pizza.getPrice(priceTable);
            }

            Order order = new Order(client, pizzas, subtotal, 0L);
            orderDao.add(order);
            JOptionPane.showMessageDialog(null, "Pedido criado com sucesso!");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar pedido: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean updateOrderStatus(Order order, String newStatus) {
        if (order == null || newStatus == null || newStatus.isBlank()) {
            return false;
        }

        try {
            order.setOrderStatus(newStatus);
            orderDao.update(order);
            JOptionPane.showMessageDialog(null, "status do pedido alterado com sucesso!");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar status: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean cancelOrder(Order order) {
        if (order == null) {
            return false;
        }

        try {
            orderDao.delete(order);
            JOptionPane.showMessageDialog(null, "Pedido cancelado com sucesso!");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cancelar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public List<Order> getAllOrders() {
        try {
            return orderDao.getAll();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return List.of();
        }
    }

    public Order findActiveOrderByClient(Client client) {
        if (client == null || client.getId() == null) {
            return null;
        }

        try {
            return orderDao.getAll().stream()
                    .filter(o -> o.getClient().getId().equals(client.getId())
                            && "ABERTO".equalsIgnoreCase(o.getOrderStatus()))
                    .findFirst()
                    .orElse(null);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}