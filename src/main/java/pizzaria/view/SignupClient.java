package pizzaria.view;
/**
 *
 * @author Vinicius Dias
 */

import javax.swing.JOptionPane;
import pizzaria.controller.ClientController;
import pizzaria.controller.FlavourController;
import pizzaria.controller.OrderController;
import pizzaria.controller.PriceTableController;
import pizzaria.model.Client;

public class SignupClient extends javax.swing.JPanel {

    private final ClientController clientController;
    private final FlavourController flavourController;
    private final OrderController orderController;
    private final PriceTableController priceTableController;

    private Client clientToEdit;
    private javax.swing.JPanel lastScreen;

    /**
     * Construtor para Cadastro (clientToEdit == null) ou Edição (clientToEdit != null)
     */
    public SignupClient(ClientController clientController,
                        FlavourController flavourController,
                        OrderController orderController,
                        PriceTableController priceTableController,
                        Client clientToEdit,
                        javax.swing.JPanel lastScreen) {
        initComponents();
        this.clientController = clientController;
        this.flavourController = flavourController;
        this.orderController = orderController;
        this.priceTableController = priceTableController;
        this.clientToEdit = clientToEdit;
        this.lastScreen = lastScreen;

        if (this.clientToEdit != null) {
            jTextField1.setText(clientToEdit.getName());
            jTextField2.setText(clientToEdit.getLastname());
            jTextField3.setText(clientToEdit.getTelephone());

            jLabel4.setText("Edição de Cliente");
            jButton3.setText("Atualizar");
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(715, 380));

        jTextField1.setToolTipText("Nome do cliente");
        jTextField1.addActionListener(this::jTextField1ActionPerformed);

        jLabel1.setText("Nome");

        jLabel2.setText("Sobrenome");

        jTextField2.setToolTipText("Sobrenome do cliente");

        jLabel3.setText("Telefone");

        jTextField3.setToolTipText("Telefone do cliente");
        jTextField3.addActionListener(this::jTextField3ActionPerformed);

        jButton1.setBackground(new java.awt.Color(204, 204, 204));
        jButton1.setText("Cancelar");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jButton3.setBackground(new java.awt.Color(51, 255, 51));
        jButton3.setText("Salvar");
        jButton3.addActionListener(this::jButton3ActionPerformed);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Cadastro de Cliente");

        jButton2.setText("Ver Clientes");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        btnHome.setText("Início");
        btnHome.setToolTipText("Voltar para o menu principal");
        btnHome.addActionListener(this::btnHomeActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(276, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1)
                        .addComponent(jTextField2)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)
                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                        .addComponent(jTextField3))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)))
                .addGap(273, 273, 273))
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(btnHome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHome)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton1))
                .addContainerGap(89, Short.MAX_VALUE))
        );
    }

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        if (this.lastScreen != null) {
            javax.swing.JFrame mainScreen = (javax.swing.JFrame) javax.swing.SwingUtilities.getWindowAncestor(this);
            mainScreen.setContentPane(this.lastScreen);
            mainScreen.revalidate();
            mainScreen.repaint();
        } else {
            jTextField1.setText("");
            jTextField2.setText("");
            jTextField3.setText("");
        }
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        String name = jTextField1.getText().trim();
        String lastname = jTextField2.getText().trim();
        String telephone = jTextField3.getText().trim();

        if (name.isEmpty() || lastname.isEmpty() || telephone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, garanta que todos os campos estejam preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!telephone.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "O telefone deve conter apenas números! Não use letras, espaços ou traços.", "Erro de Validação", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (this.clientToEdit == null) {
            boolean success = clientController.createClient(name, lastname, telephone);
            if (success) {
                jTextField1.setText("");
                jTextField2.setText("");
                jTextField3.setText("");
            }
        } else {
            boolean success = clientController.updateClient(this.clientToEdit.getId(), name, lastname, telephone);
            if (success) {
                clientListScreen();
            }
        }
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        clientListScreen();
    }

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {
        Home homeScreen = new Home(clientController, flavourController, orderController, priceTableController);
        javax.swing.JFrame mainScreen = (javax.swing.JFrame) javax.swing.SwingUtilities.getWindowAncestor(this);
        if (mainScreen != null) {
            mainScreen.setContentPane(homeScreen);
            mainScreen.revalidate();
            mainScreen.repaint();
        }
    }

    private void clientListScreen() {
        ListClient listScreen = new ListClient(clientController, flavourController, orderController, priceTableController);
        javax.swing.JFrame mainScreen = (javax.swing.JFrame) javax.swing.SwingUtilities.getWindowAncestor(this);
        mainScreen.setContentPane(listScreen);
        mainScreen.revalidate();
        mainScreen.repaint();
    }

    // Variables declaration - do not modify
    private javax.swing.JButton btnHome;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration
}