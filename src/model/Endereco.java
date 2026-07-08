package model;

public record Endereco (String cep, String estado, String localidade, String bairro, String logradouro, String uf){
    @Override
    public String toString() {
        return logradouro + ", " + bairro + " - " + localidade + "/" + uf;
    }
}
