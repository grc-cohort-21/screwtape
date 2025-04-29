import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

public class ScrewtapeApp {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java ScrewtapeApp <filename>");
            System.exit(1);
        }

        String filename = args[0];
        String program;

        try {
            byte[] bytes = Files.readAllBytes(Paths.get(filename));
            program = new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("Error reading file '" + filename + "': " + e.getMessage());
            System.exit(1);
            return;
        }

        ScrewtapeInterpreter interpreter = new ScrewtapeInterpreter();
        String output;
        try {
            output = interpreter.execute(program);
        } catch (IllegalArgumentException e) {
            System.err.println("Error executing Screwtape program: " + e.getMessage());
            System.exit(1);
            return;
        }

        System.out.print(output);
    }
}