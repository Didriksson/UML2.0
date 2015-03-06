package Command;

import runner.Diagram;
import UML.Components.UMLClassVariable;
import UML.Components.UMLComponent;
import UML.Components.UMLVariable;

public class NewClassVariableCommand implements ICommand {
    Diagram d;
    UMLComponent c;
    UMLClassVariable v;

    public NewClassVariableCommand(Diagram d, UMLComponent c) {
	this.d = d;
	this.c = c;
    }
    @Override
    public void execute() {
	this.v = new UMLClassVariable("public", "String", "var");
	d.addClassVariable(c, v);
    }

    @Override
    public void undo() {
	// TODO Auto-generated method stub

    }

}
