package com.btpjavatest.socks5;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.sql.ResultSet;
import java.util.Properties;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@RestController
@RequestMapping(path = "")
public class Socks5Controller {
    @GetMapping(path = "")
    public ResponseEntity<String> getDroneMedications() throws IOException, ClassNotFoundException {
        try {
            Properties p = new Properties();
            p.setProperty("user", "postgres");
            p.setProperty("password", "admin");
            p.setProperty("socketFactory", DBSocketFactory.class.getName());
            String url = "jdbc:postgresql://postgres-db:2035/postgres";
            Connection conn = DriverManager.getConnection(url, p);
            String query = "select generate_series(1,5) as  x;";
            try (java.sql.Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    System.out.println(rs.getInt("x"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return new ResponseEntity<String>("Hello World!", HttpStatus.OK);

    }

}
