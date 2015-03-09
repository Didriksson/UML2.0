package Command;

import UML.Components.UMLComponent;
import ConstantsAndEnums.Constants;
import runner.Diagram;

public class NewClassComponentCommand extends ToolbarCommand {

	public NewClassComponentCommand(Diagram d) {
		super(d);
	}

	UMLComponent c;

	@Override
	public void execute() {
		if (c == null)
			c = diagram.newClassComponent(Constants.CLASSNAME_STRING);
		else
			diagram.addComponent(c);
	}

	@Override
	public void undo() {
		System.out.println(c);
		diagram.removeComponent(c);
	}

}
