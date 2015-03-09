package Command;

import UML.Components.UMLRelation;
import runner.Diagram;
import ConstantsAndEnums.Constants;

public class NewRealisationComponentCommand extends ToolbarCommand {

	
	public NewRealisationComponentCommand(Diagram d) {
		super(d);
	}


	private UMLRelation r;
	@Override
	public void execute() {
		if(r == null)
			r = diagram.newRelation(Constants.REALISATION_STRING);
		else
			diagram.addRelation(r);
	}

	@Override
	public void undo() {
		diagram.removeRelation(r);
	}

}
