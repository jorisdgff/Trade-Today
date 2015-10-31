package nl.m4jit.framework.io;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.tree.DefaultMutableTreeNode;
import nl.m4jit.framework.io.filter.DirectoryFilter;
import nl.m4jit.framework.io.filter.ExtensionFilter;


public class Directory {

    private ArrayList<Directory> subDirectorys;
    private File file;
    private DefaultMutableTreeNode treeNode;
    
    public Directory(File file) {

        this.file = file;

        subDirectorys = new ArrayList<Directory>();
        treeNode = new DefaultMutableTreeNode(file.getName());

        for(File dir : file.listFiles(new DirectoryFilter())){
            
            subDirectorys.add(new Directory(dir));
        }
    }

    public ArrayList<Directory> getSubdirectorys(){

        return subDirectorys;
    }

    public DefaultMutableTreeNode getNode() {

        for (Directory directory : subDirectorys) {

            treeNode.add(directory.getNode());
        }

        return treeNode;
    }

    public File getRootDir() {

        return file;
    }

    public ArrayList<File> getFiles() {

        return new ArrayList<File>(Arrays.asList(file.listFiles()));
    }

    public ArrayList<File> getFiles(String... extensions) {

        return new ArrayList<File>(Arrays.asList(file.listFiles(new ExtensionFilter(extensions))));
    }

    public ArrayList<Directory> getDirectorys(){

        ArrayList<Directory> directorys = new ArrayList<Directory>();

        File[] files = file.listFiles(new DirectoryFilter());

        for(int i = 0; i < files.length; i++){


        }

        return directorys;
    }

    public void getAllDirectorys(ArrayList<Directory> dir){ //Niet teverden
        
        for(Directory directory : subDirectorys){
            
            directory.getAllDirectorys(dir);
            dir.add(directory);
        }
    }
    
    public ArrayList<File> getAllFiles(String... extensions) {

        ArrayList<File> files = new ArrayList<File>();
        ArrayList<Directory> directorys = new ArrayList<Directory>();

        getAllDirectorys(directorys);
        
        for(Directory directory : directorys){
            
            for(File file : directory.getFiles(extensions)){
                
                files.add(file);
            }
        }
        
        return files;
    }

    public String toString(){

        return file.toString();
    }
}