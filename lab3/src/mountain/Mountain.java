package mountain;

import java.util.*;

import fractal.Fractal;
import fractal.TurtleGraphics;

public class Mountain extends Fractal {
	private Point p1;
	private Point p2;
	private Point p3;
	private int dev;
	private List<Side> sides;

	public Mountain(int dev, Point p1, Point p2, Point p3) {
		super();
		this.dev = dev;
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		sides = new LinkedList<Side>();
	}

	@Override
	public String getTitle() {
		return "Fucking snett berg";
	}

	@Override
	public void draw(TurtleGraphics turtle) {
		turtle.moveTo(p1.getX(), p1.getY());
//		sides = new LinkedList<Side>();
		fractalTriangle(turtle, order, dev, p1, p2, p3);
	}

	/**
	 * Recursive method: Draws a recursive line of the triangle.
	 */
	private void fractalTriangle(TurtleGraphics turtle, int order, int dev,
			Point p1, Point p2, Point p3) {
		if (order == 0) {
			turtle.moveTo(p1.getX(), p1.getY());
			turtle.penDown();
			turtle.forwardTo(p2.getX(), p2.getY());
			turtle.forwardTo(p3.getX(), p3.getY());
			turtle.forwardTo(p1.getX(), p1.getY());
			turtle.penUp();
//			System.out.println("Efter check: " + sides.toString());
		} else {
			double offset1 = RandomUtilities.randFunc(dev);
			double offset2 = RandomUtilities.randFunc(dev);
			double offset3 = RandomUtilities.randFunc(dev);
			dev /= 2;
			Point mLeft;
			Point mRight;
			Point mBottom;
//			Side test;

			// Calc left middle point
			mLeft = getMPoint(p1, p2, offset1);
//			test = new Side(p1, p2);
//			if (sides.contains(test)) {
//				mLeft = getMPoint(test);
//			} else {
//				mLeft = new Point((p1.getX() + p2.getX()) / 2.0,
//						((p1.getY() + p2.getY()) / 2.0) + offset1);
//				sides.add(new Side(p1, p2, mLeft));
//			}

			// Calc right middle point
			mRight = getMPoint(p2, p3, offset2);
//			test = new Side(p2, p3);
//			if (sides.contains(test)) {
//				mRight = getMPoint(test);
//			} else {
//				mRight = new Point((p2.getX() + p3.getX()) / 2.0,
//						((p2.getY() + p3.getY()) / 2.0) + offset2);
//				sides.add(new Side(p2, p3, mRight));
//			}

			// Calc bottom middle point
			mBottom = getMPoint(p1, p3, offset3);
//			test = new Side(p1, p3);
//			if (sides.contains(test)) {
//				mBottom = getMPoint(test);
//			} else {
//				mBottom = new Point((p1.getX() + p3.getX()) / 2.0,
//						((p1.getY() + p3.getY()) / 2.0) + offset3);
//				sides.add(new Side(p1, p3, mBottom));
//			}
//			System.out.println("Efter check: " + sides.toString());
			// Top
			fractalTriangle(turtle, order - 1, dev, mLeft, p2, mRight);
			// Middle
			fractalTriangle(turtle, order - 1, dev, mLeft, mRight, mBottom);
			// Bottom left
			fractalTriangle(turtle, order - 1, dev, p1, mLeft, mBottom);
			// Bottom right
			fractalTriangle(turtle, order - 1, dev, mBottom, mRight, p3);
		}
	}

	private Point getMPoint(Side s) {
		Iterator<Side> itr = sides.iterator();
		while (itr.hasNext()) {
			Side temp = itr.next();
			if (temp.equals(s)) {
				//sides.remove(s);
				// System.out.println("Efter remove: " + sides.toString());
				return temp.getMPoint();
			}
		}
		return null;
	}
	
	private Point getMPoint(Point p1, Point p2, double offset){
		Side test = new Side(p1, p2);
		int index = sides.indexOf(test);
		if (index >= 0 ) {
			Point temp = sides.get(index).getMPoint();
			sides.remove(index);
			return temp;
		} else {
			Point temp = new Point((p1.getX() + p2.getX()) / 2.0,
					((p1.getY() + p2.getY()) / 2.0) + offset);
			sides.add(new Side(p1, p2, temp));
			return temp;
		}
	}
}
