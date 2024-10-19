module gameofthrones.proyecto {
    requires javafx.controls;
    requires javafx.fxml;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;

    opens gameofthrones.proyecto to javafx.fxml;
    exports gameofthrones.proyecto;
    opens gameofthrones.proyecto.model to com.fasterxml.jackson.databind;
    exports gameofthrones.proyecto.model;
    opens gameofthrones.proyecto.controller to javafx.fxml;
    exports gameofthrones.proyecto.controller;
}
