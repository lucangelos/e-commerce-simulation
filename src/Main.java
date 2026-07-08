import model.Endereco;
import model.Usuario;
import service.UsuariosService;
import service.ViaCepService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsuariosService usuariosService = new UsuariosService();
        ViaCepService viaCepService = new ViaCepService();

        int opc = -1;
        while (opc != 0) {
            exibirMenu();
            opc = scanner.nextInt();
            scanner.nextLine();

            switch (opc) {
                case 1:
                    cadastrarUsuario(scanner, usuariosService, viaCepService);
                    break;

                case 2:
                    listarUsuarios(usuariosService);
                    break;

                case 3:
                    buscarUsuarios(scanner, usuariosService);
                    break;

                case 4:
                    deletarUsuarios(scanner, usuariosService);
                    break;

                case 5:
                    if (usuariosService.possuiUsuarios()) {
                        System.out.println("\nTODOS OS USUÁRIOS\n");
                        System.out.println(usuariosService.listarUsuarios());

                        System.out.println("\nDigite o id do usuário em que deseja alterar algum dado: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();

                        if(usuariosService.buscarUsuario(id) != null) {
                            alterarDados(scanner, usuariosService, id, viaCepService);
                        } else {
                            System.out.println("\nNenhum usuário encontrado com este ID!\n");
                        }
                    } else {
                        System.out.println("\nNenhum usuário cadastrado!\n");
                    }
                    break;

                case 0:
                    System.out.println("\nFinalizando o programa ... \n");
                    return;

                default:
                    System.out.println("\nOpção Inválida!\n");
                    break;
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("****************************");
        System.out.println("\nE-COMMERCE");
        System.out.println("Digite a opção que deseja:\n");
        System.out.println("****************************");
        System.out.println("1-Cadastrar usuário");
        System.out.println("2-Listar Usuários");
        System.out.println("3-Buscar Usuários");
        System.out.println("4-Deletar usuários");
        System.out.println("5-Alterar informações pessoais");
        System.out.println("0-Sair");
    }

    private static void cadastrarUsuario(Scanner scanner, UsuariosService usuariosService, ViaCepService viaCepService) {
        System.out.println("\nDigite o seu nome: ");
        String nome = scanner.nextLine();

        //Pega o email
        System.out.println("Digite o seu email: ");
        String email = scanner.nextLine();

        //Pega a senha
        System.out.println("Digite a sua senha: ");
        String senha = scanner.nextLine();

        //Pega o cep
        System.out.println("Digite o seu CEP (digite apenas números): ");
        String cep = scanner.nextLine();

        //Criação dos usuários
        Endereco endereco = viaCepService.buscaEndereco(cep);
        Usuario usuario = new Usuario(nome, email, senha, endereco);
        usuariosService.cadastrarUsuario(usuario);
        System.out.println("Usuário criado com sucesso!\n");
    }

    private static void listarUsuarios(UsuariosService usuariosService) {
        if (usuariosService.possuiUsuarios()) {
            System.out.println("\nTODOS OS USUÁRIOS:\n");
            System.out.println(usuariosService.listarUsuarios());
        } else {
            System.out.println("\nNão há nenhum usuário cadastrado\n");
        }
    }

    private static void buscarUsuarios (Scanner scanner, UsuariosService usuariosService) {
        if (usuariosService.possuiUsuarios()){
            System.out.println("\nTODOS OS USUÁRIOS:\n");
            System.out.println(usuariosService.listarUsuarios());
            System.out.println("\nDigite o ID do usuário: ");
            int id = scanner.nextInt();

            Usuario usuarioId = usuariosService.buscarUsuario(id);
            if (usuarioId != null) {
                System.out.println(usuarioId);
            } else {
                System.out.println("Nenhum usuário foi encontrado com este ID\n");
            }
        } else {
            System.out.println("\nNenhum usuário encontrado\n");
        }
    }

    private static void deletarUsuarios(Scanner scanner, UsuariosService usuariosService) {
        if(usuariosService.possuiUsuarios()) {
            System.out.println("\nTODOS OS USUÁRIOS:\n");
            System.out.println(usuariosService.listarUsuarios());

            System.out.println("\nDigite o ID do usuário em que deseja deletar: ");
            int id = scanner.nextInt();

            boolean usuarioRemovido = usuariosService.deletarUsuario(id);
            if(usuarioRemovido) {
                System.out.println("Usuário deletado com sucesso!\n");
            } else {
                System.out.println("Nenhum usuário encontrado com este ID\n");
            }
        } else {
            System.out.println("\nNenhum usuário encontrado\n");
        }
    }

    private static void alterarDados(Scanner scanner, UsuariosService usuariosService, int id, ViaCepService viaCepService) {

        System.out.println("\nO que deseja alterar?\n");
        System.out.println("1 - Nome");
        System.out.println("2 - E-mail");
        System.out.println("3 - Senha");
        System.out.println("4 - CEP");

        int opc = scanner.nextInt();
        scanner.nextLine();

        switch (opc) {

            case 1:
                System.out.print("\nDigite o novo nome: ");
                String novoNome = scanner.nextLine();

                if (usuariosService.alterarNome(id, novoNome)) {
                    System.out.println("\nNome alterado com sucesso!");
                } else {
                    System.out.println("\nNão foi possível alterar o nome.");
                }
                break;

            case 2:
                System.out.print("\nDigite o novo e-mail: ");
                String novoEmail = scanner.nextLine();

                if (usuariosService.alterarEmail(id, novoEmail)) {
                    System.out.println("\nE-mail alterado com sucesso!");
                } else {
                    System.out.println("\nNão foi possível alterar o e-mail.");
                }
                break;

            case 3:
                System.out.print("\nDigite a nova senha: ");
                String novaSenha = scanner.nextLine();

                if (usuariosService.alterarSenha(id, novaSenha)) {
                    System.out.println("\nSenha alterada com sucesso!");
                } else {
                    System.out.println("\nNão foi possível alterar a senha.");
                }
                break;

            case 4:
                System.out.print("\nDigite o novo CEP: ");
                String cep = scanner.nextLine();

                Endereco endereco = viaCepService.buscaEndereco(cep);

                if (usuariosService.alterarCep(id, endereco)) {
                    System.out.println("\nCEP alterado com sucesso!");
                } else {
                    System.out.println("\nNão foi possível alterar o CEP.");
                }
                break;

            default:
                System.out.println("\nOpção inválida!");
                break;
        }
    }

}
