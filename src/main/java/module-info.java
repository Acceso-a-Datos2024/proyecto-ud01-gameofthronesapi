module gameofthrones.proyecto {
    requires javafx.controls;
    requires javafx.fxml;

    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.fasterxml.jackson.annotation;

    opens gameofthrones.proyecto to javafx.fxml;
    exports gameofthrones.proyecto;
    exports gameofthrones.proyecto.controller;
    opens gameofthrones.proyecto.controller to javafx.fxml;
}