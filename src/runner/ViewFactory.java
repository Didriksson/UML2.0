package runner;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Controller.UMLDrawAreaController;
import GUI_View.FigureViewingPanel;

public class ViewFactory {
	private static FigureViewingPanel currentPanel;
	private static UMLDrawAreaController controller;
	private static Diagram d;

	public static UMLDrawAreaController getUMLDrawController() {
		if (controller == null) {
			controller = new UMLDrawAreaController(d);
		}
		return controller;
	}

	public static FigureViewingPanel getFigureViewingPanel() {
		if (currentPanel == null) {
			if (d == null)
				d = new Diagram();
			currentPanel = new FigureViewingPanel(getUMLDrawController());
		}
		return currentPanel;
	}
	
	public static FigureViewingPanel newFigureViewPanel(){
	    d = new Diagram();
	    controller = new UMLDrawAreaController(d);
	    currentPanel = new FigureViewingPanel(controller);
	    return currentPanel;
	}

	public static void saveCurrentState() {
		try {
			FileOutputStream f_out = new FileOutputStream("myobject.data");

			ObjectOutputStream obj_out = new ObjectOutputStream(f_out);

			SavedDataHolder savedData = new SavedDataHolder();
			savedData.diagram = controller.getDiagram();
			savedData.positionData = getFigureViewingPanel().getDataPosition();
			System.out.println("Sparar diagram: \n" + controller.getDiagram());
			obj_out.writeObject(savedData);
		}

		catch (Exception e) {
			System.out.println("Spartok!\n ");
			e.printStackTrace();
		}
	}
	
	public static FigureViewingPanel figureViewFromDiagram(Diagram d){
		controller = new UMLDrawAreaController(d);
		currentPanel = new FigureViewingPanel(controller);
		d.signalUpdate();
		return currentPanel;
	}

	
	public static FigureViewingPanel figureViewFromloadedDiagram() {
		try {
			FileInputStream f_in = new FileInputStream("myobject.data");

			ObjectInputStream obj_in = new ObjectInputStream(f_in);
			
			// Read an object
			Object obj = obj_in.readObject();
			if (obj instanceof SavedDataHolder) {
				SavedDataHolder data = (SavedDataHolder) obj;
				d = data.diagram;
				controller = new UMLDrawAreaController(d);
				currentPanel = new FigureViewingPanel(controller);
				currentPanel.setDataPosition(data.positionData);
				d.signalUpdate();
				System.out.println("Diagram:\n"+d);
			}
		} catch (Exception e) {
		}
		
		return currentPanel;
	}

	public static Diagram getDiagram() {
	    return d;
	}

}