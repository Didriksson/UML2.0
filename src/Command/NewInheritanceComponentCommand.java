package Command;

import runner.Diagram;
import ConstantsAndEnums.Constants;

public class NewInheritanceComponentCommand implements ICommand{

	private Diagram d;
	
	public NewInheritanceComponentCommand(Diagram dia) {
		this.d = dia;
	}
	
	@Override
	public void redo() {
		d.newRelation(Constants.INHERITANCE_STRING);
	}

	@Override
	public void undo() {
		
	}
}
