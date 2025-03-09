package services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileReaderService {
    public List<String> readFile(Path filePath) throws IOException {
        return Files.readAllLines(filePath);
    }
}
