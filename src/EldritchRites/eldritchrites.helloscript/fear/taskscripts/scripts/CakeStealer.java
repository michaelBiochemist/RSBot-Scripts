package fear.taskscripts.scripts;

import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;

import fear.taskscripts.Task;
import fear.taskscripts.constants.ItemConstants;
import fear.taskscripts.constants.ObjectConstants;
import fear.taskscripts.constants.PathConstants;
import fear.taskscripts.tasks.Drop;
import fear.taskscripts.tasks.StealStall;
import fear.taskscripts.tasks.Walk;
import fear.taskscripts.tasks.Bank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Script.Manifest(
	name = "Steal Cake!", 
	properties = "author=EldritchRites topic=-1 client=4;", 
	description = "Steal Cake from Ardogune" )

public class CakeStealer extends PollingScript<ClientContext> {
	private List<Task> taskList = new ArrayList<Task>();
	
	@Override
    public void start() {
        System.out.println("Starting to Steal From Baker's stall.");
        taskList.addAll(Arrays.asList(new StealStall(ctx,PathConstants.ARDY_BAKE_STALL[0]), 
        		//new Drop(ctx),
        		new Walk(ctx, PathConstants.ARDY_BAKE_STALL,"steal-stall"), 
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
