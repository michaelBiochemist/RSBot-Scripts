package fear.taskscripts.tasks;

import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GroundItem;
import org.powerbot.script.rt4.Npc;
import org.powerbot.script.Filter;
//import org.powerbot.script.rt4.Item;


import fear.taskscripts.Task;
import fear.taskscripts.constants.ItemConstants;

//import org.powerbot.script.rt4.Game;

public class TakeGroundItem extends Task<ClientContext>{
	public TakeGroundItem(ClientContext ctx) {
		super(ctx);
	}
	
	@Override
	public boolean activate() {
		/**Filter<Npc> ReachableItem = new Filter<Npc>() {
			@Override
			public boolean accept(Npc npc) {
				boolean fighting = npc.healthBarVisible();
				//boolean reachable = npc.tile().matrix(ctx).reachable();
				boolean reachable = true;
				String playerStyle = ctx.combat.style().toString();
				return !fighting && (reachable || (playerStyle != "AGGRESSIVE"));
			}
		};*/
		GroundItem takeItem = ctx.groundItems.select().id(ItemConstants.TAKE_FROM_GROUND).nearest().poll();
		if (takeItem == null) {
			return false;
		};
		return ctx.inventory.select().count() != 28
				//&& ctx.players.local()
				&& takeItem.tile().distanceTo(ctx.players.local()) <= ItemConstants.GROUNDITEM_DISTANCE
				&& takeItem.tile().matrix(ctx).reachable()
				//&& takeItem.tile().matrix(ctx).
				&& ctx.players.local().animation()==-1
				&& !ctx.players.local().inMotion()
				&& !ctx.players.local().healthBarVisible();
		
	}
	
	@Override
	public void execute() {
		GroundItem takeItem = ctx.groundItems.select().id(ItemConstants.TAKE_FROM_GROUND).nearest().poll();
		if (takeItem.inViewport()) {
			takeItem.interact("Take", takeItem.name());
		} else {
			ctx.camera.turnTo(takeItem);
			ctx.movement.step(takeItem);
		};
	}
}