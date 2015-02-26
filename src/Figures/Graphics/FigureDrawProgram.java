//package Figures.Graphics;
//
//import javax.swing.JFrame;
//
//public class FigureDrawProgram extends JFrame {
//	private static final long serialVersionUID = 1L;
//
//	public FigureDrawProgram() {
//		FigureList figureList = new FigureList();
//		
//		BaseFigure associationFig = new AssociationFigure(300, 100, 500, 100);
//		BaseFigure directAssoFig = new DirectAssociationFigure(300, 200, 500, 200);
//		BaseFigure dependencyFig = new DependencyFigure(300, 300, 500, 300);
//		BaseFigure aggregationFig = new AggregationFigure(300, 400, 500, 400);
//		BaseFigure compositionFig = new CompositionFigure(300, 500, 500, 500);		
//		BaseFigure inheritanceFig = new InheritanceFigure(700, 200, 900, 200);
//		BaseFigure realisationFig = new RealisationFigure(700, 300, 900, 300);
//		
//		figureList.add(associationFig);
//		figureList.add(directAssoFig);
//		figureList.add(dependencyFig);
//		figureList.add(aggregationFig);
//		figureList.add(compositionFig);
//		figureList.add(inheritanceFig);
//		figureList.add(realisationFig);
//
//		FigureViewer figureViewer = new FigureViewer();
//		
//		MouseInteraction mouseInteraction = new MouseInteraction(figureList, associationFig);
//		figureViewer.addMouseListener(mouseInteraction);
//		figureViewer.addMouseMotionListener(mouseInteraction);
//
//		
//		mouseInteraction = new MouseInteraction(figureList, directAssoFig);
//		figureViewer.addMouseListener(mouseInteraction);
//		figureViewer.addMouseMotionListener(mouseInteraction);
//		
//		
//		mouseInteraction = new MouseInteraction(figureList, dependencyFig);
//		figureViewer.addMouseListener(mouseInteraction);
//		figureViewer.addMouseMotionListener(mouseInteraction);
//		
//		
//		mouseInteraction = new MouseInteraction(figureList, aggregationFig);
//		figureViewer.addMouseListener(mouseInteraction);
//		figureViewer.addMouseMotionListener(mouseInteraction);
//		
//		
//		mouseInteraction = new MouseInteraction(figureList, compositionFig);
//		figureViewer.addMouseListener(mouseInteraction);
//		figureViewer.addMouseMotionListener(mouseInteraction);
//		
//		
//		mouseInteraction = new MouseInteraction(figureList, inheritanceFig);
//		figureViewer.addMouseListener(mouseInteraction);
//		figureViewer.addMouseMotionListener(mouseInteraction);
//		
//		
//		mouseInteraction = new MouseInteraction(figureList, realisationFig);
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
