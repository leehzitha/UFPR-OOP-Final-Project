/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pizzaria.view;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pizzaria.controller.ClientController;
import pizzaria.controller.FlavourController;
import pizzaria.controller.OrderController;
import pizzaria.controller.PriceTableController;
import pizzaria.model.Order;

/**
 *
 * @author dias
 */
public class ListOrders extends javax.swing.JPanel {

    private final ClientController clientController;
    private final FlavourController flavourController;
    private final OrderController orderController;
    private final PriceTableController priceTableController;

    private javax.swing.JPanel lastScreen;
    private List<Order> displayedOrders = new ArrayList<>();

    public ListOrders(javax.swing.JPanel lastScreen,
                      ClientController clientController,
                      FlavourController flavourController,
                      OrderController orderController,
                      PriceTableController priceTableController) {
        this.lastScreen = lastScreen;
        this.clientController = clientController;
        this.flavourController = flavourController;
        this.orderController = orderController;
        this.priceTableController = priceTableController;

        initComponents();
        carregarTabelaPedidos();
    }

    public void carregarTabelaPedidos() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        this.displayedOrders.clear();

        for (Order o : orderController.getAllOrders()) {
            if (o.getPizzas().isEmpty()) {
                continue;
            }

            this.displayedOrders.add(o);
            model.addRow(new Object[]{
                o.getId(),
                o.getPizzas().size(),
                o.getClient().getName(),
                o.getClient().getTelephone(),
                String.format("R$ %.2f", o.getSubtotal()),
                o.getOrderStatus()
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Pedidos");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Itens", "Cliente", "Telefone", "Preço", "Status"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Voltar");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jButton2.setBackground(new java.awt.Color(255, 153, 51));
        jButton2.setText("Mudar Status");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addContainerGap(27, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(334, 334, 334))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addContainerGap(9, Short.MAX_VALUE))
        );
    }// </editor-fold>                        

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        javax.swing.JFrame lastWindow = (javax.swing.JFrame) javax.swing.SwingUtilities.getWindowAncestor(this);
        lastWindow.setContentPane(lastScreen);
        lastWindow.revalidate();
        lastWindow.repaint();
    }                                        

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        int selectedRow = jTable1.getSelectedRow();

        if (selectedRow >= 0) {
            Order selectedOrder = this.displayedOrders.get(selectedRow);

            javax.swing.JFrame window = (javax.swing.JFrame) javax.swing.SwingUtilities.getWindowAncestor(this);
            ChangeStatus telaMudaStatus = new ChangeStatus(selectedOrder, this, this.orderController, lastScreen);
            window.setContentPane(telaMudaStatus);
            window.revalidate();
            window.repaint();
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um pedido na tabela para alterar o status!", "Atenção", JOptionPane.WARNING_MESSAGE);
        }
    }                                        

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration                   
}