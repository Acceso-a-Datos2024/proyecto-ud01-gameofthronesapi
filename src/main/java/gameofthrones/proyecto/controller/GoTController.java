package gameofthrones.proyecto.controller;

import gameofthrones.proyecto.model.Characters;
import gameofthrones.proyecto.model.Response;
import gameofthrones.proyecto.model.UsuariosItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import gameofthrones.proyecto.model.CharactersItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.IOException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;


public class GoTController {
    @FXML
    private ImageView imagenCharacter;

    @FXML
    private VBox vboxOk;

    @FXML
    private Button boton_ok;

    @FXML
    private Label doc_vacio;

    @FXML
    private Label datos_incorrectos;

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
    private TableColumn<CharactersItem, String> coltitulo;

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


    private List<CharactersItem> allCharacters; // Lista de todos los personajes, cargada una vez

    @FXML
    public void initialize() {
        // Configurar columnas de la tabla
        if (colnombrecompleto != null || colcasa != null || coltitulo != null) {
            colnombrecompleto.setCellValueFactory(new PropertyValueFactory<>("fullName"));
            colcasa.setCellValueFactory(new PropertyValueFactory<>("family"));
            coltitulo.setCellValueFactory(new PropertyValueFactory<>("title"));
        } else {
            ;
        }
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
    protected void salir(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gameofthrones/proyecto/login.fxml"));
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

    @FXML
    private boolean verificacion(String nombre, String password) {
        Properties properties= new Properties();

        try(FileInputStream fis = new FileInputStream("res/usuarios.properties")){
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        // Verificar si el usuario existe y si la contraseña coincide
        String storedPassword = properties.getProperty(nombre);
        return storedPassword != null && storedPassword.equals(password);
    }

    @FXML
    public void login(ActionEvent actionEvent) {
        String nombre = txtname.getText().trim();
        String password = txtpassword.getText().trim();

        // Verificar si los campos están vacíos
        if (nombre.isEmpty() || password.isEmpty()) {
            datos_incorrectos.setVisible(true);  // Mostrar el mensaje de error si faltan datos
            return;
        }

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
            datos_incorrectos.setVisible(true);  // Mostrar mensaje de error si la verificación falla
            txtname.clear();
            txtpassword.clear();
        }
    }

    @FXML
    public void exportarXML(ActionEvent actionEvent) {
        if (!nombre_doc.getText().isEmpty()){
            doc_vacio.setVisible(false);
            // Obtener los personajes actualmente mostrados en la tabla
            ObservableList<CharactersItem> personajesAExportar = FXCollections.observableArrayList(SearchHolder.getInstance().getCharacterItems());

            // Verificar si hay personajes para exportar
            if (personajesAExportar.isEmpty()) {
                System.out.println("No hay personajes para exportar.");
                return;
            }

            // Crear un wrapper para los personajes
            Characters wrapper = new Characters();
            wrapper.setCharacters(new ArrayList<>(personajesAExportar));

            // Configurar JAXB para convertir el wrapper a XML
            try {
                JAXBContext context = JAXBContext.newInstance(Characters.class, CharactersItem.class);
                Marshaller marshaller = context.createMarshaller();

                // Para formatear bien el XML
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

                // Exportar a archivo
                File file = new File("src/main/docs/" + nombre_doc.getText() + ".xml");
                marshaller.marshal(wrapper, file);
            } catch (JAXBException e) {
                e.printStackTrace();
            }
            vboxOk.setVisible(true);
        }
        else{
            doc_vacio.setVisible(true);
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
    public void exportarBIN(ActionEvent actionEvent) throws IOException {
        if (!nombre_doc.getText().isEmpty()){
            doc_vacio.setVisible(false);
            ObjectOutputStream objectStream = new ObjectOutputStream(new FileOutputStream("src/main/docs/" + nombre_doc.getText() + ".bin"));
            try {
                objectStream.writeObject(SearchHolder.getInstance().getCharacterItems());
                objectStream.flush();
                objectStream.close();
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
    public void exportarTXT(ActionEvent actionEvent) throws IOException {
        if (!nombre_doc.getText().isEmpty()){
            BufferedWriter escritor = new BufferedWriter(new FileWriter("src/main/docs/" + nombre_doc.getText() + ".txt",false));
            try {
                for(CharactersItem character : SearchHolder.getInstance().getCharacterItems()) {
                    escritor.write(character.toString());
                    escritor.newLine();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            escritor.flush();
            escritor.close();
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

    @FXML
    public void onTableClicked(MouseEvent event) {
        if (event.getClickCount() == 2) {
            CharactersItem item = tablabusqueda.getSelectionModel().getSelectedItem();
            Image imagen = new Image(item.getImageUrl());
            imagenCharacter.setImage(imagen);
            imagenCharacter.setVisible(true);
        }
    }

}
