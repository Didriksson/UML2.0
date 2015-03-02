package runner;

import java.util.HashMap;
import java.util.Map;

import Command.ICommand;
import Command.NewAggregationComponentCommand;
import Command.NewAssociationComponentCommand;
import Command.NewClassComponentCommand;
import Command.NewCompositionComponentCommand;
import Command.NewDependencyComponentCommand;
import Command.NewDirectAssociationComponentCommand;
import Command.NewInheritanceComponentCommand;
import Command.NewRealisationComponentCommand;
import Command.RemoveClassComponentCommand;
import ConstantsAndEnums.Enums;
import Controller.UMLDrawAreaController;
import GUI_View.FigureViewingPanel;

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
		commands.put(ConstantsAndEnums.Enums.DIRECT_ASSOCIATION_ENUM, new NewDirectAssociationComponentCommand(d));
		commands.put(ConstantsAndEnums.Enums.INHERITANCE_ENUM, new NewInheritanceComponentCommand(d));
		commands.put(ConstantsAndEnums.Enums.DEPENDENCY_ENUM, new NewDependencyComponentCommand(d));
		commands.put(ConstantsAndEnums.Enums.REALIZATION_ENUM, new NewRealisationComponentCommand(d));
		commands.put(ConstantsAndEnums.Enums.AGGREGATION_ENUM, new NewAggregationComponentCommand(d));
		commands.put(ConstantsAndEnums.Enums.COMPOSITION_ENUM, new NewCompositionComponentCommand(d));

		return commands;
	}

	public static FigureViewingPanel getFigureViewingPanel() {
		if (currentPanel == null)
			currentPanel = new FigureViewingPanel(getUMLDrawController());
		return currentPanel;
	}
}
