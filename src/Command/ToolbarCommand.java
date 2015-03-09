package Command;

import runner.Diagram;

public abstract class ToolbarCommand implements ICommand{

	Diagram diagram;
	
	public ToolbarCommand(Diagram d) {
		this.diagram = d;
	}
}
