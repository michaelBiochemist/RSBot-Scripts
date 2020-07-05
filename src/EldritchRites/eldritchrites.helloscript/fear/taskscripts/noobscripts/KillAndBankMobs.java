package fear.taskscripts.noobscripts;

import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.Tile;

import fear.taskscripts.Task;
import fear.taskscripts.constants.ItemConstants;
import fear.taskscripts.constants.PathConstants;
import fear.taskscripts.tasks.Bank;
import fear.taskscripts.tasks.Attack;
import fear.taskscripts.tasks.Drop;
import fear.taskscripts.tasks.Walk;
import fear.taskscripts.tasks.TakeGroundItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Script.Manifest(
	name = "Attack & Bank Mobs!", 
	properties = "author=EldritchRites topic=-1 client=4;", 
	description = "A noob's tool for attacking mobs and banking the loot" )

public class KillAndBankMobs extends PollingScript<ClientContext> {
	private List<Task> taskList = new ArrayList<Task>();
	
	@Override
    public void start() {
		Tile usePath[] = {};
		Tile pLoc = ctx.players.local().tile();

		if (pLoc.distanceTo(PathConstants.COWS_FALADOR[0])<15 || pLoc.distanceTo(PathConstants.COWS_FALADOR[PathConstants.COWS_FALADOR.length-1])<15) {
			usePath = PathConstants.COWS_FALADOR;
		};
        System.out.println("Initiating KillAndBankMobs.");
        taskList.addAll(Arrays.asList(new Attack(ctx),
        		new TakeGroundItem(ctx),
        		new Walk(ctx,usePath,"acquire"),
        		new Bank(ctx,ItemConstants.NOOB_COMBAT,28)
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
