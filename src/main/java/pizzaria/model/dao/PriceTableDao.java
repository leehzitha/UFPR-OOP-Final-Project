/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pizzaria.model.dao;

import pizzaria.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import pizzaria.model.PriceTable;

/**
 *
 * @author dias
 */
public class PriceTableDao {

    public PriceTable get() throws Exception {
        String sql = "SELECT * FROM price_table WHERE id = 1";
        PriceTable pt = new PriceTable();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                pt.updatePrice(pizzaria.model.PizzaType.Simple, rs.getDouble("simple_price"));
                pt.updatePrice(pizzaria.model.PizzaType.Special, rs.getDouble("special_price"));
                pt.updatePrice(pizzaria.model.PizzaType.Premium, rs.getDouble("premium_price"));
            } else {
                saveOrUpdate(pt);
            }
        }
        return pt;
    }

    public void saveOrUpdate(PriceTable pt) throws Exception {
        String sql = """
            INSERT INTO price_table (id, simple_price, special_price, premium_price)
            VALUES (1, ?, ?, ?)
            ON DUPLICATE KEY UPDATE
                simple_price = VALUES(simple_price),
                special_price = VALUES(special_price),
                premium_price = VALUES(premium_price)
            """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, pt.getSimplePriceBase());
            stmt.setDouble(2, pt.getSpecialPriceBase());
            stmt.setDouble(3, pt.getPremiumPriceBase());
            stmt.executeUpdate();
        }
    }
}