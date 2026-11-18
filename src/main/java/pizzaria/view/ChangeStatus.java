/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pizzaria.view;

import pizzaria.controller.OrderController;
import pizzaria.model.Client;
import pizzaria.model.Order;

/**
 *
 * @author dias
 */
public class ChangeStatus extends javax.swing.JPanel {

    private Order orderToChange;
    private ListOrders listOrdersScreen;
    private final OrderController orderController;
    private javax.swing.JPanel lastBeforeScreen;

    public ChangeStatus(Order orderToChange,
                        ListOrders listOrdersScreen,
                        OrderController orderController,
                        javax.swing.JPanel lastBeforeScreen) {
        initComponents();
        this.orderToChange = orderToChange;
        this.listOrdersScreen = listOrdersScreen;
        this.orderController = orderController;
        this.lastBeforeScreen = lastBeforeScreen;

        Client client = orderToChange.getClient();
        jLabel2.setText("Nome: " + client.getName());
        jLabel3.setText("Telefone: " + client.getTelephone());
        jLabel4.setText("Status: " + orderToChange.getOrderStatus());
        jLabel5.setText("Código: " + orderToChange.getId());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Edição de Status");

        jLabel2.setText("Nome:");

        jLabel3.setText("Telefone:");

        jLabel4.setText("Status");

        jLabel5.setText("Código:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A CAMINHO", "ENTREGUE" }));
        jComboBox1.addActionListener(this::jComboBox1ActionPerformed);

        jButton1.setBackground(new java.awt.Color(204, 204, 204));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Cancelar");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jButton2.setBackground(new java.awt.Color(51, 255, 51));
        jButton2.setText("Alterar");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(284, 284, 284)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jLabel4))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(210, 210, 210)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(53, 53, 53)
                                .addComponent(jButton2)))))
                .addContainerGap(290, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(19, 19, 19)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addGap(29, 29, 29)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(124, Short.MAX_VALUE))
        );
    }// </editor-fold>                        

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        javax.swing.JFrame lastWindow = (javax.swing.JFrame) javax.swing.SwingUtilities.getWindowAncestor(this);
        lastWindow.setContentPane(this.listOrdersScreen);
        lastWindow.revalidate();
        lastWindow.repaint();
    }                                        

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        String newStatus = jComboBox1.getSelectedItem().toString();
        boolean updated = orderController.updateOrderStatus(orderToChange, newStatus);

        if (updated) {
            this.listOrdersScreen.carregarTabelaPedidos();
            javax.swing.JFrame lastWindow = (javax.swing.JFrame) javax.swing.SwingUtilities.getWindowAncestor(this);
            lastWindow.setContentPane(this.listOrdersScreen);
            lastWindow.revalidate();
            lastWindow.repaint();
        }
    }                                        

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {                                           
    }                                          

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration                   
}