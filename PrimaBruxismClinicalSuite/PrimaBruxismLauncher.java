// File: PrimaBruxismLauncher.java

import javafx.application.Application;
import javafx.stage.Stage;
import ui.MainDashboard;
import util.GDPRLogger;

public class PrimaBruxismLauncher extends Application {

    public void start(Stage primaryStage) {
        // Logging avvio con data/ora per tracciamento GDPR
        GDPRLogger.logEvent("Application started");

        // Avvia la GUI principale
        MainDashboard dashboard = new MainDashboard();
        dashboard.start(primaryStage);
    }

    public static void main(String[] args) {
        // Avvio dell'applicazione JavaFX
        Application.launch(args);
    }
