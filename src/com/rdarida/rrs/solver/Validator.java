package com.rdarida.rrs.solver;

import java.util.Arrays;

import com.rdarida.rrs.RRSException;
import com.rdarida.rrs.cube.Center;
import com.rdarida.rrs.cube.Corner;
import com.rdarida.rrs.cube.Cube;
import com.rdarida.rrs.cube.Edge;

public class Validator {
	private static final int[] EDGE_PARITY_INDICES = {
		1, 2, 13, 14, 17, 18, 29, 30, 36, 39, 40, 43, 49, 50, 61, 62, 68, 71, 72, 75, 81, 82, 93, 94
	};
	
	private static final byte[][] EDGE_PARITY_TABLE = {
			//W, R, B, O, G, Y
		/*W*/{0, 0, 1, 0, 1, 0},
		/*R*/{1, 0, 0, 0, 0, 1},
		/*B*/{0, 1, 0, 1, 0, 0},
		/*O*/{1, 0, 0, 0, 0, 1},
		/*G*/{0, 1, 0, 1, 0, 0},
		/*Y*/{0, 0, 1, 0, 1, 0}
	};
	
	private static final int[] CORNER_PARITY_INDICES = {
		0, 3, 12, 15, 16, 19, 28, 31, 32, 35, 44, 47, 48, 51, 60, 63, 64, 67, 76, 79, 80, 83, 92, 95
	};
	
	private static final byte[][] CORNER_PARITY_TABLE = {
			  //W, R, B, O, G, Y
		/*ULB*/{0, 1, 0, 0, 2, 0},
		/*URF*/{0, 0, 2, 1, 0, 0},
		/*URB*/{0, 0, 0, 2, 1, 0},
		/*ULF*/{0, 2, 1, 0, 0, 0},
		/*DLF*/{0, 1, 2, 0, 0, 0},
		/*DRB*/{0, 0, 0, 1, 2, 0},
		/*DRF*/{0, 0, 1, 2, 0, 0},
		/*DLB*/{0, 2, 0, 0, 1, 0}
	};
	
	public void validate(Cube cube) throws RRSException {
		validateColoring(cube);
		validateCenters(cube);
		validateEdges(cube);
		validateCorners(cube);
		validateEdgeParity(cube);
		validateCornerParity(cube);
	}
	
	private void validateColoring(Cube cube) throws RRSException {
		byte[] colors = cube.getColors();
		byte[] histogram = new byte[Cube.NUM_FACES];
		
		for (int i = 0; i < Cube.SIZE; i++) {
			histogram[colors[i]]++;
		}
		
		for (int i = 0; i < Cube.NUM_FACES; i++) {
			if (histogram[i] != Cube.N2) {
				throw new RRSException(RRSException.INVALID_COLORING);
			}
		}
	}
	
	private void validateCenters(Cube cube) throws RRSException {
		byte[] colors = cube.getColors();
		Center[] centers = Center.values();
		byte[] histogram = new byte[Cube.NUM_FACES];
		
		for (int i = 0; i < Center.NUM_CENTERS; i++) {
			histogram[colors[centers[i].x]]++;
		}
		
		for (int i = 0; i < Cube.NUM_FACES; i++) {
			if (histogram[i] != Cube.N) {
				throw new RRSException(RRSException.INVALID_CENTERS);
			}
		}
	}
	
	private void validateEdges(Cube cube) throws RRSException {
		byte[] colors = cube.getColors();
		int[] expected = Edge.VALUES.clone();
		Arrays.sort(expected);
		
		Edge[] edges = Edge.values();
		int[] values = new int[Edge.NUM_EDGES];
		
		for (int i = 0; i < Edge.NUM_EDGES; i++) {
			Edge edge = edges[i];
			int value = 0;
			
			value += Math.pow(colors[edge.x], 2);
			value += Math.pow(colors[edge.y], 2);
			
			values[i] = value;
		}
		
		Arrays.sort(values);
		
		if (!Arrays.equals(expected, values)) {
			throw new RRSException(RRSException.INVALID_EDGES);
		}
	}
	
	private void validateCorners(Cube cube) throws RRSException {
		byte[] colors = cube.getColors();
		int[] expected = Corner.VALUES.clone();
		Arrays.sort(expected);
		
		Corner[] corners = Corner.values();
		int length = corners.length;
		int[] actual = new int[length];
		
		for (int i = 0; i < length; i++) {
			Corner corner = corners[i];
			int value  = 0;
			
			value += Math.pow(colors[corner.x], 2);
			value += Math.pow(colors[corner.y], 2);
			value += Math.pow(colors[corner.z], 2);
			
			actual[i] = value;
		}
		
		Arrays.sort(actual);
		
		if (!Arrays.equals(expected, actual)) {
			throw new RRSException(RRSException.INVALID_CORNERS);
		}
	}
	
	private void validateEdgeParity(Cube cube) throws RRSException {
		byte[] colors = cube.getColors();
		byte[] counter = new byte[Cube.SIZE];
		Edge[] edges = Edge.values();
		
		for (int i = 0; i < Edge.NUM_EDGES; i++) {
			Edge edge = edges[i];
			
			int color0 = colors[edge.x];
			int color1 = colors[edge.y];
			
			counter[edge.x] = EDGE_PARITY_TABLE[color0][color1];
			counter[edge.y] = EDGE_PARITY_TABLE[color1][color0];
		}
		
		int sum = 0;
		
		for (int i = 0; i < EDGE_PARITY_INDICES.length; i++) {
			sum += counter[EDGE_PARITY_INDICES[i]];
		}
		
		if (sum % 2 == 1) {
			throw new RRSException(RRSException.INVALID_EDGE_PARITY);
		}
	}
	
	private void validateCornerParity(Cube cube) throws RRSException {
		byte[] colors = cube.getColors();
		byte[] counter = new byte[Cube.SIZE];
		Corner[] corners = Corner.values();
		
		for (int i = 0; i < Corner.NUM_CORNERS; i++) {
			Corner corner = corners[i];
			byte[] table = CORNER_PARITY_TABLE[corner.indexOf(cube)];
			
			counter[corner.x] = table[colors[corner.x]];
			counter[corner.y] = table[colors[corner.y]];
			counter[corner.z] = table[colors[corner.z]];
		}
		
		int[] values = new int[Cube.NUM_FACES];
		int sum, cnt = 0;
		
		for (int i = 0, l = CORNER_PARITY_INDICES.length; i < l; i += 4) {
			sum  = counter[CORNER_PARITY_INDICES[i]];
			sum += counter[CORNER_PARITY_INDICES[i + 1]];
			sum += counter[CORNER_PARITY_INDICES[i + 2]];
			sum += counter[CORNER_PARITY_INDICES[i + 3]];
			values[cnt++] = sum;
		}
		
		values[0] += values[5];
		values[1] += values[3];
		values[2] += values[4];
		
		if (0 < values[0] % 3 || 0 < values[1] % 3 || 0 < values[2] % 3) {
			throw new RRSException(RRSException.INVALID_CORNER_PARITY);
		}
	}
}