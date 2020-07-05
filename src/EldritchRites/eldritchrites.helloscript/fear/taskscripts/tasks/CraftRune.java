package fear.taskscripts.tasks;


import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

/**import org.powerbot.script.rt4.Widget;
import org.powerbot.script.rt4.Widgets;
import org.powerbot.script.rt4.Component;*/

import fear.taskscripts.constants.ItemConstants;
import fear.taskscripts.constants.ObjectConstants;
import fear.taskscripts.Task;

public class CraftRune extends Task<ClientContext> {
	
	public CraftRune(ClientContext ctx) {
		super(ctx);
	}

	boolean hasEssence;
	
	@Override
	public boolean activate() {
		//System.out.println("Inventory Count: " + ctx.inventory.select().count());
		//System.out.println("ruinss not around?: "+ctx.objects.select().id(ObjectConstants.RUNECRAFT_RUINS).isEmpty());
		//		|| !ctx.objects.select().id(ObjectConstants.RUNECRAFT_PORTAL).isEmpty()));
		//System.out.println("Is player animated? "+ ctx.players.local().animation());
		
		boolean runecraft_ruins = (!ctx.objects.select().id(ObjectConstants.RUNECRAFT_RUINS).isEmpty());
		boolean runecraft_altar = (!ctx.objects.select().id(ObjectConstants.RUNECRAFT_ALTAR).isEmpty());
		boolean runecraft_portal = (!ctx.objects.select().id(ObjectConstants.RUNECRAFT_PORTAL).isEmpty());
		this.hasEssence = (ctx.inventory.select().id(ItemConstants.RUNE_ESSENCE).count() != 0);
		
		return 
			 (runecraft_ruins
			|| runecraft_altar
			|| runecraft_portal
			&& (hasEssence
				|| runecraft_portal)
			&& ctx.players.local().animation() == -1
			&& !ctx.players.local().inMotion())
			;
			//&& ctx.players.local().interacting().name();
	}
	
	@Override
	public void execute() {
		System.out.println("Executing craft rune.");
		GameObject ruins = ctx.objects.select().id(ObjectConstants.RUNECRAFT_RUINS).nearest().poll();
		if (ruins != null && ctx.inventory.select().id(ItemConstants.RUNE_ESSENCE).count() != 0) {
			System.out.println("Using Ruins");
			ctx.camera.turnTo(ruins);
			ctx.movement.step(ruins);
			ruins.interact("Enter");			
		};

		GameObject altar = ctx.objects.select().id(ObjectConstants.RUNECRAFT_ALTAR).nearest().poll();
		if (altar != null && ctx.inventory.select().id(ItemConstants.RUNE_ESSENCE).count() != 0) {
			System.out.println("Using Altar");
			if (!altar.inViewport() || altar.tile().distanceTo(ctx.players.local()) > 5) {
				//System.out.println(altar.inViewport());
				ctx.camera.turnTo(altar);
				ctx.movement.step(altar);
			} else {
				//System.out.println("Clicking altar");
				//altar.click(true);
				altar.interact("Craft-rune");	
			}
			
		};
		
		GameObject portal = ctx.objects.select().id(ObjectConstants.RUNECRAFT_PORTAL).nearest().poll();
		//System.out.println(ctx.objects.id(ObjectConstants.RUNECRAFT_PORTAL).count());
		//ystem.out.println(portal.tile());
		if (portal != null && ctx.inventory.select().id(ItemConstants.RUNE_ESSENCE).count() == 0) {
			ctx.camera.turnTo(portal);
			ctx.movement.step(portal);
			portal.click(true);	
		};

			//System.out.println(ctx.widgets.component(270, 11,2).interact("CraftRune","Silver Bar"));
			//System.out.println(ctx.widgets.component(270, 16,29).click());//Silver Texture id = 1545
			/**for (Widget mywidget : ctx.widgets.array()) {
				if (mywidget.componentCount() != 0) {
					System.out.println(mywidget.id()+ " "+mywidget.toString()+" "+mywidget.componentCount());
				}
				
			}*/
			//System.out.println("Interacting CraftRune.");
			//ruins.interact("CraftRune");
			//System.out.println(ctx.widgets.component(270, 13,2));
			//Widget mywidget = ctx.widgets.select().poll();
			//S//ystem.out.println(mywidget.componentCount());
			//ctx.input.
	}
}
