module pl.dminior8.asteriskfinder {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens pl.dminior8.asteriskfinder to javafx.fxml;
    exports pl.dminior8.asteriskfinder;
}