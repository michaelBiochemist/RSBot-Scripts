package fear.taskscripts.scripts;

import org.powerbot.script.Script;
//import org.powerbot.script.rt4.
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.GroundItem;
import org.powerbot.script.rt4.Npc;
import org.powerbot.script.rt4.Player;
import org.powerbot.script.rt4.Item;
import org.powerbot.script.PollingScript; 

@Script.Manifest(name = "Read Ids!", properties = "author=EldritchRites topic=-1 client=4;",         description = "A simple id reader for RSBot" )
public class ReadObjects extends PollingScript<ClientContext> {
	//@Override
	public ReadObjects() {
		ctx.camera.angle(0);
		final Player p = ctx.players.local();
		System.out.println("Player Location="+p.tile()+" "+p.orientation());
		for(Item ctxitem : ctx.inventory.items() ) {
			if (ctxitem.id() != -1) {
				System.out.println("Inventory: Id="+ ctxitem.id() + " Name='"+ctxitem.name()+"'");
			}
		}
		
		int objectRange = 2;
		for(GroundItem ctxGI : ctx.groundItems.get()) {
			System.out.println("GroundItem: Id="+ctxGI.id() +" Name='"+ctxGI.name()+"'"
					+" Location="+ctxGI.tile());
		}
		for(Npc ctxNpc : ctx.npcs.get()) {
			System.out.println("NPC: Id="+ctxNpc.id() +" Name='"+ctxNpc.name()+"'"
					+" Location="+ctxNpc.tile() + " "+ctxNpc.orientation()
					);
		}
		
		for(GameObject ctxobject : ctx.objects.get(objectRange)) { //Default Radius 7
			if(ctxobject.name() != null && ctxobject.name() != "null") {
				System.out.println("Objects: Id="+ctxobject.id() + " Name='"+ctxobject.name()+"'"
						+" Location="+ctxobject.tile()
						//+" Meta='"+ctxobject.()+"'"
						//+"Actions='"+ctxobject.+"'"
						);
			}
		}
		//ctx.movement.
		//ctx.bank.deposit(314, 14);
	}       
	@Override     
	public void poll() {       } 
} 
//S. Fally Mining -- (2962,3240,0)
//'Guild Door' Location=(2933, 3289, 0)
//'Bank booth' Location=(3014, 3354, 0)

//Object List:
//Coal = 11367,11366
//Tin = 11360,11361
//Fire = 26185

//NPC List:
//Rod Fishing Spot= 1526

//Items List
//feather=314
//Salmon(Cooked)=329 (Raw)=
//Burnt Fish=343
//Trout(Cooked)=333 (Raw)=335
//Bones
