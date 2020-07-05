package fear.taskscripts.tasks;

import fear.taskscripts.constants.*;

import java.util.Date;

import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Game;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.Item;
import org.powerbot.script.rt4.Npc;


import fear.taskscripts.Task;

public class CookFish extends Task<ClientContext> {
	//private int[] treeIds = {10819,10831}; // 10819,10831 = Willow. 10820=Oak Swap these out with Oak tree ids?
	public Date LastCooked;
	public CookFish(ClientContext ctx) {
		super(ctx);
		this.LastCooked = new Date();
	}
	
	@Override
	public boolean activate() {
		/**System.out.println("Inventory Count: " + ctx.inventory.select().count());
		System.out.println("Trees not around?: "+ctx.objects.select().id(treeIds).isEmpty());
		System.out.println("Is player animated? "+ ctx.players.local().animation());
		*/
		//System.out.println(ctx.players.local().animation());
		//System.out.println("Animation: "+ctx.players.local().animation());
		if (ctx.players.local().animation() == 897) {
			this.LastCooked = new Date();
			//System.out.println("Animation: "+ctx.players.local().animation());
		}
		/*System.out.println("Starting to cook?"+(ctx.inventory.select().count() == 28
			&& !ctx.objects.select().id(ObjectConstants.STOVE).isEmpty()
			&& ctx.players.local().animation() == -1
			&& !ctx.inventory.select().id(ItemConstants.RAW_FISH).isEmpty()
			&& (new Date().getTime()-(this.LastCooked.getTime())) > 1000));*/
		return ctx.inventory.select().count() == 28
			&& !ctx.objects.select().id(ObjectConstants.STOVE).isEmpty()
			&& ctx.players.local().animation() == -1
			&& !ctx.inventory.select().id(ItemConstants.RAW_FISH).isEmpty()
			&& (new Date().getTime()-(this.LastCooked.getTime())) > 1000;
	}
	
	@Override
	public void execute() {
		System.out.println("Starting to cook.");
		GameObject stove = ctx.objects.select().id(ObjectConstants.STOVE).nearest().poll();
		Item rawFish = ctx.inventory.select().id(ItemConstants.RAW_FISH).poll();
		//ctx.widgets.select().id(162).poll().click();
		if(ctx.game.tab() != Game.Tab.INVENTORY) {
			ctx.game.tab(Game.Tab.INVENTORY);
		}
		if (stove != null) {
		if (stove.inViewport()) {
			//System.out.println("Interacting CookFish.");
			if (rawFish.interact("Use",rawFish.name()) && stove.interact("Use",stove.name())) {
				//if(ctx.widgets.component(162,46).visible()) {
					ctx.widgets.component(270, 14).click();
				//}
				
			};
			//stove.interact("CookFish");
			//System.out.println(new Date().getTime()- this.LastCooked.getTime());
			
			this.LastCooked = new Date();
		} else {
			ctx.camera.turnTo(stove);
			ctx.movement.step(stove);
		}
		};
	}
}