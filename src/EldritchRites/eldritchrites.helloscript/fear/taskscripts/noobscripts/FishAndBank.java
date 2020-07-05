package fear.taskscripts.noobscripts;

import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.Tile;

import fear.taskscripts.Task;
import fear.taskscripts.tasks.Bank;
import fear.taskscripts.tasks.CookFish;
import fear.taskscripts.tasks.Drop;
import fear.taskscripts.tasks.Fish;
import fear.taskscripts.tasks.Walk;
import fear.taskscripts.constants.ItemConstants;
import fear.taskscripts.constants.PathConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Script.Manifest(
	name = "Fish'n'bank!", 
	properties = "author=EldritchRites topic=-1 client=4;", 
	description = "A simple fishing tool for RSBot" )

public class FishAndBank extends PollingScript<ClientContext> {
	private List<Task> taskList = new ArrayList<Task>();
	
	@Override
    public void start() {
		int rockIds[] = {11364,11365,11371,11370,11368,11369};//Gold = 11371,11370, ; Silver=11368,11369
        System.out.println("Initiating Fisher.");
        String FishingMethod = "";
        int [][] bankItems = {{}};
        if (!ctx.inventory.select().id(ItemConstants.FLY_FISH_ITEMS[0][0]).isEmpty()) {
        	FishingMethod = "Lure";
        	bankItems = ItemConstants.FLY_FISH_ITEMS;
        } else if (!ctx.inventory.select().id(ItemConstants.NETFISH_ITEMS[0][0]).isEmpty()) {
        	FishingMethod = "Net";
        	bankItems = ItemConstants.NETFISH_ITEMS;
        } else {
        	FishingMethod = "Use-rod";
        }
        Tile usePath[] = {};
        
        Tile ploc = ctx.players.local().tile();
        if (ploc.distanceTo(PathConstants.FISHMINE_LUMBERIDGE[0])<15 || ploc.distanceTo(PathConstants.FISHMINE_LUMBERIDGE[PathConstants.FISHMINE_LUMBERIDGE.length-1])<15) {
        	usePath = PathConstants.FISHMINE_LUMBERIDGE;
        } else if (ploc.distanceTo(PathConstants.FLY_FISH_BARBV[0])<15 || ploc.distanceTo(PathConstants.FLY_FISH_BARBV[PathConstants.FISHMINE_LUMBERIDGE.length-1])<15) {
        	usePath = PathConstants.FLY_FISH_BARBV;
        }
        
        taskList.addAll(Arrays.asList(new Fish(ctx,FishingMethod)//"Lure")
        		, new Drop(ctx)
        		//, new CookFish(ctx)
        		, new Walk(ctx,usePath,"acquire")
        		, new Bank(ctx,bankItems,28)
        		));
	}

    @Override
    public void poll() {
    	for (Task task: taskList) {
    		if (task.activate() ) {
    			task.execute();
    		}
    	}
    }
}
