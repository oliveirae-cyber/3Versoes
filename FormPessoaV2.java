// Autor: Emerson da Silva Oliveira
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormPessoaV2 extends JFrame {
    private JTextField txtNumero, txtNome, txtIdade;
    private JComboBox<String> cmbSexo;
    private JButton btnOk, btnLimpar, btnMostrar, btnSair;
    private Pessoa umaPessoa;
    private static int contadorNumero = 1;

    public FormPessoaV2() {
        setTitle("Cadastro de Pessoa - Versão 2 (JComboBox)");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2, 5, 5));

        umaPessoa = new Pessoa();

        add(new JLabel(" Número:"));
        txtNumero = new JTextField(String.valueOf(contadorNumero));
        txtNumero.setEditable(false);
        add(txtNumero);

        add(new JLabel(" Nome:"));
        txtNome = new JTextField();
        add(txtNome);

        add(new JLabel(" Sexo:"));
        cmbSexo = new JComboBox<>(new String[]{"Selecione...", "M", "F"});
        add(cmbSexo);

        add(new JLabel(" Idade:"));
        txtIdade = new JTextField();
        add(txtIdade);

        btnOk = new JButton("OK");
        btnLimpar = new JButton("Limpar");
        btnMostrar = new JButton("Mostrar");
        btnSair = new JButton("Sair");

        add(btnOk);
        add(btnLimpar);
        add(btnMostrar);
        add(btnSair);

        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nome = txtNome.getText().trim();
                    int selectedIndex = cmbSexo.getSelectedIndex();
                    
                    if (nome.isEmpty() || selectedIndex == 0 || txtIdade.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Preencha todos os campos e selecione o sexo!", "Aviso", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    char sexo = cmbSexo.getSelectedItem().toString().charAt(0);
                    int idade = Integer.parseInt(txtIdade.getText().trim());

                    umaPessoa.setNome(nome);
                    umaPessoa.setSexo(sexo);
                    umaPessoa.setIdade(idade);
                    umaPessoa.setKp();

                    JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!");
                    txtNumero.setText(String.valueOf(++contadorNumero));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Idade inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnLimpar.addActionListener(e -> {
            txtNome.setText("");
            cmbSexo.setSelectedIndex(0);
            txtIdade.setText("");
            txtNome.requestFocus();
        });

        btnMostrar.addActionListener(e -> {
            String mensagem = "--- Dados da Pessoa (Versão 2) ---\n" +
                              "Nome: " + umaPessoa.getNome() + "\n" +
                              "Sexo: " + umaPessoa.getSexo() + "\n" +
                              "Idade: " + umaPessoa.getIdade() + "\n" +
                              "Contador kp (Total setado): " + Pessoa.getKp();
            JOptionPane.showMessageDialog(null, mensagem, "Mostrar Pessoa", JOptionPane.INFORMATION_MESSAGE);
        });

        btnSair.addActionListener(e -> System.exit(0));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormPessoaV2().setVisible(true));
    }
}