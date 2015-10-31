package nl.m4jit.framework.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {

    private BufferedWriter output;

    public Writer(String filepath) throws IOException {

        output = new BufferedWriter(new FileWriter(filepath));
    }

    public void writeLine(String line) throws IOException {

        output.write(line);
        output.newLine();
    }

    public void close() throws IOException {

        output.close();
    }
}
