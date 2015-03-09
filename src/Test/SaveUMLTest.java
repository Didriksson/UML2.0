package Test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import runner.Diagram;
import UML.Components.UMLComponent;
import UML.Components.UMLComponentsFactory;
import UML.Components.UMLMethod;
import UML.Components.UMLVariable;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class SaveUMLTest {
	static String path = ".\\src\\GeneratedTestFiles\\";
	UMLMethod method;
	UMLVariable v;
	UMLComponent umlClass;
	Diagram d;

	@Before
	public void setUp() {
		d = new Diagram();
		v = new UMLVariable("int", "numberOfboats");
		method = new UMLMethod("public", "void", "print");
		method.addVariable(v);

		umlClass = new UMLComponent("TestKlass", "class");

		try {
			umlClass.addMethod(method);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		d.addComponent(umlClass);

	}

	@Test
	public void saveDiagramTest(){
		Gson gson = new Gson();
		UMLComponentsFactory.saveDiagramAsJSON(d);
	}
	
	@Test
	public void createMethodJSON() {
		JsonObject json = method.toJson();
		String jsonString = "{\"name\":\"print\",\"returnType\":\"void\",\"modifier\":\"public\",\"parameters\":[{\"name\":\"numberOfboats\",\"type\":\"int\"}]}";
		assertEquals(jsonString, json.toString());
	}

	@Test
	public void saveClassJson() {
		Gson gson = new Gson();
		try {
			FileWriter writer = new FileWriter(".\\src\\GeneratedTestFiles\\TestKlassJson.json");
			writer.write(gson.toJson(umlClass.toJson()));
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void diagramToJson(){
		Diagram d = new Diagram();
		d.addComponent(umlClass);
		Gson gson = new Gson();
		String json = gson.toJson(d);
		System.out.println(json);
	}

	@Test
	public void loadVariableFromJson() {
		Gson gson = new Gson();
		String json = gson.toJson(v);
		UMLVariable v1 = UMLComponentsFactory.getVariableFromJson(json);
		assertEquals(v.toString(), v1.toString());

	}

	@Test
	public void loadClassComponentFromJson() {
		String toStringBefore = umlClass.toString();

		Gson gson = new Gson();
		String test = gson.toJson(umlClass);
		UMLComponent umlC = UMLComponentsFactory.getUMLComponentFromJson(test);
		String toStringAfter = umlC.toString();
		assertEquals(toStringBefore, toStringAfter);

	}

	@AfterClass
	public static void cleanUp() {

		File directory = new File(path);
		for (File file : directory.listFiles())
			file.delete();
	}

}
