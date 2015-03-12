package Figures.Graphics;

import java.awt.Point;

import ConstantsAndEnums.Constants;

public class FigureFactory {

    public static AssociationFigure getRelationsFigure(String type, Point start,
	    Point endPoint) throws Exception {
	AssociationFigure figure = null;

	switch (type) {
	case Constants.AGGREGATION_STRING:
	    figure = new AggregationFigure(start, endPoint);
	    break;
	case Constants.ASSOCIATION_STRING:
	    figure = new AssociationFigure(start, endPoint);
	    break;
	case Constants.COMPOSITION_STRING:
	    figure = new CompositionFigure(start, endPoint);
	    break;
	case Constants.DEPENDENCY_STRING:
	    figure = new DependencyFigure(start, endPoint);
	    break;
	case Constants.DIRECT_ASSOCIATION_STRING:
	    figure = new DirectAssociationFigure(start, endPoint);
	    break;
	case Constants.INHERITANCE_STRING:
	    figure = new InheritanceFigure(start, endPoint);
	    break;
	case Constants.REALISATION_STRING:
	    figure = new RealisationFigure(start, endPoint);
	    break;
	default:
	    throw new Exception("Unable to find associated relationfigure.");

	}
	
	return figure;
    }
}
