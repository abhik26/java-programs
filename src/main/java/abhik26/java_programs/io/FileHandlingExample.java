package abhik26.java_programs.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandlingExample {

    public static void main(String[] args) throws Exception {
        final String fileFullPath = "java-file-handling-example.txt";

        writeWithfileWriter(fileFullPath);
        readWithFileReader(fileFullPath);
        deleteFile(fileFullPath);
    }

    private static void writeWithfileWriter(final String fileFullPath) throws IOException {
        String fileContent = "sample content for java file handling";
        File file = new File(fileFullPath);
        
        if (!file.exists()) {
            file.createNewFile();
        }
        
        try (FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fileWriter)) {
            bw.write(fileContent);
            bw.newLine();
            bw.write(fileContent);
        }
    }

    private static void readWithFileReader(final String fileFullPath) throws IOException {
        File file = new File(fileFullPath);

        if (file.exists()) {
            try (FileReader fileReader = new FileReader(file);
                    BufferedReader br = new BufferedReader(fileReader)) {

                String line = null;

                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }
        } else {
            throw new FileNotFoundException("file not found");
        }
    }

    private static void deleteFile(String fileFullPath) {
        File file = new File(fileFullPath);

        if (file.exists()) {
            boolean fileDeleted = file.delete();

            if (fileDeleted) {
                System.out.println("file deleted");
            }

        }
    }

}
