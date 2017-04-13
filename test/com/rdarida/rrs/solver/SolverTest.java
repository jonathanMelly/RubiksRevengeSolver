package com.rdarida.rrs.solver;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.rdarida.rrs.RRSException;
import com.rdarida.rrs.cube.Cube;
import com.rdarida.rrs.cube.Twist;
import com.rdarida.rrs.databases.BackEdgeDatabase;
import com.rdarida.rrs.databases.FrontEdgeDatabase;
import com.rdarida.rrs.databases.RLCenterDatabase;
import com.rdarida.rrs.databases.UDFBCenterDatabase;

/*
 * The test are generated with WCA Official Cube Scrambler.
 * https://www.worldcubeassociation.org/regulations/history/files/scrambles/scramble_cube.htm?size=4&num=1&len=20&col=yrgwob&subbutton=Scramble%21
 */
public class SolverTest {
	private static final String PATTERN = "WRBOGY";
	private static Validator validator;
	private static Solver solver;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		RLCenterDatabase.getInstance().load();
		UDFBCenterDatabase.getInstance().load();
		BackEdgeDatabase.getInstance().load();
		FrontEdgeDatabase.getInstance().load();
		
		validator = new Validator();
		solver = new Solver();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		validator = null;
		solver = null;
	}
	
	@Test
	public void testRandomScramble() {
		try {
			Cube cube = new Cube();
			cube.scramble();
			
			validator.validate(cube);
			solver.solve(cube);
			
			assertTrue(cube.isSolved());
		}
		catch (RRSException exception) {
			fail(exception.getMessage());
		}
	}
	
	@Test
	public void testSolve0() {
		try {
			Cube cube = fromString(
				  "ywoo bbgy rrwr wgyg"
				+ "gogo brgr oogr bwry"
				+ "gwgr wogb goyw ryyy"
				+ "wbrw obbg oyrb oywb"
				+ "bgrr wbwy wowg ryby"
				+ "bgog byyr brwo oyow"
			);
			
			validator.validate(cube);
			solver.solve(cube);
			
			assertTrue(cube.isSolved());
		}
		catch (RRSException exception) {
			fail(exception.getMessage());
		}
	}

	@Test
	public void testSolve1() {
		try {
			Cube cube = fromString(
				  "ywbw wbry gryy rrbo"
				+ "boow gogg yyob rorg"
				+ "ggww yobb obwr yoww"
				+ "bobr yryo bggw ggyb"
				+ "brbo gwbr ggwr yorg"
				+ "obro yrow yywg ywwr"
			);
			
			validator.validate(cube);
			solver.solve(cube);
			
			assertTrue(cube.isSolved());
		}
		catch (RRSException exception) {
			fail(exception.getMessage());
		}
	}
	
	@Test
	public void testSolve2() {
		try {
			Cube cube = fromString(
				  "brgo bwry wrby wbwb"
				+ "orog royr bory gybr"
				+ "rwgo ggoo oggo bbrb"
				+ "wgbg wywg grwo rwyy"
				+ "woyy rbbw yybr owyy"
				+ "yoww ooyb bwgr rggg"
			);
			
			validator.validate(cube);
			solver.solve(cube);
			
			assertTrue(cube.isSolved());
		}
		catch (RRSException exception) {
			fail(exception.getMessage());
		}
	}
	
	@Test
	public void testSolve3() {
		try {
			Cube cube = fromString(
				  "ryrr obbo ggob wogo"
				+ "wwyo yygo obrb gowy"
				+ "ggwy bbgw yyog ggyy"
				+ "gwwy rrwy rrww brrb"
				+ "bbrb oogb rwry wbbr"
				+ "rogo gyog bwyy wwro"
			);
			
			validator.validate(cube);
			solver.solve(cube);
			
			assertTrue(cube.isSolved());
		}
		catch (RRSException exception) {
			fail(exception.getMessage());
		}
	}
	
	@Test
	public void testSolve4() {
		try {
			Cube cube = fromString(
				  "bbrw bwgw oorw bwog"
				+ "rrgr ryob bwgy obgg"
				+ "ybbo obrg orww rgyo"
				+ "yrgg wbbw oroo bbro"
				+ "ryyw oyow gwgr wryb"
				+ "yrow ygbw yyyy yggg"
			);
			
			validator.validate(cube);
			solver.solve(cube);
			
			assertTrue(cube.isSolved());
		}
		catch (RRSException exception) {
			fail(exception.getMessage());
		}
	}
	
	@Test
	public void testSolve5() {
		try {
			Cube cube = fromString(
				  "wrgw rwgo wyrb yybr"
				+ "rwbo yboy ogry bowg"
				+ "booy rwbb gwbg yorw"
				+ "bybb wogy rorr gwgg"
				+ "oywg orbb byyg rwrw"
				+ "ogbo ggwo wyor rygy"
			);
			
			validator.validate(cube);
			solver.solve(cube);
			
			assertTrue(cube.isSolved());
		}
		catch (RRSException exception) {
			fail(exception.getMessage());
		}
	}
	
	@Test
	public void testSolve6() {
		try {
			Cube cube = fromString(
				  "bgww bybw wwwy gwry"
				+ "oogr oobw ogow ggbw"
				+ "wgyb ooyb rbog ogor"
				+ "rbrr rgyr orro ybgg"
				+ "bbyy bwry gbgw obyo"
				+ "bryg owgw yyrr yryw"
			);
			
			validator.validate(cube);
			solver.solve(cube);
			
			assertTrue(cube.isSolved());
		}
		catch (RRSException exception) {
			fail(exception.getMessage());
		}
	}
	
	@Test
	public void testSolve7() {
		try {
			Cube cube = fromString(
				  "gywo oyro wwgb obyb"
				+ "wbob ybwo boor ggrr"
				+ "yror yyrr gbrr wggw"
				+ "yowg gbbw wgwb ogby"
				+ "wrrr brgb ywor owgy"
				+ "byob yygw oyow rygg"
			);
			
			validator.validate(cube);
			solver.solve(cube);
			
			assertTrue(cube.isSolved());
		}
		catch (RRSException exception) {
			fail(exception.getMessage());
		}
	}
	
	@Test
	public void testSolve8() {
		try {
			Cube cube = fromString(
				  "gwgy rryb grbo gggg"
				+ "oywo ybyr ooby yybo"
				+ "yyor byrg rgow bbrb"
				+ "wyor roww bobb rwbr"
				+ "gwbw oywo ygwg bgwo"
				+ "woww rwgo ggry brry"
			);
			
			validator.validate(cube);
			solver.solve(cube);
			
			assertTrue(cube.isSolved());
		}
		catch (RRSException exception) {
			fail(exception.getMessage());
		}
	}
	
	@Test
	public void testSolve9() {
		try {
			Cube cube = fromString(
				  "ryog yggw rorb orrb"
				+ "wowy yyro ryor rbow"
				+ "gyyo wgwb bwbr gggr"
				+ "wwgy yogb gbyg bbwy"
				+ "rwob wwrg yyrg bwbw"
				+ "oooy bwby robr gogo"
			);
			
			validator.validate(cube);
			solver.solve(cube);
			
			assertTrue(cube.isSolved());
		}
		catch (RRSException exception) {
			fail(exception.getMessage());
		}
	}
	
	@Ignore @Test
	public void testSolve10() {
		String[] tests = {
			"B F u' L D' r b F D' f2 l2 d u B2 u2 L' l2 u2 U' B f' d' u' F2 d2 u L' R d U' B2 l r' f2 L2 r B' f r' F2",
			"f' d2 U' r' D' R' b r' d l' d2 L f' R2 f L r' b f' r F2 u2 f r2 F r R' d l u' f' L2 r2 b f2 r' u2 B2 l R'",
			"B u' B2 d' R' B2 F D2 u2 B' r2 d' u2 F l' u2 b' d' u' L D' u2 b2 r' D' d' R2 F D' b2 F u' U2 f' u f' F2 d r B2",
			"B u' U b2 d' u' f' R u U2 f' d2 U' b2 f' U l f' U2 R2 B' f D d2 U R D' U F' L' l' U2 B2 f' l' d U f' D' d'",
			"d' l U' B d2 B' b U' F2 l2 u2 U2 l f L2 B2 D2 d' F2 R' d2 B b2 U' B' d B b2 U' L' r2 D f' l R' u' U' r2 B2 R2",
			"b2 l2 d' L' r2 d2 L2 r2 B2 f2 L2 l2 u' F r R' d L' R B l2 d' u l R d f U L' B2 R2 d2 B F u' r R2 b L' u",
			"B b' D U2 B l2 r' D2 L R' F2 d2 u r2 R' u B d u L' D' f2 d U' F' r d2 u2 l2 B D' r f' u2 r2 R2 F' D2 f d",
			"f' F2 U l F2 r2 D' f' l2 u2 b f2 u' r' D2 r2 d2 R b2 l2 B f2 d u L' b2 R' D2 d2 f U2 B2 f' F l' D L' r b' l'",
			"L b' f r R' b2 d2 L' f' R D' r2 u r2 B2 r f U2 R2 d' B l f2 r2 U B' b2 u f U L2 b' F L U2 r B2 R' D' u2",
			"R2 f2 u' R F2 d l2 d l2 r' d' U' b2 D2 B b' R D2 d' l' R' u r2 R2 u R b' u2 b2 F L2 u' r d2 u' b' D U' b F'",
			"l2 F2 u2 U2 B l D' u2 F' R' D2 d' F D2 d' b' D' U2 F' D' b F2 D u L2 b2 f2 L' u' r' d U B' d' u2 F2 u2 B2 D u2",
			"d2 b' F2 r2 D' d2 b2 l2 U R' U r U f' u2 b2 D' r' f2 F2 D u' r B' d U2 F2 D' U2 r2 b d2 B2 D' r b' r R2 B' L'",
			"B' l R D L' F' L D b' r' f2 U' B2 b R D2 R B d l2 R U2 B2 f l2 r2 U' f2 L' f d b2 d2 L2 F U2 f D2 U F",
			"B2 l R2 u' l r' b' L' R' U B' D2 U2 b' F u' r u r2 d' b' U L r' B F' u' F R2 D2 u B D2 r2 d f F2 L B2 l2",
			"u l' u' r' R D b U f' F2 u' f r' R2 f R2 f' u b F' U' L r' U L B2 F' U' r d b' L' u2 B' b' u2 L2 U' f R'",
			"r f' F' D B f2 l' f' L2 B2 r' d' U' R D F' u B D2 r2 F2 L f' d U r2 u2 U' l b' u L R f u' r d' b f r2",
			"U r R2 B F r2 f2 D' L' l U f2 l F r2 d' U F l R2 B' R B' b2 u' r f' R' B' b u' B2 L' u2 B' b2 l u' b2 F2",
			"R' B F l' d L2 D2 u L' l2 u2 r' B2 b2 l R' d2 R' u' f2 U' r' d l' f2 l2 R f L l d U F2 u' U2 L2 l f F' L'",
			"d2 l' D2 r d U2 f2 R2 D' d B b' f2 U' l' d2 U2 L2 U' L r2 b' l2 R b U2 b' F d' B2 L l d2 u F2 l' D2 L2 R U",
			"U2 l2 r2 u2 B f2 d' B' l R2 F' d2 B' F' R' u' U2 f R d2 u' L2 l' f U' B R2 F D2 b2 d r' U' r D' U' l2 R2 B2 D' d' R2 u2 L2 B2 F2 d' u' B2 F' r2 R d B2 r' R B d f' F2 L2 f D2 F2 d l' D2 B r2 D' u2 f' U B' f2 l' b l u f' F d U f2 D2 L d2 F d2 L2 U2 l' F U' l2 r2 f2 u' b F2"
		};
		
		solveTests(10, tests);
	}
	
	@Ignore @Test
	public void testSolve11() {
		String[] tests = {
			"L f F2 L' l D2 L U' f d2 F2 R U2 L2 R' u L2 r' f' u2 l' D r R2 u F d b d2 B' r R2 b L l' U L2 l' R' f' D2 u F u f U l2 D' l' r' F2 d' F d' r f' r d2 b R",
			"L2 d2 b u' f L' f2 d U' F u2 l' b U2 L' l' u2 f d2 r2 U2 b' u F2 R2 B' F R2 B' F2 l2 u' U' b R2 B2 r' u R2 d' r B2 l2 R' f L2 l b2 f2 D U f' U' B' F d2 b' L' l d2",
			"D' F r' F2 U l U2 R' B2 D' b F2 r U' L2 l' R' U' R B' f u L2 F' l2 u2 B' R D B' f' r2 b2 F D u' B' l2 R2 b u B2 U2 l2 B u2 B2 l' f2 R2 U' f F D u2 U F u b f2",
			"b2 u B2 F' l' d F' U' L f' L' l R2 d2 f u L d R' d' l2 b D' U2 b2 r2 u F' L' R u' B2 U F' d' b r' b' L2 R2 f F l2 r' u' l' B D2 u R2 F2 d2 U2 r' B2 R2 u L r u'",
			"d2 L' r2 b' U' l2 u2 b2 L' b2 R F' U' B b2 u2 l2 R2 u2 B F' d2 R2 b2 u F' d2 u' B' f l2 b' U2 R' u2 B R' B d2 R' F' d' f2 F' d2 F' D2 B U2 B' f' l' B' l b' u2 L' d F2 r'",
			"D2 l' u2 R b l2 D' R' f2 U b L r b f2 D' u2 B D B2 b f r2 R D2 d L' f F' R F U f2 F2 D' L R2 D u' b F' D' d2 U' r u2 U2 L b' D' b U2 b D U' L' R2 d2 U r'",
			"B2 L b L' R F2 D' b' D r2 u' R u U' B f F2 R2 u' U2 b L2 r' d2 B' f2 L D2 d2 L2 d' r2 b2 R f' D' U2 f2 D L' d B d' U b' R2 b2 r' R' d2 R D' u2 B2 b' R f' U' F' D2",
			"d b2 f u' r b2 u2 L b U' B2 R B d L2 r' D2 d2 b2 F r2 d' L' r' f' D' F d' B F2 L' r' F u2 f d' l' B U L2 f R d F2 D b' l u' f' F2 l U r D' d' l2 R' u F' U2",
			"b2 f l' r2 b' F2 U2 r U' F R D' l' u L' l r2 f2 l2 f2 D' l' B2 b' l2 d' L' F' L R2 B l' F D U' l R2 B2 D' u' R d2 U l2 d2 R D' b2 f2 D2 u' B f u2 B U b' l2 U' F",
			"L r R2 d2 U2 b2 f F' U' F' l R' u r' d' u L2 r' B' r R' U l2 U' b d2 R' d B2 L2 r R' F2 L2 l' r' f' D L R2 F' L D2 u2 L R2 U2 L2 r R' D' d l F' D d' F2 l2 D' d2"
		};
		
		solveTests(11, tests);
	}
	
	@Test
	public void testSolve12() {
		String[] tests = {
			"B F L' U b2 D2 d' l r B2 L' b2 f2 L' d2 B' b2 l2 b2 f R D' u' L F2 L' B2 L D' L l u b' f l' D2 B' b2 L' F2 L R u2 B u' R D2 L2 D' d' f2 u U2 L' l' B' F' D2 f' u2 b' d' L2 r d2 u l' R U2 l2 B' r2 R' d2 f2 L' l U2 f U L' r2 b' r2 u2 B' b r2 R' F R2 b r2 d' r2 b F' L r2 B2 D' f L2 u F2 U' F R2 d B' R F' l' D' b d F L B u2 F R2 U L' l U' b' f' l F D2 d r2 U' r' b D' u2 b2 D' d2 B l' r' F' D2 u F' d2 B' b L' b' r2 D l2 r2 u2 f D u2 U R' b' F2 d2 f D2 d' b L' F' d u B' u2 r D' R' f2 d' U2 L D2 B b' L2 f r' f' F' u2 L b U' f L R2 U L'",
			"b2 d' u2 U' B' d' f F D L' D d b' L' d' L D' L2 r R' u U' r' F u B F' L R2 b' D2 u' U b R' d2 u' b2 r R2 F r B2 L D f2 u2 l' r u' b2 u' r D l2 R' D2 d' L U2 b2 F2 d' U' F d f2 L' u L2 B b R D U b U' L r2 B' l' U' L b l2 b2 f2 L' r f' R2 u U l r b D d' l' u2 U2 l' u B' F l2 b2 u2 b f' l b2 L' F' U b R b F2 l r d B F l2 U l B F r B2 u' B L' d' b' u' F' u' L2 D2 d U l2 U l r' u' f D' u2 b' D B2 f2 L2 r2 b F2 r' R D R B b' L' l' B' R f D' U' L' b f2 r R2 d' l' R F2 L D' U l2 b d' r2 b L2 R2 B2 f L' R d' u2 B2 b' R2",
			"D b' r f F D' d2 L R2 B' l' u2 U b d b' R' b2 u2 U' b' D2 d L2 D2 f' D' R b' D f' l' b R2 B2 d2 f L r' b2 R2 F' D' d2 L' r2 D' u R' d2 R2 d' r' D b' F' L2 D d2 B' F' d' U' L' D' f2 u b' u l2 r' D u2 L B' L B D U b r' d u l' b' R' u' b r2 b R2 B2 f' L' r' B b u' B' u2 f2 r D2 l R f' l U' R' F' l' R2 b' D' d2 b2 l2 R D2 r' B b2 u2 r2 b2 d U2 b f' u' B2 r b D2 d B R f' l2 r f F L2 F2 L' l' D2 d' B D u2 f' R' D L' u2 b2 D2 d2 r2 f' L b r R B' b d' u2 R' d l' R2 U2 b u' B2 F' D2 l2 r2 u2 l2 U' b2 l' d2 U2 l2 f F2 D' B2 D' l R D R D' B",
			"f2 L u' L2 r U L U' F2 d r U' B' f' F2 L' F U2 B' F L2 D' L2 r R' f R' b f r b' D R f r b L f u' U' B2 L2 B' l2 R' U2 B2 L l2 u b d U' f' d' R2 U l' B l' d' U' b' D L2 D2 R' U2 b2 D d F' u2 U2 F r' U' f r f2 F2 r f' u U B' L' B2 D l2 b2 f F' D' u2 b2 U2 B' b F2 d u' B f l U2 r2 f2 d2 l2 b' D U l2 d2 l' d' L' l2 f F2 L' d2 b' L R U2 r2 b2 F L2 f' D2 d' u' F R' f' D' L2 f' r' U2 L B F l U' r b' F2 l' R' B2 F2 D2 U B2 f r' d r' d2 U L B' f' D2 r' B' D B' d2 r d' U2 B r u' F2 U' b2 u' U r2 d2 u b' D F r2 R B' L' l2 D F d' U F'",
			"d u F2 d u2 B' f R d2 u2 F2 R b2 r' R2 D' B' R' u l' R b' l f' r2 B F2 r' B F' L' l2 r' B' D2 R' B' f' D f' D b' f2 u U' b' d B L2 D' d2 U' F L2 r' R d' u b u F2 r F2 U L2 l2 B' f2 D2 f D2 b2 D U2 f2 u' B2 f l' R' B' r d2 b L D2 B' b U' l F L' R F' D2 u2 f D b F' u' b F D2 U l d R b F l2 d L2 f' F' D' u' L F L' l B f2 u B r2 u2 R' U L R' d U' B' d2 U2 l' F' R b2 L' f2 u2 U2 f D2 d2 L b R' B D b' u2 U l2 f' R U2 f R b' F D2 f' F2 u2 l2 D d' L2 l2 f' D' R d' f F2 D' f u' r' D2 d' R2 B' l2 R2 U' l B' d2 U' l' r2 B2 b2 d B2 u'"
		};
		
		solveTests(12, tests);
	}
	
	@Test
	public void testSolve13() {
		String[] tests = {
			"b' u2 l2 r2 U l' R2 d' B2 U b2 F2 d2 l2 R d f' R D' B f D f' F2 D' d2 r R' B2 b2 L2 r b D2 B' L b' r B2 d2",
		};
		
		double runtime = System.currentTimeMillis();
		solveTests(13, tests);
		System.out.println(((System.currentTimeMillis() - runtime) / 1000.0));
	}
	
	private void solveTests(int id, String[] tests) {
		try {
			for (int i = 0, l = tests.length; i < l; i++) {
				System.out.println("Test" + id + ": " + i);
				Cube cube = new Cube();
				shuffle(cube, tests[i]);
				validator.validate(cube);
				solver.solve(cube);
				assertTrue("Test" + i, cube.isSolved());
			}
		}
		catch (RRSException exception) {
			fail(exception.getMessage());
		}
	}
	
	private void shuffle(Cube cube, String input) {
		String[] twists = input.replaceAll("'", "_").split(" ");
		
		for (int i = 0, l = twists.length; i < l; i++) {
			cube.turn(Twist.valueOf(twists[i]));
		}
	}
	
	@Ignore @Test
	public void testPostProcess() {
		fail("Not yet implemented");
	}
	
	public static Cube fromString(String input) {
		input = input.replaceAll(" ", "").toUpperCase();
		byte[] colors = new byte[Cube.SIZE];
		
		for (int i = 0; i < Cube.SIZE; i++) {
			colors[i] = (byte) PATTERN.indexOf(input.charAt(i));
		}
		
		return new Cube(colors);
	}
}