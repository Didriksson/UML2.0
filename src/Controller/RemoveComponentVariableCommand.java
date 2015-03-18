package Controller;

import UML.Components.UMLComponent;
import UML.Components.UMLMethod;
import Command.ICommand;

public class RemoveComponentVariableCommand implements ICommand {

	private UMLComponent component;
	private UMLMethod e;
	private int index;
	
	public RemoveComponentVariableCommand(UMLComponent component, int index) {
		this.component = component;
		this.index = index;	
	}
	
	@Override
	public void execute() {
		component.getVariables().remove(index);
	}

	@Override
	public void undo() {
	}

}
