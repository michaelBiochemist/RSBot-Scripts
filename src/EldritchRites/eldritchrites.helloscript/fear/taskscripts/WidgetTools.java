package fear.taskscripts;

import org.powerbot.script.rt4.Widget;
import java.awt.Point;

import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;
public class WidgetTools {
	public static Component LastChecked;
	
	public static Point hoverSubComponent(ClientContext ctx,Component checkComponent, String menuText) {
		Point blanky = new Point();
		blanky.setLocation(-1,-1);// = new Point2D(-1,-1);
		for (Component j : checkComponent.components()) {
			j.hover();
			
			if (ctx.menu.commands()[0].toString().strip().equals(menuText)) {
				System.out.println("Widget component at "+checkComponent.centerPoint().x+" "+checkComponent.centerPoint().y
						+ "Id "+ checkComponent.index()+ " widget"+checkComponent.parent().index());
				System.out.println("Widget component at "+j.centerPoint().x+" "+j.centerPoint().y
						+ "Id "+ j.index()+ " widget"+j.parent().id());
				
				blanky.setLocation(j.centerPoint());
				break;
			};
		}
		return blanky;
	}
	public static Point getClickable(ClientContext ctx, int widgetId,String menuText) {
		Widget mywidget = ctx.widgets.select().id(widgetId).poll();
		//for (Component i : ctx.components.select(visibleComponents)) {
		Point blanky = new Point();
		blanky.setLocation(-1,-1);// = new Point2D(-1,-1);
		
		if (LastChecked != null) {
			blanky = hoverSubComponent(ctx,LastChecked,menuText);
			if (blanky.x != -1) {
				return blanky;
			};
		};
		
		for (Component i : mywidget.components()) {
			blanky = hoverSubComponent(ctx,i,menuText);
			if (blanky.x != -1) {
				LastChecked = i;
				break;
			};
		}

		return blanky;
		//return 
}
}
