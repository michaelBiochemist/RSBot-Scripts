package fear.taskscripts.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

import fear.taskscripts.Task;

import java.util.concurrent.Callable;

public class Mine extends Task<ClientContext> {

    int rockIds[];
    Tile MINING_SITE;

    public Mine(ClientContext ctx, int[] rockIds, Tile MINING_SITE) {
        super(ctx);
        this.rockIds = rockIds;
        this.MINING_SITE=MINING_SITE;
    }

    @Override
    public boolean activate() {
    	//System.out.println("Mining Site="+(MINING_SITE==Tile.NIL)+" Reachable? "+MINING_SITE.matrix(ctx).reachable());
        return (
        		//ctx.objects.select().id(rockIds).poll().equals(ctx.objects.nil()) 
        		!ctx.objects.select().id(rockIds).isEmpty()
        		&& (ctx.objects.select().id(rockIds).nearest().poll().tile().distanceTo(MINING_SITE) < 20 || MINING_SITE==Tile.NIL )
        		&& (MINING_SITE==Tile.NIL || MINING_SITE.matrix(ctx).reachable())
        		&& ctx.players.local().animation()==-1)
        		&& ctx.inventory.select().count()<28;
    }

    @Override
    public void execute() {
    	//System.out.println("Executing Mine.");
        GameObject rockToMine = ctx.objects.select().id(rockIds).nearest().poll();
        //rockLocation = rockToMine.tile();
        if (rockToMine.inViewport()) {
        rockToMine.interact("Mine");
        } else {
			ctx.movement.step(rockToMine);
			ctx.camera.turnTo(rockToMine);	
        }
        Condition.wait(new Callable<Boolean>(){
            @Override
            public Boolean call() throws Exception {
                return ctx.players.local().animation()!=-1;
            }
        }, 200, 10);

    }
}
