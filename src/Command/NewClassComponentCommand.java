package Command;

import runner.Diagram;

public class NewClassComponentCommand implements ICommand{

	Diagram diagram;
    
    public NewClassComponentCommand(Diagram d) {
		this.diagram= d;
	}
	
	@Override
	public void execute() {
	    diagram.newClassComponent("Class name");
	}

	@Override
	public void undo() {
	}

}
