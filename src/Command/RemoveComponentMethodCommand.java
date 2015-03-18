package Command;

import runner.Diagram;
import UML.Components.UMLComponent;
import UML.Components.UMLMethod;

public class RemoveComponentMethodCommand implements ICommand {

	private UMLComponent component;
	private UMLMethod e;
	private Diagram d;
	private int index;
	
	public RemoveComponentMethodCommand(Diagram d, UMLComponent component, int index) {
		this.component = component;
		this.index = index;
		this.d = d;
	}
	
	@Override
	public void execute() {
		this.e = component.getMethods().remove(index);
		d.signalUpdate();
	}

	@Override
	public void undo() {
		try {
			component.addMethod(e);
			d.signalUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
