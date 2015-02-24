package Figures;

import java.awt.Point;

import UML.Components.UMLComponent;

public class UMLComponent_GUI {
    private Point point;
    private UMLComponent component;
    private boolean hasChanged;

    public UMLComponent_GUI(UMLComponent c, Point p) {
	this.point = p;
	this.component = c;
	this.hasChanged = true;
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

    public boolean hasChanged() {
	return hasChanged;
    }

    public void clearChanged() {
	hasChanged = false;
    }

    public void setChanged() {
	hasChanged = true;
    }
}
