
package SistemasVendas.conexao;

    
    import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    public Connection getConexao() { // [cite: 689]
        try {
            // A senha para o XAMPP por padrão é vazia ("")
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sistema_vendas?useTimezone=true&serverTimezone=UTC", // URL do banco
                "root", // usuário padrão do XAMPP
                ""      // senha padrão do XAMPP
            );
            System.out.println("Conexão realizada com sucesso!"); // [cite: 689]
            return conn; // [cite: 689]
        } catch (Exception e) {
            System.out.println("Erro ao conectar no BD: " + e.getMessage()); // [cite: 689]
            return null; // [cite: 689]
        }
    }
}

