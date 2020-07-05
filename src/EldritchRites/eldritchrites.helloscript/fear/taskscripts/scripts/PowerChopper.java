package fear.taskscripts.scripts;

import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;

import fear.taskscripts.Task;
import fear.taskscripts.tasks.Chop;
import fear.taskscripts.tasks.Drop;
import fear.taskscripts.tasks.SetFire;
import fear.taskscripts.tasks.Walk;

import fear.taskscripts.constants.PathConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Script.Manifest(name = "Chop'n'Burn!", properties = "author=EldritchRites topic=-1 client=4;",         description = "A simple woodcutting tool for RSBot" )
public class PowerChopper extends PollingScript<ClientContext> {
	private List<Task> taskList = new ArrayList<Task>();
	
	@Override
    public void start() {
        taskList.addAll(Arrays.asList(new Chop(ctx),
        		//new Drop(ctx)
        		new Walk(ctx,PathConstants.WOODCUT_BARB,"woodcut"),
        		new SetFire(ctx, PathConstants.WOODCUT_BARB)
        		));
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
