// File: ui/MainDashboard.java

package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.ExportManager;

public class MainDashboard extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("PrimaBruxismClinicalSuite");

        // Top Menu Bar
        MenuBar menuBar = new MenuBar();
        Menu languageMenu = new Menu("Language");
        MenuItem english = new MenuItem("English");
        MenuItem italian = new MenuItem("Italiano");

        english.setOnAction(e -> LanguageSwitcher.setLanguage("en"));
        italian.setOnAction(e -> LanguageSwitcher.setLanguage("it"));

        languageMenu.getItems().addAll(english, italian);
        menuBar.getMenus().add(languageMenu);

        // Central Buttons
        Label title = new Label("PRIMA Clinical Suite");
        Button loadSTABButton = new Button("Load STAB Data");
        Button calculateFTSButton = new Button("Calculate FTS");
        Button afiButton = new Button("View AFI");
        Button redFlagButton = new Button("Run Red Flag Detection");
        Button therapyButton = new Button("Therapy Planner");
        Button exportButton = new Button("Export Report");

        loadSTABButton.setOnAction(e -> STABLoaderUI.launchLoader());
        calculateFTSButton.setOnAction(e -> FTSCalculatorUI.launchCalculator());
        afiButton.setOnAction(e -> AFIGraphPanel.showAFI());
        redFlagButton.setOnAction(e -> RedFlagUI.launch());
        therapyButton.setOnAction(e -> TherapyUI.launchPlanner());
        exportButton.setOnAction(e -> ExportManager.exportCurrentReport());

        VBox centerLayout = new VBox(12);
        centerLayout.getChildren().addAll(
                title, loadSTABButton, calculateFTSButton,
                afiButton, redFlagButton, therapyButton, exportButton
        );
        centerLayout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        // Root Layout
        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(centerLayout);

        // Scene Setup
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
