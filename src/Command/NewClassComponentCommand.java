package Command;

import UML.Components.UMLComponent;
import runner.ComponentFactory;
import runner.Diagram;
import runner.UMLDrawAreaController;

public class NewClassComponentCommand implements ICommand{

	private UMLDrawAreaController controller;
	private UMLComponent component;
	
	public NewClassComponentCommand(UMLDrawAreaController c) {
		this.controller = c;
	}
	
	@Override
	public void execute() {
		UMLComponent c = ComponentFactory.getClassComponent();
		this.component = c;
		controller.addUMLComponent(component);
	}

	@Override
	public void undo() {
//		controller.removeUMLComponent(component);
	}

}
