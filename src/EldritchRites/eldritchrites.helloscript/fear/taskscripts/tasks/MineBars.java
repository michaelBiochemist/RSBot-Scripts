package fear.taskscripts.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Filter;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.Item;
import org.powerbot.script.rt4.Magic;
//import org.powerbot.script.rt4.Npc;

import fear.taskscripts.Task;

import java.util.concurrent.Callable;

public class MineBars extends Task<ClientContext> {

    int rockIds;
    int []coal_rocks= {11366};
    int []mithril_rocks= {11373,11372};
    int coal=453;
    int mithril_ore=447;
    int nature_rune=561;
    boolean debug=false;
    Tile MINING_SITE;
    Filter<GameObject> oreAvailable;

    public MineBars(ClientContext ctx, Tile MINING_SITE) {
        super(ctx);
        this.MINING_SITE=MINING_SITE;
        this.MINING_SITE=new Tile(3049, 9739, 0);
        
        this.oreAvailable = new Filter<GameObject>() {
			@Override
			public boolean accept(GameObject gobj) {
				boolean reachable = gobj.tile().distanceTo(MINING_SITE) < 12;
				return reachable;
				//return true;
			}
		};
    }
    /** 
     * Inventory: Id=561 Name='Nature rune'
     * Inventory: Id=2359 Name='Mithril bar'
     * Inventory: Id=453 Name='Coal'
     * Inventory: Id=447 Name='Mithril ore'
     */

    @Override
    public boolean activate() {
    	//System.out.println("Mining Site="+MINING_SITE+" Reachable? "+MINING_SITE.matrix(ctx).reachable());
        return (
        		//ctx.objects.select().id(rockIds).poll().equals(ctx.objects.nil()) 
        		!ctx.objects.select().id(coal_rocks).isEmpty()
        		//&& !ctx.objects.select().id(coal_rocks).isEmpty()
        		&& (ctx.inventory.select().count() != 28 || 
        			ctx.inventory.select().id(mithril_ore).count() >= 1
        			&& ctx.inventory.select().id(coal).count() >= 4
        			&& !ctx.inventory.select().id(nature_rune).isEmpty()
        				)
        		//&& ctx.objects.select().id(rockIds).tile().matrix(ctx).reachable()
        		&& (MINING_SITE==Tile.NIL || MINING_SITE.matrix(ctx).reachable())
        		&& ctx.players.local().animation()==-1);
        		//&& ctx.inventory.select().count()<28;
    }

    @Override
    public void execute() {

        GameObject rockToMine = null;
        if (ctx.magic.casting(Magic.Spell.SUPERHEAT_ITEM)) {
        	if (this.debug) {
        		System.out.println("Spell ready to cast");
        	};
        	ctx.inventory.select().poll().click("Cast");
        };
        
        //System.out.println("Filter works? "+!ctx.objects.select().id(mithril_rocks).select(this.oreAvailable).isEmpty());
        if ((ctx.inventory.select().id(mithril_ore).count()==0 || ctx.inventory.select().id(nature_rune).count()==0)
        		&& !ctx.objects.select().id(mithril_rocks).nearest().select(this.oreAvailable).isEmpty()) {
        	if (this.debug) {
        		System.out.println("Mining Mithril");
        	};
        	rockToMine = ctx.objects.select().id(mithril_rocks).nearest().select(oreAvailable).poll();
        }
        else if (ctx.inventory.select().id(coal).count() < 4) {
        	if (this.debug) {
        		System.out.println("Mining Coal");
        	};
        	
        	rockToMine = ctx.objects.select().id(coal_rocks).nearest().poll();
        } else if (ctx.inventory.select().id(nature_rune).count() != 0 && ctx.inventory.select().id(mithril_ore).count()!=0) {
        	if (this.debug) {
        		System.out.println("Smelting Mithril");
        	}

        	Item mith = ctx.inventory.select().id(mithril_ore).poll();
        	ctx.magic.cast(Magic.Spell.SUPERHEAT_ITEM);
        	mith.click("Cast");
            Condition.wait(new Callable<Boolean>(){
                @Override
                public Boolean call() throws Exception {
                    return ctx.players.local().animation()!=-1;
                }
            }, 200, 10);
        } else { //if (!ctx.objects.select().id(coal_rocks).isEmpty()) {
        	rockToMine = ctx.objects.select().id(coal_rocks).select(oreAvailable).nearest().poll();
        };
        
        if (rockToMine != null) {
            if (rockToMine.inViewport()) {
                rockToMine.interact("Mine");
                } else {
        			ctx.camera.turnTo(rockToMine);	
        			ctx.movement.step(rockToMine);
                }
                Condition.wait(new Callable<Boolean>(){
                    @Override
                    public Boolean call() throws Exception {
                        return ctx.players.local().animation()!=-1;
                    }
                }, 200, 10);
        };
        		//ctx.objects.select().id(coal_rocks).nearest().poll();
        //rockLocation = rockToMineBars.tile();


    }
}
