package Figures;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class Resizable extends JComponent {

	ComponentMover mouseListener;
	GUIComponent content;
	Point position;

	public Resizable(GUIComponent comp, Point p) {
		this(comp, new ResizableBorder(8), p);
	}

	public Resizable(GUIComponent comp, ResizableBorder border, Point p) {
		this.position = p;
		mouseListener = new ComponentMover(this);
		this.content = comp;
		setLayout(new BorderLayout());
		add(content);
		addMouseListener(mouseListener);
		addMouseMotionListener(mouseListener);
		setupKeyBindings();
		setBorder(border);
		setBounds(p.x, p.y, 200, 200);
	}

	private void setupKeyBindings() {
		Action deleteMethod = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				content.controller.removeComponent();
			}
		};

		this.getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(
				KeyStroke.getKeyStroke("DELETE"), "doNothing");
		this.getActionMap().put("doNothing", deleteMethod);
	}

	public void updatePosition(int x, int y) {
		this.position.x = x;
		this.position.y = y;
		this.setLocation(position);
		System.out.println(position);

	}

	public GUIComponent getGUIComponent() {
		return content;
	}

	public void resize() {
		if (getParent() != null) {
			((JComponent) getParent()).revalidate();
		}
	}

	public boolean equals(Object o) {
		if (o instanceof Resizable) {
			Resizable r = (Resizable) o;
		}
		return false;
	}

	@Override
	public boolean contains(Point p) {
		return containsWidth(p) && containsHeight(p);

	}

	private boolean containsWidth(Point p) {
		return p.x >= position.x && p.x <= (position.x + this.getWidth());
	}

	private boolean containsHeight(Point p) {
		return p.y >= position.y && p.y <= (position.y + this.getHeight());
	}

}
