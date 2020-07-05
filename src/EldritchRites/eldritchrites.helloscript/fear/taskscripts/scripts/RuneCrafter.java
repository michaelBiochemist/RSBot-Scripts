package fear.taskscripts.scripts;

import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;

import fear.taskscripts.Task;
import org.powerbot.script.Tile;
import fear.taskscripts.constants.ItemConstants;
import fear.taskscripts.constants.PathConstants;
import fear.taskscripts.tasks.Bank;
import fear.taskscripts.tasks.CraftRune;
import fear.taskscripts.tasks.Walk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Script.Manifest(name = "Craft Runes!", properties = "author=EldritchRites topic=-1 client=4;",         description = "A simple rune crafting tool for RSBot" )
public class RuneCrafter extends PollingScript<ClientContext> {
	private List<Task> taskList = new ArrayList<Task>();
	
	@Override
    public void start() {
		Tile [] usePath;
		if (ctx.equipment.select().id(ItemConstants.TIARAS[0]).count()!=0) {
			usePath = PathConstants.AIR_RUNE;
		}
		else if (ctx.equipment.select().id(ItemConstants.TIARAS[1]).count()!=0) {
			usePath = PathConstants.MIND_RUNE;
		} else if (ctx.equipment.select().id(ItemConstants.TIARAS[2]).count()!=0){
			usePath = PathConstants.BODY_RUNE;
		} else if(ctx.equipment.select().id(ItemConstants.TIARAS[3]).count()!=0) {
			usePath = PathConstants.COSMIC_RUNE;
		} else {
			usePath = PathConstants.NATURE_RUNE;
		};
        taskList.addAll(Arrays.asList(new CraftRune(ctx), 
        		new Walk(ctx, usePath,"runecraft"),
        		new Bank(ctx,ItemConstants.RUNECRAFT_ITEMS,1)
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
