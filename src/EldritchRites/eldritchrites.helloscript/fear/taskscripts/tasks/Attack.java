package fear.taskscripts.tasks;

import java.util.Date;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Game;

//import org.powerbot.script.rt4.Item;

import org.powerbot.script.rt4.Npc;
//import org.powerbot.script.rt4.Npcs;
import org.powerbot.script.Filter;
import fear.taskscripts.Task;
//import fear.taskscripts.constants.NpcConstants;
import fear.taskscripts.constants.*;
//import org.powerbot.script.rt4.Game;
import java.util.Date;

public class Attack extends Task<ClientContext>{
	public Date LastCombat;
	public Attack(ClientContext ctx) {
		super(ctx);
		this.LastCombat = new Date();
	}
	
	@Override
	public boolean activate() {
		//Npc attackThem = 
		//Filter<Npc> filter = new Filter;
		Filter<Npc> specificMobs = new Filter<Npc>() {
			@Override
			public boolean accept(Npc npc) {
				boolean fighting = npc.healthBarVisible();
				//boolean reachable = npc.tile().matrix(ctx).reachable();
				boolean reachable = true;
				String playerStyle = ctx.combat.style().toString();
				return !fighting && (reachable || (playerStyle != "AGGRESSIVE"));
			}
		};
		
		for (int i : AnimationConstants.COMBAT) {
			if (ctx.players.local().animation() == i) {
				this.LastCombat = new Date();
			};
		};
		if (ctx.players.local().animation() != -1) {
			System.out.println(ctx.players.local().animation());
		};
		
		//System.out.println((ctx.groundItems.select().id(ItemConstants.TAKE_FROM_GROUND).isEmpty() ||
		//ctx.groundItems.select().id(ItemConstants.TAKE_FROM_GROUND).nearest().poll().tile().distanceTo(ctx.players.local())>=12));
		boolean returnValue = !ctx.npcs.select().id(NpcConstants.ATTACK).select(specificMobs).isEmpty()
				&& ctx.players.local().healthPercent() > 50
				&& (new Date().getTime()-(this.LastCombat.getTime())) > 6000
				&& !ctx.players.local().inMotion()
				&& ctx.inventory.select().count() != 28
				&& (ctx.groundItems.select().id(ItemConstants.TAKE_FROM_GROUND).isEmpty() ||
						ctx.groundItems.select().id(ItemConstants.TAKE_FROM_GROUND).nearest().poll().tile().distanceTo(ctx.players.local())>ItemConstants.GROUNDITEM_DISTANCE)
				|| (ctx.players.local().healthPercent() < 75 && ctx.inventory.select().id(ItemConstants.FOOD).count()!=0);
		
		//System.out.println(returnValue);
		return returnValue;
		
	}
	
	@Override
	public void execute() {
		if (ctx.players.local().healthPercent() < 75 && ctx.inventory.select().id(ItemConstants.FOOD).count()!=0) {
			if(ctx.game.tab() != Game.Tab.INVENTORY) {
				ctx.game.tab(Game.Tab.INVENTORY);
			}
			ctx.inventory.select().id(ItemConstants.FOOD).poll().click("Eat");
		} else {		
		System.out.println("Attacking Mob? w/ style"+ctx.combat.style());
		Filter<Npc> specificMobs = new Filter<Npc>() {
			@Override
			public boolean accept(Npc npc) {
				boolean fighting = npc.healthBarVisible();
				boolean reachable = npc.tile().matrix(ctx).reachable();
				String playerStyle = ctx.combat.style().toString();
				return !fighting && (reachable || (playerStyle != "AGGRESSIVE"));
			}
		};
		Npc victim = ctx.npcs.select().id(NpcConstants.ATTACK).nearest().select(specificMobs).poll();
		if (!victim.inViewport()) {
			ctx.camera.turnTo(victim);
			ctx.movement.step(victim);
		}
		ctx.npcs.select().id(NpcConstants.ATTACK).nearest().select(specificMobs).poll().interact("Attack");
		this.LastCombat = new Date();
		Condition.sleep(2500);
		};
	}
}