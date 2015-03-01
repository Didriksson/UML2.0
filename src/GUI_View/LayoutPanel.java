package GUI_View;

import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.plaf.basic.BasicSplitPaneUI;

import net.miginfocom.swing.MigLayout;
import runner.ViewFactory;
import ConstantsAndEnums.Constants;

public class LayoutPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private JSplitPane splitPane;
	private TreePanel treePanel;
	private JPanel figurePanel;
	private JPanel buttonPanel;
	private JButton minimizeButton;

	public LayoutPanel() {
		this.setLayout(new MigLayout("", "[grow, fill]", "[grow]"));

		createComponents();

		this.setBorder(Constants.RAISED_BEVEL_BORDER);
	}

	private void createComponents() {
		treePanel = new TreePanel();
		figurePanel = ViewFactory.getFigureViewingPanel();
		buttonPanel = new JPanel(new MigLayout("", "", ""));

		minimizeButton = new JButton();

		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treePanel,
				figurePanel);
		

		setComponents();
	}

	private void setComponents() {
		buttonPanel.setBorder(Constants.RAISED_BEVEL_BORDER);
		splitPane.setOneTouchExpandable(true);
		
		minimizeButton = buttonSettings(Constants.MINIMIZE_ICON,
				Constants.MINIMIZE_STRING);
		minimizeButton.addActionListener(arg0 -> {
			setDividerToOne();
		});
		buttonPanel.add(minimizeButton);
		
		
		

		this.add(buttonPanel, "north");
		this.add(splitPane, "grow");
	}

	private void setDividerToOne() {
		BasicSplitPaneUI ui = (BasicSplitPaneUI) splitPane.getUI();
		Container divider = ui.getDivider();
		JButton miniButton = (JButton) divider.getComponent(0);
		miniButton.doClick();
	}

	private JButton buttonSettings(ImageIcon icon, String tipText) {
		JButton button;
		button = new JButton(icon);
		button.setFocusPainted(false);
		button.setSize(20, 20);
		button.setToolTipText(tipText);
		return button;
	}
}
