package nl.m4jit.tradetoday.presentation.statistics;

import java.awt.Dimension;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import nl.m4jit.framework.presentation.swing.abstractdialogs.*;
import nl.m4jit.framework.presentation.swing.improvedcomponents.*;
import nl.m4jit.tradetoday.domainlogic.statistics.*;

public class StatisticsIFrame extends ButtonBarDialog {

    private ITaskPaneContainer taskPaneContainer;
    private ITable table;
    private StatisticsModule module;

    public StatisticsIFrame() {

        module = StatisticsModule.getInstance();

        taskPaneContainer = new ITaskPaneContainer(this);
        addToWest(taskPaneContainer);

        for (QueryCategory category : module.getCategories()) {

            taskPaneContainer.addTaskPane(category.getName(), category.getText());

            for (Query query : category.getQueries().values()) {

                taskPaneContainer.addTaskPaneItem(query.getText(), query.getName());
            }
        }

        table = new ITable(module.getResultSetModel());
        table.getPane().setPreferredSize(new Dimension(750, 600));
        addToCenter(table.getPane());

        addButton("Toevoegen", "add", RIGHT);
        addButton("Exporteren", "export", RIGHT);

        setUI();
    }

    public void updateTable() {

        table.updateUI();
    }

    @Override
    public String getScreenTitle() {

        return "Statistieken";
    }

    @Override
    protected void processActionCommand(String ac) {

        if (ac.equals("add")) {

            module.addQuery();
        } else if (ac.equals("export")) {

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Excel bestand", "xls"));
            fileChooser.setAcceptAllFileFilterUsed(false);

            int returnval = fileChooser.showSaveDialog(this);
            
            if (returnval == JFileChooser.APPROVE_OPTION) {
            
                File file = fileChooser.getSelectedFile();
                module.export(file);
            }
        } else {

            module.processQuery(ac);
            updateTable();
        }
    }
}
