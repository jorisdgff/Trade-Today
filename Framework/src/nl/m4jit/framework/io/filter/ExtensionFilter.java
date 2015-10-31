package nl.m4jit.framework.io.filter;

import java.io.File;
import java.io.FileFilter;

public class ExtensionFilter implements FileFilter{
    
    private String[] extensions;
    
    public ExtensionFilter(String... extensions) {
        
        this.extensions = extensions;
    }

    public boolean accept(File f) {

        for(String extension : extensions){

            if(f.getName().toLowerCase().endsWith(extension)){

                return true;
            }
        }

        return false;
    }
}
