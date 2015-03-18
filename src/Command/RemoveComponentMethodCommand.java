package Command;

import runner.Diagram;
import UML.Components.UMLComponent;
import UML.Components.UMLMethod;

public class RemoveComponentMethodCommand implements ICommand {

	private UMLComponent component;
	private UMLMethod e;
	private int index;
	
	public RemoveComponentMethodCommand(UMLComponent component, int index) {
		this.component = component;
		this.index = index;
	}
	
	@Override
	public void execute() {
		component.getMethods().remove(index);
		
	}

	@Override
	public void undo() {
		
	}

}
