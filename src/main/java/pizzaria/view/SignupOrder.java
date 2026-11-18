 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package pizzaria.view;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pizzaria.controller.ClientController;
import pizzaria.controller.OrderController;
import pizzaria.model.Client;
import pizzaria.model.Order;

/**
 *
 * @author Vinicius Dias
 */
public class SignupOrder extends javax.swing.JPanel {

    private final ClientController clientController;
    private final OrderController orderController;
    private Client currentClient;

    public SignupOrder(ClientController clientController, OrderController orderController) {
        this.clientController = clientController;
        this.orderController = orderController;
        initComponents();

        // Configura colunas reais da tabela de pedidos do cliente
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setColumnIdentifiers(new String[]{"Código", "Qtd. Pizzas", "Subtotal", "Status"});
        model.setRowCount(0);

        jTextField2.setEditable(false); // Nome é preenchido ao buscar pelo telefone
    }

    private void buscarPedidosPorTelefone() {
        String telephone = jTextField1.getText().trim();
        if (telephone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite o telefone do cliente e pressione Enter.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        this.currentClient = clientController.findClientByTelephone(telephone);
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);

        if (this.currentClient == null) {
            jTextField2.setText("");
            JOptionPane.showMessageDialog(this, "Cliente não encontrado com esse telefone.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        jTextField2.setText(currentClient.getName() + " " + currentClient.getLastname());

        List<Order> allOrders = orderController.getAllOrders();
        for (Order o : allOrders) {
            if (o.getClient() != null && o.getClient().getId().equals(currentClient.getId())) {
                model.addRow(new Object[]{
                    o.getId(),
                    o.getPizzas().size(),
                    String.format("R$ %.2f", o.getSubtotal()),
                    o.getOrderStatus()
                });
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        jTextField1.addActionListener(this::jTextField1ActionPerformed);

        jLabel1.setText("Nome do cliente");

        jLabel2.setText("Telefone");

        jTextField2.addActionListener(this::jTextField2ActionPerformed);

        jLabel3.setText("Pedidos do Cliente");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, ""},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addGap(73, 73, 73)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(176, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>                        

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // Ao pressionar Enter no campo Telefone, busca o cliente e carrega seus pedidos
        buscarPedidosPorTelefone();
    }                                           

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {                                            
    }                                           

    // Variables declaration - do not modify                     
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration                   
}