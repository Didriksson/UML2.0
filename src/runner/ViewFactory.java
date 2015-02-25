package runner;

import Figures.FigureViewingPanel;

public class ViewFactory {
    private static FigureViewingPanel currentPanel;
    private static UMLDrawAreaController controller;

    public static UMLDrawAreaController getUMLDrawController() {
	if (controller == null) {
	    Diagram d = new Diagram();
	    controller = new UMLDrawAreaController(d);
	}
	return controller;
    }

    public static FigureViewingPanel getFigureViewingPanel() {
	if (currentPanel == null)
	    currentPanel = new FigureViewingPanel(getUMLDrawController());
	return currentPanel;
    }
}
