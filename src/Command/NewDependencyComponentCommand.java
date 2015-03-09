package Command;

import UML.Components.UMLRelation;
import ConstantsAndEnums.Constants;
import runner.Diagram;

public class NewDependencyComponentCommand extends ToolbarCommand {



	public NewDependencyComponentCommand(Diagram d) {
		super(d);
	}

	private UMLRelation r;
	@Override
	public void execute() {
		if(r == null)
			r = diagram.newRelation(Constants.DEPENDENCY_STRING);
		else
			diagram.addRelation(r);
	}

	@Override
	public void undo() {
		diagram.removeRelation(r);
	}

}
