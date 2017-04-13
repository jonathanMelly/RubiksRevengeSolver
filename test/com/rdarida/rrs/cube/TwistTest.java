package com.rdarida.rrs.cube;

import static org.junit.Assert.*;

import org.junit.Test;

import com.rdarida.rrs.cube.Twist;

public class TwistTest {
	@Test
	public void testNumTwists() {
		int[] expected = {
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
			2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2
		};
		
		Twist[] twists = Twist.values();
		
		for (int i = 0, l = twists.length; i < l; i++) {
			int value = expected[i];
			Twist twist = twists[i];
			assertEquals(twist.toString(),  value, twist.numTwists());
		}
	}
	
	@Test
	public void testIndices() {
		int[][] expected = {
			{67,19,35,51, 66,18,34,50, 65,17,33,49, 64,16,32,48, 0,12,15,3, 1,8,14,7, 2,4,13,11, 5,9,10,6},
			{15,47,95,64, 11,43,91,68, 7,39,87,72, 3,35,83,76, 48,60,63,51, 49,56,62,55, 50,52,61,59, 53,57,58,54},
			{12,31,83,48, 13,27,82,52, 14,23,81,56, 15,19,80,60, 32,44,47,35, 33,40,46,39, 34,36,45,43, 37,41,42,38},
			{44,28,76,60, 45,29,77,61, 46,30,78,62, 47,31,79,63, 80,92,95,83, 81,88,94,87, 82,84,93,91, 85,89,90,86},
			{0,79,80,32, 4,75,84,36, 8,71,88,40, 12,67,92,44, 16,28,31,19, 17,24,30,23, 18,20,29,27, 21,25,26,22},
			{3,63,92,16, 2,59,93,20, 1,55,94,24, 0,51,95,28, 64,76,79,67, 65,72,78,71, 66,68,77,75, 69,73,74,70},
			{71,23,39,55, 70,22,38,54, 69,21,37,53, 68,20,36,52},
			{14,46,94,65, 10,42,90,69, 6,38,86,73, 2,34,82,77},
			{8,30,87,49, 9,26,86,53, 10,22,85,57, 11,18,84,61},
			{40,24,72,56, 41,25,73,57, 42,26,74,58, 43,27,75,59},
			{1,78,81,33, 5,74,85,37, 9,70,89,41, 13,66,93,45},
			{7,62,88,17, 6,58,89,21, 5,54,90,25, 4,50,91,29}
		};
		
		Twist[] twists = Twist.values();
		
		for (int i = 0, l = twists.length; i < l; i++) {
			int[] values = expected[i % 12];
			Twist twist = twists[i];
			assertArrayEquals(twist.toString(), values, twist.indices());
		}
	}
	
	//@Test
	public void testShouldAvoid() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testAxis() {
		int[] expected = {
			0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2,
			0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2,
			0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2
		};
		
		Twist[] twists = Twist.values();
		
		for (int i = 0, l = twists.length; i < l; i++) {
			Twist twist = twists[i];
			assertEquals(twist.toString(), expected[i], twist.axis());
		}
	}
	
	@Test
	public void testRotation() {
		int[] expected = {
			-90, -90, -90,  90,  90,  90,  -90, -90, -90,  90,  90,  90,
			-90, -90, -90,  90,  90,  90,  -90, -90, -90,  90,  90,  90,
			-90, -90, -90,  90,  90,  90,  -90, -90, -90,  90,  90,  90
		};
		
		Twist[] twists = Twist.values();
		
		for (int i = 0, l = twists.length; i < l; i++) {
			Twist twist = twists[i];
			assertEquals(twist.toString(), expected[i], twist.rotation());
		}
	}
	
	@Test
	public void testToString() {
		String[] expected = {
			"U",  "R",  "F",  "D",  "L",  "B",  "u",  "r",  "f",  "d",  "l",  "b",
			"U2", "R2", "F2", "D2", "L2", "B2", "u2", "r2", "f2", "d2", "l2", "b2",
			"U'", "R'", "F'", "D'", "L'", "B'", "u'", "r'", "f'", "d'", "l'", "b'"
		};
		
		Twist[] twists = Twist.values();
		
		for (int i = 0, l = twists.length; i < l; i++) {
			Twist twist = twists[i];
			assertEquals(expected[i], twist.toString());
		}
	}
}