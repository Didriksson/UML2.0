package Command;

import UML.Components.UMLRelation;
import runner.Diagram;
import ConstantsAndEnums.Constants;

public class NewDirectAssociationComponentCommand extends ToolbarCommand {

	public NewDirectAssociationComponentCommand(Diagram dia) {
		super(dia);
	}

	private UMLRelation r;
	@Override
	public void execute() {
		if(r == null)
			r = diagram.newRelation(Constants.DIRECT_ASSOCIATION_STRING);
		else
			diagram.addRelation(r);
	}

	@Override
	public void undo() {
		diagram.removeRelation(r);
	}


}
