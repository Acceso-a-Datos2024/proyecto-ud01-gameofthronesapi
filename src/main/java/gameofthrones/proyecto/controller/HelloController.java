package gameofthrones.proyecto.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import gameofthrones.proyecto.model.CharactersItem;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class HelloController {
    @FXML
    private TextField buscarid;

    @FXML
    private TextField buscarnombre;

    @FXML
    private TextField buscarapellido;

    @FXML
    private TableView<CharactersItem> tablabusqueda;

    @FXML
    private TableColumn<CharactersItem, String> colnombrecompleto;

    @FXML
    private TableColumn<CharactersItem, String> colcasa;

    @FXML
    private TableColumn<CharactersItem, String> colimagen;

    @FXML
    private Button botonbuscar;

    private List<CharactersItem> allCharacters; // Lista de todos los personajes, cargada una vez

    @FXML
    public void initialize() {
        // Configurar columnas de la tabla
        colnombrecompleto.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        colcasa.setCellValueFactory(new PropertyValueFactory<>("family"));
        colimagen.setCellValueFactory(new PropertyValueFactory<>("imageUrl"));

        // Cargar todos los personajes desde el archivo JSON
        cargarPersonajesDesdeJSON();
    }

    @FXML
    public void busqueda(MouseEvent event) {
        String idText = buscarid.getText().trim();
        String nombre = buscarnombre.getText().trim();
        String apellido = buscarapellido.getText().trim();

        // Filtrar los personajes según los criterios de búsqueda
        List<CharactersItem> filteredList = allCharacters.stream()
                .filter(character -> (idText.isEmpty() || character.getId() == parseId(idText)) &&
                        (nombre.isEmpty() || character.getFirstName().toLowerCase().contains(nombre.toLowerCase())) &&
                        (apellido.isEmpty() || character.getLastName().toLowerCase().contains(apellido.toLowerCase())))
                .collect(Collectors.toList());

        // Mostrar solo los resultados filtrados en la tabla
        ObservableList<CharactersItem> observableList = FXCollections.observableArrayList(filteredList);
        tablabusqueda.setItems(observableList);
    }

    private int parseId(String idText) {
        try {
            return Integer.parseInt(idText);
        } catch (NumberFormatException e) {
            // Si no es un número válido, devolvemos un valor negativo que no coincide con ningún ID
            return -1;
        }
    }

    private void cargarPersonajesDesdeJSON() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Ruta al archivo characters.json
            File file = new File("res/characters.json");

            // Leer JSON y convertir a lista de objetos CharactersItem
            allCharacters = mapper.readValue(file, new TypeReference<List<CharactersItem>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
