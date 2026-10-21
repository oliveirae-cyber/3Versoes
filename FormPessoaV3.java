// Autor: Emerson da Silva Oliveira
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormPessoaV3 extends JFrame {
    private JTextField txtNumero, txtNome, txtIdade;
    private JRadioButton rbMasculino, rbFeminino;
    private ButtonGroup grupoSexo;
    private JButton btnOk, btnLimpar, btnMostrar, btnSair;
    private Pessoa umaPessoa;
    private static int contadorNumero = 1;

    public FormPessoaV3() {
        setTitle("Cadastro de Pessoa - Versão 3 (JRadioButton)");
        setSize(380, 260);
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
        JPanel painelSexo = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        rbMasculino = new JRadioButton("M");
        rbFeminino = new JRadioButton("F");
        grupoSexo = new ButtonGroup();
        grupoSexo.add(rbMasculino);
        grupoSexo.add(rbFeminino);
        painelSexo.add(rbMasculino);
        painelSexo.add(rbFeminino);
        add(painelSexo);

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
                    if (nome.isEmpty() || txtIdade.getText().trim().isEmpty() || (!rbMasculino.isSelected() && !rbFeminino.isSelected())) {
                        JOptionPane.showMessageDialog(null, "Preencha todos os campos e selecione o sexo!", "Aviso", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    char sexo = rbMasculino.isSelected() ? 'M' : 'F';
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
            grupoSexo.clearSelection();
            txtIdade.setText("");
            txtNome.requestFocus();
        });

        btnMostrar.addActionListener(e -> {
            String mensagem = "--- Dados da Pessoa (Versão 3) ---\n" +
                              "Nome: " + umaPessoa.getNome() + "\n" +
                              "Sexo: " + umaPessoa.getSexo() + "\n" +
                              "Idade: " + umaPessoa.getIdade() + "\n" +
                              "Contador kp (Total setado): " + Pessoa.getKp();
            JOptionPane.showMessageDialog(null, mensagem, "Mostrar Pessoa", JOptionPane.INFORMATION_MESSAGE);
        });

        btnSair.addActionListener(e -> System.exit(0));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormPessoaV3().setVisible(true));
    }
}