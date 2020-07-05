package fear.taskscripts.tasks;


import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

/**import org.powerbot.script.rt4.Widget;
import org.powerbot.script.rt4.Widgets;
import org.powerbot.script.rt4.Component;*/
import java.util.Date;

import fear.taskscripts.constants.AnimationConstants;
import fear.taskscripts.constants.ItemConstants;
import fear.taskscripts.Task;

public class Smelt extends Task<ClientContext> {
	private int[] furnaceIds = {16469}; // 10819,10831 = Willow. 10820=Oak Swap these out with Oak furnace ids?
	public Date LastSmelted;
	
	public Smelt(ClientContext ctx) {
		super(ctx);
		this.LastSmelted = new Date();
	}
	
	@Override
	public boolean activate() {
		/**System.out.println("Inventory Count: " + ctx.inventory.select().count());
		System.out.println("furnaces not around?: "+ctx.objects.select().id(furnaceIds).isEmpty());
		System.out.println("Is player animated? "+ ctx.players.local().animation());
		*/
		if (ctx.players.local().animation() == AnimationConstants.SMELT) {
			this.LastSmelted = new Date();
			//System.out.println("Animation: "+ctx.players.local().animation());
		}
		return ctx.inventory.select().id(ItemConstants.SMELT_ITEMS).count() != 0
			&& !ctx.objects.select().id(furnaceIds).isEmpty()
			&& ctx.players.local().animation() == -1
			&& !ctx.players.local().inMotion()
			&& (new Date().getTime()-(this.LastSmelted.getTime())) > 2000;
			//&& ctx.players.local().interacting().name();
	}
	
	@Override
	public void execute() {
		System.out.println("Executing smelt."+ctx.inventory.select().id(ItemConstants.SMELT_ITEMS).count());
		GameObject furnace = ctx.objects.nearest().poll();
		if (furnace.inViewport()) {
			furnace.interact("Smelt");
			//ctx.widgets.component(270, 16).click(); //16 is for f2p
			ctx.widgets.component(270, 17).click();
			System.out.println(new Date().getTime()- this.LastSmelted.getTime());
			
			this.LastSmelted = new Date();
			//System.out.println(ctx.widgets.component(270, 11,2).interact("Smelt","Silver Bar"));
			//System.out.println(ctx.widgets.component(270, 16,29).click());//Silver Texture id = 1545
			/**for (Widget mywidget : ctx.widgets.array()) {
				if (mywidget.componentCount() != 0) {
					System.out.println(mywidget.id()+ " "+mywidget.toString()+" "+mywidget.componentCount());
				}
				
			}*/
			//System.out.println("Interacting Smelt.");
			//furnace.interact("Smelt");
			//System.out.println(ctx.widgets.component(270, 13,2));
			//Widget mywidget = ctx.widgets.select().poll();
			//S//ystem.out.println(mywidget.componentCount());
			//ctx.input.
			
		} else {
			ctx.movement.step(furnace);
			ctx.camera.turnTo(furnace);
		}
	}
}
