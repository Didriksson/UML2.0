package Command;

import runner.Diagram;
import ConstantsAndEnums.Constants;

public class NewRealisationComponentCommand implements ICommand {

	Diagram diagram;
	
    public NewRealisationComponentCommand(Diagram d) {
		this.diagram= d;
	}
	
	@Override
	public void execute() {
	    diagram.newRelation(Constants.REALISATION_STRING);
	}

	@Override
	public void undo() {
	}
}
