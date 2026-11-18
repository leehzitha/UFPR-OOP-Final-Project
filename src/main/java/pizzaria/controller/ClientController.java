/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pizzaria.controller;

import java.util.List;
import javax.swing.JOptionPane;
import pizzaria.model.Client;
import pizzaria.model.dao.ClientDao;

/**
 *
 * @author dias
 */
public class ClientController {

    private final ClientDao clientDao;

    public ClientController() {
        this.clientDao = new ClientDao();
    }

    public boolean createClient(String name, String lastname, String telephone) {
        if (name == null || name.isBlank() || telephone == null || telephone.isBlank()) {
            JOptionPane.showMessageDialog(null, "Name and telephone are required.", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        try {
            Client client = new Client(name.trim(), lastname != null ? lastname.trim() : "", telephone.trim());
            clientDao.add(client);
            JOptionPane.showMessageDialog(null, "Cliente registrado com sucesso!");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean updateClient(Long id, String name, String lastname, String telephone) {
        if (id == null) {
            JOptionPane.showMessageDialog(null, "Selecione um cliente para atualizaçao.", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        try {
            Client client = new Client(id, name.trim(), lastname != null ? lastname.trim() : "", telephone.trim());
            clientDao.update(client);
            JOptionPane.showMessageDialog(null, "Client Atualizado com sucesso!");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar cliente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean deleteClient(Client client) {
        if (client == null || client.getId() == null) {
            return false;
        }

        try {
            clientDao.delete(client);
            JOptionPane.showMessageDialog(null, "Cliente removido com sucesso!");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao apagar cliente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public List<Client> getAllClients() {
        try {
            return clientDao.getAll();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error ao carregar os clientes: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return List.of();
        }
    }

    public Client findClientByTelephone(String telephone) {
        if (telephone == null || telephone.isBlank()) {
            return null;
        }
        try {
            return clientDao.getAll().stream()
                    .filter(c -> c.getTelephone().equals(telephone.trim()))
                    .findFirst()
                    .orElse(null);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar cliente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}