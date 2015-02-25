package Figures;

import java.awt.Point;

import javax.swing.JPanel;

import runner.UMLComponentController;

public class GUIComponent extends JPanel{
	protected UMLComponentController controller;
	public UMLComponentController getController(){
		return controller;
	}
	public void setPosition(Point location) {
	    controller.setPosition(location);
	}
	
	public Point getPosition(){
	    return controller.getPosition();
	}
	
	}
