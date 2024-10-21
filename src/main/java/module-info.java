module gameofthrones.proyecto {
    requires javafx.controls;
    requires javafx.fxml;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires java.xml;
    requires jakarta.xml.bind;

    opens gameofthrones.proyecto to javafx.fxml, jakarta.xml.bind;
    exports gameofthrones.proyecto;
    opens gameofthrones.proyecto.model to com.fasterxml.jackson.databind,jakarta.xml.bind;
    exports gameofthrones.proyecto.model;
    exports gameofthrones.proyecto.controller;
    opens gameofthrones.proyecto.controller to com.fasterxml.jackson.databind, jakarta.xml.bind, javafx.fxml;
}
