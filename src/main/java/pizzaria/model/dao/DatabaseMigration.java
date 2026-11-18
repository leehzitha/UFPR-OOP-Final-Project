/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pizzaria.model.dao;

import java.sql.Connection;
import java.sql.Statement;
import pizzaria.ConnectionFactory;

/**
 *
 * @author dias
 */
public class DatabaseMigration {

    public static void migrate() throws Exception {
        String[] queries = {
            """
            CREATE TABLE IF NOT EXISTS client (
                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                name VARCHAR(100) NOT NULL,
                lastname VARCHAR(100) NOT NULL,
                telephone VARCHAR(20) NOT NULL
            )
            """,
            """
            CREATE TABLE IF NOT EXISTS price_table (
                id BIGINT PRIMARY KEY DEFAULT 1,
                simple_price DOUBLE NOT NULL,
                special_price DOUBLE NOT NULL,
                premium_price DOUBLE NOT NULL
            )
            """,
            """
            CREATE TABLE IF NOT EXISTS flavour (
                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                name VARCHAR(100) NOT NULL,
                pizza_type VARCHAR(20) NOT NULL
            )
            """,
            """
            CREATE TABLE IF NOT EXISTS orders (
                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                client_id BIGINT NOT NULL,
                subtotal DOUBLE NOT NULL,
                order_status VARCHAR(30) NOT NULL,
                FOREIGN KEY (client_id) REFERENCES client(id) ON DELETE CASCADE
            )
            """,
            """
            CREATE TABLE IF NOT EXISTS pizza (
                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                order_id BIGINT NOT NULL,
                shape_type VARCHAR(30) NOT NULL,
                dimension DOUBLE NOT NULL,
                price DOUBLE NOT NULL,
                FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE
            )
            """,
            """
            CREATE TABLE IF NOT EXISTS pizza_flavour (
                pizza_id BIGINT NOT NULL,
                flavour_id BIGINT NOT NULL,
                PRIMARY KEY (pizza_id, flavour_id),
                FOREIGN KEY (pizza_id) REFERENCES pizza(id) ON DELETE CASCADE,
                FOREIGN KEY (flavour_id) REFERENCES flavour(id)
            )
            """
        };

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement()) {

            for (String sql : queries) {
                stmt.execute(sql);
            }
        }
    }
}