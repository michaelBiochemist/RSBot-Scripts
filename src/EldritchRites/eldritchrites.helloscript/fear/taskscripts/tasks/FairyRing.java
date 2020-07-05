package fear.taskscripts.tasks;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

import fear.taskscripts.Task;

import fear.taskscripts.constants.ItemConstants;
import fear.taskscripts.constants.ObjectConstants;
import fear.taskscripts.constants.PathConstants;

public class FairyRing extends Task<ClientContext> {

    public Tile pathToBank[];

    private String taskType;

    public FairyRing(ClientContext ctx, String scriptType) {
        super(ctx);
        this.taskType = scriptType;
        System.out.println(scriptType);

    }

    @Override
    public boolean activate() {
    	PathConstants ptx = new PathConstants();
    	/**System.out.println(ctx.inventory.select().count()<28 
				&& pathToBank[0].distanceTo(ctx.players.local())>6 
				&& ctx.bank.nearest().tile() != ptx.IGNORE_BANK);*/
    	//System.out.println(ctx.bank.nearest().tile().compareTo(PathConstants.IGNORE_BANK) != 0);
    	//Tile guild_entrance = new Tile(2933, 3288, 0);
    	GameObject ring = null;// = new GameObject();
    	if (!ctx.objects.select().id(ObjectConstants.FAIRY_RING).isEmpty()) {
    		ring = ctx.objects.select().id(ObjectConstants.FAIRY_RING).nearest().poll();
    	};
    	
    	if (ring == null) {
    		return false;
    	};
    	
    	switch (taskType) {

    	case "runecraft":
    		boolean hasEssence = !ctx.inventory.select().id(ItemConstants.RUNE_ESSENCE).isEmpty();
    		
    		return (hasEssence && ring.id()==ObjectConstants.ZANARIS_RING) 
    				|| (!hasEssence && ring.id()==ObjectConstants.TAIBOWANNI_RING); 
    	default:
    		System.out.println("Set a case for your walk task.");
    		return false;
    	}
    }

    @Override
    public void execute() {
    	//System.out.println("Executing walk");
    	//System.out.println(pathToBank[pathToBank.length-1].distanceTo(ctx.players.local()));
    	GameObject ring = ctx.objects.select().id(ObjectConstants.FAIRY_RING).nearest().poll();
    	if (ring.tile().distanceTo(ctx.players.local()) < 6) {
    		ring.interact("Last-Destination");
    	} else {
    		ctx.camera.turnTo(ring);
    		ctx.movement.step(ring);
    	}
    }
}
