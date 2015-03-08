package Command;

import runner.Diagram;
import ConstantsAndEnums.Constants;

public class NewAggregationComponentCommand implements ICommand {
	
	private Diagram d;
	
	public NewAggregationComponentCommand(Diagram dia) {
		this.d = dia;
	}
	
	@Override
	public void redo() {
		d.newRelation(Constants.AGGREGATION_STRING);
	}

	@Override
	public void undo() {
		
	}
}
