// Autor: Emerson da Silva Oliveira
public class Pessoa {
    private static int kp = 0; // Contador estático de pessoas setadas
    private String nome;
    private char sexo;
    private int idade;

    // Construtor padrão
    public Pessoa() {
        this.nome = "";
        this.sexo = ' ';
        this.idade = 0;
    }

    // Construtor com parâmetros
    public Pessoa(String nome, char sexo, int idade) {
        this.nome = nome;
        this.sexo = sexo;
        this.idade = idade;
        kp++;
    }

    public void setKp() {
        kp++;
    }

    public static int getKp() {
        return kp;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public char getSexo() {
        return sexo;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getIdade() {
        return idade;
    }
}