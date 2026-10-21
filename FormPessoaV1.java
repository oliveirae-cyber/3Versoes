// Autor: Emerson da Silva Oliveira
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormPessoaV1 extends JFrame {
    private JTextField txtNumero, txtNome, txtSexo, txtIdade;
    private JButton btnOk, btnLimpar, btnMostrar, btnSair;
    private Pessoa umaPessoa;
    private static int contadorNumero = 1;

    public FormPessoaV1() {
        setTitle("Cadastro de Pessoa - Versão 1");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2, 5, 5));

        umaPessoa = new Pessoa();

        add(new JLabel(" Número:"));
        txtNumero = new JTextField(String.valueOf(contadorNumero));
        txtNumero.setEditable(false); // Não acessível à digitação
        add(txtNumero);

        add(new JLabel(" Nome:"));
        txtNome = new JTextField();
        add(txtNome);

        add(new JLabel(" Sexo (M/F):"));
        txtSexo = new JTextField();
        add(txtSexo);

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
                    String sexoStr = txtSexo.getText().trim().toUpperCase();
                    
                    if (nome.isEmpty() || sexoStr.isEmpty() || txtIdade.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!", "Aviso", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    if (sexoStr.length() != 1 || (sexoStr.charAt(0) != 'M' && sexoStr.charAt(0) != 'F')) {
                        JOptionPane.showMessageDialog(null, "O campo Sexo deve ser apenas 'M' ou 'F'!", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    char sexo = sexoStr.charAt(0);
                    int idade = Integer.parseInt(txtIdade.getText().trim());

                    umaPessoa.setNome(nome);
                    umaPessoa.setSexo(sexo);
                    umaPessoa.setIdade(idade);
                    umaPessoa.setKp();

                    JOptionPane.showMessageDialog(null, "Dados transferidos para o objeto com sucesso!");
                    txtNumero.setText(String.valueOf(++contadorNumero));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "A idade deve ser um número inteiro válido!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnLimpar.addActionListener(e -> {
            txtNome.setText("");
            txtSexo.setText("");
            txtIdade.setText("");
            txtNome.requestFocus();
        });

        btnMostrar.addActionListener(e -> {
            String mensagem = "--- Dados da Pessoa (Objeto UmaPessoa) ---\n" +
                              "Nome: " + umaPessoa.getNome() + "\n" +
                              "Sexo: " + umaPessoa.getSexo() + "\n" +
                              "Idade: " + umaPessoa.getIdade() + "\n" +
                              "Contador kp (Total setado): " + Pessoa.getKp();
            JOptionPane.showMessageDialog(null, mensagem, "Mostrar Pessoa", JOptionPane.INFORMATION_MESSAGE);
        });

        btnSair.addActionListener(e -> System.exit(0));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormPessoaV1().setVisible(true));
    }
}