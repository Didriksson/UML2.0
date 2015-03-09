package Command;

import UML.Components.UMLRelation;
import ConstantsAndEnums.Constants;
import runner.Diagram;

public class NewAssociationComponentCommand extends ToolbarCommand{
	
	UMLRelation r;
	
	public NewAssociationComponentCommand(Diagram d) {
		super(d);
	}
	@Override
	public void execute() {
		if(r == null)
			r = diagram.newRelation(Constants.ASSOCIATION_STRING);
		else
			diagram.addRelation(r);
	}

	@Override
	public void undo() {
		diagram.removeRelation(r);
	}
}
