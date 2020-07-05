package fear.taskscripts.scripts;

import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.Tile;

import fear.taskscripts.Task;
import fear.taskscripts.tasks.Attack;
import fear.taskscripts.tasks.Drop;
import fear.taskscripts.tasks.TakeGroundItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Script.Manifest(
	name = "Attack Mobs!", 
	properties = "author=EldritchRites topic=-1 client=4;", 
	description = "A simple tool for attacking mobs" )

public class AttackMobs extends PollingScript<ClientContext> {
	private List<Task> taskList = new ArrayList<Task>();
	
	@Override
    public void start() {
        System.out.println("Initiating AttackMobs.");
        taskList.addAll(Arrays.asList(new Attack(ctx),
        		new TakeGroundItem(ctx),
        		new Drop(ctx)));
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
