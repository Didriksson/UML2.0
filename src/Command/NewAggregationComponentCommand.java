package Command;

import UML.Components.UMLRelation;
import runner.Diagram;
import ConstantsAndEnums.Constants;

public class NewAggregationComponentCommand extends ToolbarCommand {

	UMLRelation r;

	public NewAggregationComponentCommand(Diagram d) {
		super(d);
	}

	@Override
	public void execute() {
		if (r == null)
			r = diagram.newRelation(Constants.AGGREGATION_STRING);
		else
			diagram.addRelation(r);
	}

	@Override
	public void undo() {
		diagram.removeRelation(r);
	}
}
