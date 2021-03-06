package Command;

import java.awt.Point;

import UML.Components.UMLComponent;
import UML.Components.UMLRelation;
import runner.Diagram;

public class NewDestinationForRelationCommand implements ICommand {
	Diagram diagram;
	UMLRelation rel;
	UMLComponent comp;

	public NewDestinationForRelationCommand(Diagram d, UMLRelation res,
			UMLComponent comp) {
		this.diagram = d;
		this.rel = res;
		this.comp = comp;
	}

	@Override
	public void execute() {
		UMLComponent oldComp = rel.getDestination();
		diagram.setDestinationForRelation(rel, comp);
		comp = oldComp;
		diagram.signalUpdate();
	}

	@Override
	public void undo() {
		execute();
	}

}
