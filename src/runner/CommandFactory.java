package runner;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
import ConstantsAndEnums.Enums;

public class CommandFactory {

	private static Map<Enums, Class> commands;

	static {
		// Stoppa bara in ToolbarCommand-objekt här!! Hittar ingen elegant enkel
		// lösning för att typa denna.

		commands = new HashMap<Enums, Class>();
		commands.put(ConstantsAndEnums.Enums.CLASS_ENUM,
				NewClassComponentCommand.class);
		commands.put(ConstantsAndEnums.Enums.ASSOCIATION_ENUM,
				NewAssociationComponentCommand.class);
		commands.put(ConstantsAndEnums.Enums.DIRECT_ASSOCIATION_ENUM,
				NewDirectAssociationComponentCommand.class);
		commands.put(ConstantsAndEnums.Enums.INHERITANCE_ENUM,
				NewInheritanceComponentCommand.class);
		commands.put(ConstantsAndEnums.Enums.DEPENDENCY_ENUM,
				NewDependencyComponentCommand.class);
		commands.put(ConstantsAndEnums.Enums.REALIZATION_ENUM,
				NewRealisationComponentCommand.class);
		commands.put(ConstantsAndEnums.Enums.AGGREGATION_ENUM,
				NewAggregationComponentCommand.class);
		commands.put(ConstantsAndEnums.Enums.COMPOSITION_ENUM,
				NewCompositionComponentCommand.class);
	}

	public static ICommand getToolbarCommand(Enums commandName, Diagram d) {
		
		Class command = commands.get(commandName);
		ICommand commandInstance = null;
		try {
			Constructor<?> constructor = command.getConstructor(Diagram.class);
			commandInstance = (ICommand) constructor.newInstance(d);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return commandInstance;
	}

}
