package ConstantsAndEnums;

import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.Border;

public class Constants {

    public static final ImageIcon OPEN_FILE_ICON = new ImageIcon(
	    "src/images/openfile.png");
    public static final ImageIcon SAVE_FILE_ICON = new ImageIcon(
	    "src/images/savefile.png");

    public static final ImageIcon CLASS_ICON = new ImageIcon(
	    "src/images/class.png");
    public static final ImageIcon ABSTRACT_CLASS_ICON = new ImageIcon(
	    "src/images/.png");
    public static final ImageIcon INTERFACE_ICON = new ImageIcon(
	    "src/images/.png");

    public static final ImageIcon COMPOSITION_ICON = new ImageIcon(
	    "src/images/composition.png");
    public static final ImageIcon AGGREGATION_ICON = new ImageIcon(
	    "src/images/aggregation.png");
    public static final ImageIcon DEPENDENCY_ICON = new ImageIcon(
	    "src/images/dependency.png");
    public static final ImageIcon INHERITANCE_ICON = new ImageIcon(
	    "src/images/inheritance.png");
    public static final ImageIcon ASSOCIATION_ICON = new ImageIcon(
	    "src/images/association.png");
    public static final ImageIcon DIRECT_ASSOCIATION_ICON = new ImageIcon(
	    "src/images/directassociation.png");
    public static final ImageIcon REALIZATION_ICON = new ImageIcon(
	    "src/images/realisation.png");

    public static final ImageIcon REDO_ICON = new ImageIcon(
	    "src/images/redo.png");
    public static final ImageIcon UNDO_ICON = new ImageIcon(
	    "src/images/undo.png");

    public static final Image FIGURE_VIEWER_BACKGROUND = new ImageIcon(
	    "src/images/figureviewer.png").getImage();

    public static final ImageIcon MINIMIZE_ICON = new ImageIcon(
	    "src/images/minimizeIcon.png");
    public static final String MINIMIZE_STRING = "Minimize";
    public static final ImageIcon HIDETREE_ICON = new ImageIcon(
	    "src/images/hideIcon.png");
    public static final String HIDETREE_STRING = "Hide Tree";

    public static final String CLASSNAME_STRING = "CLASS";
    public static final String COMPOSITION_STRING = "COMPOSITION";
    public static final String REDO_STRING = "REDO";
    public static final String UNDO_STRING = "UNDO";
    public static final String AGGREGATION_STRING = "AGGREGATION";
    public static final String DEPENDENCY_STRING = "DEPENDENCY";
    public static final String INHERITANCE_STRING = "INHERITANCE";
    public static final String ASSOCIATION_STRING = "ASSOCIATION";
    public static final String DIRECT_ASSOCIATION_STRING = "DIRECT_ASSOCIATION";
    public static final String REALISATION_STRING = "REALISATION";

    public static final Border RAISED_BEVEL_BORDER = BorderFactory
	    .createRaisedBevelBorder();

}
