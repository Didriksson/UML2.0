//package Figures.Graphics;
//
//import java.awt.Point;
//
//import javax.swing.JFrame;
//
//public class FigureDrawProgram extends JFrame {
//	private static final long serialVersionUID = 1L;
//
//	public FigureDrawProgram() {
//		FigureList figureList = new FigureList();
//		
//		BaseFigure associationFig = new AssociationFigure(new Point(300, 100), new Point(500, 100));
//	
//		
//		figureList.add(associationFig);
//
//		FigureViewer figureViewer = new FigureViewer();
//		
//		MouseInteraction mouseInteraction = new MouseInteraction(figureList, associationFig);
//		figureViewer.addMouseListener(mouseInteraction);
//		figureViewer.addMouseMotionListener(mouseInteraction);
//		
//		
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//		this.setContentPane(figureViewer);
//		this.setSize(1200, 800);
//		this.setVisible(true);
//
//	}
//}
