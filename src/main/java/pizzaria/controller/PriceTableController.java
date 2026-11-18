/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pizzaria.controller;

import javax.swing.JOptionPane;
import pizzaria.model.PizzaType;
import pizzaria.model.PriceTable;
import pizzaria.model.dao.PriceTableDao;

/**
 *
 * @author dias
 */
public class PriceTableController {

    private final PriceTableDao priceTableDao;

    public PriceTableController() {
        this.priceTableDao = new PriceTableDao();
    }

    public PriceTable getPriceTable() {
        try {
            return priceTableDao.get();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return new PriceTable();
        }
    }

    public boolean updatePrice(PizzaType type, double newPrice) {
        if (type == null || newPrice <= 0) {
            JOptionPane.showMessageDialog(null, "O preço deve ser superior a zero.", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        try {
            PriceTable priceTable = priceTableDao.get();
            priceTable.updatePrice(type, newPrice);
            priceTableDao.saveOrUpdate(priceTable);
            JOptionPane.showMessageDialog(null, "Preco atualizado com sucessos!");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}