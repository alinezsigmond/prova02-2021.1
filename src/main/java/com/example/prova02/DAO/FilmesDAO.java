package com.example.prova02.DAO;

import com.example.prova02.factory.ConnectionFactory;
import com.example.prova02.model.Categorias;
import com.example.prova02.model.Filmes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmesDAO {
    private Connection connection;

    public FilmesDAO() {
        this.connection = new ConnectionFactory().getConnection();
        this.criaTabelaFilmes();
    }

    public void criaTabelaFilmes() {
        String sql = "CREATE TABLE IF NOT EXISTS filmes (" +
                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                "titulo VARCHAR(50) NOT NULL, " +
                "duracao INT NOT NULL, " +
                "categoria INT NOT NULL, " +
                "classificacao INT NOT NULL, " +
                "FOREIGN KEY (categoria) REFERENCES categorias(id)" +
                ");";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Filmes> listaFilmes() {
        String sql = "SELECT * FROM filmes";

        List<Filmes> filmesList = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Filmes filme = new Filmes();
                filme.setId(rs.getInt("id"));
                filme.setTitulo(rs.getString("titulo"));
                filme.setDuracao(rs.getInt("duracao"));
                CategoriasDAO cDAO = new CategoriasDAO();
                Categorias categoria = cDAO.buscaPorId(rs.getInt("id"));
                filme.setCategoria(categoria);
                filme.setClassificacao(rs.getInt("classificacao"));
                filmesList.add(filme);
            }
            return filmesList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void criaFilme(Filmes filme) {
        String sql = "INSERT INTO filmes VALUES (?, ?, ?, ?, ?);";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,filme.getId());
            stmt.setString(2, filme.getTitulo());
            stmt.setInt(3,filme.getDuracao());
            stmt.setInt(4,filme.getCategoria().getId());
            stmt.setInt(5,filme.getClassificacao());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
