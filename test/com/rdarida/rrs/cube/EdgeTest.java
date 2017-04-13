package com.rdarida.rrs.cube;

import static org.junit.Assert.*;

import org.junit.Test;

import com.rdarida.rrs.cube.Edge;

public class EdgeTest {
	@Test
	public void testIndices() {
		int[][] expected = {
			{ 1, 66}, { 2, 65}, { 4, 17}, { 7, 50}, { 8, 18}, {11, 49}, {13, 33}, {14, 34},
			{20, 71}, {55, 68}, {23, 36}, {52, 39}, {27, 40}, {56, 43}, {24, 75}, {59, 72},
			{81, 45}, {82, 46}, {84, 30}, {87, 61}, {88, 29}, {91, 62}, {93, 78}, {94, 77}
		};
		
		Edge[] values = Edge.values();
		
		for (int i = 0, l = values.length; i < l; i++) {
			Edge edge = values[i];
			int[] actual = {edge.x, edge.y};
			assertArrayEquals(edge.toString(), expected[i], actual);
		}
	}
}