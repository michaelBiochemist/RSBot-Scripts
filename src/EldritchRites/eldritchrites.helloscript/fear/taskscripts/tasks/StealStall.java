package fear.taskscripts.tasks;

import fear.taskscripts.constants.*;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.Tile;



import fear.taskscripts.Task;

public class StealStall extends Task<ClientContext> {

	Tile StallLocation;
	public StealStall(ClientContext ctx, Tile StallLocation) {
		super(ctx);
		this.StallLocation=StallLocation;
	}
	
	@Override
	public boolean activate() {
		/**System.out.println("Inventory Count: " + ctx.inventory.select().count());
		System.out.println("Trees not around?: "+ctx.objects.select().id(treeIds).isEmpty());
		System.out.println("Is player animated? "+ ctx.players.local().animation());
		*/

		return ctx.inventory.select().count() != 28
			&& !ctx.objects.select().id(ObjectConstants.STEAL_STALL).isEmpty()
			&& ctx.players.local().animation() == -1
			&& (ctx.objects.select().id(ObjectConstants.STEAL_STALL).nearest().poll().tile().distanceTo(ctx.players.local()) < 4)
			&& this.StallLocation.distanceTo(ctx.players.local()) < 3;
	}
	
	@Override
	public void execute() {
		System.out.println("Stealing Stall");
		GameObject stall = ctx.objects.select().id(ObjectConstants.STEAL_STALL).nearest().poll();
		if (stall.inViewport()) {
			stall.click("Steal-from");
			Condition.sleep(1000);
		} else {
			ctx.camera.turnTo(stall);
			ctx.movement.step(stall);
		}

	}
}