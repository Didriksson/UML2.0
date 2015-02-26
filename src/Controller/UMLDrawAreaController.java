package Controller;

import java.util.Map;
import java.util.Observer;

import runner.Diagram;
import Command.ICommand;
import Command.NewClassComponentCommand;
import Command.RemoveClassComponentCommand;
import ConstantsAndEnums.Enums;
import UML.Components.UMLComponent;

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

}
