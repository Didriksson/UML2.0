package Controller;

import java.awt.Point;
import java.util.Map;
import java.util.Observer;

import runner.Diagram;
import Command.ICommand;
import Command.NewClassComponentCommand;
import Command.NewDestinationForRelationCommand;
import Command.NewRootForRelationCommand;
import Command.RemoveClassComponentCommand;
import Command.RemoveRelationCommand;
import ConstantsAndEnums.Enums;
import GUI_View.FigureViewingPanel;
import GUI_View.UMLRelationPoints;
import UML.Components.UMLComponent;
import UML.Components.UMLRelation;

public class UMLDrawAreaController{
    private Diagram diagram;
    private Map<Enums, ICommand> toolbarCommands;
    private FigureViewingPanel viewPanel;
    private UMLRelationPoints points;

    public UMLDrawAreaController(Diagram d, Map<Enums, ICommand>commands) {
	this.diagram = d;
	this.toolbarCommands = commands;
    }

    public void setViewPanel(FigureViewingPanel view){
    	this.viewPanel = view;
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

	public void setDestinationForRelation(UMLRelation rel, UMLComponent c, Point point) {
		points = viewPanel.getRelation().get(rel);
		points.end = point;
		new NewDestinationForRelationCommand(diagram, rel, c).execute();
	}

	public void setRootForRelation(UMLRelation rel, UMLComponent c, Point point) {
		points = viewPanel.getRelation().get(rel);
		points.start = point;
		new NewRootForRelationCommand(diagram, rel, c).execute();
	}

}
