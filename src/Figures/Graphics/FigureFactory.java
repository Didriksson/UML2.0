package Figures.Graphics;

import java.awt.Point;

import runner.ViewFactory;
import ConstantsAndEnums.Constants;
import Controller.RelationController;
import Controller.UMLDrawAreaController;
import UML.Components.UMLRelation;

public class FigureFactory {
	public static AssociationFigure getRelationsFigure(UMLRelation relation, Point start, Point endPoint) throws Exception {
		
		
		UMLDrawAreaController controller = ViewFactory.getUMLDrawController();
		AssociationFigure figure = null;
		
		switch (relation.getType()) {
		case Constants.AGGREGATION_STRING:
			figure = new AggregationFigure(new RelationController(relation, controller),
					start, endPoint);
			break;
		case Constants.ASSOCIATION_STRING:
			figure = new AssociationFigure(new RelationController(relation, controller),
					start, endPoint);
			break;
		case Constants.COMPOSITION_STRING:
			figure = new CompositionFigure(new RelationController(relation, controller),
					start, endPoint);
			break;
		case Constants.DEPENDENCY_STRING:
			figure = new DependencyFigure(new RelationController(relation, controller),
					start, endPoint);
			break;
		case Constants.DIRECT_ASSOCIATION_STRING:
			figure = new DirectAssociationFigure(new RelationController(
					relation, controller), start, endPoint);
			break;
		case Constants.INHERITANCE_STRING:
			figure = new InheritanceFigure(new RelationController(relation, controller),
					start, endPoint);
			break;
		case Constants.REALISATION_STRING:
			figure = new RealisationFigure(new RelationController(relation, controller),
					start, endPoint);
			break;
		default:
			throw new Exception("Unable to find associated relationfigure.");

		}

		return figure;
	}
}
