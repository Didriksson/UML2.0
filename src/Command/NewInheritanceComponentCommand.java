package Command;

import UML.Components.UMLRelation;
import runner.Diagram;
import ConstantsAndEnums.Constants;

public class NewInheritanceComponentCommand extends ToolbarCommand {

	public NewInheritanceComponentCommand(Diagram d) {
		super(d);
	}


	private UMLRelation r;
	@Override
	public void execute() {
		if(r == null)
			r = diagram.newRelation(Constants.INHERITANCE_STRING);
		else
			diagram.addRelation(r);
	}

	@Override
	public void undo() {
		diagram.removeRelation(r);
	}

}
