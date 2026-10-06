
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public boolean cadastrarProduto(ProdutosDTO produto) {
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
        try {
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            prep.executeUpdate();
            return true;
        } catch (Exception erro) {
            return false;
        } finally {
            fecharConexao();
        }
    }

        public ArrayList<ProdutosDTO> listarProdutos() {
        String sql = "SELECT id, nome, valor, status FROM produtos";
        ArrayList<ProdutosDTO> lista = new ArrayList<>();
        try {
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();
            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                lista.add(produto);
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + erro.getMessage());
        } finally {
            fecharConexao();
        }
        return lista;
    }
    public boolean venderProduto(int id) {
        String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ? AND (status IS NULL OR status <> 'Vendido')";
        try {
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement(sql);
            prep.setInt(1, id);
            return prep.executeUpdate() > 0;
        } catch (Exception erro) {
            return false;
        } finally {
            fecharConexao();
        }
    }
    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
        String sql = "SELECT id, nome, valor, status FROM produtos WHERE status = 'Vendido'";
        ArrayList<ProdutosDTO> lista = new ArrayList<>();
        try {
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();
            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                lista.add(produto);
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos vendidos: " + erro.getMessage());
        } finally {
            fecharConexao();
        }
        return lista;
    }
    private void fecharConexao() {
        try {
            if (resultset != null) resultset.close();
            if (prep != null) prep.close();
            if (conn != null) conn.close();
        } catch (SQLException erro) {
            // nada a fazer
        }
    }
}