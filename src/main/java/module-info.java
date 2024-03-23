module pl.dminior8.asteriskfinder {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.google.gson;

    opens pl.dminior8.asteriskfinder to javafx.fxml;
    exports pl.dminior8.asteriskfinder;
    exports pl.dminior8.asteriskfinder.service;
    opens pl.dminior8.asteriskfinder.service to javafx.fxml;
    exports pl.dminior8.asteriskfinder.controller;
    opens pl.dminior8.asteriskfinder.controller to javafx.fxml;
}