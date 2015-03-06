package Command;

import runner.Diagram;
import UML.Components.UMLComponent;
import UML.Components.UMLMethod;

public class NewMethodCommand implements ICommand {
    Diagram d;
    UMLComponent c;
    UMLMethod m;

    public NewMethodCommand(Diagram d, UMLComponent c) {
	this.d = d;
	this.c = c;
    }

    @Override
    public void execute() {
	m = new UMLMethod("public", "void", "MethodName");
	try {
	    d.addMethod(c, m);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    @Override
    public void undo() {
	// TODO Auto-generated method stub

    }

}
