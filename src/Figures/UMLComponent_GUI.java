package Figures;

import java.awt.Point;

import UML.Components.UMLComponent;

public class UMLComponent_GUI {
    private Point point;
    private UMLComponent component;
    

    
    public UMLComponent_GUI(UMLComponent c, Point p) {
	this.point = p;
	this.component = c;
    }

    public Point getPoint() {
	return point;
    }

    public void setPoint(Point point) {
	this.point = point;
    }

    public UMLComponent getComponent() {
	return component;
    }

    public void setComponent(UMLComponent component) {
	this.component = component;
    }

}
