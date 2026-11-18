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
import pizzaria.model.Flavour;
import pizzaria.model.PizzaType;

/**
 *
 * @author dias
 */
public class FlavourDao implements Dao<Flavour> {

    @Override
    public void add(Flavour flavour) throws Exception {
        String sql = "INSERT INTO flavour (name, pizza_type) VALUES (?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, flavour.getName());
            stmt.setString(2, flavour.getType().name());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    flavour.setId(rs.getLong(1));
                }
            }
        }
    }

    @Override
    public List<Flavour> getAll() throws Exception {
        String sql = "SELECT * FROM flavour";
        List<Flavour> flavours = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Flavour f = new Flavour(
                    PizzaType.valueOf(rs.getString("pizza_type")),
                    rs.getString("name")
                );
                f.setId(rs.getLong("id"));
                flavours.add(f);
            }
        }
        return flavours;
    }

    @Override
    public Flavour getById(long id) throws Exception {
        String sql = "SELECT * FROM flavour WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Flavour f = new Flavour(
                        PizzaType.valueOf(rs.getString("pizza_type")),
                        rs.getString("name")
                    );
                    f.setId(rs.getLong("id"));
                    return f;
                }
            }
        }
        return null;
    }

    @Override
    public void update(Flavour flavour) throws Exception {
        String sql = "UPDATE flavour SET name = ?, pizza_type = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, flavour.getName());
            stmt.setString(2, flavour.getType().name());
            stmt.setLong(3, flavour.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(Flavour flavour) throws Exception {
        String sql = "DELETE FROM flavour WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, flavour.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteAll() throws Exception {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM flavour")) {
            stmt.executeUpdate();
        }
    }
}