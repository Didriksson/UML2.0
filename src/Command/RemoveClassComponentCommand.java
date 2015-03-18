package Command;

import runner.Diagram;
import UML.Components.UMLComponent;

public class RemoveClassComponentCommand implements ICommand {
	private Diagram diagram;
	private UMLComponent component;

	public RemoveClassComponentCommand(Diagram d, UMLComponent c) {
		this.diagram = d;
		this.component = c;
	}

	@Override
	public void execute() {
		diagram.removeComponent(component);
	}

	@Override
	public void undo() {
		diagram.addComponent(component);
	}

}
