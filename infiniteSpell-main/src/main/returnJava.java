package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class returnJava {



    
     private static final String URL = "jdbc:sqlite:infinite_spell.db";  
public String getCards() {
   
    String sql = "SELECT nome, dano, tempo_conjuracao, url, raridade FROM cards ";

    List<Map<String, Object>> listcards = new ArrayList<>();

    try (Connection conn = DriverManager.getConnection(URL);
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {

        while (rs.next()) {
            Map<String, Object> card = new HashMap<>();
            card.put("nome", rs.getString("nome"));
            card.put("dano", rs.getInt("dano"));
            card.put("tempo_conjuracao", rs.getInt("tempo_conjuracao")); 
            card.put("url", rs.getString("url"));
            card.put("raridade",rs.getInt("raridade"));
            listcards.add(card);
        }

    } catch (SQLException e) {
        System.err.println("Erro ao buscar monjaro !");
        e.printStackTrace();
        return "[]"; 
    }

    com.google.gson.Gson gson = new com.google.gson.Gson();
    return gson.toJson(listcards);
}
}
