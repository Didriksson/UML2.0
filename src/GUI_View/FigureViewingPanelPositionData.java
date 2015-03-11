package GUI_View;

import java.awt.Point;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import Controller.UMLDrawAreaController;
import Figures.Resizable;
import UML.Components.UMLComponent;
import UML.Components.UMLRelation;

public class FigureViewingPanelPositionData implements Serializable {
    public Map<UMLComponent, Point> components;
    public transient Map<UMLComponent, Resizable> resizables;
    public Map<UMLRelation, UMLRelationPoints> relations;

    public FigureViewingPanelPositionData() {
    }
    
    public void checkForMissingPositionValues(){
	if (resizables == null)
	    resizables = new HashMap<UMLComponent, Resizable>();
	if (relations == null)
	    relations = new HashMap<UMLRelation, UMLRelationPoints>();
	if(components == null)
	    components = new HashMap<UMLComponent, Point>();
    }
}