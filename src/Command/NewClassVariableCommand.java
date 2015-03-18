package Command;

import runner.Diagram;
import UML.Components.UMLClassVariable;
import UML.Components.UMLComponent;
import UML.Components.UMLVariable;

public class NewClassVariableCommand implements ICommand {
	private UMLComponent c;
	private UMLClassVariable v;
	private Diagram d;
	public NewClassVariableCommand(Diagram d, UMLComponent c) {
		this.c = c;
		this.d = d;
	}

	@Override
	public void execute() {
		if(this.v == null)
			this.v = new UMLClassVariable("public", "String", "var");
		try {
			c.addVariable(v);
			d.signalUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void undo() {
		c.removeVariable(v);
		d.signalUpdate();
	}

}
