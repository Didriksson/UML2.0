package Controller;

import java.util.Map;
import java.util.Observer;

import runner.Diagram;
import Command.ICommand;
import Command.NewClassComponentCommand;
import Command.NewClassVariableCommand;
import Command.NewDestinationForRelationCommand;
import Command.NewMethodCommand;
import Command.NewRootForRelationCommand;
import Command.RemoveClassComponentCommand;
import Command.RemoveRelationCommand;
import ConstantsAndEnums.Enums;
import UML.Components.UMLComponent;
import UML.Components.UMLMethod;
import UML.Components.UMLRelation;

public class UMLDrawAreaController{
    private Diagram diagram;
    Map<Enums, ICommand> toolbarCommands;

    public UMLDrawAreaController(Diagram d, Map<Enums, ICommand>commands) {
	this.diagram = d;
	this.toolbarCommands = commands;
    }

    public void addUMLComponent(UMLComponent c) {
	diagram.addComponent(c);
    }

    public void newClass() {
	new NewClassComponentCommand(diagram).execute();
    }

    public void registerMeAsObserver(Observer o) {
	diagram.addObserver(o);
    }
    
    public void removeComponent(UMLComponent c) {
	new RemoveClassComponentCommand(diagram, c).execute();
    }
    
    
    public void toolbarCommands(Enums command){
    	toolbarCommands.get(command).execute();
    }

	public void removeRelation(UMLRelation r) {
		new RemoveRelationCommand(diagram, r).execute();
	}

	public void setDestinationForRelation(UMLRelation rel, UMLComponent c) {
		new NewDestinationForRelationCommand(diagram, rel, c).execute();
	}

	public void setRootForRelation(UMLRelation rel, UMLComponent c) {
		new NewRootForRelationCommand(diagram, rel, c).execute();
	}

	public void addMethod(UMLComponent c) {
	    new NewMethodCommand(diagram, c).execute();
	}

	public void addVariable(UMLComponent c) {
	    new NewClassVariableCommand(diagram, c).execute();
	}

}
