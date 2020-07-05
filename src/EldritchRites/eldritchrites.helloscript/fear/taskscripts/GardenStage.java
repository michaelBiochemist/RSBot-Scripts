package fear.taskscripts;

import org.powerbot.script.Tile;

import fear.taskscripts.GardenNpcRange;
import org.powerbot.script.rt4.ClientContext;

public class GardenStage {
	
	public GardenNpcRange NpcRanges[];
	public boolean RunHere;
	
	public Tile SafeSpot;
	
	public GardenStage(GardenNpcRange setRanges[],Tile safeSpot,boolean runHere) {
		this.NpcRanges = setRanges;
		this.SafeSpot = safeSpot;
		this.RunHere = runHere;
		
	}
	public boolean TimeToMove(ClientContext ctx) {
		//boolean safeToMove = false;
		if (this.NpcRanges == null) {
			return true;
		}
		for (GardenNpcRange npcRange : this.NpcRanges) {
			if (!npcRange.safeToMove(ctx)) {
				return false;
			}
		}
		System.out.println("Moving to next spot "+this.SafeSpot);
		return true;
	}

}
