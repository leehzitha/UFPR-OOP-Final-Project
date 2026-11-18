/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package pizzaria.view;

/**
 *
 * @author Vinicius Dias
 */

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pizzaria.controller.ClientController;
import pizzaria.controller.FlavourController;
import pizzaria.controller.OrderController;
import pizzaria.controller.PriceTableController;
import pizzaria.model.Client;

public class ListClient extends javax.swing.JPanel {

    private final ClientController clientController;
    private final FlavourController flavourController;
    private final OrderController orderController;
    private final PriceTableController priceTableController;

    private DefaultTableModel tableModel;
    private List<Client> clientList;
    private List<Client> displayedClients;

    public ListClient(ClientController clientController,
                      FlavourController flavourController,
                      OrderController orderController,
                      PriceTableController priceTableController) {
        initComponents();
        this.clientController = clientController;
        this.flavourController = flavourController;
        this.orderController = orderController;
        this.priceTableController = priceTableController;
        this.tableModel = (DefaultTableModel) jTable1.getModel();

        loadClients();
    }

    private void loadClients() {
        this.clientList = clientController.getAllClients();
        updateTable(this.clientList);
    }

    private void updateTable(List<Client> listToShow) {
        this.displayedClients = listToShow;
        tableModel.setNumRows(0);

        for (Client c : listToShow) {
            tableModel.addRow(new Object[]{
                c.getName(),
                c.getLastname(),
                c.getTelephone()
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jButton1.setText("Buscar");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jTextField1.setToolTipText("Buscar Cliente");
        jTextField1.setCaretColor(new java.awt.Color(204, 204, 204));
        jTextField1.addActionListener(this::jTextField1ActionPerformed);

        jLabel2.setText("Buscar Cliente");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nome", "Sobrenome", "Telefone"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton4.setBackground(new java.awt.Color(0, 255, 0));
        jButton4.setText("Adicionar");
        jButton4.addActionListener(this::jButton4ActionPerformed);

        jButton5.setBackground(new java.awt.Color(204, 204, 204));
        jButton5.setText("Adicionar Pedido");
        jButton5.addActionListener(this::jButton5ActionPerformed);
        
        jButton2.setBackground(new java.awt.Color(255, 0, 0));
        jButton2.setText("Excluir");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jButton3.setBackground(new java.awt.Color(204, 204, 204));
        jButton3.setText("Editar");
        jButton3.addActionListener(this::jButton3ActionPerformed);

        btnVoltar.setText("Inicio");
        btnVoltar.addActionListener(this::btnVoltarActionPerformed);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18));
        jLabel1.setText("Clientes");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnVoltar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addGap(12, 12, 12)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jButton1))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3))
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(btnVoltar)
                    .addComponent(jButton4) 
                    .addComponent(jButton5))
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton3)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
    }// </editor-fold>

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        String search = jTextField1.getText().toLowerCase().trim();
        if (search.isEmpty()) {
            updateTable(clientList);
            return;
        }

        List<Client> filteredList = new ArrayList<>();

        for (Client c : clientList) {
            if (c.getName().toLowerCase().contains(search) ||
                c.getLastname().toLowerCase().contains(search) ||
                c.getTelephone().contains(search)) {
                filteredList.add(c);
            }
        }

        updateTable(filteredList);
    }

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        SignupClient signupScreen = new SignupClient(
            clientController,
            flavourController,
            orderController,
            priceTableController,
            null,
            this
        );
        javax.swing.JFrame mainScreen = (javax.swing.JFrame) javax.swing.SwingUtilities.getWindowAncestor(this);
        mainScreen.setContentPane(signupScreen);
        mainScreen.revalidate();
        mainScreen.repaint();
    }
    
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = jTable1.getSelectedRow();

        if (selectedRow >= 0) {
            Client selectedClient = this.displayedClients.get(selectedRow);
            
            // Nota: ao refatorar makeOrder.java, atualize o construtor dela para receber os controllers + this + selectedClient
            MakeOrder makeOrderScreen = new MakeOrder(
                clientController,
                flavourController,
                orderController,
                priceTableController,
                this,
                selectedClient
            );
            
            javax.swing.JFrame mainScreen = (javax.swing.JFrame) javax.swing.SwingUtilities.getWindowAncestor(this);
            mainScreen.setContentPane(makeOrderScreen);
            mainScreen.revalidate();
            mainScreen.repaint();
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um cliente na tabela para gerar o pedido!", "Atenção", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = jTable1.getSelectedRow();

        if (selectedRow >= 0) {
            Client selectedClient = this.displayedClients.get(selectedRow);
            int answer = JOptionPane.showConfirmDialog(
                this,
                "Tem certeza que deseja excluir o cliente " + selectedClient.getName() + "?",
                "Confirmar Exclusão",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );

            if (answer == JOptionPane.YES_OPTION) {
                boolean deleted = clientController.deleteClient(selectedClient);
                if (deleted) {
                    jTextField1.setText("");
                    loadClients(); // Recarrega a lista atualizada do banco
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um cliente na tabela para excluir.", "Atenção", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {
        Home homeScreen = new Home(clientController, flavourController, orderController, priceTableController);
        javax.swing.JFrame mainScreen = (javax.swing.JFrame) javax.swing.SwingUtilities.getWindowAncestor(this);
        if (mainScreen != null) {
            mainScreen.setContentPane(homeScreen);
            mainScreen.revalidate();
            mainScreen.repaint();
        }
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = jTable1.getSelectedRow();

        if (selectedRow >= 0) {
            Client selectedClient = this.displayedClients.get(selectedRow);
            SignupClient editScreen = new SignupClient(
                clientController,
                flavourController,
                orderController,
                priceTableController,
                selectedClient,
                this
            );
            javax.swing.JFrame mainScreen = (javax.swing.JFrame) javax.swing.SwingUtilities.getWindowAncestor(this);
            mainScreen.setContentPane(editScreen);
            mainScreen.revalidate();
            mainScreen.repaint();
        } else {
            JOptionPane.showMessageDialog(this, "Por favor selecione um cliente para edição!", "Atenção", JOptionPane.WARNING_MESSAGE);
        }
    }

    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration
}