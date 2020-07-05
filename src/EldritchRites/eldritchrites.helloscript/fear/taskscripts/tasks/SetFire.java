package fear.taskscripts.tasks;

import java.util.Date;
import java.util.concurrent.Callable;

import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Game;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.Filter;

import fear.taskscripts.Task;
import fear.taskscripts.constants.AnimationConstants;
import fear.taskscripts.constants.ItemConstants;
import fear.taskscripts.constants.PathConstants;

public class SetFire extends Task<ClientContext> {
	private int[] treeIds = {10819,10831}; // 10819,10831 = Willow. 10820=Oak Swap these out with Oak tree ids?
	Date LastChopped;
	boolean LastActionFire;
	public static Tile[] FirePath;
	
	public SetFire(ClientContext ctx, Tile[] WalkPath) {
		super(ctx);
		this.LastChopped = new Date();
		this.LastActionFire = false;
		this.FirePath = WalkPath;
	}
	
	@Override
	public boolean activate() {
		/**System.out.println("Inventory Count: " + ctx.inventory.select().count());
		System.out.println("Trees not around?: "+ctx.objects.select().id(treeIds).isEmpty());
		System.out.println("Is player animated? "+ ctx.players.local().animation());
		*/
		//System.out.println("Is player animated? "+ ctx.players.local().animation());
		if (ctx.players.local().animation() == AnimationConstants.CHOP) {
			this.LastChopped = new Date();
			this.LastActionFire = false;
			//System.out.println("Animation: "+ctx.players.local().animation());
		} else if (ctx.inventory.select().id(ItemConstants.LOGS).isEmpty()) {
			this.LastActionFire = false;
		};
		return !ctx.inventory.select().id(ItemConstants.LOGS).isEmpty()
			&& !ctx.inventory.select().id(ItemConstants.TINDERBOX).isEmpty()
			&& ctx.players.local().animation() == -1
			&& !ctx.players.local().inMotion()
			&& (ctx.inventory.select().count() == 28 
				&& ctx.players.local().tile().distanceTo(this.FirePath[this.FirePath.length-1]) < 5
				|| this.LastActionFire);
			//|| (new Date().getTime()-(this.LastChopped.getTime())) > 3000);
	}
	
	@Override
	public void execute() {
		//System.out.println("Setting fire");
		if(ctx.game.tab() != Game.Tab.INVENTORY) {
			ctx.game.tab(Game.Tab.INVENTORY);
		}
		GameObject obstruction;
		
		Filter<GameObject> upstream = new Filter<GameObject>() {
			@Override
			public boolean accept(GameObject thingo) {
				Tile ppos = ctx.players.local().tile();
				boolean yUpByOne = (thingo.tile().y() == ppos.y()+1);
				boolean xUp = (thingo.tile().x() > ppos.x()
						&& thingo.tile().x() < ppos.x() + 17
						);
				//boolean reachable = npc.tile().matrix(ctx).reachable();
				return yUpByOne && xUp;
			}
		};
		//obstruction = ctx.objects.select().poll();
		//obstruction = ctx.objects.nearest(ctx.players.local()).poll();
		//Tile cpos = ctx.players.local().tile();
		/**if (obstruction.tile().distanceTo(ctx.players.local())!=0) {
			System.out.println(obstruction.tile().distanceTo(ctx.players.local()));
			//System.out.println("Interacting SetFire.");*/
			ctx.inventory.select().id(ItemConstants.TINDERBOX).poll().click("Use");
			ctx.inventory.select().id(ItemConstants.LOGS).poll().click();
			this.LastActionFire = true;
            Condition.wait(new Callable<Boolean>(){
                @Override
                public Boolean call() throws Exception {
                    return ctx.players.local().animation()!=-1;
                }
            }, 200, 10);
		/**} else {
			/*Tile newTile = new Tile(cpos.x()+15,cpos.y()+1,0);
				if (!ctx.objects.select().nearest().select(upstream).isEmpty()) {
					obstruction = ctx.objects.select().nearest().select(upstream).poll();
					ctx.camera.turnTo(obstruction);
					ctx.movement.step(obstruction);
			} else {
				ctx.movement.step(newTile);
			}*/
			//System.out.println("Needs walk path");

		//};*/
	}
}
