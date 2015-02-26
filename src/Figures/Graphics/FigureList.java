package Figures.Graphics;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

public class FigureList extends Observable implements Iterable<BaseFigure>,
		Observer {
	protected LinkedList<BaseFigure> figures = new LinkedList<BaseFigure>();

	public void add(BaseFigure figure) {
		figures.add(figure);
		this.setChanged();
		this.notifyObservers();
	}

	public void remove(BaseFigure figure) {
		figures.remove(figure);
		this.setChanged();
		this.notifyObservers();
	}

	public Iterator<BaseFigure> iterator() {
		return figures.iterator();
	}

	@Override
	public void update(Observable arg0, Object arg1) // eg if a part is changed
	{
		this.setChanged();
		this.notifyObservers(); // then pass it along
	}
}
