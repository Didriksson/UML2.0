package Command;

import runner.Diagram;
import UML.Components.UMLClassVariable;
import UML.Components.UMLComponent;
import UML.Components.UMLVariable;

public class NewClassVariableCommand implements ICommand {
	private Diagram d;
	private UMLComponent c;
	private UMLClassVariable v;

	public NewClassVariableCommand(Diagram d, UMLComponent c) {
		this.d = d;
		this.c = c;
	}

	@Override
	public void execute() {
		this.v = new UMLClassVariable("public", "String", "var");
		try {
			d.addClassVariable(c, v);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub

	}

}
