package fear.taskscripts.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
//import fear.powerchopper.Walker;
import org.powerbot.script.rt4.Item;

import fear.taskscripts.Task;
import fear.taskscripts.Walker;
import fear.taskscripts.constants.ItemConstants;
import fear.taskscripts.constants.ObjectConstants;
import fear.taskscripts.constants.PathConstants;

public class Walk extends Task<ClientContext> {

    public Tile pathToBank[];
    private final Walker walker = new Walker(ctx);
    private String taskType;
    
	boolean natureTiara;
	boolean hasFairyStaff;
	boolean fairyRingNearby;
	boolean fairyRingZanaris;
	boolean hasEssence;

    public Walk(ClientContext ctx, Tile[] pathToBank, String scriptType) {
        super(ctx);
        this.taskType = scriptType;
        this.pathToBank = pathToBank;
        System.out.println(scriptType);

    }

    @Override
    public boolean activate() {
    	for (Item i : ctx.inventory.select().id(ItemConstants.DONT_WALK)) {
    		return false;
    	}
    	PathConstants ptx = new PathConstants();
    	/**System.out.println(ctx.inventory.select().count()<28 
				&& pathToBank[0].distanceTo(ctx.players.local())>6 
				&& ctx.bank.nearest().tile() != ptx.IGNORE_BANK);*/
    	//System.out.println(ctx.bank.nearest().tile().compareTo(PathConstants.IGNORE_BANK) != 0);
    	//Tile guild_entrance = new Tile(2933, 3288, 0);
    	switch (taskType) {
    	case "acquire":
            return ctx.inventory.select().count()==28 && pathToBank[pathToBank.length-1].distanceTo(ctx.players.local())>5 || 
            		(ctx.inventory.select().count()<28 
            				&& (pathToBank[0].distanceTo(ctx.players.local())>14 || ctx.players.local().tile().equals(new Tile(2933, 3288, 0)))
            				
            				//&& (ctx.bank.nearest().tile().compareTo(PathConstants.IGNORE_BANK) != 0 || ctx.bank.nearest().tile().compareTo(PathConstants.IGNORE_BANK) == 0 && ctx.bank.nearest().tile().distanceTo(ctx.players.local()) > 5)
            				&& !ctx.players.local().inMotion());
    	case "smelt":
    		return (ctx.inventory.select().id(ItemConstants.SMELT_ITEMS).count() != 0
    			&& pathToBank[0].distanceTo(ctx.players.local()) > 9)
    			|| (ctx.inventory.select().id(ItemConstants.SMELT_ITEMS).count() == 0
    					&& ctx.inventory.select().id(ItemConstants.SMELTED_ITEMS).count() != 0
    	    			&& pathToBank[0].distanceTo(ctx.players.local()) < 5);
    	case "steal-stall":
            return ctx.inventory.select().count()>27 && pathToBank[pathToBank.length-1].distanceTo(ctx.players.local())>5 || 
            		(ctx.inventory.select().count() != 28 
            				&& (pathToBank[0].distanceTo(ctx.players.local())>3)
            				
            				//&& (ctx.bank.nearest().tile().compareTo(PathConstants.IGNORE_BANK) != 0 || ctx.bank.nearest().tile().compareTo(PathConstants.IGNORE_BANK) == 0 && ctx.bank.nearest().tile().distanceTo(ctx.players.local()) > 5)
            				&& !ctx.players.local().inMotion());
    	case "runecraft":
    		this.natureTiara = (!ctx.equipment.select().id(ItemConstants.TIARAS[4]).isEmpty());
    		this.hasFairyStaff = (!ctx.equipment.select().id(ItemConstants.FAIRY_STAFF).isEmpty());
    		this.fairyRingNearby = (!ctx.objects.select().id(ObjectConstants.FAIRY_RING).isEmpty());
    		this.fairyRingZanaris = (!ctx.objects.select().id(ObjectConstants.ZANARIS_RING).isEmpty());
    		this.hasEssence = (ctx.inventory.select().id(ItemConstants.RUNE_ESSENCE).count() != 0);
    		
    		return (ctx.inventory.select().id(ItemConstants.RUNE_ESSENCE).count() != 0 && pathToBank[0].distanceTo(ctx.players.local()) > 8
    		|| ctx.inventory.select().count()==1 && pathToBank[pathToBank.length-1].distanceTo(ctx.players.local())>7)
    				&& (pathToBank[0].distanceTo(ctx.players.local()) < 1000 || pathToBank[pathToBank.length-1].distanceTo(ctx.players.local()) < 1000);
    	case "woodcut":
    		return (ctx.inventory.select().id(ItemConstants.LOGS).isEmpty() || ctx.inventory.select().count()==28);
    	default:
    		System.out.println("Set a case for your walk task.");
    		return false;
    	}
    }

    @Override
    public void execute() {
    	//System.out.println("fairy ring zanaris?: "+this.fairyRingZanaris + " has essence? "+this.hasEssence+" nature tiara? "+this.natureTiara + " fairy staff? "+this.hasFairyStaff
    	//		+ " fairy ring? "+this.fairyRingNearby);
    	//System.out.println(pathToBank[pathToBank.length-1].distanceTo(ctx.players.local()));
    	if (this.taskType == "runecraft") {
    		GameObject fairyRing;
    		boolean useFairyRing = false;
    		String interaction = "";
    		if (this.fairyRingNearby && this.natureTiara) {
    			if (this.hasEssence && this.fairyRingZanaris) {
    				useFairyRing = true;
    				interaction = "Last-Destination";
    			};
    			if (!this.hasEssence && !this.fairyRingZanaris) {
    				useFairyRing = true;
    				interaction = "Zanaris";
    			};
    			if (useFairyRing) {
    				fairyRing = ctx.objects.select().id(ObjectConstants.FAIRY_RING).nearest().poll();
    				if (!fairyRing.inViewport()) {
    					ctx.camera.turnTo(fairyRing);
    					ctx.movement.step(fairyRing);
    				} else {
    					fairyRing.interact(interaction);
    					Condition.sleep(2500);
    					
    				};
    			};
    		};
    	};
    	
        if(!ctx.movement.running() && ctx.movement.energyLevel()> Random.nextInt(35,55)){
            ctx.movement.running(true);
        }

        if (!ctx.players.local().inMotion() || ctx.movement.destination().equals(Tile.NIL) || ctx.movement.destination().distanceTo(ctx.players.local()) < 5) {
            if(ctx.inventory.select().count() == 28 && (taskType == "acquire" || taskType == "steal-stall" || taskType == "smelt" || taskType == "woodcut")
            	|| ctx.inventory.select().count()==1 && taskType == "runecraft") {
                walker.walkPath(pathToBank);
            } else {
                walker.walkPathReverse(pathToBank);
            }
        }

    }
}
