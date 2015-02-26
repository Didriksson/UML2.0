package Command;

import ConstantsAndEnums.Constants;
import runner.Diagram;

public class NewAssociationComponentCommand implements ICommand{
	
	private Diagram d;
	
	public NewAssociationComponentCommand(Diagram dia) {
		this.d = dia;
	}
	
	@Override
	public void execute() {
		d.newRelation(Constants.ASSOCIATION_STRING);
	}

	@Override
	public void undo() {
		
	}


}
