package com.rdarida.rrs.view.cube3d;

import java.awt.Color;
import java.util.Arrays;

import javax.media.opengl.GL2;

import com.rdarida.rrs.cube.Cube;
import com.rdarida.rrs.view.Gui;

import javafx.geometry.Point3D;

public class Rectangle {
	private static final float GAP = 0.06f;
	private static final float IN = 0.99f;
	private Point3D a, b, c, d;
	private Point3D e, f, g, h;
	private int colorId, prevColorId;
	private float[] color;
	private float rotationX, rotationY, rotationZ;
	private float[] dRotation;
	private float elapsed;
	
	public int getColor() {
		return colorId;
	}
	
	public void setColor(int color) {
		colorId = color;
	}
	
	public void setRotation(int axis, float degree) {
		dRotation[axis] = degree;		
		elapsed = 0;
	}
	
	public Rectangle(int side, int row, int col) {
		double i = Cube.N / 2;
		double j = i - 1;
		
		if (side == Cube.W) {
			a = new Point3D(-i + col, i * IN, -i + row);
			b = new Point3D(-j + col, i * IN, -i + row);
			c = new Point3D(-j + col, i * IN, -j + row);
			d = new Point3D(-i + col, i * IN, -j + row);
			
			e = new Point3D(-i + col + GAP, i, -i + row + GAP);
			f = new Point3D(-j + col - GAP, i, -i + row + GAP);
			g = new Point3D(-j + col - GAP, i, -j + row - GAP);
			h = new Point3D(-i + col + GAP, i, -j + row - GAP);
		}
		else if (side == Cube.R) {
			a = new Point3D(-i * IN, i - row, -i + col);
			b = new Point3D(-i * IN, i - row, -j + col);
			c = new Point3D(-i * IN, j - row, -j + col);
			d = new Point3D(-i * IN, j - row, -i + col);
			
			e = new Point3D(-i, i - row - GAP, -i + col + GAP);
			f = new Point3D(-i, i - row - GAP, -j + col - GAP);
			g = new Point3D(-i, j - row + GAP, -j + col - GAP);
			h = new Point3D(-i, j - row + GAP, -i + col + GAP);
		}
		else if (side == Cube.B) {
			a = new Point3D(-i + col, i - row, i * IN);
			b = new Point3D(-j + col, i - row, i * IN);
			c = new Point3D(-j + col, j - row, i * IN);
			d = new Point3D(-i + col, j - row, i * IN);
			
			e = new Point3D(-i + col + GAP, i - row - GAP, i);
			f = new Point3D(-j + col - GAP, i - row - GAP, i);
			g = new Point3D(-j + col - GAP, j - row + GAP, i);
			h = new Point3D(-i + col + GAP, j - row + GAP, i);
		}
		else if (side == Cube.O) {
			a = new Point3D(i * IN, i - row,  i - col);
			b = new Point3D(i * IN, i - row,  j - col);
			c = new Point3D(i * IN, j - row,  j - col);
			d = new Point3D(i * IN, j - row,  i - col);
			
			e = new Point3D(i, i - row - GAP,  i - col - GAP);
			f = new Point3D(i, i - row - GAP,  j - col + GAP);
			g = new Point3D(i, j - row + GAP,  j - col + GAP);
			h = new Point3D(i, j - row + GAP,  i - col - GAP);
		}
		else if (side == Cube.G) {
			a = new Point3D(i - col, i - row, -i * IN);
			b = new Point3D(j - col, i - row, -i * IN);
			c = new Point3D(j - col, j - row, -i * IN);
			d = new Point3D(i - col, j - row, -i * IN);
			
			e = new Point3D(i - col - GAP, i - row - GAP, -i);
			f = new Point3D(j - col + GAP, i - row - GAP, -i);
			g = new Point3D(j - col + GAP, j - row + GAP, -i);
			h = new Point3D(i - col - GAP, j - row + GAP, -i);
		}
		else if (side == Cube.Y) {
			a = new Point3D(-i + col, -i * IN, i - row);
			b = new Point3D(-j + col, -i * IN, i - row);
			c = new Point3D(-j + col, -i * IN, j - row);
			d = new Point3D(-i + col, -i * IN, j - row);
			
			e = new Point3D(-i + col + GAP, -i, i - row - GAP);
			f = new Point3D(-j + col - GAP, -i, i - row - GAP);
			g = new Point3D(-j + col - GAP, -i, j - row + GAP);
			h = new Point3D(-i + col + GAP, -i, j - row + GAP);
		}
		
		colorId = side;
		prevColorId = side;
		color = new float[] {0, 0, 0};
		setColor(Gui.COLORS[side]);
		
		rotationX = 0;
		rotationY = 0;
		rotationZ = 0;
		
		dRotation = new float[3];
		
		elapsed = 0;
	}
	
	private void setColor(Color c) {
		color[0] = (float)((float)(c.getRed()) / 255.0);
		color[1] = (float)((float)(c.getGreen()) / 255.0);
		color[2] = (float)((float)(c.getBlue()) / 255.0);
	}
	
	public void animate(float diff) {
		elapsed += diff;
		float ratio = (float)(elapsed / Cube3D.DURATION);
		ratio *= ratio * ratio;
		
		if (1 <= ratio) {
			Arrays.fill(dRotation, 0);
			elapsed = 0;
			
			if (colorId != prevColorId) {
				setColor(Gui.COLORS[colorId]);
				prevColorId = colorId;
			}
		}
		
		rotationY = dRotation[0] * ratio;
		rotationX = dRotation[1] * ratio;
		rotationZ = dRotation[2] * ratio;
	}
	
	public void render(GL2 gl) {		
		gl.glPushMatrix();
		{
			gl.glRotatef(rotationX, 1f, 0f, 0f);
			gl.glRotatef(rotationY, 0f, 1f, 0f);
			gl.glRotatef(rotationZ, 0f, 0f, 1f);
			
			gl.glBegin(GL2.GL_QUADS);
			{
				gl.glColor3f(0f, 0f, 0f);
				gl.glVertex3f((float) a.getX(), (float) a.getY(), (float) a.getZ());
				gl.glVertex3f((float) b.getX(), (float) b.getY(), (float) b.getZ());
				gl.glVertex3f((float) c.getX(), (float) c.getY(), (float) c.getZ());
				gl.glVertex3f((float) d.getX(), (float) d.getY(), (float) d.getZ());
				
				gl.glColor3f(color[0], color[1], color[2]);
				gl.glVertex3f((float) e.getX(), (float) e.getY(), (float) e.getZ());
				gl.glVertex3f((float) f.getX(), (float) f.getY(), (float) f.getZ());
				gl.glVertex3f((float) g.getX(), (float) g.getY(), (float) g.getZ());
				gl.glVertex3f((float) h.getX(), (float) h.getY(), (float) h.getZ());
				
				gl.glColor3f(0f, 0f, 0f);
			}
			gl.glEnd();
		}
		gl.glPopMatrix();
	}
}