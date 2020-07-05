package fear.taskscripts.scripts;

import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.Tile;

import fear.taskscripts.Task;
import fear.taskscripts.constants.ItemConstants;
import fear.taskscripts.constants.ObjectConstants;
import fear.taskscripts.tasks.Drop;
import fear.taskscripts.tasks.Mine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Script.Manifest(
	name = "Mine'n'drop!", 
	properties = "author=EldritchRites topic=-1 client=4;", 
	description = "A simple mining tool for RSBot" )

public class PowerMiner extends PollingScript<ClientContext> {
	private List<Task> taskList = new ArrayList<Task>();
	
	@Override
    public void start() {
		int rockIds[] = {11364,11365,11371,11370,11368,11369};//Gold = 11371,11370, ; Silver=11368,11369
        System.out.println("Initiating PowerMiner.");
        taskList.addAll(Arrays.asList(new Mine(ctx,ObjectConstants.NOOB_ROCKS,Tile.NIL), new Drop(ctx)));
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
