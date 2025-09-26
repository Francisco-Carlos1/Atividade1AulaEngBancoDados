
package SistemaVendas.view;

import SistemaVendas.beans.NotaSaida;
import SistemasVendas.dao.NotaSaidaDAO;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author clara
 */
public class TelaRelatorioVendas extends javax.swing.JFrame {

    /**
     * Creates new form TelaRelatorioVendas
     */
    public TelaRelatorioVendas() {
        initComponents();
        preencheTabela();
    }

    public void preencheTabela() {
    // 1. Conecta ao DAO para buscar as vendas
    NotaSaidaDAO notaDAO = new NotaSaidaDAO(); //
    List<NotaSaida> listaNotas = notaDAO.getNotasSaida(); //

    // 2. Pega o modelo da tabela que você nomeou
    DefaultTableModel tabelaVendas = (DefaultTableModel) tblVendas.getModel(); //
    
    // 3. Limpa a tabela para não duplicar dados
    tabelaVendas.setNumRows(0);

    // 4. Percorre a lista de notas e adiciona cada uma como uma nova linha na tabela
    for (NotaSaida nota : listaNotas) { //
        Object[] obj = new Object[]{
            // A ORDEM AQUI DEVE SER A MESMA DAS SUAS COLUNAS
            nota.getId(),
            nota.getIdCliente(),
            nota.getIdProduto(),
            nota.getQuantidadeVendida(),
            nota.getDataVenda()
        };
        tabelaVendas.addRow(obj); //
    }
}
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblVendas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblVendas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID Venda", "ID Cliente", "ID", "Produto", "Quantidade", "Data da Venda"
            }
        ));
        jScrollPane1.setViewportView(tblVendas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaRelatorioVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaRelatorioVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaRelatorioVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaRelatorioVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaRelatorioVendas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblVendas;
    // End of variables declaration//GEN-END:variables
}
