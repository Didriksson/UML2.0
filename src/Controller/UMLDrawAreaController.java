package Controller;

import java.awt.Point;
import java.util.Map;
import java.util.Observer;
import java.util.Set;
import java.util.Stack;

import runner.CommandFactory;
import runner.Diagram;
import Command.ICommand;
import Command.NewClassVariableCommand;
import Command.NewDestinationForRelationCommand;
import Command.NewMethodCommand;
import Command.NewRootForRelationCommand;
import Command.RemoveClassComponentCommand;
import Command.RemoveRelationCommand;
import ConstantsAndEnums.Enums;
import GUI_View.FigureViewingPanel;
import GUI_View.UMLRelationPoints;
import UML.Components.UMLComponent;
import UML.Components.UMLRelation;

public class UMLDrawAreaController {
	private Diagram diagram;
	Stack<ICommand> executedCommands;
	Stack<ICommand> revertedCommands;
	private FigureViewingPanel viewPanel;

	public UMLDrawAreaController(Diagram d) {
		this.diagram = d;
		executedCommands = new Stack<ICommand>();
		revertedCommands = new Stack<ICommand>();
	}

	public void setDiagram(Diagram d) {
		this.diagram = d;
	}

	public void registerMeAsObserver(Observer o) {
		diagram.addObserver(o);
	}

	public void setViewPanel(FigureViewingPanel view) {
		this.viewPanel = view;
	}

	public void removeComponent(UMLComponent c) {
		exectuteCommand(new RemoveClassComponentCommand(diagram, c));
	}

	public void removeRelation(UMLRelation r) {
		exectuteCommand(new RemoveRelationCommand(diagram, r));
	}

	public void setDestinationForRelation(UMLRelation rel, UMLComponent c,
			Point point) {
		UMLRelationPoints points = viewPanel.getRelation().get(rel);
		points.end = point;
		exectuteCommand(new NewDestinationForRelationCommand(diagram, rel, c));
	}

	public void setRootForRelation(UMLRelation rel, UMLComponent c, Point point) {
		UMLRelationPoints points = viewPanel.getRelation().get(rel);
		points.start = point;
		exectuteCommand(new NewRootForRelationCommand(diagram, rel, c));
	}

	public void addMethod(UMLComponent c) {
		exectuteCommand(new NewMethodCommand(diagram, c));
	}

	public void addVariable(UMLComponent c) {
		exectuteCommand(new NewClassVariableCommand(diagram, c));
	}

	public void toolbarCommands(Enums command) {
		exectuteCommand(CommandFactory.getToolbarCommand(command, diagram));
	}

	private void exectuteCommand(ICommand command) {
		executedCommands.push(command);
		command.execute();
	}

	public Set<String> getParameterList() {
		return diagram.getParametersForMethod();
	}

	public void redoCommand() {
		if (!revertedCommands.isEmpty())
			redoCommand(revertedCommands.pop());
	}

	private void redoCommand(ICommand command) {
		exectuteCommand(command);
	}

	public void undoCommand() {
		if (!executedCommands.isEmpty()) {
			undoCommand(executedCommands.pop());
		}
	}

	private void undoCommand(ICommand command) {
		revertedCommands.push(command);
		command.undo();
	}

	public Diagram getDiagram() {
		return diagram;
	}

	public void removeDestinationForRelation(UMLRelation rel) {
		exectuteCommand(new NewDestinationForRelationCommand(diagram, rel, null));
	}

	public void removeRootForRelation(UMLRelation rel) {
		System.out.println(rel);
		exectuteCommand(new NewRootForRelationCommand(diagram, rel, null));
		
	}

}
