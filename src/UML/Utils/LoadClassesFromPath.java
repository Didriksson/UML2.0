package UML.Utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class LoadClassesFromPath {

    public static List<Class<?>> loadClasses(String path) {
	List<String> classPaths = null;
	File root = new File(LoadClassesFromPath.class.getClassLoader()
		.getResource("").getPath());
	String rootPath = root.getPath();
	String pathToClassDirectory = rootPath + path;
	try {
	    classPaths = Files.walk(Paths.get(pathToClassDirectory))
		    .filter(item -> item.toString().contains(".class"))
		    .map(item -> item.toString()).collect(Collectors.toList());
	} catch (IOException e) {
	    e.printStackTrace();
	}

	classPaths = classPaths
		.stream()
		.map(item -> item.substring(rootPath.length() + 1,
			item.length() - 6))
		.map(item -> item.replace('\\', '.'))
		.collect(Collectors.toList());
	
	List<Class<?>> classes = classPaths.stream()
		.map(s -> loadClassByName(s)).collect(Collectors.toList());
	return classes;
    }

    private static Class<?> loadClassByName(String s) {
	Class<?> c = null;
	try {
	    c = ClassLoader.getSystemClassLoader().loadClass(s);
	} catch (ClassNotFoundException e) {
	    System.out.println("Unable to load class: " + s);
	}
	return c;
    }
}
