package GUI_View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import UML.Components.UMLComponentsFactory;
import runner.Diagram;
import runner.ViewFactory;
import ConstantsAndEnums.Constants;
import Controller.UMLDrawAreaController;

public class WindowFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private JMenuBar menuBAR;
	private JMenu menuFILE, menuEDIT, menuSAVE;
	private JMenuItem itemEXIT, subMenuSave, subMenuOpen, subMenuNew;

	public WindowFrame() {
		this.setSize(800, 800);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

		createComponents();
	}

	private void createComponents() {
		menuBAR = new JMenuBar();

		menuFILE = new JMenu("File");
		menuEDIT = new JMenu("Edit");

		subMenuNew = new JMenuItem("New diagram");
		subMenuOpen = new JMenuItem("Open File", Constants.OPEN_FILE_ICON);

		subMenuOpen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ViewFactory.loadFromDiagram();
			}

		});

		subMenuSave = new JMenuItem("Save File As", Constants.SAVE_FILE_ICON);

		subMenuSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ViewFactory.saveCurrentState();
			}
		});

		itemEXIT = new JMenuItem("Exit");

		setComponents();
	}

	private void setComponents() {

		menuFILE.add(subMenuNew);
		menuFILE.add(subMenuOpen);
		menuFILE.add(subMenuSave);
		menuFILE.add(itemEXIT);

		menuBAR.add(menuFILE);
		menuBAR.add(menuEDIT);

		this.setJMenuBar(menuBAR);
		this.setContentPane(new LayoutPanel());

	}

	private void updateContentPane() {
		this.removeAll();
		this.setComponents();
	}

}
