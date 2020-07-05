package fear.taskscripts.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;

import fear.taskscripts.Task;
import fear.taskscripts.constants.ItemConstants;

import org.powerbot.script.rt4.Game;

public class Drop extends Task<ClientContext>{
	private int[] dropItemIds = ItemConstants.DROP_ITEMS;
	public Drop(ClientContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		return ctx.inventory.select().count() == 28
				&& ctx.inventory.select().id(ItemConstants.DONT_DROP).isEmpty()
				&& (!ctx.inventory.select().id(ItemConstants.DROP_ITEMS).isEmpty()
				|| !ctx.inventory.select().id(ItemConstants.BONES).isEmpty())
				;
		
	}
	
	@Override
	public void execute() {
		System.out.println("Executing drop");
		if(ctx.game.tab() != Game.Tab.INVENTORY) {
			ctx.game.tab(Game.Tab.INVENTORY);
		}
		//ctx.input.send("{VK_SHIFT down}");
		for (Item i : ctx.inventory.id(dropItemIds)) {
			//System.out.println(i.name());
			//i.interact("Drop");
			ctx.inventory.drop(i,true);
			//i.click(true);
			Condition.wait(() -> ctx.players.local().animation() == -1, 250, 3);
		};
		//ctx.input.send("{VK_SHIFT up}");
		for (Item i : ctx.inventory.select().id(ItemConstants.BONES)) {
			while (!i.click("Bury")) {
				Condition.wait(() -> ctx.players.local().animation() == -1, 500, 3);
			};
			Condition.wait(() -> ctx.players.local().animation() == -1, 500, 3);
		}
	}
}
