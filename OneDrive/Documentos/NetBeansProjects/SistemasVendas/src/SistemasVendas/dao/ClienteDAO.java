
package SistemasVendas.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import SistemaVendas.beans.Cliente;
import SistemasVendas.conexao.Conexao;

public class ClienteDAO {
     private Conexao conexao; // [cite: 745]
    private Connection conn; // [cite: 746]

    // Construtor que inicia a conexão com o banco
    public ClienteDAO() {
        this.conexao = new Conexao(); // [cite: 749]
        this.conn = this.conexao.getConexao(); // [cite: 750]
    }

    // Método para inserir um novo cliente no banco
    public void inserir(Cliente cliente) { // [cite: 751]
        String sql = "INSERT INTO clientes(nome, endereco, email, telefone) VALUES (?, ?, ?, ?)"; // [cite: 752]
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql); // [cite: 754]
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEndereco());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getTelefone());
            stmt.execute(); // [cite: 757]
        } catch (SQLException e) {
            System.out.println("Erro ao inserir cliente: " + e.getMessage()); // [cite: 759]
        }
    }
    
    public List<Cliente> getClientes() {
    String sql = "SELECT * FROM clientes";
    try {
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery(); // [cite: 816]
        List<Cliente> listaClientes = new ArrayList<>(); // [cite: 817]

        while (rs.next()) {
            Cliente cliente = new Cliente();
            cliente.setId(rs.getInt("id"));
            cliente.setNome(rs.getString("nome"));
            cliente.setEndereco(rs.getString("endereco"));
            cliente.setEmail(rs.getString("email"));
            cliente.setTelefone(rs.getString("telefone"));
            listaClientes.add(cliente); // [cite: 824]
        }
        return listaClientes; // [cite: 825]
    } catch (SQLException ex) {
        System.out.println("Erro ao consultar clientes: " + ex.getMessage());
        return null;
    }
}
}
