/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pizzaria.controller;

import java.util.List;
import javax.swing.JOptionPane;
import pizzaria.model.Flavour;
import pizzaria.model.PizzaType;
import pizzaria.model.dao.FlavourDao;

/**
 *
 * @author dias
 */
public class FlavourController {

    private final FlavourDao flavourDao;

    public FlavourController() {
        this.flavourDao = new FlavourDao();
    }

    public boolean createFlavour(PizzaType pizzaType, String name) {
        if (pizzaType == null || name == null || name.isBlank()) {
            JOptionPane.showMessageDialog(null, "Nome e tipo são obrigatórios.", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        try {
            Flavour flavour = new Flavour(pizzaType, name.trim());
            flavourDao.add(flavour);
            JOptionPane.showMessageDialog(null, "Sabor cadastrado com sucesso!");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar sabor: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean updateFlavour(Long id, PizzaType pizzaType, String name) {
        if (id == null || pizzaType == null || name == null || name.isBlank()) {
            JOptionPane.showMessageDialog(null, "Sabor inválido.", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        try {
            Flavour flavour = new Flavour(pizzaType, name.trim());
            flavour.setId(id);
            flavourDao.update(flavour);
            JOptionPane.showMessageDialog(null, "Sabor atualizado com Sucesso!");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar sabor: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean deleteFlavour(Flavour flavour) {
        if (flavour == null || flavour.getId() == null) {
            return false;
        }

        try {
            flavourDao.delete(flavour);
            JOptionPane.showMessageDialog(null, "Sabor deletado com sucesso!");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao apagar sabores: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public List<Flavour> getAllFlavours() {
        try {
            return flavourDao.getAll();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar sabores: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return List.of();
        }
    }
}