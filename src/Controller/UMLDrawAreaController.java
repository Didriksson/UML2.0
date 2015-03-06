package Controller;

import java.util.Map;
import java.util.Observer;
import java.util.Stack;

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
import UML.Components.UMLRelation;

public class UMLDrawAreaController {
    private Diagram diagram;
    Map<Enums, ICommand> toolbarCommands;
    Stack<ICommand> executedCommands;
    Stack<ICommand> revertedCommands;

    public UMLDrawAreaController(Diagram d, Map<Enums, ICommand> commands) {
	this.diagram = d;
	this.toolbarCommands = commands;
	executedCommands = new Stack<ICommand>();
	revertedCommands = new Stack<ICommand>();
    }

    public void registerMeAsObserver(Observer o) {
	diagram.addObserver(o);
    }

    public void removeComponent(UMLComponent c) {
	ICommand command = new RemoveClassComponentCommand(diagram, c);
	executedCommands.push(command);
	command.execute();
    }

    public void toolbarCommands(Enums command) {
	ICommand cmd = toolbarCommands.get(command);
	executedCommands.push(cmd);
	cmd.execute();
    }

    public void removeRelation(UMLRelation r) {
	ICommand command = new RemoveRelationCommand(diagram, r);
	executedCommands.push(command);
	command.execute();
    }

    public void setDestinationForRelation(UMLRelation rel, UMLComponent c) {
	ICommand command = new NewDestinationForRelationCommand(diagram, rel, c);
	executedCommands.push(command);
	command.execute();

    }

    public void setRootForRelation(UMLRelation rel, UMLComponent c) {
	ICommand command = new NewRootForRelationCommand(diagram, rel, c);
	executedCommands.push(command);
	command.execute();
    }

    public void addMethod(UMLComponent c) {
	ICommand command = new NewMethodCommand(diagram, c);
	executedCommands.push(command);
	command.execute();
    }

    public void addVariable(UMLComponent c) {
	ICommand command = new NewClassVariableCommand(diagram, c);
	executedCommands.push(command);
	command.execute();

    }

    public void redoCommand() {
	if (!revertedCommands.isEmpty()) {
	    ICommand command = revertedCommands.pop();
	    executedCommands.push(command);
	    command.execute();

	}
    }

    public void undoCommand() {
	if (!executedCommands.isEmpty()) {
	    ICommand command = executedCommands.pop();
	    revertedCommands.push(command);
	    command.undo();
	}
    }

}
