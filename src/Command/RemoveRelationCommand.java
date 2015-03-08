package Command;

import runner.Diagram;
import UML.Components.UMLComponent;
import UML.Components.UMLRelation;

public class RemoveRelationCommand implements ICommand {
	private Diagram diagram;
	private UMLRelation relation;

	public RemoveRelationCommand(Diagram d, UMLRelation c) {
		this.diagram = d;
		this.relation = c;
	}

	@Override
	public void redo() {
		diagram.removeRelation(relation);
	}

	@Override
	public void undo() {
		
	}

}
