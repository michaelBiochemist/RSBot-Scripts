package fear.taskscripts.scripts;

import fear.taskscripts.Task;
import fear.taskscripts.constants.GardenConstants;
import fear.taskscripts.tasks.JuiceSqirk;
import fear.taskscripts.tasks.StealSqirk;

import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Script.Manifest(
		name = "Sorceress Garden", 
		properties = "author=EldritchRites topic=-1 client=4;", 
		description = "Attempts to steal sqirks from the sorceress' garden." )
public class SorceressGarden extends PollingScript<ClientContext> {
	private List<Task> taskList = new ArrayList<Task>();
	
	@Override
    public void start() {
		//int rockIds[] = {11364,11365};
		GardenConstants gconst = new GardenConstants();
        taskList.addAll(Arrays.asList(new StealSqirk(ctx,gconst.AUTUMN_GARDEN),
        		new JuiceSqirk(ctx)));
        System.out.println("Gotta steal stuff");
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
