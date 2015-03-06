package GUI_View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import ConstantsAndEnums.Constants;
import ConstantsAndEnums.Enums;

public class ToolbarUML extends JToolBar {
    private static final long serialVersionUID = 1L;

    private FigureViewingPanel figureViewingPanel;

    public ToolbarUML(FigureViewingPanel figureViewingPanel) {
	super("Toolbar", JToolBar.VERTICAL);
	this.setBorder(Constants.RAISED_BEVEL_BORDER);
	this.setFloatable(false);
	this.figureViewingPanel = figureViewingPanel;
	addButtonsToToolbar(this);
	this.setFocusable(false);
    }

    public void addButtonsToToolbar(JToolBar jtbToolBar) {
	JButton jbnToolbarButtons = null;

	jbnToolbarButtons = buttonSettings(Constants.CLASS_ICON,
		Constants.CLASSNAME_STRING);
	jbnToolbarButtons.addActionListener(new MyListerner(Enums.CLASS_ENUM));
	jtbToolBar.add(jbnToolbarButtons);

	jbnToolbarButtons = buttonSettings(Constants.ASSOCIATION_ICON,
		Constants.ASSOCIATION_STRING);
	jbnToolbarButtons.addActionListener(new MyListerner(
		Enums.ASSOCIATION_ENUM));
	jtbToolBar.add(jbnToolbarButtons);

	jbnToolbarButtons = buttonSettings(Constants.DIRECT_ASSOCIATION_ICON,
		Constants.DIRECT_ASSOCIATION_STRING);
	jbnToolbarButtons.addActionListener(new MyListerner(
		Enums.DIRECT_ASSOCIATION_ENUM));
	jtbToolBar.add(jbnToolbarButtons);

	jbnToolbarButtons = buttonSettings(Constants.INHERITANCE_ICON,
		Constants.INHERITANCE_STRING);
	jbnToolbarButtons.addActionListener(new MyListerner(
		Enums.INHERITANCE_ENUM));
	jtbToolBar.add(jbnToolbarButtons);

	jbnToolbarButtons = buttonSettings(Constants.DEPENDENCY_ICON,
		Constants.DEPENDENCY_STRING);
	jbnToolbarButtons.addActionListener(new MyListerner(
		Enums.DEPENDENCY_ENUM));
	jtbToolBar.add(jbnToolbarButtons);

	jbnToolbarButtons = buttonSettings(Constants.REALIZATION_ICON,
		Constants.REALISATION_STRING);
	jbnToolbarButtons.addActionListener(new MyListerner(
		Enums.REALIZATION_ENUM));
	jtbToolBar.add(jbnToolbarButtons);

	jbnToolbarButtons = buttonSettings(Constants.AGGREGATION_ICON,
		Constants.AGGREGATION_STRING);
	jbnToolbarButtons.addActionListener(new MyListerner(
		Enums.AGGREGATION_ENUM));
	jtbToolBar.add(jbnToolbarButtons);

	jbnToolbarButtons = buttonSettings(Constants.COMPOSITION_ICON,
		Constants.COMPOSITION_STRING);
	jbnToolbarButtons.addActionListener(new MyListerner(
		Enums.COMPOSITION_ENUM));
	jtbToolBar.add(jbnToolbarButtons);

	jbnToolbarButtons = buttonSettings(Constants.REDO_ICON,
		Constants.REDO_STRING);
	jbnToolbarButtons.addActionListener(e -> redoLastCommand());
	jtbToolBar.add(jbnToolbarButtons);
	
	jbnToolbarButtons = buttonSettings(Constants.UNDO_ICON,
		Constants.UNDO_STRING);
	jbnToolbarButtons.addActionListener(e -> undoLastCommand());
	jtbToolBar.add(jbnToolbarButtons);
	

    }

    private void redoLastCommand() {
	figureViewingPanel.redoCommand();
    }

    private void undoLastCommand() {
	figureViewingPanel.undoCommand();
    }

    private JButton buttonSettings(ImageIcon icon, String tipText) {
	JButton jbnToolbarButtons;
	jbnToolbarButtons = new JButton(icon);
	jbnToolbarButtons.setFocusPainted(false);
	jbnToolbarButtons.setToolTipText(tipText);

	return jbnToolbarButtons;
    }

    private class MyListerner implements ActionListener {

	private Enums enumeration;

	public MyListerner(Enums enums) {
	    this.enumeration = enums;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    figureViewingPanel.toolbarCommand(enumeration);
	}
    }

}
