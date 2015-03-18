package Command;

import runner.Diagram;
import UML.Components.UMLClassVariable;
import UML.Components.UMLComponent;
import UML.Components.UMLMethod;

public class RemoveComponentVariableCommand implements ICommand {

	private UMLComponent component;
	private Diagram d;
	private UMLClassVariable v;
	private int index;
	
	public RemoveComponentVariableCommand(Diagram d, UMLComponent component, int index) {
		this.d = d;
		this.component = component;
		this.index = index;	
	}
	
	@Override
	public void execute() {
		if(component.getVariables().size() > 0)
			this.v = component.getVariables().remove(index);
		d.signalUpdate();
	}

	@Override
	public void undo() {
		try {
			component.addVariable(v);
			d.signalUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
