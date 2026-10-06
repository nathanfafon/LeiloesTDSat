
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class vendasVIEW extends JFrame {

    private JTable listaVendas;

    public vendasVIEW() {
        montarTela();
        listarProdutosVendidos();
    }

    private void montarTela() {
        setTitle("Produtos Vendidos");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JLabel titulo = new JLabel("Produtos Vendidos", SwingConstants.CENTER);
        titulo.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
        titulo.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));

        DefaultTableModel model = new DefaultTableModel(new Object[][]{}, new String[]{"ID", "Nome", "Valor", "Status"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        listaVendas = new JTable(model);
        JScrollPane rolagem = new JScrollPane(listaVendas);
        rolagem.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(evt -> dispose());
        JPanel rodape = new JPanel();
        rodape.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        rodape.add(btnVoltar);

        setLayout(new BorderLayout());
        add(titulo, BorderLayout.NORTH);
        add(rolagem, BorderLayout.CENTER);
        add(rodape, BorderLayout.SOUTH);

        setPreferredSize(new Dimension(600, 400));
        pack();
        setLocationRelativeTo(null);
    }

    private void listarProdutosVendidos() {
        try {
            ProdutosDAO produtosdao = new ProdutosDAO();

            DefaultTableModel model = (DefaultTableModel) listaVendas.getModel();
            model.setNumRows(0);

            ArrayList<ProdutosDTO> vendidos = produtosdao.listarProdutosVendidos();

            for (int i = 0; i < vendidos.size(); i++) {
                model.addRow(new Object[]{
                    vendidos.get(i).getId(),
                    vendidos.get(i).getNome(),
                    vendidos.get(i).getValor(),
                    vendidos.get(i).getStatus()
                });
            }
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new vendasVIEW().setVisible(true));
    }
}