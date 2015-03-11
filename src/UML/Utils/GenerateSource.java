package UML.Utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import runner.Diagram;
import UML.Components.UMLComponent;

public class GenerateSource {
    public static void generateFromComponent(UMLComponent c, String path){
	System.out.println("Hej");
	File file = new File(path + "\\"+ c.getName() + ".java");
	try {
	    FileWriter writer = new FileWriter(file.getAbsoluteFile());
	    BufferedWriter bufferedwriter = new BufferedWriter(writer);
	    bufferedwriter.write(c.generateSourceString());
	    bufferedwriter.close();
	} 
	catch (IOException e) {
	    e.printStackTrace();
	}
    }
    
    
    public static void generateFromDiagram(Diagram d, String path){
	System.out.println(path);
	d.getComponents().stream().forEach(c -> generateFromComponent(c,path));
    }
}	
