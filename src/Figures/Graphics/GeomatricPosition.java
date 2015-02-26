package Figures.Graphics;

import java.awt.Graphics;

public class GeomatricPosition extends BaseFigure {
	protected int x, y;

	public GeomatricPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	protected void drawSpecific(Graphics g) {
		g.drawOval(x, y, 0, 0);
	}

	@Override
	public void move(int dx, int dy) {
		x += dx;
		y += dy;
	}

	@Override
	public void moveTo(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean encloses(int x, int y) {
		return this.x == x && this.y == y;
	}

}
