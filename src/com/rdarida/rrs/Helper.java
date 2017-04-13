package com.rdarida.rrs;

import java.util.Arrays;

import com.rdarida.rrs.cube.Center;
import com.rdarida.rrs.cube.Corner;
import com.rdarida.rrs.cube.Cube;
import com.rdarida.rrs.cube.Dedge;
import com.rdarida.rrs.cube.Edge;
import com.rdarida.rrs.cube.Twist;

public class Helper {
	public static void main(String[] args) {		
		Cube cube = new Cube();		
		printCenterValuesAndIndices(cube);
		printEdgeValuesAndIndices(cube);
		printDedgeValuesAndIndices(cube);
		printCornerValues(cube);
		
		printCornerParityValues();
		
		printEdgeParityValues();
	}
	
	private static void printCenterValuesAndIndices(Cube cube) {
		byte[] colors = cube.getColors();
		Center[] centers = Center.values();
		int[] values = new int[Center.NUM_CENTERS];
		
		for (int i = 0; i < Center.NUM_CENTERS; i++) {
			values[i] = colors[centers[i].x];
		}
		
		printArray("Center values:", values);
		
		for (int i = 0; i < Center.NUM_CENTERS; i++) {
			values[i] = centers[i].indexOf(cube);
		}
		
		printArray("Center configuration:", values);
	}
	
	private static void printEdgeValuesAndIndices(Cube cube) {
		byte[] colors = cube.getColors();
		Edge[] edges = Edge.values();
		int[] values = new int[Edge.NUM_EDGES];
		
		for (int i = 0; i < Edge.NUM_EDGES; i++) {
			Edge edge = edges[i];
			
			int value = 0;
			value += Math.pow(colors[edge.x], 2);
			value += Math.pow(colors[edge.y], 2);
			
			values[i] = value;
		}
		
		printArray("Edge values:", values);
		
		for (int i = 0; i < Edge.NUM_EDGES; i++) {
			values[i] = edges[i].indexOf(cube);
		}
		
		printArray("Edge indices:", values);
	}
	
	private static void printDedgeValuesAndIndices(Cube cube) {
		byte[] colors = cube.getColors();
		Dedge[] dedges = Dedge.values();
		int[] values = new int[Dedge.NUM_DEDGES];
		
		for (int i = 0; i < Dedge.NUM_DEDGES; i++) {
			Dedge dedge = dedges[i];
			
			int value = 0;
			value += Math.pow(colors[dedge.x], 2);
			value += Math.pow(colors[dedge.y], 2);
			
			values[i] = value;
		}
		
		printArray("Dedge values:", values);
		
		for (int i = 0; i < Dedge.NUM_DEDGES; i++) {
			values[i] = dedges[i].indexOf(cube);
		}
		
		printArray("Dedge indices:", values);
	}
	
	private static void printCornerValues(Cube cube) {
		byte[] colors = cube.getColors();
		Corner[] corners = Corner.values();
		int[] values = new int[Corner.NUM_CORNERS];
		
		for (int i = 0; i < Corner.NUM_CORNERS; i++) {
			Corner corner = corners[i];
			
			int value = 0;
			value += Math.pow(colors[corner.x], 2);
			value += Math.pow(colors[corner.y], 2);
			value += Math.pow(colors[corner.z], 2);
			
			values[i] = value;
		}
		
		printArray("Corner values:", values);
		
		for (int i = 0; i < Corner.NUM_CORNERS; i++) {
			values[i] = corners[i].indexOf(cube);
		}
		
		printArray("Corner indices:", values);
	}
	
	private static void printArray(String name, int[] array) {
		System.out.println(name);
		System.out.println(Arrays.toString(array));
		System.out.println();
	}
	
	private static void printCornerParityValues() {
		int[] mask = {0, 5, 1, 3, 2, 4};
		
		Twist[] twists = {
			Twist.U, Twist.L, Twist.F, Twist.R, Twist.B, Twist.D
		};
		
		System.out.print("    ");
		
		for (int i = 0; i < Cube.NUM_FACES; i++) {
			System.out.print(twists[mask[i]] + "  ");
		}
		
		System.out.println();
		
		int[] values = new int[Cube.NUM_FACES];
		
		byte[] colors = {
			0,0,0,0, 0,0,0,0, 0,0,0,0, 0,0,0,0,
			1,0,0,2, 0,0,0,0, 0,0,0,0, 2,0,0,1,
			1,0,0,2, 0,0,0,0, 0,0,0,0, 2,0,0,1,
			1,0,0,2, 0,0,0,0, 0,0,0,0, 2,0,0,1,
			1,0,0,2, 0,0,0,0, 0,0,0,0, 2,0,0,1,
			0,0,0,0, 0,0,0,0, 0,0,0,0, 0,0,0,0
		};
		
		Cube cube = new Cube(colors.clone());
		
		for (Twist twist : twists) {
			cube.turn(twist);
			
			for (int i = 0; i < Cube.NUM_FACES; i++) {
				int sum = 0;
				
				for (int j = 0; j < Cube.N2; j++) {
					int index = mask[i] * Cube.N2 + j;
					sum += cube.getColors()[index];
				}
				
				values[i] = sum;
			}
			
			System.out.println(twist + ": " + Arrays.toString(values));
		}
	}
	
	private static void printEdgeParityValues() {
		int[] window = {
			1, 2, 13, 14,
			17, 18, 29, 30,
			36, 39, 40, 43,
			49, 50, 61, 62,
			68, 71, 72, 75,
			81, 82, 93, 94
		};
		
		Twist[] twists = {
			Twist.U, Twist.L, Twist.F, Twist.R, Twist.B, Twist.D,
			Twist.u, Twist.l, Twist.f, Twist.r, Twist.b, Twist.d
		};
		
		byte[] colors = {
			0,1,1,0, 0,0,0,0, 0,0,0,0, 0,1,1,0,
			0,1,1,0, 0,0,0,0, 0,0,0,0, 0,1,1,0,
			0,0,0,0, 1,0,0,1, 1,0,0,1, 0,0,0,0,
			0,1,1,0, 0,0,0,0, 0,0,0,0, 0,1,1,0,
			0,0,0,0, 1,0,0,1, 1,0,0,1, 0,0,0,0,
			0,1,1,0, 0,0,0,0, 0,0,0,0, 0,1,1,0
		};
		
		int[] values = new int[twists.length];
		Cube cube = new Cube(colors.clone());
		
		for (int t = 0; t < twists.length; t++) {
			cube.turn(twists[t]);
			
			int sum = 0;
			
			for (int i = 0; i < window.length; i++) {
				sum += cube.getColors()[window[i]];
			}
			
			values[t] = sum;
		}
		
		System.out.println("Edge parity value:");
		System.out.println(Arrays.toString(values));
	}
}
