package pizzaria.view;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author dias
 */

import javax.swing.JOptionPane;
import pizzaria.controller.PriceTableController;
import pizzaria.model.PizzaType;
import static pizzaria.model.PizzaType.Premium;
import static pizzaria.model.PizzaType.Simple;
import static pizzaria.model.PizzaType.Special;
import pizzaria.model.PriceTable;

public class ConfigPrice extends javax.swing.JPanel {

    private final PriceTableController priceTableController;
    private javax.swing.JPanel lastScreen;

    public ConfigPrice(PriceTableController priceTableController, javax.swing.JPanel lastScreen) {
        this.priceTableController = priceTableController;
        this.lastScreen = lastScreen;
        initComponents();
        carregarPrecoAtual();
    }

    private void initComponents() {
        jLabelTitle = new javax.swing.JLabel("Gerenciar Preços por cm²");
        jLabelType = new javax.swing.JLabel("Tipo de Pizza:");
        jComboBoxType = new javax.swing.JComboBox<>(PizzaType.values());
        jLabelPrice = new javax.swing.JLabel("Preço por cm²:");
        jTextFieldPrice = new javax.swing.JTextField(10);
        jButtonSalvar = new javax.swing.JButton("Salvar Alteração");
        jButtonVoltar = new javax.swing.JButton("Voltar");

        jComboBoxType.addActionListener(evt -> carregarPrecoAtual());

        jButtonSalvar.addActionListener(evt -> {
            try {
                PizzaType tipoSelecionado = (PizzaType) jComboBoxType.getSelectedItem();
                double novoPreco = Double.parseDouble(jTextFieldPrice.getText().replace(",", "."));

                if (novoPreco <= 0) {
                    JOptionPane.showMessageDialog(this, "O preço deve ser maior que zero.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                priceTableController.updatePrice(tipoSelecionado, novoPreco);

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Insira um valor numérico válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        jButtonVoltar.addActionListener(evt -> {
            if (lastScreen != null) {
                javax.swing.JFrame mainScreen = (javax.swing.JFrame) javax.swing.SwingUtilities.getWindowAncestor(this);
                mainScreen.setContentPane(lastScreen);
                mainScreen.revalidate();
                mainScreen.repaint();
            }
        });

        // Layout
        add(jLabelTitle);
        add(jLabelType);
        add(jComboBoxType);
        add(jLabelPrice);
        add(jTextFieldPrice);
        add(jButtonSalvar);
        add(jButtonVoltar);
    }

    private void carregarPrecoAtual() {
        PizzaType tipoSelecionado = (PizzaType) jComboBoxType.getSelectedItem();
        if (tipoSelecionado != null) {
            PriceTable priceTable = priceTableController.getPriceTable();
            double precoAtual = 0;

            switch (tipoSelecionado) {
                case Simple -> precoAtual = priceTable.getSimplePriceBase();
                case Special -> precoAtual = priceTable.getSpecialPriceBase();
                case Premium -> precoAtual = priceTable.getPremiumPriceBase();
            }
            jTextFieldPrice.setText(String.format("%.2f", precoAtual));
        }
    }

    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JButton jButtonVoltar;
    private javax.swing.JComboBox<PizzaType> jComboBoxType;
    private javax.swing.JLabel jLabelPrice;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JLabel jLabelType;
    private javax.swing.JTextField jTextFieldPrice;
}