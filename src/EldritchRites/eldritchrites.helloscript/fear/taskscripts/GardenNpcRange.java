package fear.taskscripts;
import org.powerbot.script.Tile;
import fear.taskscripts.Spot;

import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Npc;

public class GardenNpcRange {
	
	public int npcId;
	public Spot range[];
	
	public GardenNpcRange(int id, Spot NpcRange[]) {
		this.npcId=id;
		this.range=NpcRange;
	}

	public boolean safeToMove(ClientContext ctx) {
		boolean safe = false;
		Npc guard = ctx.npcs.select().id(this.npcId).poll();
		if (inRange(guard.tile(),guard.orientation(), this.range)) {
			return true;
		};
		return false;
		
	}
	public boolean inRange(Tile npcLoc,int npcOrient, Spot range[]) {
		for (Spot spot : range) {
			if (npcLoc.x()==spot.tile.x() && npcLoc.y()==spot.tile.y() && npcOrient==spot.orientation) {
				return true;
			}
		}
		return false;
	}
	
}
