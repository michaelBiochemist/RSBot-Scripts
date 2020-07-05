package fear.taskscripts.tasks;


import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.Component;
import org.powerbot.script.Input;
import org.powerbot.script.rt4.Widget;
//import org.powerbot.bot.rt4.client.Widget;
import org.powerbot.script.Condition;
import java.util.Date;

import fear.taskscripts.constants.*;
import fear.taskscripts.WidgetTools;
import fear.taskscripts.Task;

public class Smith extends Task<ClientContext> {
	public Date LastSmithed;
	//public int BarsNeeded;
	public WidgetTools wt;
	
	public Smith(ClientContext ctx) {
		super(ctx);
		this.LastSmithed = new Date();
		this.wt = new WidgetTools();
	}
	
	@Override
	public boolean activate() {
		/**System.out.println("Inventory Count: " + ctx.inventory.select().count());
		System.out.println("anvils not around?: "+ctx.objects.select().id(anvilIds).isEmpty());
		System.out.println("Is player animated? "+ ctx.players.local().animation());
		*/
		/*if (ctx.players.local().animation() != -1) {
			System.out.println("Animation: "+ctx.players.local().animation());
		}*/
		if (ctx.players.local().animation() == AnimationConstants.SMITH) {
			this.LastSmithed = new Date();
			//System.out.println("Animation: "+ctx.players.local().animation());
		}
		
		return ctx.inventory.select().id(ItemConstants.SMITH_ITEMS).count() != 0 
			&& !ctx.objects.select().id(ObjectConstants.Anvil).isEmpty()
			&& ctx.players.local().animation() == -1
			&& !ctx.players.local().inMotion()
			&& (new Date().getTime()-(this.LastSmithed.getTime())) > 2000;
			//&& ctx.players.local().interacting().name();
	}
	
	@Override
	public void execute() {
		System.out.println("Executing smith."+ctx.inventory.select().id(ItemConstants.SMITH_ITEMS).count());
		GameObject anvil = ctx.objects.select().id(ObjectConstants.Anvil).nearest().poll();
		if (anvil.inViewport() && anvil.tile().distanceTo(ctx.players.local()) <= 4) {
			System.out.println("Anvil in view port");
			Widget smithWidget = ctx.widgets.select().id(WidgetConstants.SMITH_NAILS[0]).poll();
			if (!smithWidget.valid() || !smithWidget.component(1).visible()) {
				anvil.interact("Smith");
			}
			/**Component nails = ctx.widgets.component(WidgetConstants.SMITH_NAILS[0], WidgetConstants.SMITH_NAILS[1]
					, WidgetConstants.SMITH_NAILS[2]
							);
			//nails.
				nails.hover();
				if (ctx.menu.commands()[0].toString().strip().equals("Smith set Steel nails")) {
					System.out.println(ctx.menu.commands()[0]);
					ctx.input.click(nails.centerPoint(),true);
					this.LastSmithed = new Date();
					//nails.click();
				}
				 else {
					ctx.camera.turnTo(anvil);
					//System.out.println(nails.component(0).text().compareTo("Nails")+nails.component(2).itemId());
				}*/
			ctx.input.click(wt.getClickable(ctx, WidgetConstants.SMITH_NAILS[0], "Smith set Mithril arrowtips"),true);
			Condition.wait(() -> ctx.players.local().animation() == -1, 500, 3);
			this.LastSmithed = new Date();

		} else {
			System.out.println("Anvil not in view port");
			if (ctx.bank.opened()) {
				System.out.println("closing bank");
				ctx.bank.close();
			}
			ctx.camera.turnTo(anvil);
			ctx.movement.step(anvil);

		}
	}
}
