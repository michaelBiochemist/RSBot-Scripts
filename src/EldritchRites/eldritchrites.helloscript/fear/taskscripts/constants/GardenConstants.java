package fear.taskscripts.constants;
import fear.taskscripts.constants.PathConstants;
import fear.taskscripts.Spot;

import fear.taskscripts.GardenNpcRange;
import fear.taskscripts.GardenStage;

import org.powerbot.script.Tile;


public class GardenConstants {
	public GardenStage[] SPRING_GARDEN;
	public GardenStage[] AUTUMN_GARDEN;
	
	public GardenConstants() {
		// North = + on y axis, East = + on X-axis
		int north = PathConstants.FACE_NORTH;
		int south = PathConstants.FACE_SOUTH;
		int east = PathConstants.FACE_EAST;
		int west = PathConstants.FACE_WEST;
		System.out.println("North: "+north+" South: "+south+" East: "+east+" West: "+west);
		
		GardenStage springPoint0 = new GardenStage(null,new Tile(2923, 5471, 0),false);
		
		final Spot useRange1[] = {
			new Spot(2922,5470,0,south),
			new Spot(2922,5469,0,south),
			new Spot(2922,5468,0,south),
			new Spot(2922,5467,0,south),
			new Spot(2922,5466,0,south)
		};
		GardenNpcRange springNpc1Range = new GardenNpcRange(2956,useRange1);
		GardenNpcRange springNpc1Ranges[] = {
				springNpc1Range
		};

		GardenStage springPoint1 = new GardenStage(springNpc1Ranges,new Tile(2923, 5465,0),false);
		final Spot useRange2[] = {
				new Spot(2922,5470,0,north),
				new Spot(2922,5469,0,north),
				new Spot(2922,5468,0,north),
				new Spot(2922,5467,0,north),
				new Spot(2922,5466,0,north)
			};
		GardenNpcRange springNpc2Range = new GardenNpcRange(2956,useRange1);
		GardenNpcRange springNpc2Ranges[] = {
				springNpc2Range
		};
		GardenStage springPoint2 = new GardenStage(springNpc2Ranges,new Tile(2923, 5459, 0),false);
		
		final Spot useRange30[] = {
				new Spot(2924,5461,0,east),
				new Spot(2925,5461,0,east),
				new Spot(2926,5461,0,east),
				new Spot(2926,5461,0,north),
				new Spot(2926,5460,0,north)
		};

		final Spot useRange31[] = {
				new Spot(2926,5463,0,east),
				new Spot(2927,5463,0,east),
				new Spot(2928,5463,0,east),
				new Spot(2929,5463,0,east),
				new Spot(2930,5463,0,east),
				new Spot(2930,5463,0,north),
				new Spot(2930,5462,0,north),
				new Spot(2930,5461,0,north)
		};
		final Spot useRange32[] = {
				new Spot(2925,5466,0,north),
				new Spot(2925,5467,0,north),
				new Spot(2925,5468,0,north),
				new Spot(2925,5469,0,north),
				new Spot(2925,5470,0,north),
				new Spot(2925,5471,0,north),
				new Spot(2925,5472,0,north),
				new Spot(2925,5473,0,north),
				//new Spot(2925,5474,0,north)
				//new Spot(2925,5475,0,north),
				/**new Spot(2925,5466,0,south),
				new Spot(2925,5467,0,south),
				new Spot(2925,5468,0,south),
				new Spot(2925,5469,0,south),
				new Spot(2925,5470,0,south),*/
				//new Spot(2925,5474,0,south),
				//new Spot(2925,5475,0,south)
		};
		GardenNpcRange springNpc3Ranges[] = {
				new GardenNpcRange(2958,useRange30),
				new GardenNpcRange(2957,useRange31),
				new GardenNpcRange(2962,useRange32)
		};
		GardenStage springPoint3 = new GardenStage(springNpc3Ranges,new Tile(2924,5468,0),true);
		
		final Spot useRange4[] = {
				new Spot(2925,5469,0,north),
				new Spot(2925,5470,0,north),
				new Spot(2925,5471,0,north),
				new Spot(2925,5468,0,south),
				new Spot(2925,5467,0,south),
				new Spot(2925,5466,0,south)
		};
		GardenNpcRange springNpc4Ranges[] = {
				new GardenNpcRange(2962,useRange4)
		};
		GardenStage springPoint4 = new GardenStage(springNpc4Ranges,new Tile(2928,5470,0),true);
		final Spot useRange5[] = { 
			new Spot(2931,5469,0,east),
			new Spot(2932,5469,0,east),
			new Spot(2933,5469,0,east),
			new Spot(2934,5469,0,east),
			new Spot(2935,5469,0,east),
			new Spot(2934,5469,0,west),
			new Spot(2935,5469,0,west)
		};
		GardenNpcRange springNpc5Ranges[] = {
				new GardenNpcRange(2961,useRange5)
		};
		GardenStage springPoint5 = new GardenStage(springNpc5Ranges,new Tile(2930,5470,0),true);
		final Spot useRange60[] = {
				new Spot(2933,5469,0,east),
				new Spot(2934,5469,0,east),
				new Spot(2930,5469,0,west)	
		};
		final Spot useRange61[] = {
			new Spot(2930,5470,0,north),
			new Spot(2931,5471,0,north),
			new Spot(2931,5472,0,north),	
			new Spot(2931,5473,0,north),	
			new Spot(2931,5474,0,north),	
			new Spot(2931,5475,0,north),	
			new Spot(2931,5476,0,north),	
			new Spot(2931,5477,0,north),	
			new Spot(2931,5474,0,south),
			new Spot(2931,5475,0,south),	
			new Spot(2931,5476,0,south),	
			new Spot(2931,5477,0,south)	
		};
		GardenNpcRange springNpc6Ranges[] = {
				new GardenNpcRange(2961,useRange60),
				new GardenNpcRange(2963,useRange61)
		};
		GardenStage springPoint6 = new GardenStage(springNpc6Ranges,new Tile(2933,5468,0),true);
		this.SPRING_GARDEN = new GardenStage[]{
				springPoint0,
				springPoint1,
				springPoint2,
				springPoint3,
				springPoint4,
				springPoint5,
				springPoint6
		};
		
		GardenStage fallPoint0 = new GardenStage(null,new Tile(2908, 5461, 0),false);
		final Spot fallRange1 [] = {
			new Spot(2908,5460,0,west),
			new Spot(2907,5460,0,west),
			new Spot(2906,5460,0,west),
			new Spot(2905,5460,0,west),
			new Spot(2904,5460,0,west),
			new Spot(2903,5460,0,west)
			//new Spot(2902,5460,0,west)
			//new Spot(2901,5460,0,west),
		};//(2904, 5459, 0)
		GardenNpcRange AutumnNpc1Ranges[] = {
				new GardenNpcRange(5802,fallRange1)
		};
		GardenStage fallPoint1 = new GardenStage(AutumnNpc1Ranges, new Tile(2904, 5459, 0),false);
		
		final Spot fallRange2 [] = {
				new Spot(2908,5460,0,east),
				new Spot(2907,5460,0,east),
				new Spot(2906,5460,0,east),
				new Spot(2905,5460,0,east),
				new Spot(2904,5460,0,east),
		};
		GardenNpcRange AutumnNpc2Ranges[] = {
				new GardenNpcRange(5802,fallRange2)
		};
		GardenStage fallPoint2 = new GardenStage(AutumnNpc2Ranges, new Tile(2901, 5455, 0),false);
		
		final Spot fallRange3 [] = {
			new Spot(2900,5454,0,south),
			new Spot(2900,5453,0,south)	
		};
		
		GardenNpcRange AutumnNpc3Ranges[] = {
				new GardenNpcRange(5803,fallRange3)
		};
		GardenStage fallPoint3 = new GardenStage(AutumnNpc3Ranges, new Tile(2899, 5453, 0), true);
		
		final Spot fallRange40 [] = {
				/*new Spot(2900,5456,0,north),
				new Spot(2900,5455,0,north),
				new Spot(2900,5454,0,north),*/
				new Spot(2900,5453,0,north),
				new Spot(2900,5452,0,north)
		};
		final Spot fallRange41 [] = {
				new Spot(2899,5449,0,west),
				new Spot(2899,5449,0,east)//,
				//new Spot(2900,5449,0,east),
				//new Spot(2901,5449,0,east)
				//new Spot(2902,5449,0,east),
				//new Spot(2903,5449,0,east)//,
				//new Spot(2904,5449,0,east)
				//new Spot(2905,5449,0,east)
		};
		GardenNpcRange AutumnNpc4Ranges[] = {
				new GardenNpcRange(5803,fallRange40),
				new GardenNpcRange(5804,fallRange41)
		};
		GardenStage fallPoint4 = new GardenStage(AutumnNpc4Ranges, new Tile(2903, 5450, 0), true);
		
		final Spot fallRange5 [] = {
				new Spot(2903,5451,0,north),
				new Spot(2903,5452,0,north),
				new Spot(2903,5453,0,north),
				new Spot(2903,5454,0,north),
				new Spot(2903,5455,0,north),
				new Spot(2903,5455,0,east),
				new Spot(2904,5455,0,east),
				new Spot(2905,5455,0,east),
				new Spot(2905,54554,0,south),
				new Spot(2905,5453,0,south),
				new Spot(2905,5452,0,south),
		};
		GardenNpcRange AutumnNpc5Ranges[] = {
				new GardenNpcRange(5805,fallRange5)
		};
		GardenStage fallPoint5 = new GardenStage(AutumnNpc5Ranges, new Tile(2902, 5453, 0), false);
		
		final Spot fallRange60 [] = {
				/**new Spot(2903,5451,0,north),
				new Spot(2903,5452,0,north),
				new Spot(2903,5453,0,north),
				new Spot(2903,5454,0,north),*/
				new Spot(2903,5455,0,north),
				new Spot(2903,5455,0,east),
				new Spot(2904,5455,0,east),
				new Spot(2905,5455,0,east),
				new Spot(2905,5455,0,south),
				new Spot(2904,5454,0,south),
				new Spot(2903,5453,0,south),
				new Spot(2905,5452,0,south)
				//new Spot(2905,5451,0,south)
				//new Spot(2905,5451,0,west),
		};
		
		final Spot fallRange61 [] = {
				new Spot(2906,5457,0,east),
				new Spot(2907,5457,0,east),
				new Spot(2908,5457,0,east),
				new Spot(2909,5457,0,east),
				new Spot(2910,5457,0,east),
				new Spot(2911,5457,0,east),
				new Spot(2912,5457,0,east),
				new Spot(2913,5457,0,east),
				new Spot(2914,5457,0,east),
				new Spot(2915,5457,0,east),
				new Spot(2916,5457,0,east)//,
				//new Spot(2917,5457,0,east)
				
		};
		GardenNpcRange AutumnNpc6Ranges[] = {
				new GardenNpcRange(5805,fallRange60),
				new GardenNpcRange(5806,fallRange61),
		};
		GardenStage fallPoint6 = new GardenStage(AutumnNpc6Ranges, new Tile(2908, 5456, 0), true);
		
		final Spot fallRange7 [] = {
			new Spot(2908,5455,0,east),
			new Spot(2909,5455,0,east)
			//new Spot(2910,5455,0,east)
			//new Spot(2911,5455,0,east),
			//new Spot(2912,5455,0,east),
		};
		GardenNpcRange AutumnNpc7Ranges[] = {
			new GardenNpcRange(5807,fallRange7)	
		};
		GardenStage fallPoint7 = new GardenStage(AutumnNpc7Ranges, new Tile(2913, 5453, 0), false);
		this.AUTUMN_GARDEN = new GardenStage[]{
				fallPoint0,
				fallPoint1,
				fallPoint2,
				fallPoint3,
				fallPoint4,
				fallPoint5,
				fallPoint6,
				fallPoint7
		};
		
	}
	
}
