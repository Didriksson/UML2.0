package runner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observer;

import Command.ICommand;
import Command.NewClassComponentCommand;
import UML.Components.UMLComponent;

public class UMLDrawAreaController{
    private Diagram diagram;
    Map<String, ICommand> commands;
    
    
    
    public UMLDrawAreaController() {
	this.diagram = new Diagram();
	commands = new HashMap<String, ICommand>();
	commands.put("NewClass", new NewClassComponentCommand(diagram));
    }

    public void addUMLComponent(UMLComponent c) {
	diagram.addComponent(c);
    }
    
    public void removeUMLComponent(UMLComponent c) {
    }

    public void newClass() {
		commands.get("NewClass").execute();
	}

	public void registerMeAsObserver(Observer o) {
		diagram.addObserver(o);
	}

}
