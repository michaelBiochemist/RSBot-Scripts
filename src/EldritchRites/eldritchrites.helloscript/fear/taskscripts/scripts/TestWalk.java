package fear.taskscripts.scripts;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;

import fear.taskscripts.Task;
import fear.taskscripts.constants.PathConstants;
import fear.taskscripts.tasks.Walk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Script.Manifest(
		name = "Test Walk", 
		properties = "author=EldritchRites topic=-1 client=4;", 
		description = "Attempts to walk a designated path." )
public class TestWalk extends PollingScript<ClientContext> {
	private List<Task> taskList = new ArrayList<Task>();
	
	@Override
    public void start() {
		//int rockIds[] = {11364,11365};
        taskList.addAll(Arrays.asList(new Walk(ctx,PathConstants.SARIM_IRON,"acquire")));
        System.out.println("Hi!");
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
