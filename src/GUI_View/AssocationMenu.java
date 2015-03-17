package GUI_View;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.junit.experimental.theories.internal.Assignments;

import net.miginfocom.swing.MigLayout;
import ConstantsAndEnums.Constants;
import Figures.Graphics.AssociationFigure;

public class AssocationMenu extends JPanel {

	private JButton updateButton;
	private JLabel rootLabel, destLabel;
	private JTextField textfieldRoot, textfieldDest; 
	private AssociationFigure selectedFigure;
	private JPanel figureSettingPanel;
	
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
		
		updateButton = new JButton("Update");
		
		rootLabel = new JLabel("Root Multiplicities");
		destLabel = new JLabel("Desitnation Multiplicities");

		textfieldRoot = new JTextField();
		textfieldDest = new JTextField();
		
		
		manipulateComponents();
	}

	private void manipulateComponents() {
		textfieldRoot.setText(selectedFigure.getRootMulString());
		textfieldDest.setText(selectedFigure.getDestinationMulString());
		
		figureSettingPanel.add(rootLabel);
		figureSettingPanel.add(textfieldRoot);
		figureSettingPanel.add(destLabel);
		figureSettingPanel.add(textfieldDest);
		figureSettingPanel.add(updateButton);
		
		this.add(figureSettingPanel, "dock west");
		
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
		return panel;
	}
	

}
