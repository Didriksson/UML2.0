package Figures.Graphics;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import UML.Components.UMLRelation;

public class FigureList extends Observable implements Observer, Iterable<AssociationFigure>{
	private Map<UMLRelation, AssociationFigure> relationFigureMap;
	
	public FigureList() {
		relationFigureMap = new HashMap<UMLRelation, AssociationFigure>();
	}
	public void add(AssociationFigure figure, UMLRelation relation) {
		relationFigureMap.put(relation, figure);
		this.setChanged();
		this.notifyObservers();
	}

	public void remove(UMLRelation relation) {
		relationFigureMap.remove(relation);
		this.setChanged();
		this.notifyObservers();
	}


	@Override
	public void update(Observable arg0, Object arg1) // eg if a part is changed
	{
		this.setChanged();
		this.notifyObservers(); // then pass it along
	}

	@Override
	public Iterator<AssociationFigure> iterator() {
		return relationFigureMap.values().iterator();
	}
	public boolean contains(UMLRelation r) {
		return relationFigureMap.containsKey(r);
	}
}
