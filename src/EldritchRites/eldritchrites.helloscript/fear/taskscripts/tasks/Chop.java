package fear.taskscripts.tasks;

import java.util.Date;
import java.util.concurrent.Callable;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

import fear.taskscripts.Task;
import fear.taskscripts.constants.AnimationConstants;
import fear.taskscripts.constants.ItemConstants;

public class Chop extends Task<ClientContext> {
	private int[] treeIds = {10819,10831}; // 10819,10831 = Willow. 10820=Oak Swap these out with Oak tree ids?
	Date LastActed;
	boolean LastActionChop;
	
	public Chop(ClientContext ctx) {
		super(ctx);
		this.LastActed = new Date();
		this.LastActionChop = true;
	}
	
	@Override
	public boolean activate() {
		/**System.out.println("Inventory Count: " + ctx.inventory.select().count());
		System.out.println("Trees not around?: "+ctx.objects.select().id(treeIds).isEmpty());
		*/
		//System.out.println("Is player animated? "+ ctx.players.local().animation());
		int animation = ctx.players.local().animation();
		if (animation != -1) {
			this.LastActed = new Date();
		};
		if (animation == AnimationConstants.LIGHT || ctx.inventory.select().count()==28) {
			this.LastActionChop = false;
		};
		
		return (ctx.inventory.select().id(ItemConstants.LOGS).count() == 0 || ctx.inventory.select().count() < 28
				&& this.LastActionChop)
			&& !ctx.objects.select().id(treeIds).isEmpty()
			&& ctx.players.local().animation() == -1
			&& (new Date().getTime()-(this.LastActed.getTime())) > 3000;
	}
	
	@Override
	public void execute() {
		GameObject tree = ctx.objects.nearest().poll();
		if (tree.inViewport()) {
			//System.out.println("Interacting Chop.");
			tree.interact("Chop");
			this.LastActionChop = true;
            Condition.wait(new Callable<Boolean>(){
                @Override
                public Boolean call() throws Exception {
                    return ctx.players.local().animation()!=-1;
                }
            }, 200, 10);
		} else {
			ctx.movement.step(tree);
			ctx.camera.turnTo(tree);
		}
	}
}
