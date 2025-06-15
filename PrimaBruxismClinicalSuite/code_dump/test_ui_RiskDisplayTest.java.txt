// File: test/ui/RiskDisplayTest.java

package test.ui;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import ui.RiskDisplay;

import static org.junit.jupiter.api.Assertions.*;

public class RiskDisplayTest extends ApplicationTest {

    private RiskDisplay riskDisplay;

    @Override
    public void start(Stage stage) {
        riskDisplay = new RiskDisplay();
        Scene scene = new Scene(riskDisplay, 400, 300);
        stage.setScene(scene);
        stage.show();
    }

    @BeforeEach
    public void setupEach() {
        interact(() -> {
            riskDisplay.setFTSScore("muscular", 0.15);  // Unlikely
            riskDisplay.setFTSScore("articular", 0.50); // Possible
            riskDisplay.setFTSScore("occlusal", 0.80);  // Probable
        });
    }

    @Test
    public void testFTSScoresAreDisplayedCorrectly() {
        Label muscularLabel = lookup("#muscularLabel").query();
        Label articularLabel = lookup("#articularLabel").query();
        Label occlusalLabel = lookup("#occlusalLabel").query();

        assertEquals("Unlikely (15%)", muscularLabel.getText());
        assertEquals("Possible (50%)", articularLabel.getText());
        assertEquals("Probable (80%)", occlusalLabel.getText());
    }

    @Test
    public void testColorCoding() {
        Label muscularLabel = lookup("#muscularLabel").query();
        Label articularLabel = lookup("#articularLabel").query();
        Label occlusalLabel = lookup("#occlusalLabel").query();

        String muscularStyle = muscularLabel.getStyle();
        String articularStyle = articularLabel.getStyle();
        String occlusalStyle = occlusalLabel.getStyle();

        assertTrue(muscularStyle.contains("green"));
        assertTrue(articularStyle.contains("orange"));
        assertTrue(occlusalStyle.contains("red"));
    }

    @Test
    public void testInvalidDomainIgnored() {
        interact(() -> {
            riskDisplay.setFTSScore("invalidDomain", 0.9);
        });

        Label invalidLabel = lookup("#invalidDomainLabel").query();
        assertNull(invalidLabel); // Non deve esistere
    }
}
