package fear.taskscripts.scripts;

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
	name = "Mine Gem!", 
	properties = "author=EldritchRites topic=-1 client=4;", 
	description = "A simple mining tool that banks Gems" )

public class GemMiner extends PollingScript<ClientContext> {
	private List<Task> taskList = new ArrayList<Task>();
	
	@Override
    public void start() {
		int rockIds[] = ObjectConstants.MINE_ROCKS;
        System.out.println("Starting to mine gems.");
        taskList.addAll(Arrays.asList(new Mine(ctx,rockIds,PathConstants.SHILO_VILLAGE_GEMS[0]), 
        		new Drop(ctx),
        		new Walk(ctx, PathConstants.SHILO_VILLAGE_GEMS,"acquire"), 
        		new Bank(ctx,ItemConstants.TAKE_NOTHING,28)));
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
