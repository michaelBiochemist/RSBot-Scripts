package fear.taskscripts.tasks;

import fear.taskscripts.constants.*;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.Npc;


import fear.taskscripts.Task;

public class Fish extends Task<ClientContext> {

	String fishingMethod = "";
	public Fish(ClientContext ctx, String FishType) {
		super(ctx);
		this.fishingMethod = FishType;
	}
	
	@Override
	public boolean activate() {
		/**System.out.println("Inventory Count: " + ctx.inventory.select().count());
		System.out.println("Trees not around?: "+ctx.objects.select().id(treeIds).isEmpty());
		System.out.println("Is player animated? "+ ctx.players.local().animation());
		*/

		return ctx.inventory.select().count() != 28
			&& !ctx.npcs.select().id(NpcConstants.FISH).isEmpty()
			&& ctx.players.local().animation() == -1
			&& (!ctx.inventory.select().id(ItemConstants.feather).isEmpty()|| fishingMethod=="Net" || fishingMethod=="Harpoon" || fishingMethod=="Cage");
	}
	
	@Override
	public void execute() {
		System.out.println("Starting to fish");
		Npc FishingSpot = ctx.npcs.select().id(NpcConstants.FISH).nearest().poll();
		if (FishingSpot.inViewport()) {
			//System.out.println("Interacting Fish.");
			FishingSpot.interact(fishingMethod);
			Condition.sleep(2500);
		} else {
			ctx.camera.turnTo(FishingSpot);
			ctx.movement.step(FishingSpot);
		}
	}
}