package fear.taskscripts.scripts;

import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;

import fear.taskscripts.Task;
import fear.taskscripts.constants.ItemConstants;
import fear.taskscripts.constants.PathConstants;
import fear.taskscripts.tasks.Smith;
import fear.taskscripts.tasks.Walk;
import fear.taskscripts.tasks.BankSmelted;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Script.Manifest(name = "Smith Nails!", properties = "author=EldritchRites topic=-1 client=4;",         description = "A simple smelting tool for RSBot" )
public class NailSmither extends PollingScript<ClientContext> {
	private List<Task> taskList = new ArrayList<Task>();
	
	@Override
    public void start() {
        taskList.addAll(Arrays.asList(new Smith(ctx) 
        		//new Walk(ctx,PathConstants.SMELT_FURNACE,"smelt"),
        		, new BankSmelted(ctx,ItemConstants.SMITHING_STEEL,ItemConstants.SMITH_ITEMS)
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
