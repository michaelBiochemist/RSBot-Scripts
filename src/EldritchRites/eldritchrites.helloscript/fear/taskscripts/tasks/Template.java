package fear.taskscripts.tasks;

import org.powerbot.script.rt4.ClientContext;
//import org.powerbot.script.rt4.Item;

import fear.taskscripts.Task;
import fear.taskscripts.constants.ItemConstants;

//import org.powerbot.script.rt4.Game;

public class Template extends Task<ClientContext>{
	public Template(ClientContext ctx) {
		super(ctx);
	}
	
	@Override
	public boolean activate() {

		return false;
		
	}
	
	@Override
	public void execute() {
		System.out.println("Boop.");
	}
}