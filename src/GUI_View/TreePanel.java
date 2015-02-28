package GUI_View;
import java.awt.Dimension;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;

import net.miginfocom.swing.MigLayout;
import ConstantsAndEnums.Constants;

public class TreePanel extends JPanel {

    private JTree tree;
    
    public TreePanel() {
        this.initialize();
        this.configure();
    }

    public void initialize() {
		this.setLayout(new MigLayout("","[grow, fill]","grow"));
		this.setBorder(Constants.RAISED_BEVEL_BORDER);
        this.tree = new JTree();
    }

    public void configure() {
    	    	
    	
        tree.setModel(new FileSystemModel(new File("C:\\Users\\"+System.getenv("USERNAME")+"\\Desktop")));
    
        JScrollPane scrollpane = new JScrollPane(tree);
        
        Dimension dim = scrollpane.getPreferredSize();
        dim.width = 250;
        
        scrollpane.setPreferredSize(dim);
       
        this.add(scrollpane, "north");
        
    }
    
}