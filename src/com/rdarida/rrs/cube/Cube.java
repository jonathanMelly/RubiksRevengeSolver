package com.rdarida.rrs.cube;

import java.util.ArrayList;
import java.util.Random;

/**
 *                        -------------------
 *                       |  0 |  1 |  2 |  3 |
 *                       ---------------------
 *                       |  4 |  5 |  6 |  7 |
 *                       ---------------------
 *                       |  8 |  9 | 10 | 11 |
 *                       ---------------------
 *                       | 12 | 13 | 14 | 15 |
 *                        -------------------
 *  -------------------   -------------------   -------------------   -------------------
 * | 16 | 17 | 18 | 19 | | 32 | 33 | 34 | 35 | | 48 | 49 | 50 | 51 | | 64 | 65 | 66 | 67 |
 * --------------------- --------------------- --------------------- ---------------------
 * | 20 | 21 | 22 | 23 | | 36 | 37 | 38 | 39 | | 52 | 53 | 54 | 55 | | 68 | 69 | 70 | 71 |
 * --------------------- --------------------- --------------------- ---------------------
 * | 24 | 25 | 26 | 27 | | 40 | 41 | 42 | 43 | | 56 | 57 | 58 | 59 | | 72 | 73 | 74 | 75 |
 * --------------------- --------------------- --------------------- ---------------------
 * | 28 | 29 | 30 | 31 | | 44 | 45 | 46 | 47 | | 60 | 61 | 62 | 63 | | 76 | 77 | 78 | 79 |
 *  -------------------   -------------------   -------------------   -------------------
 *                        -------------------
 *                       | 80 | 81 | 82 | 83 |
 *                       ---------------------
 *                       | 84 | 85 | 86 | 87 |
 *                       ---------------------
 *                       | 88 | 89 | 90 | 91 |
 *                       ---------------------
 *                       | 92 | 93 | 94 | 95 |
 *                        -------------------
 */
public class Cube {
	private static final int NUM_RANDOM_TWISTS = 20;
	public static final int N = 4;
	public static final int N2 = N * N;
	public static final int NUM_FACES = 6;
	public static final int SIZE = N2 * NUM_FACES;
	
	public static final byte W = 0;
	public static final byte R = 1;
	public static final byte B = 2;
	public static final byte O = 3;
	public static final byte G = 4;
	public static final byte Y = 5;
	
	private static final byte[] SOLVED = {
		W,W,W,W, W,W,W,W, W,W,W,W, W,W,W,W,
		R,R,R,R, R,R,R,R, R,R,R,R, R,R,R,R,
		B,B,B,B, B,B,B,B, B,B,B,B, B,B,B,B,
		O,O,O,O, O,O,O,O, O,O,O,O, O,O,O,O,
		G,G,G,G, G,G,G,G, G,G,G,G, G,G,G,G,
		Y,Y,Y,Y, Y,Y,Y,Y, Y,Y,Y,Y, Y,Y,Y,Y
	};
	
	private byte[] colors;
	private ArrayList<Twist> twists;
	
	public byte[] getColors() {
		return colors;
	}
	
	public ArrayList<Twist> getTwists() {
		return twists;
	}
	
	public Cube() {
		this(SOLVED.clone());
	}
	
	public Cube(byte[] cube) {
		this(cube, new ArrayList<Twist>());
	}
	
	private Cube(byte[] cube, ArrayList<Twist> twists) {
		this.colors = cube;
		this.twists = twists;
	}
	
	public void twist(Twist twist) {
		turn(twist);
		twists.add(twist);
	}
	
	public void turn(Twist twist) {
		int numTwists = twist.numTwists();
		int[] indices = twist.indices();
		
		for (int t = 0; t <= numTwists; t++) {
			for (int i = 0; i < indices.length; i += 4) {
				byte color = colors[indices[i]];
				colors[indices[i + 0]] = colors[indices[i + 1]];
				colors[indices[i + 1]] = colors[indices[i + 2]];
				colors[indices[i + 2]] = colors[indices[i + 3]];
				colors[indices[i + 3]] = color;
			}
		}
	}
	
	public void scramble() {
		scramble(NUM_RANDOM_TWISTS);
	}
	
	//U' B' L2 u B' L2 r2 f' u' b D L2 l d2 B L b' f2 U R2 u2 L r' B2 b D2 u F L2 l'
	public void scramble(int maxNumTwists) {
		Twist[] twists = Twist.values();
		Random random = new Random();
		Twist last = null;
		
		for (int i = 0; i < maxNumTwists; i++) {
			int index = random.nextInt(twists.length);
			Twist twist = twists[index];
			
			if (twist.shouldAvoid(last)) {
				i--;
				continue;
			}
			
			turn(twist);
			last = twist;
		}
	}
	
	public boolean isSolved() {
		for (int i = 0; i < SIZE; i++) {
			if (colors[i] != SOLVED[i]) return false;
		}
		
		return true;
	}
	
	public Twist lastTwist() {
		if (twists.isEmpty()) return null;
		else return twists.get(twists.size() - 1);
	}
	
	public int numTwists() {
		return twists.size();
	}
	
	public void clear() {
		twists.clear();
	}
	
	public Cube clone() {
		return new Cube(
			colors.clone(),
			new ArrayList<Twist>(twists)
		);
	}
}