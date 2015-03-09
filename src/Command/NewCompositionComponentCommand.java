package Command;

import UML.Components.UMLRelation;
import runner.Diagram;
import ConstantsAndEnums.Constants;

public class NewCompositionComponentCommand extends ToolbarCommand {

	private UMLRelation r;

	public NewCompositionComponentCommand(Diagram d) {
		super(d);
	}

	@Override
	public void execute() {
		if(r == null)
			r = diagram.newRelation(Constants.COMPOSITION_STRING);
		else
			diagram.addRelation(r);
	}

	@Override
	public void undo() {
		diagram.removeRelation(r);
	}

}
