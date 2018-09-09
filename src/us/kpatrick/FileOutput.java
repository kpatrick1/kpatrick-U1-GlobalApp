package us.kpatrick;

import java.io.*;

/**
 * Class designed to write to a file
 * @author kpatr
 * @version 1.0.0
 */
public class FileOutput {

    Writer out = null;
    private String fileName;

    /**
     * Open file to write to
     * @param fileName File to open
     */
    public FileOutput(String fileName) {
        this.fileName = fileName;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
        }
        catch(FileNotFoundException e) {
            System.out.println("File Open Error: " + fileName + " "  + e);
        }
    }

    /**
     * Write the provided string to the file
     * @param line LIne to write to the file
     */
    public void fileWrite(String line) {
        try {
            out.write(line+"\n");
        }
        catch(Exception e) {
            System.out.println("File Write Error: " + fileName + " "  + e);
        }
    }

    /**
     * Close the file that we were writing to.
     */
    public void fileClose() {
        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}