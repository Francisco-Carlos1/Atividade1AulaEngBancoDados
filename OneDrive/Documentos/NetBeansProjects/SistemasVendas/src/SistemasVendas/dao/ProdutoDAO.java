package SistemasVendas.dao;

import SistemaVendas.beans.Produto;
import SistemasVendas.conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por persistir os dados (inserção, edição, exclusão e
 * [cite_start]pesquisa) da aplicação na tabela de produtos. [cite: 459]
 */
public class ProdutoDAO {

    private final Conexao conexao;
    private final Connection conn;

    /**
     * [cite_start]Construtor que inicia a conexão com o banco de dados. [cite: 467]
     */
    public ProdutoDAO() {
       this.conexao = new Conexao(); 
       this.conn = this.conexao.getConexao(); 
    }

    /**
     * [cite_start]Insere um novo produto no banco de dados. [cite: 471]
     * @param produto O objeto Produto a ser inserido.
     */
    public void inserir(Produto produto) {
        String sql = "INSERT INTO produtos(nome, descricao, preco_venda, quantidade_estoque) VALUES (?, ?, ?, ?)"; 
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setDouble(3, produto.getPrecoVenda());
            stmt.setInt(4, produto.getQuantidadeEstoque());
          stmt.execute(); 
        } catch (SQLException e) {
           System.out.println("Erro ao inserir produto: " + e.getMessage()); 
        }
    }

    /**
     * [cite_start]Retorna uma lista com todos os produtos cadastrados no banco. [cite: 533]
     * @return Uma lista de objetos Produto.
     */
    public List<Produto> getProdutos() {
            String sql = "SELECT * FROM produtos"; 
        try {
        PreparedStatement stmt = this.conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery();
            List<Produto> listaProdutos = new ArrayList<>(); 

            while (rs.next()) {
               Produto produto = new Produto(); 
                produto.setId(rs.getInt("id")); 
                produto.setNome(rs.getString("nome")); 
                produto.setDescricao(rs.getString("descricao"));
                produto.setPrecoVenda(rs.getDouble("preco_venda"));
                produto.setQuantidadeEstoque(rs.getInt("quantidade_estoque"));
                listaProdutos.add(produto); 
            }
            return listaProdutos; 
        } catch (SQLException ex) {
           System.out.println("Erro ao consultar produtos: " + ex.getMessage()); 
            return null;
        }
    }

    /**
     * [cite_start]Atualiza os dados de um produto no banco de dados. [cite: 1271]
     * @param produto O objeto Produto com os dados atualizados.
     */
    public void editar(Produto produto) {
        String sql = "UPDATE produtos SET nome = ?, descricao = ?, preco_venda = ?, quantidade_estoque = ? WHERE id = ?"; 
        try {
            PreparedStatement stmt = conn.prepareStatement(sql); 
            stmt.setString(1, produto.getNome()); 
            stmt.setString(2, produto.getDescricao());
            stmt.setDouble(3, produto.getPrecoVenda());
            stmt.setInt(4, produto.getQuantidadeEstoque());
           stmt.setInt(5, produto.getId()); 
           stmt.execute(); 
        } catch (SQLException ex) {
           System.out.println("Erro ao atualizar produto: " + ex.getMessage()); 
        }
    }

    /**
     * [cite_start]Exclui um produto do banco de dados pelo seu ID. [cite: 1309]
     * @param id O ID do produto a ser excluído.
     */
    public void excluir(int id) {
        String sql = "DELETE FROM produtos WHERE id = ?"; 
        try {
           PreparedStatement stmt = conn.prepareStatement(sql); 
            stmt.setInt(1, id); 
            stmt.execute(); 
        } catch (SQLException ex) {
           System.out.println("Erro ao excluir produto: " + ex.getMessage()); 
        }
    }
}