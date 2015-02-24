package runner;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

import Command.ICommand;
import Command.NewClassComponentCommand;
import Figures.FigureViewingPanel;
import Figures.UMLComponent_GUI;
import UML.Components.UMLComponent;

public class UMLDrawAreaController extends Observable implements Observer{
    private Diagram diagram;
    Map<String, ICommand> commands;
    List<UMLComponent_GUI> components;
    FigureViewingPanel drawArea;
    
    
    public UMLDrawAreaController(Diagram d) {
	this.diagram = d;
	d.addObserver(this);
	components = new ArrayList<UMLComponent_GUI>();
	commands = new HashMap<String, ICommand>();
	commands.put("NewClass", new NewClassComponentCommand(this));
    }
    
    public void addUMLComponent(UMLComponent c) {
 	diagram.addComponent(c);
     }
    
    public void newClass() {
		commands.get("NewClass").execute();
	}

	public void registerMeAsObserver(Observer o) {
		diagram.addObserver(o);
	}

	public List<UMLComponent_GUI> getAllComponents() {
	    return components;
	}

	@Override
	public void update(Observable o, Object arg) {
	    for(UMLComponent c : diagram.getComponents()){
		boolean present = false;
		for(UMLComponent_GUI gui_comp : components){
		    if(gui_comp.getComponent().equals(c))
			{	
				present = true;
				continue;
			}
		}
		if(!present){
		    components.add(new UMLComponent_GUI(c, new Point(50,50)));
		}
	    }
	    
	    setChanged();
	    notifyObservers();
	    
	}

}
