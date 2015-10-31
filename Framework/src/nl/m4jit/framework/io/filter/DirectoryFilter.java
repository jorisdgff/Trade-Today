package nl.m4jit.framework.io.filter;

import java.io.File;
import java.io.FileFilter;

public class DirectoryFilter implements FileFilter{

    public boolean accept(File file) {

        return file.isDirectory();
    }
}
