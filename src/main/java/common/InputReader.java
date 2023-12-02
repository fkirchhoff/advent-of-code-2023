package common;

import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.List;

public class InputReader {

    public static List<String> readAllLines(String input) {
        // Get the call stack
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        try {

            // The calling class is at index 2 (index 0 and 1 are related to getStackTrace())
            if (stackTrace.length >= 3) {
                var callingClassName = stackTrace[2].getClassName();
                Class<?> callingClass = null;
                callingClass = Class.forName(callingClassName);
                var stream = callingClass.getResourceAsStream(input);
                if (stream != null) {
                    var reader = new InputStreamReader(stream);
                    // read the file line by line
                    List<String> lines = new java.io.BufferedReader(reader).lines().toList();
                    return lines;
                }
            }
            return Files.readAllLines(java.nio.file.Path.of(input));
        } catch (Exception e) {
            throw new RuntimeException("Could not read lines from " + input, e);
        }

    }

}
