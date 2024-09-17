package com.example.padraosingleton.dao;

import org.springframework.stereotype.Repository;
import com.example.padraosingleton.data.DBConnect;
import com.example.padraosingleton.model.Funcionario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FuncionarioDAO implements DAO<Funcionario> {

    private Connection connection;

    public FuncionarioDAO() {
        this.connection = DBConnect.getInstance().getConnection();
    }

    @Override
    public void save(Funcionario funcionario) {
        String query = "INSERT INTO funcionario (id, nome, endereco) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, funcionario.getId());
            pstmt.setString(2, funcionario.getNome());
            pstmt.setString(3, funcionario.getEndereco());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Funcionario funcionario, String[] params) {
        String query = "UPDATE funcionario SET nome=?, endereco=? WHERE id=?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, params[0]);
            pstmt.setString(2, params[1]);
            pstmt.setInt(3, funcionario.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Funcionario funcionario) {
        String query = "DELETE FROM funcionario WHERE id=?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, funcionario.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Funcionario> getAll() {
        List<Funcionario> funcionarios = new ArrayList<>();
        String query = "SELECT * FROM funcionario";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Funcionario funcionario = new Funcionario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("endereco")
                );
                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return funcionarios;
    }

    @Override
    public Funcionario get(int id) {
        Funcionario funcionario = null;
        String query = "SELECT * FROM funcionario WHERE id=?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    funcionario = new Funcionario(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("endereco")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return funcionario;
    }
}
