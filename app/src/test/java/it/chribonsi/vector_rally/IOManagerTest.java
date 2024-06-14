package it.chribonsi.vector_rally;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IOManagerTest {

    @Test
    void testSelectSchemaFilePath() {
        // Path to the test file in the resources folder
        Path testFilePath = Paths.get("src/test/resources/test.txt");

        // Simulate user input with the test file path
        String simulatedInput = testFilePath.toString() + "\n";
        InputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());

        // Create IOManager instance with the simulated input
        IOManager ioManager = new IOManager(inputStream);

        // Invoke the method
        Path selectedPath = ioManager.selectSchemaFilePath();

        // Validate the result
        assertEquals(testFilePath, selectedPath);
    }
}