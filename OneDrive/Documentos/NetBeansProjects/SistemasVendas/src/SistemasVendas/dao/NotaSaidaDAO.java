package SistemasVendas.dao;

import SistemaVendas.beans.NotaSaida;
import SistemasVendas.conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date; // Import para conversão de data

public class NotaSaidaDAO {

    private final Conexao conexao;
    private final Connection conn;

    public NotaSaidaDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    /**
     * Insere uma nova nota de saída e atualiza o estoque do produto.
     * @param nota O objeto NotaSaida a ser inserido.
     */
    public void inserir(NotaSaida nota) {
        try {
            // 1. Inserir o registro na tabela de notas_saida
            String sqlNota = "INSERT INTO notas_saida(id_cliente, id_produto, quantidade_vendida, data_venda) VALUES (?, ?, ?, ?)";
            PreparedStatement stmtNota = this.conn.prepareStatement(sqlNota);
            stmtNota.setInt(1, nota.getIdCliente());
            stmtNota.setInt(2, nota.getIdProduto());
            stmtNota.setInt(3, nota.getQuantidadeVendida());
            // Converte de java.time.LocalDate para java.sql.Date
            stmtNota.setDate(4,nota.getDataVenda());
            stmtNota.execute();
            
            // 2. Atualizar o estoque na tabela de produtos
            String sqlProduto = "UPDATE produtos SET quantidade_estoque = quantidade_estoque - ? WHERE id = ?";
            PreparedStatement stmtProduto = this.conn.prepareStatement(sqlProduto);
            stmtProduto.setInt(1, nota.getQuantidadeVendida());
            stmtProduto.setInt(2, nota.getIdProduto());
            stmtProduto.execute();

        } catch (SQLException e) {
            System.out.println("Erro ao lançar nota de saída: " + e.getMessage());
        }
    }

    /**
     * Retorna uma lista com todas as notas de saída (vendas) registradas.
     * @return Uma lista de objetos NotaSaida.
     */
    public List<NotaSaida> getNotasSaida() {
        String sql = "SELECT * FROM notas_saida";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<NotaSaida> listaNotas = new ArrayList<>();

            while (rs.next()) {
                NotaSaida nota = new NotaSaida();
                nota.setId(rs.getInt("id"));
                nota.setIdCliente(rs.getInt("id_cliente"));
                nota.setIdProduto(rs.getInt("id_produto"));
                nota.setQuantidadeVendida(rs.getInt("quantidade_vendida"));
                // Converte de java.sql.Date para java.time.LocalDate
                nota.setDataVenda(rs.getDate("data_venda"));
                listaNotas.add(nota);
            }
            return listaNotas;
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar notas de saída: " + ex.getMessage());
            return null;
        }
    }
}