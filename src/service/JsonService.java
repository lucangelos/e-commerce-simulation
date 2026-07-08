package service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Usuario;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonService {
    private static final String arquivo = "usuarios.json";

    public void salvarUsuarios(List<Usuario> usuarios) {
        try (FileWriter writer = new FileWriter(arquivo)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(usuarios, writer);
        } catch (IOException e) {
            throw new RuntimeException("\nErro ao salvar os usuários!\n");
        }
    }

    public List<Usuario> carregarUsuarios() {
        try (FileReader reader = new FileReader(arquivo)) {

            Gson gson = new GsonBuilder().setPrettyPrinting().create();


            Type tipoLista = new TypeToken<List<Usuario>>() {}.getType();

            return gson.fromJson(reader, tipoLista);

        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}
