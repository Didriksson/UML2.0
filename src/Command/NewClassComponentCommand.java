package Command;

import ConstantsAndEnums.Constants;
import runner.Diagram;

public class NewClassComponentCommand implements ICommand{

	Diagram diagram;
    
    public NewClassComponentCommand(Diagram d) {
		this.diagram= d;
	}
	
	@Override
	public void execute() {
	    diagram.newClassComponent(Constants.CLASSNAME_STRING);
	}

	@Override
	public void undo() {
	}

}
