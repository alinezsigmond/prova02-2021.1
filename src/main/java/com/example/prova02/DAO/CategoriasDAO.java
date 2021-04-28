package com.example.prova02.DAO;

import com.example.prova02.factory.ConnectionFactory;
import com.example.prova02.model.Categorias;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriasDAO {
    private Connection connection;

    public CategoriasDAO() {
        this.connection = new ConnectionFactory().getConnection();
        this.criaTabelaCategorias();
    }

    public void criaTabelaCategorias() {
        String sql = "CREATE TABLE IF NOT EXISTS categorias (" +
                "id INT PRIMARY KEY AUTO_INCREMENT," +
                "nome VARCHAR(50) NOT NULL" +
                ");";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cadastraCategoria(Categorias categoria) {
        String sql = "INSERT INTO categorias VALUES (? , ?);";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,categoria.getId());
            stmt.setString(2,categoria.getNome());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Categorias> listaCategorias() {
        String sql = "SELECT * FROM categorias";

        List<Categorias> categoriasList = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Categorias categoria = new Categorias();
                categoria.setId(rs.getInt("id"));
                categoria.setNome(rs.getString("nome"));

                categoriasList.add(categoria);
            }
            return categoriasList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Categorias buscaPorId(int id) {
        String sql = "SELECT * FROM categorias WHERE id = ?;";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                Categorias categoria = new Categorias();
                categoria.setId(rs.getInt("id"));
                categoria.setNome(rs.getString("nome"));
                return categoria;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
