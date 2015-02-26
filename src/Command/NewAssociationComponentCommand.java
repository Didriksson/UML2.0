package Command;

import runner.Diagram;

public class NewAssociationComponentCommand implements ICommand{
	
	private Diagram d;
	
	public NewAssociationComponentCommand(Diagram dia) {
		this.d = dia;
	}
	
	@Override
	public void execute() {
		d.newRelation("Association");
	}

	@Override
	public void undo() {
		
	}


}
