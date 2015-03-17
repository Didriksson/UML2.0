package GUI_View;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import Figures.Graphics.AssociationFigure;

public class AssocationMenu extends JPanel {

	public AssocationMenu(AssociationFigure selectedComponent) {
		this.setLayout(new MigLayout("wrap 2", "[grow, fill]", ""));

		JButton updateButton = new JButton("Update");
		JLabel rootLabel = new JLabel("rootmulty");
		JLabel destLabel = new JLabel("destmulty");
		JTextField textfieldRoot = new JTextField();
		JTextField textfieldDest = new JTextField();

		textfieldRoot.setText(selectedComponent.getRootMulString());
		textfieldDest.setText(selectedComponent.getDestinationMulString());

		add(rootLabel);
		add(textfieldRoot);
		add(destLabel);
		add(textfieldDest);
		add(updateButton);

		updateButton.addActionListener(e -> {
			selectedComponent.updateMultiplicites(textfieldRoot.getText(),
					textfieldDest.getText());
		});

	}

}
