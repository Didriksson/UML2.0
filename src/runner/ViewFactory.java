package runner;

import Figures.FigureViewingPanel;

public class ViewFactory {
    
    public static UMLDrawAreaController getUMLDrawAreaController(){
	Diagram d = new Diagram();
	return new UMLDrawAreaController(d);
    }
    public static FigureViewingPanel getFigureViewingPanel(){
	return new FigureViewingPanel(getUMLDrawAreaController());
    }
}
