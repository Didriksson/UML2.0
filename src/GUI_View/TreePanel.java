package GUI_View;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;

import ConstantsAndEnums.Constants;
import net.miginfocom.swing.MigLayout;

public class TreePanel extends JPanel {

    private JTree tree;

    public TreePanel() {
        this.initialize();
        this.build();
        this.configure();
    }

    public void initialize() {
		this.setLayout(new MigLayout("fill","grow","grow"));
		this.setBorder(Constants.RAISED_BEVEL_BORDER);
        this.tree = new JTree();
    }

    public void build() {
        this.add(this.tree);
    }

    public void configure() {

        this.tree.setModel(new FileSystemModel(new File("C:\\Users\\"+System.getenv("USERNAME")+"\\Desktop")));
    
        JScrollPane scrollpane = new JScrollPane(tree);
        
        Dimension dim = scrollpane.getPreferredSize();
        dim.width = 200;
        
        scrollpane.setPreferredSize(dim);
       
        add(scrollpane, "dock north");
        
    }
}