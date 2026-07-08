package service;

import model.Endereco;
import model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuariosService {
    private List<Usuario> usuarios = new ArrayList<>();
    private JsonService jsonService;

    public UsuariosService(){
        jsonService = new JsonService();
        usuarios = jsonService.carregarUsuarios();
        atualizarContadorId();
    }

    public boolean cadastrarUsuario(Usuario usuario) {
            if (!validarNome(usuario.getNome())) {
                return false;
            }
            if (!validarEmail(usuario.getEmail())){
                return false;
            }
            if (!validarSenha(usuario.getSenha())) {
                return false;
            }

            for (Usuario usuariosCadastrados : usuarios) {
                if(usuariosCadastrados.getEmail().equals(usuario.getEmail())) {
                    return false;
                }
            }
            usuarios.add(usuario);
            jsonService.salvarUsuarios(usuarios);
            return true;
    }

    public List<Usuario> listarUsuarios() {
        return usuarios;
    }

    public Usuario buscarUsuario (int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        return null;
    }

    public boolean deletarUsuario(int id) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId() == id) {
                usuarios.remove(i);
                jsonService.salvarUsuarios(usuarios);
                return true;
            }
        }

        return false;
    }

    private void atualizarContadorId() {
        int maiorId = 0;

        for (Usuario usuario : usuarios) {
            if (usuario.getId() > maiorId) {
                maiorId = usuario.getId();
            }
        }

        Usuario.atualizarProximoId(maiorId + 1);
    }

    //Alterações de dados

    public boolean alterarNome (int id, String novoNome) {
        Usuario usuario = buscarUsuario(id);
        if (usuario == null) {
            return false;
        }

        boolean alterou = usuario.alterarNome(novoNome);
        if (alterou) {
            jsonService.salvarUsuarios(usuarios);
        }
        return alterou;
    }

    public boolean alterarSenha (int id, String novaSenha) {
        Usuario usuario = buscarUsuario(id);
        if (usuario == null) {
            return false;
        }

        boolean alterou = usuario.alterarNome(novaSenha);
        if (alterou) {
            jsonService.salvarUsuarios(usuarios);
        }
        return alterou;
    }

    public boolean alterarEmail(int id, String novoEmail) {
        Usuario usuario = buscarUsuario(id);

        if (usuario == null) {
            return false;
        }

        boolean alterou = usuario.alterarNome(novoEmail);
        if (alterou) {
            jsonService.salvarUsuarios(usuarios);
        }
        return alterou;
    }

    public boolean alterarCep(int id, Endereco endereco) {
        Usuario usuario = buscarUsuario(id);
        if (usuario==null){
            return false;
        }

        boolean alterou = usuario.alterarCep(endereco);
        if (alterou) {
            jsonService.salvarUsuarios(usuarios);
        }
        return alterou;
    }

    // Métodos de validação de dados

    public boolean validarNome(String nome) {
        if (nome == null) {
            return false;
        }

        String nomeFormatado = nome.trim();

        return !nomeFormatado.isEmpty() && nomeFormatado.length() >= 3;
    }

    public boolean validarSenha(String senha) {
        if(senha == null) {
            return false;
        }

        String senhaFormatada = senha.trim();

        return !senhaFormatada.isEmpty() && senhaFormatada.length() >= 8;
    }

    public boolean validarEmail(String email) {
        if (email == null) {
            return false;
        }

        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        return email.matches(regex);
    }

    public boolean possuiUsuarios() {
        return !usuarios.isEmpty();
    }


}
