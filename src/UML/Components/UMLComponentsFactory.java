package UML.Components;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

import runner.Diagram;
import runner.ViewFactory;

import com.google.gson.Gson;

public class UMLComponentsFactory implements Serializable{
	static Gson gson = new Gson();

	public static UMLVariable getVariableFromJson(String json) {
		return gson.fromJson(json, UMLVariable.class);
	}

	public static UMLComponent getUMLComponentFromJson(String json) {
		return gson.fromJson(json, UMLComponent.class);
	}

	public static Diagram getDiagramFromJson(String json) {
		return gson.fromJson(json, Diagram.class);
	}

	public static void saveDiagramAsBinaryObject(Diagram diagram){
		
	}
	
	public static void saveDiagramAsJSON(Diagram diagram) {
		diagram.removeObserver(ViewFactory.getFigureViewingPanel());
		String s = gson.toJson(diagram);
		try {
			FileWriter writer = new FileWriter("currentDiagram.json");
			writer.write(s);
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		diagram.addObserver(ViewFactory.getFigureViewingPanel());
	}

	public static Diagram loadDiagramFromPath(String path) {
		path = "currentDiagram.json";
		Diagram d = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			d = gson.fromJson(br, Diagram.class);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return d;
	}

}
