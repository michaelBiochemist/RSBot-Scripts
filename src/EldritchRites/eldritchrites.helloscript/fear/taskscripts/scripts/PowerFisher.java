package fear.taskscripts.scripts;

import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.Tile;

import fear.taskscripts.Task;
import fear.taskscripts.constants.ItemConstants;
import fear.taskscripts.tasks.CookFish;
import fear.taskscripts.tasks.Drop;
import fear.taskscripts.tasks.Fish;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Script.Manifest(
	name = "Fish'n'drop!", 
	properties = "author=EldritchRites topic=-1 client=4;", 
	description = "A simple fishing tool for RSBot" )

public class PowerFisher extends PollingScript<ClientContext> {
	private List<Task> taskList = new ArrayList<Task>();
	
	@Override
    public void start() {
		int rockIds[] = {11364,11365,11371,11370,11368,11369};//Gold = 11371,11370, ; Silver=11368,11369
        System.out.println("Initiating PowerFisher.");
        String FishingMethod = "";
        if (!ctx.inventory.select().id(ItemConstants.FLY_FISH_ITEMS[0][0]).isEmpty()) {
        	FishingMethod = "Lure";
        } else {
        	FishingMethod = "Use-rod";
        }
        taskList.addAll(Arrays.asList(new Fish(ctx,FishingMethod)//"Lure")
        		, new Drop(ctx)
        		, new CookFish(ctx)
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
