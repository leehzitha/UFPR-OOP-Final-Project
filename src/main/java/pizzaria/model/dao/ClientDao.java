/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pizzaria.model.dao;

import pizzaria.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pizzaria.model.Client;

/**
 *
 * @author dias
 */
public class ClientDao implements Dao<Client> {

    @Override
    public void add(Client client) throws Exception {
        String sql = "INSERT INTO client (name, lastname, telephone) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getLastname());
            stmt.setString(3, client.getTelephone());
            stmt.execute();
        }
    }

    @Override
    public List<Client> getAll() throws Exception {
        String sql = "SELECT * FROM client";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery();   
            ){
            List<Client> clients = new ArrayList();
            while (rs.next()) {
                long id = rs.getLong("id");
                String name= rs.getString("name");
                String lastname = rs.getString("lastname");
                String telephone = rs.getString("telephone");                

                clients.add(new Client(id,name,lastname,telephone));
            }
            
            return clients;
        }
    }

    @Override
    public Client getById(long id) throws Exception {
        String sql = "SELECT * FROM client WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    long clientId = rs.getLong("id");
                    String name = rs.getString("name");
                    String lastname = rs.getString("lastname");
                    String telephone = rs.getString("telephone");

                    return new Client(clientId, name, lastname, telephone);
                }
            }
        }
        return null;
    }

    @Override
    public void update(Client client) throws Exception {
        if (this.getById(client.getId()) == null) {
            return;
        }
        
        String sql = "UPDATE client SET name = ?, lastname = ?, telephone = ? WHERE id = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getLastname());
            stmt.setString(3, client.getTelephone());
            stmt.setLong(4, client.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(Client client) throws Exception {
        String sql = "DELETE FROM client WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setLong(1, client.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteAll() throws Exception {
        String sql = "DELETE FROM client";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.executeUpdate();
        }
    }
}
