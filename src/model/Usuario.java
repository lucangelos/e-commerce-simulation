package model;

public class Usuario {
    private String nome;
    private String email;
    private String senha;
    private Endereco endereco;
    private final int id;
    private static int proximoId = 1;

    public Usuario(String nome, String email, String senha, Endereco endereco) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
        this.id = proximoId;
        proximoId++;
    }

    public static void atualizarProximoId(int novoProximoId) {
        proximoId = novoProximoId;
    }

    public boolean alterarNome (String novoNome) {
        if (novoNome == null || novoNome.trim().isEmpty()  || novoNome.trim().length() < 3) {
            return false;
        } else {
            this.nome = novoNome;
            return true;
        }
    }

    public boolean alterarEmail (String novoEmail) {
        if (novoEmail == null || novoEmail.trim().isEmpty() || novoEmail.trim().length() < 3) {
            return false;
        } else {
            this.email = novoEmail;
            return true;
        }
    }

    public boolean alterarSenha (String novaSenha) {
        if (novaSenha == null || novaSenha.trim().isEmpty() || novaSenha.trim().length() < 3) {
            return false;
        } else {
            this.senha = novaSenha;
            return true;
        }
    }

    // GETTERS

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "\n\nID: " + id +
                "\nNome: " + nome +
                "\nEmail: " + email +
                "\nEndereço: " + endereco;
    }
}
