package com.rdarida.rrs.cube;

import static org.junit.Assert.*;

import org.junit.Test;

import com.rdarida.rrs.cube.Center;

public class CenterTest {
	@Test
	public void testIndex() {
		int[] expected = {
			5,  6,  9,  10,
			53, 54, 57, 58,
			37, 38, 41, 42,
			85, 86, 89, 90,
			21, 22, 25, 26,
			69, 70, 73, 74
		};
		
		Center[] values = Center.values();
		
		for (int i = 0, l = values.length; i < l; i++) {
			Center center = values[i];
			assertEquals(center.toString(), expected[i], center.x);
		}
	}
}