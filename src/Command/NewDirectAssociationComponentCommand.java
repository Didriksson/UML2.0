package Command;

import runner.Diagram;
import ConstantsAndEnums.Constants;

public class NewDirectAssociationComponentCommand implements ICommand{
	
	private Diagram d;
	
	public NewDirectAssociationComponentCommand(Diagram dia) {
		this.d = dia;
	}
	
	@Override
	public void execute() {
		d.newRelation(Constants.DIRECT_ASSOCIATION_STRING);
	}

	@Override
	public void undo() {
		
	}


}
