package UMLComponents;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class ConvertToUML {
    
    public static void main(String[] args) {
	new ConvertToUML();
    }

    public ConvertToUML() {
	File folder = new File(getCurrentPath());
	ClassLoader loader = ClassLoader.getSystemClassLoader();

	System.out.println("Root path: " + getCurrentPath());
	Class<?> c = null;

	try {
	    c = loader.loadClass("UMLComponents.ExampleClass");
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	}

	List<String> classPaths = null;

	try {
	    classPaths = Files.walk(Paths.get(getCurrentPath()))
		    .filter(item -> item.toString().contains(".class"))
		    .map(item -> item.toString()).collect(Collectors.toList());
	} catch (IOException e) {
	    e.printStackTrace();
	}

	// Maps over the list and first substrings "out" the current directory
	// aswell as replaces the "\" with ".". Boom.
	classPaths = classPaths
		.stream()
		.map(item -> item.substring(getCurrentPath().length() + 1,
			item.length() - 6))
		.map(item -> item.replace('\\', '.'))
		.collect(Collectors.toList());

	for (String p : classPaths) {
	    try {
		c = loader.loadClass(p);
	    } catch (ClassNotFoundException e) {
		e.printStackTrace();
	    }
	    System.out.println(c.getName());
	}
    }

    private String getCurrentPath() {
	File root = new File(ConvertToUML.class.getClassLoader()
		.getResource("").getPath());
	return root.getPath();
    }
}
