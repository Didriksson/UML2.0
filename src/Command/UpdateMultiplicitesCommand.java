package Command;

import runner.Diagram;
import UML.Components.UMLRelation;

public class UpdateMultiplicitesCommand implements ICommand {

	private UMLRelation r;
	private Diagram d;
	private String root, dest;
	
	
	
	
	public UpdateMultiplicitesCommand(UMLRelation r, String root, String dest,
			Diagram diagram) {
		this.r = r;
		this.d = diagram;
		this.root = root;
		this.dest = dest;

	}

	@Override
	public void execute() {
		//root and dest will not contain old values
		String oldDest = r.getMultiplicityDestination();
		String oldRoot = r.getMultiplicityRoot();
		
		r.setMultiplicityDestination(dest);
		r.setMultiplicityRoot(root);
		
		root = oldRoot;
		dest = oldDest;
		
		d.signalUpdate();

	
	}

	@Override
	public void undo() {
		execute();		
	}

}
