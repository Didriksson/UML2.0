package runner;

import java.util.HashMap;
import java.util.Map;

import Command.ICommand;
import Command.NewAssociationComponentCommand;
import Command.NewClassComponentCommand;
import Command.RemoveClassComponentCommand;
import ConstantsAndEnums.Enums;
import Controller.UMLDrawAreaController;
import Figures.FigureViewingPanel;

public class ViewFactory {
	private static FigureViewingPanel currentPanel;
	private static UMLDrawAreaController controller;

	public static UMLDrawAreaController getUMLDrawController() {
		if (controller == null) {
			Diagram d = new Diagram();
			controller = new UMLDrawAreaController(d, getCommands(d));
		}
		return controller;
	}

	private static Map<Enums, ICommand> getCommands(Diagram d) {
		Map<Enums, ICommand> commands = new HashMap<Enums, ICommand>();
		commands.put(ConstantsAndEnums.Enums.CLASS_ENUM, new NewClassComponentCommand(d));
		commands.put(ConstantsAndEnums.Enums.ASSOCIATION_ENUM, new NewAssociationComponentCommand(d));

		return commands;
	}

	public static FigureViewingPanel getFigureViewingPanel() {
		if (currentPanel == null)
			currentPanel = new FigureViewingPanel(getUMLDrawController());
		return currentPanel;
	}
}
