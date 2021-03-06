package fear.taskscripts.noobscripts;

import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;

import fear.taskscripts.Task;
import fear.taskscripts.constants.ItemConstants;
import fear.taskscripts.constants.ObjectConstants;
import fear.taskscripts.constants.PathConstants;
import fear.taskscripts.tasks.Drop;
import fear.taskscripts.tasks.Mine;
import fear.taskscripts.tasks.Walk;
import fear.taskscripts.tasks.Bank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Script.Manifest(
	name = "Mine Copper/Tin!", 
	properties = "author=EldritchRites topic=-1 client=4;", 
	description = "A simple mining tool that banks Copper/Tin" )

public class NoobMiner extends PollingScript<ClientContext> {
	private List<Task> taskList = new ArrayList<Task>();
	
	@Override
    public void start() {
		int rockIds[] = ObjectConstants.NOOB_ROCKS;
        System.out.println("Starting to mine gems.");
        taskList.addAll(Arrays.asList(new Mine(ctx,rockIds,PathConstants.FISHMINE_LUMBERIDGE[0]), 
        		new Drop(ctx),
        		new Walk(ctx, PathConstants.FISHMINE_LUMBERIDGE,"acquire"), 
        		new Bank(ctx,ItemConstants.NOOB_MINING_ITEMS,28)));
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
