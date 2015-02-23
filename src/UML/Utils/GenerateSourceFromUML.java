package UML.Utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import UML.Components.UMLComponent;

public class GenerateSourceFromUML {
    public static void generate(UMLComponent c, String path){
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
}	
