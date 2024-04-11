package levit104dami404.tpo.lab2.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class CsvHandler {
    private final String SEPARATOR;

    public CsvHandler(String separator) {
        SEPARATOR = separator;
    }

    public void writeData(String file, String function, double x, double result) {
        Path path = Paths.get(ensureCsvExtension(file));
        String data = x + SEPARATOR + result + System.lineSeparator();

        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            }

            if (Files.size(path) == 0) {
                data = "x" + SEPARATOR + function + "(x)" + System.lineSeparator() + data;
            }

            Files.write(path, data.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    public List<String> readData(String file) {
        Path path = Paths.get(ensureCsvExtension(file));
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<String> readData(String file, boolean ignoreHeader) {
        List<String> data = readData(file);
        if (ignoreHeader && !data.isEmpty())
            return data.subList(1, data.size());
        return data;
    }

    private String ensureCsvExtension(String filePath) {
        if (!filePath.toLowerCase().endsWith(".csv")) {
            return filePath + ".csv";
        }
        return filePath;
    }
}
