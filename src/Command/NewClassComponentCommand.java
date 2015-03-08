package Command;

import UML.Components.UMLComponent;
import ConstantsAndEnums.Constants;
import runner.Diagram;

public class NewClassComponentCommand implements ICommand{

	Diagram diagram;
	UMLComponent c;
    
    public NewClassComponentCommand(Diagram d) {
		this.diagram= d;
	}
	
	@Override
	public void redo() {
	    c = diagram.newClassComponent(Constants.CLASSNAME_STRING);
	}

	@Override
	public void undo() {
	    diagram.removeComponent(c);
	}

}
