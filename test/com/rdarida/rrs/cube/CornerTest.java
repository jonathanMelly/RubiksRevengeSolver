package com.rdarida.rrs.cube;

import static org.junit.Assert.*;

import org.junit.Test;

import com.rdarida.rrs.cube.Corner;
import com.rdarida.rrs.cube.Cube;

public class CornerTest {
	@Test
	public void testIndices() {
		int[][] expected = {
			{ 0, 16, 67}, {15, 48, 35}, { 3, 51, 64}, {12, 19, 32},
			{80, 31, 44}, {95, 63, 76}, {83, 60, 47}, {92, 28, 79}
		};
		
		Corner[] values = Corner.values();
		
		for (int i = 0, l = values.length; i < l; i++) {
			Corner corner = values[i];
			int[] actual = {corner.x, corner.y, corner.z};
			assertArrayEquals(corner.toString(), expected[i], actual);
		}
	}
	
	@Test
	public void testIndexOf() {
		int[] expected = {0, 1, 2, 3, 4, 5, 6, 7};
		int[] actual = new int[Corner.NUM_CORNERS];
		Corner[] corners = Corner.values();
		Cube cube = new Cube();
		
		for (int i = 0; i < Corner.NUM_CORNERS; i++) {
			actual[i] = corners[i].indexOf(cube);
		}
		
		assertArrayEquals(expected, actual);
	}
}