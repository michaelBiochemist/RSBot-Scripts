package fear.taskscripts.scripts;

import org.powerbot.script.Script;
//import org.powerbot.script.rt4.
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.PollingScript; 

import org.powerbot.script.rt4.Widgets;

//import z.i;

import org.powerbot.script.rt4.Widget;
import org.powerbot.script.rt4.Component;
import org.powerbot.script.rt4.Npc;
import org.powerbot.script.Filter;

@Script.Manifest(name = "Read Widgets!", properties = "author=EldritchRites topic=-1 client=4;",         description = "A simple widget reader for RSBot" )
public class ReadWidgets extends PollingScript<ClientContext> {
	//@Override
	public ReadWidgets() {
		Filter<Component> visibleComponents = new Filter<Component>() {
			@Override
			public boolean accept(Component component) {
				boolean valid = component.valid();
				boolean visible = component.visible();
				//boolean id = component.widget()
				boolean actionable = (component.actions().length != 0);
				return visible && valid; 
				//return actionable;
			}
		};
		Widget mywidget = ctx.widgets.select().id(312).poll();
		//for (Component i : ctx.components.select(visibleComponents)) {
		for (Component i : mywidget.components()) {
			for (Component j : i.components()) {
				j.hover();
				
				if (ctx.menu.commands()[0].toString().strip().equals("Smith set Steel nails")) {
					System.out.println("Widget component at "+i.centerPoint().x+" "+i.centerPoint().y
							+ "Id "+ i.index()+ " widget"+i.parent().index());
					System.out.println("Widget component at "+j.centerPoint().x+" "+j.centerPoint().y
							+ "Id "+ j.index()+ " widget"+j.parent().id());
					break;
				};
			}
			//System.out.println(i.hover());
			//i.hover();
			//ctx.input.focus(i.centerPoint());

			System.out.println(ctx.menu.commands()[0]);
			//System.out.println(i.actions()[0]);
		}

		//System.out.println("Player Location="+p.tile());

		//ctx.movement.
		//ctx.bank.deposit(314, 14);
	}       
	@Override     
	public void poll() {       } 
} 
