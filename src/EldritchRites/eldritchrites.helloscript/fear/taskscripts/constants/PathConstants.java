package fear.taskscripts.constants;


import org.powerbot.script.Tile;

public class PathConstants {
	// North = + on y axis, East = + on X-axis
	public static final int FACE_NORTH=4;
	public static final int FACE_SOUTH=0;
	public static final int FACE_EAST=6;
	public static final int FACE_WEST=2;
	
	public static final Tile IGNORE_BANK = new Tile(2936, 3280, 0);
    //sarim iron mining site to falador bank
	public static final Tile SARIM_IRON[] = 
		{
				new Tile(2968,3240,0),
				new Tile(2962,3240,0),
				new Tile(2972,3250,0),
				new Tile(2982,3260,0),
				new Tile(2992,3270,0),
				new Tile(2992,3280,0),
				new Tile(3008,3280,0),
				new Tile(3008,3296,0),
				new Tile(3005,3300,0),
				new Tile(3005,3310,0),
				new Tile(3007,3324,0),
				new Tile(3007,3334,0),
				new Tile(3008,3344,0),
				new Tile(3010,3350,0),
				//new Tile(3014,3354,0),
				new Tile(3015,3355,0)
			};
	public static final Tile SARIM_CLAY[] = 
		{
				new Tile(2986, 3240, 0),
				new Tile(2982,3250,0),
				new Tile(2982,3260,0),
				new Tile(2992,3270,0),
				new Tile(2992,3280,0),
				new Tile(3008,3280,0),
				new Tile(3008,3296,0),
				new Tile(3005,3300,0),
				new Tile(3005,3310,0),
				new Tile(3007,3324,0),
				new Tile(3007,3334,0),
				new Tile(3008,3344,0),
				new Tile(3010,3350,0),
				//new Tile(3014,3354,0),
				new Tile(3015,3355,0)
			};

    //crafting guild mining area to falador bank
	public static final Tile CRAFT_GUILD[] =
		{
				new Tile(2939, 3283, 0),
				new Tile(2933,3288,0),
				new Tile(2933,3291,0),
				new Tile(2933,3295,0),
				new Tile(2948,3295,0),
				new Tile(2957,3295,0),
				new Tile(2970,3295,0),
				new Tile(2992,3295,0),
				new Tile(3005,3310,0),
				new Tile(3007,3324,0),
				new Tile(3007,3334,0),
				new Tile(3008,3344,0),
				new Tile(3010,3350,0),
				//new Tile(3014,3354,0),
				new Tile(3015,3355,0)
			};


	//Falador S. Bank to Air Rune Ruins
	public static final Tile AIR_RUNE[] = 
	{
			new Tile(2987, 3294, 0),
			new Tile(2998,3298,0),
			new Tile(3005,3305,0),
			new Tile(3008,3310,0),
			new Tile(3008,3320,0),
			new Tile(3008,3330,0),
			new Tile(3008,3340,0),
			new Tile(3008,3350,0),
			new Tile(3008,3359,0),
			new Tile(3014,3354,0),
			new Tile(3019,3355,0)
	};

	//Falador N. Bank to Mind Rune Ruins
	public static final Tile MIND_RUNE[] = 
		{
			new Tile(2980, 3510, 0),
			new Tile(2980, 3501, 0),
			new Tile(2980, 3491, 0),
			new Tile(2975, 3486, 0),
			new Tile(2970, 3476, 0),
			new Tile(2973, 3468, 0),
			new Tile(2973, 3456, 0),
			new Tile(2975, 3444, 0),
			new Tile(2984, 3432, 0),
			new Tile(2977, 3418, 0),
			new Tile(2984, 3418, 0),
			new Tile(2976, 3411, 0),
			new Tile(2968, 3402, 0),
			new Tile(2966, 3392, 0),
			new Tile(2964, 3384, 0),
			new Tile(2953, 3380, 0),
			new Tile(2946, 3368, 0)
		};

	//Edgeville Bank to Body Rune Ruins
	public static final Tile BODY_RUNE[] = 
	{
			new Tile(3057, 3447, 0),
			new Tile(3070, 3453, 0),
			new Tile(3080, 3467, 0),
			new Tile(3081, 3477, 0),
			new Tile(3083, 3486, 0),
			new Tile(3093, 3490, 0)
	};
	public static final Tile COSMIC_RUNE[] =
	{
		new Tile(2406, 4382, 0),
		new Tile(2405, 4390, 0),
		new Tile(2394, 4394, 0),
		new Tile(2386, 4397, 0),
		new Tile(2389, 4408, 0),
		new Tile(2400, 4408, 0),
		new Tile(2412, 4407, 0),
		new Tile(2419, 4420, 0),
		new Tile(2413, 4433, 0),
		new Tile(2403,4445,0),
		new Tile(2393, 4450, 0),
		new Tile(2383, 4458, 0)		
	};

	public static final Tile NATURE_RUNE[] = {
			new Tile(2867, 3018, 0),
			new Tile(2861, 3009, 0),
			new Tile(2850, 3008, 0),
			new Tile(2840, 3008, 0),
			new Tile(2830, 3008, 0),
			new Tile(2820, 3008, 0),
			new Tile(2805, 3007, 0),
			new Tile(2411, 4436, 0),
			new Tile(2403,4445,0),
			new Tile(2393, 4450, 0),
			new Tile(2383, 4458, 0)		
	};
	
	//Edgeville Bank to smelting furnace
	public static final Tile SMELT_FURNACE[] = 
	{
		new Tile(3109, 3499, 0),
		new Tile(3095, 3495, 0)
	};

	public static final Tile MINING_GUILD[] = 
	{
		new Tile(3049, 9739, 0),
		new Tile(3039, 9739, 0),
		new Tile(3029, 9739, 0),
		new Tile(3021, 9739, 0),
		new Tile(3018, 3338, 0),
		//new Tile(3021, 3339, 0),
		new Tile(3030, 3337, 0),
		new Tile(3030, 3346, 0),
		new Tile(3022, 3353, 0),
		new Tile(3013, 3360, 0),
		new Tile(3013, 3355, 0)
	};
	//Willow tree to edge for setting fires
	public static final Tile WOODCUT_BARB[] = {
		new Tile(2518, 3581, 0),
		new Tile(2520, 3588, 0),
		new Tile(2531, 3588, 0),
		new Tile(2542, 3588, 0)	
	};
	
	public static final Tile ARDY_BAKE_STALL[] = {
		new Tile(2669, 3310, 0),
		new Tile(2670, 3313, 0),
		new Tile(2663, 3313, 0),
		new Tile(2656, 3313, 0),
		new Tile(2651, 3320, 0),
		new Tile(2648, 3328, 0),
		new Tile(2635, 3336, 0),
		new Tile(2625, 3337, 0),
		new Tile(2616, 3333, 0)
	};
	public static final Tile SHILO_VILLAGE_GEMS[] = {
			new Tile(2824, 3000, 0),
			new Tile(2824, 2990, 0),
			new Tile(2830, 2980, 0),
			new Tile(2831, 2976, 0),
			new Tile(2832, 2969, 0),
			new Tile(2842, 2960, 0),
			new Tile(2852, 2957, 0)
	};
	
	public static final Tile FISHMINE_LUMBERIDGE[] = {
		new Tile(3236, 3152, 0),
		new Tile(3244, 3160, 0),
		new Tile(3244, 3170, 0),
		new Tile(3244, 3180, 0),
		new Tile(3244, 3189, 0),
		new Tile(3241, 3196, 0),
		new Tile(3235, 3203, 0),
		new Tile(3234, 3213, 0),
		new Tile(3231, 3219, 0),
		new Tile(3219, 3219, 0),
		new Tile(3214, 3210, 0),
		new Tile(3205, 3209, 0),
		new Tile(3205, 3209, 1),
		new Tile(3205, 3209, 2),
		new Tile(3208, 3219, 2)
	};
	
	public static final Tile FLY_FISH_BARBV[] ={
		new Tile(3102, 3425, 0),
		new Tile(3094, 3431, 0),
		new Tile(3085, 3440, 0),
		new Tile(3080, 3449, 0),
		new Tile(3080, 3458, 0),
		new Tile(3080, 3467, 0),
		new Tile(3081, 3477, 0),
		new Tile(3083, 3486, 0),
		new Tile(3093, 3490, 0)
	};

	public static final Tile COWS_FALADOR[] = {
			new Tile(3031, 3307, 0),
			new Tile(3031, 3313, 0),
			new Tile(3031,3315, 0),
			new Tile(3021,3315,0),
			new Tile(3012,3315,0),
			new Tile(3008,3320,0),
			new Tile(3008,3330,0),
			new Tile(3008,3340,0),
			new Tile(3008,3350,0),
			new Tile(3008,3358,0),
			new Tile(3014,3354,0),
			
			new Tile(3015,3357,0)
	};
	
	public static final Tile FROGS_LUMBERIDGE[] = {
			
	};
	
	
}

