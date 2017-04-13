package com.rdarida.rrs.cube;

import static org.junit.Assert.*;

import org.junit.Test;

import com.rdarida.rrs.cube.Cube;
import com.rdarida.rrs.cube.Twist;

public class CubeTest {
	@Test
	public void testTwist() {
		byte[] expected = {
			0,1,2,3, 1,5,4,0, 4,2,0,3, 4,5,4,1,
			1,2,0,0, 5,0,5,5, 1,3,3,3, 5,4,3,5,
			3,3,3,2, 1,1,3,5, 5,5,3,3, 1,0,4,0,
			0,0,1,2, 2,0,1,1, 2,1,2,5, 3,5,5,5,
			5,0,4,4, 2,4,5,1, 2,0,1,0, 3,0,3,1,
			2,2,1,2, 2,4,2,4, 0,4,2,4, 4,4,3,4
		};
		
		Cube cube = new Cube();
		
		cube.twist(Twist.b);
		cube.twist(Twist.l);
		cube.twist(Twist.R);
		cube.twist(Twist.d2);
		cube.twist(Twist.l2);
		cube.twist(Twist.r);
		cube.twist(Twist.D);
		cube.twist(Twist.u2);
		cube.twist(Twist.F_);
		cube.twist(Twist.U2);
		cube.twist(Twist.F2);
		cube.twist(Twist.L);
		cube.twist(Twist.r2);
		cube.twist(Twist.d);
		cube.twist(Twist.L);
		
		assertArrayEquals(expected, cube.getColors());
	}
	
	@Test
	public void testIsSolved() {
		Cube cube = new Cube();
		assertTrue(cube.isSolved());
		
		cube.twist(Twist.U);
		cube.twist(Twist.U2);
		cube.twist(Twist.U_);
		cube.twist(Twist.U2);
		
		cube.twist(Twist.R);
		cube.twist(Twist.R2);
		cube.twist(Twist.R_);
		cube.twist(Twist.R2);
		
		cube.twist(Twist.F);
		cube.twist(Twist.F2);
		cube.twist(Twist.F_);
		cube.twist(Twist.F2);
		
		cube.twist(Twist.D);
		cube.twist(Twist.D2);
		cube.twist(Twist.D_);
		cube.twist(Twist.D2);
		
		cube.twist(Twist.L);
		cube.twist(Twist.L2);
		cube.twist(Twist.L_);
		cube.twist(Twist.L2);
		
		cube.twist(Twist.B);
		cube.twist(Twist.B2);
		cube.twist(Twist.B_);
		cube.twist(Twist.B2);
		
		cube.twist(Twist.u);
		cube.twist(Twist.u2);
		cube.twist(Twist.u_);
		cube.twist(Twist.u2);
		
		cube.twist(Twist.r);
		cube.twist(Twist.r2);
		cube.twist(Twist.r_);
		cube.twist(Twist.r2);
		
		cube.twist(Twist.f);
		cube.twist(Twist.f2);
		cube.twist(Twist.f_);
		cube.twist(Twist.f2);
		
		cube.twist(Twist.d);
		cube.twist(Twist.d2);
		cube.twist(Twist.d_);
		cube.twist(Twist.d2);
		
		cube.twist(Twist.l);
		cube.twist(Twist.l2);
		cube.twist(Twist.l_);
		cube.twist(Twist.l2);
		
		cube.twist(Twist.b);
		cube.twist(Twist.b2);
		cube.twist(Twist.b_);
		cube.twist(Twist.b2);
		
		assertTrue(cube.isSolved());
	}
}