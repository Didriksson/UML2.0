package Command;

import UML.Components.UMLComponent;
import UML.Components.UMLRelation;
import runner.Diagram;

public class NewRootForRelationCommand implements ICommand {
	Diagram diagram;
	UMLRelation rel;
	UMLComponent comp;
	
	

	public NewRootForRelationCommand(Diagram d, UMLRelation res, UMLComponent comp) {
		this.diagram = d;
		this.rel = res;
		this.comp = comp;
	}

	@Override
	public void redo() {
		diagram.setRootForRelation(rel, comp);
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub

	}

}