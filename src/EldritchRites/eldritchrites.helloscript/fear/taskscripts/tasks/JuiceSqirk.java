package fear.taskscripts.tasks;

import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;
import org.powerbot.script.rt4.Game;

import fear.taskscripts.Task;
import fear.taskscripts.constants.ItemConstants;
import fear.taskscripts.constants.ObjectConstants;

//import org.powerbot.script.rt4.Game;

public class JuiceSqirk extends Task<ClientContext>{
	public JuiceSqirk(ClientContext ctx) {
		super(ctx);
	}
	
	@Override
	public boolean activate() {

		return ctx.inventory.select().id(ItemConstants.SQIRK).count() == 3 //2 for summer, 4 for spring and 5 for winter
				&& !ctx.inventory.select().id(ItemConstants.BEER_GLASS).isEmpty()
				&& !ctx.inventory.select().id(ItemConstants.PESTLE_MORTAR).isEmpty()
				;//Assumes spring sqirk
	}
	
	@Override
	public void execute() {
		System.out.println("Making Sq'irk juice!");
		if(ctx.game.tab() != Game.Tab.INVENTORY) {
			ctx.game.tab(Game.Tab.INVENTORY);
		};
		Item pestle = ctx.inventory.select().id(ItemConstants.PESTLE_MORTAR).poll();
		Item sqirk = ctx.inventory.select().id(ItemConstants.SQIRK).poll();
		pestle.interact("Use",pestle.name());
		sqirk.interact("Use",sqirk.name());
	}
}