package Figures.Graphics;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import UML.Components.UMLRelation;

public class FigureList extends Observable implements Observer, Iterable<AssociationFigure>{
	private Map<AssociationFigure, UMLRelation> relationFigureMap;
	
	public FigureList() {
		relationFigureMap = new HashMap<AssociationFigure, UMLRelation>();
	}
	public void add(AssociationFigure figure, UMLRelation relation) {
		relationFigureMap.put(figure, relation);
		this.setChanged();
		this.notifyObservers();
	}

	public UMLRelation remove(AssociationFigure a) {
		UMLRelation r  = relationFigureMap.remove(a);
		this.setChanged();
		this.notifyObservers();
		return r;
	}

	@Override
	public void update(Observable arg0, Object arg1) // eg if a part is changed
	{
		this.setChanged();
		this.notifyObservers(); // then pass it along
	}

	@Override
	public Iterator<AssociationFigure> iterator() {
		return relationFigureMap.keySet().iterator();
	}
	public boolean contains(UMLRelation r) {
		return relationFigureMap.containsValue(r);
	}
}
