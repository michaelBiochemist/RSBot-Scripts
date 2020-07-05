package fear.taskscripts.tasks;

import org.powerbot.script.Condition;

import fear.taskscripts.GardenNpcRange;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.Player;
import org.powerbot.script.rt4.Npc;

import fear.taskscripts.Task;
import fear.taskscripts.constants.ItemConstants;
import fear.taskscripts.constants.ObjectConstants;
import fear.taskscripts.GardenStage;

//import org.powerbot.script.rt4.Game;

public class StealSqirk extends Task<ClientContext>{
	GardenStage[] NavigateGarden;
	Boolean InGarden;
	Boolean BesideTree;
	Boolean debug;
	int TurnedToStep;  
	Boolean startedTrack;
	public StealSqirk(ClientContext ctx,GardenStage [] NavigateGarden) {
		super(ctx);
		this.NavigateGarden=NavigateGarden;
		this.InGarden=false;
		this.BesideTree=false;
		this.TurnedToStep=-1;
		this.debug=false;
	}
	
	@Override
	public boolean activate() {
		boolean inMotion = ctx.players.local().inMotion();
		boolean hasEmptyGlass = !ctx.inventory.select().id(ItemConstants.BEER_GLASS).isEmpty();
		boolean spaceInInventory = (ctx.inventory.select().count() != 28);
		return !inMotion && hasEmptyGlass & spaceInInventory;
		
	}
	
	@Override
	public void execute() {
		Player p = ctx.players.local();
		//GameObject fountain = ctx.objects.select().id(ObjectConstants.GARDEN_FOUNTAIN).poll();
		/* Spring garden -- x < 2921 ; camera.pitch(30), camera.angle(110)
		 * 
		 * */
		GameObject gate = ctx.objects.select().id(ObjectConstants.AUTUMN_GARDEN_GATE).poll();
		if (p.tile().y() > 5462) {//Assuming Spring Garden
			this.InGarden=false;
			this.startedTrack=false;
			this.BesideTree=false;
			this.TurnedToStep=-1;
			if (gate.inViewport()) {
				ctx.camera.pitch(30);
				ctx.camera.angle(10);
				gate.click();
				//gate.interact("Open");
				
				Condition.sleep(1000);
			} else {
				ctx.camera.turnTo(gate);
				ctx.movement.step(gate);
			}
		} else if(this.BesideTree) {
			GameObject tree = ctx.objects.select().id(ObjectConstants.AUTUMN_SQIRK_TREE).poll();
			tree.click();
			if(tree.inViewport()) {
				tree.click("Pick-Fruit");
				Condition.sleep(1000);
			} else {
				ctx.camera.turnTo(tree);
				ctx.movement.step(tree);
			}
		} else {
			this.InGarden = true;
			int stage = -1;
			for (int i=0; i < this.NavigateGarden.length; i++) {
				if(p.tile().x()==this.NavigateGarden[i].SafeSpot.x() && p.tile().y()==this.NavigateGarden[i].SafeSpot.y()) {
					stage = i;
					this.startedTrack=true;
					break;
				}
			}
			if (stage == this.NavigateGarden.length-1) {
				System.out.println("Gotta grab that fruit");
				this.BesideTree = true;
				//this.TurnedToStep = -1;
			} else if (this.startedTrack && (stage !=-1) || !this.startedTrack && (stage==-1)) {
				//System.out.println(stage);
				if (this.TurnedToStep != (stage+1)) {
					ctx.camera.turnTo(this.NavigateGarden[stage+1].SafeSpot);
					this.TurnedToStep = stage+1;
				};
				if(this.NavigateGarden[stage+1].TimeToMove(ctx)) {
					if (this.debug) {
						System.out.println("Moving to spot: "+this.NavigateGarden[stage+1].SafeSpot);
						if (this.NavigateGarden[stage+1].NpcRanges!=null) {
							for (GardenNpcRange k : this.NavigateGarden[stage+1].NpcRanges) {
								Npc j = ctx.npcs.select().id(k.npcId).poll();
								System.out.println("Npc id: "+k.npcId+ " at "+j.tile()+" facing: "+j.orientation());
							};
						};

					}
					ctx.movement.running(this.NavigateGarden[stage+1].RunHere);
					ctx.movement.step(this.NavigateGarden[stage+1].SafeSpot);
				} 
			}	
		}
	}
}