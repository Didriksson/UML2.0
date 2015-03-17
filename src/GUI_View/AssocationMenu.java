package GUI_View;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.junit.experimental.theories.internal.Assignments;

import net.miginfocom.swing.MigLayout;
import ConstantsAndEnums.Constants;
import Figures.ClassFigure;
import Figures.GUIComponent;
import Figures.Graphics.AssociationFigure;

public class AssocationMenu extends JPanel {
	private static final long serialVersionUID = 1L;

	private AssociationFigure selectedFigure;

	private JPanel figureSettingPanel, rootPanel, destPanel;;
	private JTextField textfieldRoot, textfieldDest;
	private JLabel rootLabelMulti, destLabelMulti, rootLabel, destinationLabel;
	private JButton updateButton;

	private String root = "root";
	private String destination = "destination";

	public AssocationMenu(AssociationFigure selectedFigure) {
		this.selectedFigure = selectedFigure;
		init();
	}

	private void init() {
		this.setLayout(new MigLayout("", "[grow, fill]", "grow"));
		this.setBorder(Constants.LOWERED_BEVEL_BORDER);
		createComponents();
	}

	private void createComponents() {
		figureSettingPanel = panelSetup("Figure Settings:");
		rootPanel = panelSetup("Root:");
		destPanel = panelSetup("Destination:");

		updateButton = new JButton("Update");

		rootLabelMulti = new JLabel("Root Multiplicities");
		destLabelMulti = new JLabel("Desitnation Multiplicities");
		rootLabel = labelSetup(root);
		destinationLabel = labelSetup(destination);

		textfieldRoot = new JTextField();
		textfieldDest = new JTextField();

		manipulateComponents();
	}

	private void manipulateComponents() {
		textfieldRoot.setText(selectedFigure.getRootMulString());
		textfieldDest.setText(selectedFigure.getDestinationMulString());

		if (selectedFigure.getRootComponent() != null) {
			root = selectedFigure.getRootComponent().getName();
			rootLabel.setText(root);
		}

		if (selectedFigure.getDestinationComponent() != null) {
			destination = selectedFigure.getDestinationComponent().getName();
			destinationLabel.setText(destination);
		}

		rootPanel.add(rootLabel);
		destPanel.add(destinationLabel);

		figureSettingPanel.add(rootLabelMulti);
		figureSettingPanel.add(textfieldRoot);
		figureSettingPanel.add(destLabelMulti);
		figureSettingPanel.add(textfieldDest);
		figureSettingPanel.add(updateButton);

		this.add(rootPanel, "dock west");
		this.add(figureSettingPanel, "dock west");
		this.add(destPanel, "dock west");

		updateButton.addActionListener(e -> {
			selectedFigure.updateMultiplicites(textfieldRoot.getText(),
					textfieldDest.getText());
		});
	}

	private JPanel panelSetup(String title) {
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout("wrap", "[grow, fill]", "grow"));
		TitledBorder titledBorder = BorderFactory.createTitledBorder(title);
		panel.setBorder(titledBorder);
		panel.setPreferredSize(new Dimension(200,200));
		return panel;
	}
	
	private JLabel labelSetup(String title) {
		JLabel label = new JLabel(title);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		label.setFont(new Font("Courier New", Font.BOLD, 15));
		return label;
	}
	

}
