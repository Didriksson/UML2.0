package Command;

import runner.Diagram;
import ConstantsAndEnums.Constants;

public class NewCompositionComponentCommand implements ICommand {

	Diagram diagram;
    
    public NewCompositionComponentCommand(Diagram d) {
		this.diagram= d;
	}
	
	@Override
	public void execute() {
	    diagram.newRelation(Constants.COMPOSITION_STRING);
	}

	@Override
	public void undo() {
	}
	
}
