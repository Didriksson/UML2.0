package Figures.Graphics;

import java.awt.Graphics;

public class CircleFigure extends GeomatricPosition {
	private int radius;

	public CircleFigure(int x, int y, int radius) {
		super(x, y);
		this.radius = radius;
	}

	@Override
	protected void drawSpecific(Graphics g) {
		g.drawOval(x - radius, y - radius, 2 * radius, 2 * radius);
	}

	@Override
	public boolean encloses(int x, int y) {
		int dx = this.x - x;
		int dy = this.y - y;
		return dx * dx + dy * dy <= radius * radius;
	}

}
