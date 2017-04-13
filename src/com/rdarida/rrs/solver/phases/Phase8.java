package com.rdarida.rrs.solver.phases;

import com.rdarida.rrs.cube.Corner;
import com.rdarida.rrs.cube.Cube;
import com.rdarida.rrs.cube.Dedge;
import com.rdarida.rrs.cube.Twist;

/*
 * 8!*3^7
 * 12!*2^11
 * The interesting part is the 7 and the 11, 
 * cornerConfiguration.length - 1 = 7,
 * dedgeConfiguration.length - 1 = 11.
 */
public class Phase8 extends Phase5 {
	public Phase8() {
		super();
		
		generators = new Twist[] {
			Twist.U2, Twist.R2, Twist.F2, Twist.D2, Twist.L2, Twist.B2
		};
	}
	
	@Override
	protected long index(Cube cube) {
		long ret = 0;
		int index = 0;
		
		int[] cornerConfiguration = Corner.getConfiguration(cube);
		
		for (int i = 0; i < Corner.NUM_CORNERS - 1; i++) {
			ret += (cornerConfiguration[i] << index);
			index += 2;
		}
		
		int[] dedgeConfiguration = Dedge.getConfiguration(cube);
		
		for (int i = 0; i < Dedge.NUM_DEDGES - 1; i++) {
			ret += (dedgeConfiguration[i] << index);
			index += 2;
		}
		
		return ret;
	}
}