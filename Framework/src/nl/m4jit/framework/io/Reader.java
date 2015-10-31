package nl.m4jit.framework.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Reader {

    private BufferedReader input;

    public Reader(String filepath) throws FileNotFoundException {

        input = new BufferedReader(new FileReader(filepath));
    }

    public String readLine() throws IOException {

        return input.readLine();
    }

    public void close() throws IOException {

        input.close();
    }
}
