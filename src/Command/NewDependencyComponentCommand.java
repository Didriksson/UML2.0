package Command;

import ConstantsAndEnums.Constants;
import runner.Diagram;

public class NewDependencyComponentCommand implements ICommand {

	Diagram diagram;
    
    public NewDependencyComponentCommand(Diagram d) {
		this.diagram= d;
	}
	
	@Override
	public void execute() {
	    diagram.newClassComponent(Constants.DEPENDENCY_STRING);
	}

	@Override
	public void undo() {
	}
	
}
