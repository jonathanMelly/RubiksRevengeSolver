package com.rdarida.rrs.view.cube3d;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.ListIterator;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;

import com.rdarida.rrs.cube.Cube;
import com.rdarida.rrs.cube.Twist;
import com.rdarida.rrs.view.Gui;

public class Cube3D extends GLCanvas implements GLEventListener, MouseListener, MouseMotionListener {
	private static final long serialVersionUID = 1L;
	static final float DURATION = 0.65f;
	private GLU glu;
	private Rectangle[] rectangles;
	private float rotationX, rotationY;
	private int dX, dY;
	private double timestamp;
	private double elapsedTime;
	private boolean isTwistPlaying;
	private ListIterator<Twist> solution;
	private int direction;
	
	public Cube3D() {
		super(new GLCapabilities(GLProfile.getDefault()));
		setSize(Gui.WIDTH, Gui.HEIGHT);
		rectangles = new Rectangle[Cube.SIZE];
		
		for (int side = 0; side < 6; side++) {
			int s = side * Cube.N2;
			
			for (int row = 0; row < Cube.N; row++) {
				int r = row * Cube.N;
				
				for (int col = 0; col < Cube.N; col++) {
					rectangles[s + r + col] = new Rectangle(side, row, col);
				}
			}
		}
		
		solution = null;
		direction = 1;
		
		resetView();
		addGLEventListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	public void reset(byte[] colors) {
		removeMouseListener(this);
		removeMouseMotionListener(this);
		solution = null;
		
		for (int i = 0; i < rectangles.length; i++) {
			rectangles[i].setColor(colors[i]);
		}
		
		resetView();
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	public boolean setSolution(ArrayList<Twist> solution) {
		this.solution = solution.listIterator();
		return this.solution.hasNext();
	}
	
	public void resetView() {
		rotationX = 30f;
		rotationY = 45f;
		dX = 0;
		dY = 0;
	}
	
	public boolean isTwistPlaying() {
		return isTwistPlaying;
	}
	
	public void playPreviousTwist() {
		if (solution.hasPrevious()) {
			isTwistPlaying = true;
			elapsedTime = 0;
			undo(solution.previous());
		}
	}
	
	public void playNextTwist() {
		if (solution.hasNext()) {
			isTwistPlaying = true;
			elapsedTime = 0;
			twist(solution.next());
		}
	}
	
	public boolean hasPrevious() {
		return solution.hasPrevious();
	}
	
	public boolean hasNext() {
		return solution.hasNext();
	}
	
	private void twist(Twist twist) {
		direction = 1;
		int times = twist.numTwists();
		
		if (times == 0) twistCW(twist);
		else if (times == 1) twist180(twist);
		else if (times == 2) twistCCW(twist);
	}
	
	private void undo(Twist twist) {
		direction = -1;
		int times = twist.numTwists();
		
		if (times == 0) twistCCW(twist);
		else if (times == 1) twist180(twist);
		else if (times == 2) twistCW(twist);
	}
	
	private void twistCW(Twist twist) {
		int[] group = twist.indices();
		int axis = twist.axis();
		float degree = twist.rotation();
		
		for (int i = 0; i < group.length; i += 4) {
			rectangles[group[i]].setRotation(axis, degree);
			int color = rectangles[group[i]].getColor();
			
			rectangles[group[i + 1]].setRotation(axis, degree);
			rectangles[group[i]].setColor(rectangles[group[i + 1]].getColor());
			
			rectangles[group[i + 2]].setRotation(axis, degree);
			rectangles[group[i + 1]].setColor(rectangles[group[i + 2]].getColor());
			
			rectangles[group[i + 3]].setRotation(axis, degree);
			rectangles[group[i + 2]].setColor(rectangles[group[i + 3]].getColor());
			rectangles[group[i + 3]].setColor(color);
		}
	}
	
	private void twistCCW(Twist twist) {
		int[] idxs = twist.indices();
		int axis = twist.axis();
		float rotation = -twist.rotation();
		
		for (int i = 0; i < idxs.length; i += 4) {
			rectangles[idxs[i]].setRotation(axis, rotation);
			int color = rectangles[idxs[i]].getColor();
			
			rectangles[idxs[i + 3]].setRotation(axis, rotation);
			rectangles[idxs[i]].setColor(rectangles[idxs[i + 3]].getColor());
			
			rectangles[idxs[i + 2]].setRotation(axis, rotation);
			rectangles[idxs[i + 3]].setColor(rectangles[idxs[i + 2]].getColor());
			
			rectangles[idxs[i + 1]].setRotation(axis, rotation);
			rectangles[idxs[i + 2]].setColor(rectangles[idxs[i + 1]].getColor());
			rectangles[idxs[i + 1]].setColor(color);
		}
	}
	
	private void twist180(Twist twist) {
		int[] group = twist.indices();
		int axis = twist.axis();
		float degree = 2 * twist.rotation() * direction;
		
		for (int i = 0; i < group.length; i += 4) {
			rectangles[group[i]].setRotation(axis, degree);
			int color = rectangles[group[i]].getColor();
			
			rectangles[group[i + 2]].setRotation(axis, degree);
			rectangles[group[i]].setColor(rectangles[group[i + 2]].getColor());
			rectangles[group[i + 2]].setColor(color);
			
			rectangles[group[i + 1]].setRotation(axis, degree);
			color = rectangles[group[i + 1]].getColor();
			
			rectangles[group[i + 3]].setRotation(axis, degree);
			rectangles[group[i + 1]].setColor(rectangles[group[i + 3]].getColor());
			rectangles[group[i + 3]].setColor(color);
		}
	}
	
	@Override
	public void init(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		glu = new GLU();
		gl.glClearColor(0.6f, 0.6f, 0.6f, 1f);
		gl.glClearDepth(1f);
		gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glDepthFunc(GL.GL_LEQUAL);
		
		timestamp = new Date().getTime() * 0.001;
		elapsedTime = 1;
	}
	
	@Override
	public void display(GLAutoDrawable drawable) {
		double now = new Date().getTime() * 0.001;
		double diff = now - timestamp;
		timestamp = now;
		elapsedTime += diff;
		
		GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		
		gl.glPushMatrix();
		{
			gl.glTranslatef(0f, 0f, -2.5f * Cube.N);
			gl.glRotatef(rotationX, 1f, 0f, 0f);
			gl.glRotatef(rotationY, 0f, 1f, 0f);
			
			for (int i = 0; i < rectangles.length; i++) {
				Rectangle rect = rectangles[i];
				rect.animate((float) diff);
				rect.render(gl);
			}
		}
		gl.glPopMatrix();
		
		gl.glColor3f(1f, 1f, 1f);
		
		if (DURATION <= elapsedTime) {
			isTwistPlaying = false;
		}
	}
	
	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		GL2 gl = drawable.getGL().getGL2();
		
		if (height == 0) height = 1;
		
		float aspect = (float)(width) / (float)(height);
		
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		
		glu.gluPerspective(45.0, aspect, 0.1, 100.0);
		
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
	}
	
	@Override
	public void dispose(GLAutoDrawable drawable) {}
	
	@Override
	public void mouseDragged(MouseEvent event) {
		int x = event.getX();
		int y = event.getY();
		rotationY += x - dX;
		rotationX += y - dY;
		dX = x;
		dY = y;
	}
	
	@Override
	public void mouseClicked(MouseEvent event) {}
	
	@Override
	public void mouseEntered(MouseEvent event) {}
	
	@Override
	public void mouseExited(MouseEvent event) {}
	
	@Override
	public void mousePressed(MouseEvent event) {
		dX = event.getX();
		dY = event.getY();
	}
	
	@Override
	public void mouseReleased(MouseEvent event) {}
	
	@Override
	public void mouseMoved(MouseEvent event) {}
}