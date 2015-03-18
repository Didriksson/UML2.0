package Command;

import runner.Diagram;
import UML.Components.UMLComponent;
import UML.Components.UMLMethod;

public class NewMethodCommand implements ICommand {
	private Diagram d;
	private UMLComponent c;
	private UMLMethod m;

	public NewMethodCommand(Diagram d, UMLComponent c) {
		this.d = d;
		this.c = c;
	}

	@Override
	public void execute() {
		if(m == null)
			m = new UMLMethod("public", "void", "MethodName");
		try {
			d.addMethod(c, m);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void undo() {
		c.removeMethod(m);
		d.signalUpdate();
	}

}
