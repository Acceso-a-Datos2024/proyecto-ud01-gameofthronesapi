package gameofthrones.proyecto.controller;

import gameofthrones.proyecto.model.Response;
import gameofthrones.proyecto.model.UsuariosItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import gameofthrones.proyecto.model.CharactersItem;
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

public class GoTController {

     @FXML
    private VBox vboxOk;

    @FXML
    private Button boton_ok;
    
    @FXML
    private Label doc_vacio;
    
    @FXML
    private TextField nombre_doc;
    
    @FXML
    private VBox exportar_documento;

    @FXML
    private Button Volver;

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
    private TextField txtname;

    @FXML
    private TextField txtpassword;

    @FXML
    private Button botonbuscar;

    @FXML
    private Button botonexportartabla;

    @FXML
    private Button botonlogin;

    private List<UsuariosItem> usuarios; // Lista de usuarios cargados desde JSON

    private List<CharactersItem> allCharacters; // Lista de todos los personajes, cargada una vez

    @FXML
    public void initialize() {
        // Configurar columnas de la tabla
        if (colnombrecompleto != null || colcasa != null || colimagen != null) {
            colnombrecompleto.setCellValueFactory(new PropertyValueFactory<>("fullName"));
            colcasa.setCellValueFactory(new PropertyValueFactory<>("family"));
            colimagen.setCellValueFactory(new PropertyValueFactory<>("imageUrl"));
        } else {
            System.out.println("algun campo es null");
        }

        // Cargar todos los personajes desde el archivo JSON
        cargarPersonajesDesdeJSON();
        // Cargar los usuarios cuando se inicializa la ventana
        cargarUsuariosDesdeJSON();
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
        SearchHolder.getInstance().setCharacterItems(tablabusqueda.getItems());
    }

    private int parseId(String idText) {
        try {
            return Integer.parseInt(idText);
        } catch (NumberFormatException e) {
            // Si no es un número válido, devolvemos un valor negativo que no coincide con ningún ID
            return -1;
        }
    }

    private void cargarUsuariosDesdeJSON() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Ruta al archivo usuarios.json
            File file = new File("res/usuarios.json");

            // Leer JSON y convertir a una lista de UsuariosItem dentro de Response
            Response response = mapper.readValue(file, Response.class);
            usuarios = response.getUsuarios();
        } catch (IOException e) {
            e.printStackTrace();
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

    @FXML
    protected void clickVolver(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gameofthrones/proyecto/consultas_tabla.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void abrirExportarDocumento(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gameofthrones/proyecto/exportar_documento.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean verificacion(String nombre, String password) {
        for (UsuariosItem usuario : usuarios) {
            if (usuario.getUsuario().equals(nombre) && usuario.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    @FXML
    public void login(ActionEvent actionEvent) {
        // Obtener los valores ingresados en el formulario
        String nombre = txtname.getText().trim();
        String password = txtpassword.getText().trim();

        // Verificar si las credenciales son correctas
        if (verificacion(nombre, password)) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gameofthrones/proyecto/consultas_tabla.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            txtname.clear();
            txtpassword.clear();
            System.out.println("Usuario o contraseña incorrectos.");
        }
    }

    @FXML
    public void exportarJSON(ActionEvent actionEvent) {
         if (!nombre_doc.getText().isEmpty()){
            doc_vacio.setVisible(false);
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("src/main/docs/" + nombre_doc.getText() + ".json"), SearchHolder.getInstance().getCharacterItems());
            } catch (IOException e) {
                e.printStackTrace();
            }
            vboxOk.setVisible(true);
        }
        else{
            doc_vacio.setVisible(true);
        }
    }

    @FXML
    public void clickOk(ActionEvent actionEvent) {
        vboxOk.setVisible(false);
    }
}
