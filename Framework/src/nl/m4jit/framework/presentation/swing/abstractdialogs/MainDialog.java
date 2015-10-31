package nl.m4jit.framework.presentation.swing.abstractdialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import nl.m4jit.framework.Util;
import nl.m4jit.framework.presentation.swing.compositecomponents.ButtonBar;
import nl.m4jit.framework.presentation.swing.compositecomponents.StatusBar;

public abstract class MainDialog extends ContentDialog{

    protected final int NORTH = 0, EAST = 1, SOUTH = 2, WEST = 3;
    protected JMenuBar menuBar;
    protected JMenu currentmenu;
    protected HashMap<String, JMenuItem> menuitems;
    protected ButtonBar buttonBarNorth, buttonBarEast, buttonBarSouth, buttonBarWest;
    protected JLabel backgroundLabel;
    protected static StatusBar statusBar;
    
    public MainDialog() {
        
        super(true);
        
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        menuitems = new HashMap<String, JMenuItem>();
        
        backgroundLabel = new JLabel(new ImageIcon(Util.getInstalDir() + "files/background.jpg"));
        backgroundLabel.setLayout(new BorderLayout());
        backgroundLabel.setOpaque(true);
        backgroundLabel.setBackground(Color.WHITE);
        add(backgroundLabel, BorderLayout.CENTER);

        buttonBarNorth = new ButtonBar(true, 120, 100, this);
        add(buttonBarNorth, BorderLayout.NORTH);

        buttonBarEast = new ButtonBar(false, 120, 100, this);
        add(buttonBarEast, BorderLayout.EAST);

        Box southBox = new Box(BoxLayout.PAGE_AXIS);
        add(southBox, BorderLayout.SOUTH);
        
        buttonBarSouth = new ButtonBar(true, 120, 100, this);
        southBox.add(buttonBarSouth);
        
        statusBar = new StatusBar("", "Copyright M4J - IT", "");
        southBox.add(statusBar);

        buttonBarWest = new ButtonBar(false, 120, 100, this);
        add(buttonBarWest, BorderLayout.WEST);
    }
    
    protected final void addMenu(String text) {

        currentmenu = new JMenu(text);
        menuBar.add(currentmenu);
    }

    protected void addMenuItem(String text, String action) {

        JMenuItem tempItem = new JMenuItem(text.replaceAll("<br>", " "));
        tempItem.setActionCommand(action);
        tempItem.addActionListener(this);
        currentmenu.add(tempItem);

        menuitems.put(action, tempItem);
    }

    protected void addMenuSeparator() {

        currentmenu.addSeparator();
    }
    
    protected void addMenuItemToButtonBar(String action, int location) {

        JMenuItem item = menuitems.get(action);
    
        if(item != null){
            
            ButtonBar bb = getButtonBar(location);
            bb.addButton(item.getText(), action);
        }
    }
    
    protected void addButtonBarSeparator(int location) {

        getButtonBar(location).addGlue();
    }
    
    public ButtonBar getButtonBar(int location) {

        switch (location) {

            case NORTH:
                return buttonBarNorth;

            case EAST:
                return buttonBarEast;

            case SOUTH:
                return buttonBarSouth;

            case WEST:
                return buttonBarWest;
        }

        return null;
    }
    
    protected void addMenuItemToButtonBar(String text, String action, int location) {

        ButtonBar bb = getButtonBar(location);
        bb.addButton(text, action);
    }

    public static void giveNotification(String notification){

        statusBar.setText(StatusBar.LEFT, notification);
    }
    
    public void actionPerformed(ActionEvent ev) {

        giveNotification("");

        String ac = ev.getActionCommand();

        if (ac.equals("close")) {

            exitApp();
        } else if (ac.equals("updateprogram")) {

            updateProgram();
        } else if (ac.equals("showmanual")) {

            showManual();
        } else if (ac.equals("showabout")) {

            showAbout();
        } else {

            processActionCommand(ac);
        }
    }

    protected abstract void processActionCommand(String ac);

    protected void exitApp() {

        System.exit(0);
    }

    protected void updateProgram() {

        String installdir;
        installdir = Util.getInstalDir();
        //installdir = "/home/joris/test/";

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("M4J bestanden", new String[]{"m4j"}));
        int returnval = fileChooser.showOpenDialog(this);

        if (returnval == JFileChooser.APPROVE_OPTION) {

            try {

                File selectedFile = fileChooser.getSelectedFile();
                ZipFile zipFile = new ZipFile(selectedFile);
                Enumeration entries = zipFile.entries();

                while (entries.hasMoreElements()) {

                    ZipEntry entry = (ZipEntry) entries.nextElement();

                    if (entry.isDirectory()) {

                        File dirFile = new File(installdir + entry.getName());

                        if (!dirFile.exists()) {

                            dirFile.mkdir();
                        }
                    } else {

                        File file = new File(installdir + entry.getName());

                        if (file.exists()) {

                            file.delete();
                        }

                        InputStream in = zipFile.getInputStream(entry);
                        OutputStream out = new BufferedOutputStream(new FileOutputStream(installdir + entry.getName()));

                        byte[] buffer = new byte[1024];
                        int len = in.read(buffer);

                        while (len >= 0) {

                            out.write(buffer, 0, len);
                            len = in.read(buffer);
                        }

                        in.close();
                        out.close();
                    }
                }
                
                JOptionPane.showMessageDialog(null, "De update is geinstalleerd. Start het programma opnieuw op.", "Update geinstalleerd", JOptionPane.INFORMATION_MESSAGE);
                
            } catch (IOException ex) {

                //application.manageException(ex);
            }
        }
    }

    protected void showAbout() {

        JOptionPane.showMessageDialog(this, getAboutMessage());
    }

    protected void showManual() {

        try {

            Desktop desktop = Desktop.getDesktop();
            desktop.open(new File("files/manual.pdf")); 
        } catch (IOException ex) {

            JOptionPane.showMessageDialog(this, "Handleiding kon niet worden geopend.\n" + ex.getMessage());
        }
    }

    protected abstract String getAboutMessage();
}