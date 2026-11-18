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
import pizzaria.forma.Circle;
import pizzaria.forma.Forma;
import pizzaria.forma.Square;
import pizzaria.forma.Triangle;
import pizzaria.model.Client;
import pizzaria.model.Flavour;
import pizzaria.model.Pizza;
import pizzaria.model.PriceTable;

/**
 *
 * @author dias
 */
public class ListAvaliableFlavours extends javax.swing.JPanel {

    private final ClientController clientController;
    private final FlavourController flavourController;
    private final OrderController orderController;
    private final PriceTableController priceTableController;

    private javax.swing.JPanel lastScreen;
    private String selectedFormat;
    private Client selectedClient;
    private Double orderArea;
    private Forma formaInstanciada;
    private DefaultTableModel tableModelSabores;
    private List<Flavour> availableFlavours = new ArrayList<>();
    private ArrayList<Flavour> saboresSelecionados = new ArrayList<>();
    private DefaultTableModel tableModelSaboresSelecionados;

    public ListAvaliableFlavours(javax.swing.JPanel lastScreen,
                                String selectedFormat,
                                ClientController clientController,
                                FlavourController flavourController,
                                OrderController orderController,
                                PriceTableController priceTableController,
                                Client selectedClient,
                                String orderArea) {
        this.lastScreen = lastScreen;
        this.selectedFormat = selectedFormat;
        this.clientController = clientController;
        this.flavourController = flavourController;
        this.orderController = orderController;
        this.priceTableController = priceTableController;
        this.selectedClient = selectedClient;
        this.orderArea = Double.parseDouble(orderArea.replace(",", "."));

        switch (selectedFormat) {
            case "Círculo":
                this.formaInstanciada = new Circle(this.orderArea);
                break;
            case "Triângulo":
                this.formaInstanciada = new Triangle(this.orderArea);
                break;
            case "Quadrado":
                this.formaInstanciada = new Square(this.orderArea);
                break;
            default:
                break;
        }

        initComponents();

        jLabel2.setText("Formato Selecionado: " + selectedFormat);
        jLabel4.setText("Cliente: " + selectedClient.getName());
        jLabel5.setText("Telefone: " + selectedClient.getTelephone());
        jLabel6.setText("Área cm²: " + String.format("%.2f", this.orderArea));

        this.tableModelSabores = (DefaultTableModel) jTable2.getModel();
        this.tableModelSaboresSelecionados = (DefaultTableModel) jTable3.getModel();
        tableModelSaboresSelecionados.setColumnIdentifiers(new String[]{"Sabor Escolhido", "Tipo"});
        tableModelSaboresSelecionados.setNumRows(0);

        popularTabelaSabores();
        atualizarPrecoTotalPedido();
    }

    private void popularTabelaSabores() {
        tableModelSabores.setNumRows(0);
        this.availableFlavours = this.flavourController.getAllFlavours();
        PriceTable tabelaPrecos = this.priceTableController.getPriceTable();

        for (Flavour sabor : availableFlavours) {
            Pizza pizzaPrevia = new Pizza();
            pizzaPrevia.setForma(this.formaInstanciada);
            pizzaPrevia.setFlavours(new Flavour[]{sabor, null});

            double precoFinalSabor = pizzaPrevia.getPrice(tabelaPrecos);

            tableModelSabores.addRow(new Object[]{
                sabor.getName(),
                sabor.getType().name(),
                String.format("R$ %.2f", precoFinalSabor)
            });
        }
    }

    private void atualizarPrecoTotalPedido() {
        if (saboresSelecionados.isEmpty()) {
            labelPrecoPedido.setText("Preço total do Pedido: R$ 0,00");
            return;
        }

        Pizza pizzaAtual = new Pizza();
        pizzaAtual.setForma(this.formaInstanciada);

        Flavour[] saboresArray = new Flavour[2];
        for (int i = 0; i < saboresSelecionados.size(); i++) {
            saboresArray[i] = saboresSelecionados.get(i);
        }
        pizzaAtual.setFlavours(saboresArray);

        PriceTable tabelaPrecos = this.priceTableController.getPriceTable();
        double total = pizzaAtual.getPrice(tabelaPrecos);

        labelPrecoPedido.setText(String.format("Preço total do Pedido: R$ %.2f", total));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jToolBar2 = new javax.swing.JToolBar();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        labelPrecoPedido = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnAdicionarSabor = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        btnRemoverSabor = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Sabor", "Tipo", "Preço Estimado Integral\""
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Fazer Pedido");

        jButton1.setBackground(new java.awt.Color(51, 255, 0));
        jButton1.setText("Concluir Pedido");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jButton2.setText("Voltar");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jToolBar1.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar1.setFloatable(true);
        jToolBar1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jToolBar1.setRollover(true);

        jLabel4.setText("Cliente:");
        jToolBar1.add(jLabel4);

        jLabel5.setText("Telefone:");
        jLabel5.setAutoscrolls(true);
        jToolBar1.add(jLabel5);

        jToolBar2.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar2.setFloatable(true);
        jToolBar2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jToolBar2.setRollover(true);

        jLabel6.setText("Área cm²:");
        jToolBar2.add(jLabel6);

        jLabel2.setText("Formato Selecionado:");
        jToolBar2.add(jLabel2);

        labelPrecoPedido.setText("Preço total do Pedido:");
        jToolBar2.add(labelPrecoPedido);

        jLabel7.setText("Info. Pizza:");

        jLabel8.setText("Info. Cliente:");

        btnAdicionarSabor.setText("Adicionar");
        btnAdicionarSabor.addActionListener(this::btnAdicionarSaborActionPerformed);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Sabor", "Tipo"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        jLabel9.setText("Sabores Selecionados");

        btnRemoverSabor.setBackground(new java.awt.Color(255, 51, 51));
        btnRemoverSabor.setForeground(new java.awt.Color(255, 255, 255));
        btnRemoverSabor.setText("Remover Sabor");
        btnRemoverSabor.setToolTipText("");
        btnRemoverSabor.addActionListener(this::btnRemoverSaborActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(252, 252, 252)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jLabel9)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAdicionarSabor)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(301, 301, 301))
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(btnRemoverSabor)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton2))
                .addGap(35, 35, 35)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addGap(55, 55, 55))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemoverSabor)
                        .addContainerGap(29, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAdicionarSabor)
                .addGap(69, 69, 69)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>                        

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        if (saboresSelecionados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Adicione pelo menos 1 sabor à pizza antes de concluir!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int resposta = JOptionPane.showConfirmDialog(this, "Deseja concluir este pedido?", "Confirmar Pedido", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (resposta == JOptionPane.YES_OPTION) {
            Pizza novaPizza = new Pizza();
            novaPizza.setForma(this.formaInstanciada);

            Flavour[] saboresArray = new Flavour[2];
            for (int i = 0; i < saboresSelecionados.size(); i++) {
                saboresArray[i] = saboresSelecionados.get(i);
            }
            novaPizza.setFlavours(saboresArray);

            ArrayList<Pizza> pizzaUnicaDoPedido = new ArrayList<>();
            pizzaUnicaDoPedido.add(novaPizza);

            boolean created = this.orderController.createOrder(this.selectedClient, pizzaUnicaDoPedido);

            if (created) {
                javax.swing.JFrame window = (javax.swing.JFrame) javax.swing.SwingUtilities.getWindowAncestor(this);
                Home homeScreen = new Home(clientController, flavourController, orderController, priceTableController);
                ListOrders listOrdersWindow = new ListOrders(homeScreen, clientController, flavourController, orderController, priceTableController);
                window.setContentPane(listOrdersWindow);
                window.revalidate();
                window.repaint();
            }
        }
    }                                        

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        javax.swing.JFrame lastWindow = (javax.swing.JFrame) javax.swing.SwingUtilities.getWindowAncestor(this);
        lastWindow.setContentPane(lastScreen);
        lastWindow.revalidate();
        lastWindow.repaint();
    }                                        

    private void btnAdicionarSaborActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        int[] linhasSelecionadas = jTable2.getSelectedRows();

        if (linhasSelecionadas.length == 0) {
            JOptionPane.showMessageDialog(this, "Selecione um sabor na tabela para adicionar!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (saboresSelecionados.size() + linhasSelecionadas.length > 2) {
            JOptionPane.showMessageDialog(this, "Você só pode adicionar no máximo 2 sabores nesta pizza!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        for (int i = 0; i < linhasSelecionadas.length; i++) {
            int indexReal = jTable2.convertRowIndexToModel(linhasSelecionadas[i]);
            Flavour saborEscolhido = availableFlavours.get(indexReal);

            saboresSelecionados.add(saborEscolhido);

            tableModelSaboresSelecionados.addRow(new Object[]{
                saborEscolhido.getName(),
                saborEscolhido.getType().name()
            });
        }

        atualizarPrecoTotalPedido();
        jTable2.clearSelection();
    }                                                 

    private void btnRemoverSaborActionPerformed(java.awt.event.ActionEvent evt) {                                                
        int linhaSelecionada = jTable3.getSelectedRow();

        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um sabor na tabela de cima para remover.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        tableModelSaboresSelecionados.removeRow(linhaSelecionada);
        saboresSelecionados.remove(linhaSelecionada);
        atualizarPrecoTotalPedido();
    }                                               

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnAdicionarSabor;
    private javax.swing.JButton btnRemoverSabor;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JLabel labelPrecoPedido;
    // End of variables declaration                   
}