// File: test/AllTests.java

package test;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.ConfigurationParameter;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;

@Suite
@SelectPackages({
    "test.logic",       // Test per i motori logici
    "test.model",       // Test per le classi cliniche
    "test.util",        // Test per CSVParser, FileManager, etc.
    "test.ui"           // Test GUI interni o logiche collegate
})
@ConfigurationParameter(key = "junit.jupiter.execution.parallel.enabled", value = "true")
public class AllTests {
    // Empty class body: annotations define test suite behavior
}
