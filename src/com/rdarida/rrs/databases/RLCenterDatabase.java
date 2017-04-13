package com.rdarida.rrs.databases;

import java.util.LinkedList;

import com.rdarida.rrs.cube.Center;
import com.rdarida.rrs.cube.Cube;
import com.rdarida.rrs.cube.Twist;

/**
Running time of data/rlcenter.ddb:139.0666 minutes.
0	1
1	12
2	229
3	4302
4	64570
5	750140
6	6356320
7	25791392
8	18361188
9	154816
*/
public class RLCenterDatabase extends Database {
	private static final String PATH = "data/rlcenter.ddb";
	private static final int SIZE = 51482970;
	private static final Center[] CENTERS = Center.values();
	private static RLCenterDatabase Instance;
	
	public static RLCenterDatabase getInstance() {
		if (Instance == null) {
			Instance = new RLCenterDatabase();
		}
		
		return Instance;
	}
	
	public RLCenterDatabase() {
		super(PATH, SIZE);
	}
	
	@Override
	public void run() {
		Twist[] generators = Twist.values();
		LinkedList<Cube> cubes = new LinkedList<Cube>();		
		cubes.addLast(new Cube());
		generate(generators, cubes);
	}
	
	@Override
	protected int index(Cube cube) {
		byte[] colors = cube.getColors();
		int length = CENTERS.length;
		int[] permutation = new int[length];
		
		for (int i = 0; i < length; i++) {
			byte color = colors[CENTERS[i].x];
			permutation[i] = color == Cube.O || color == Cube.R ? color : 0;
		}
		
		return index(permutation);
	}
}