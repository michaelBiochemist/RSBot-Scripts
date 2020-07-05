package fear.taskscripts.tasks;

import fear.taskscripts.constants.ItemConstants;
import fear.taskscripts.constants.*;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

import fear.taskscripts.Task;
//import fear.taskscripts.constants.ItemConstants;
//import fear.taskscripts.constants.PathConstants;

import java.util.concurrent.Callable;

public class Bank extends Task<ClientContext> {
	public int[][] withdrawItems;
	public int bankAmount;
    public Bank(ClientContext ctx, int[][] takeItems, int bankAmount) {
        super(ctx);
        withdrawItems=takeItems;
        this.bankAmount=bankAmount;
    }

    public boolean withdraw() {
    	if (withdrawItems.length == 0) {
    		return true;
    	}
    	for(int[] takeItem : withdrawItems) {
    		if (!ctx.bank.withdraw(takeItem[0], takeItem[1])) {
    			return false;
    		}
    			
    	}
    	return true;
    }
    @Override
    public boolean activate() {
    	//System.out.println(PathConstants.IGNORE_BANK == ctx.bank.nearest().tile());
    	//System.out.println(ctx.bank.nearest().tile().distanceTo(ctx.players.local()) < 8);
    	//System.out.println(ctx.bank.nearest().tile().compareTo(PathConstants.IGNORE_BANK));
    	boolean useBank = (ctx.inventory.select().count() == bankAmount 
        		&& ctx.inventory.select().id(ItemConstants.DONT_BANK).count()==0) 
        		&& (ctx.bank.nearest().tile().distanceTo(ctx.players.local())<8 
        				|| ctx.objects.select().id(ObjectConstants.BANK_BOOTH).nearest().poll().tile().distanceTo(ctx.players.local()) < 8)
        		&& (ctx.bank.nearest().tile().matrix(ctx).reachable()
        				|| ctx.objects.select().id(ObjectConstants.BANK_BOOTH).nearest().poll().inViewport())
        		&& ctx.bank.nearest().tile().compareTo(PathConstants.IGNORE_BANK) != 0;
    	if (ctx.bank.opened() && !useBank) {
    		ctx.bank.close();
    	}
    	//System.out.println(useBank);
        return useBank;
    }

    @Override
    public void execute() {
    	
    	System.out.println("Executing bank");
        if(ctx.bank.opened()){
        	System.out.println("Bank Opened!");
            if(ctx.bank.depositInventory()){
            	System.out.println("Deposited?");
                final int inventCount = ctx.inventory.select().count();
                //withdraw();
                System.out.println("Completed withdraw");
                Condition.wait(new Callable<Boolean>(){
                    @Override
                    public Boolean call() throws Exception {
                        //return ctx.inventory.select().count() != inventCount;
                        return withdraw();
                    }
                }, 250, 20);
            }
        } else {
        	GameObject booth = ctx.objects.select().id(ObjectConstants.BANK_BOOTH).nearest().poll();
            if(booth.inViewport() || ctx.bank.inViewport()) { //bank.inViewport()
                if(ctx.bank.open()){
                	
                	System.out.println("Bank Opened? "+ctx.bank.opened());
                	this.execute();
                    /*Condition.wait(new Callable<Boolean>(){
                        @Override
                        public Boolean call() throws Exception {
                            return ctx.bank.opened();
                        }
                    }, 250, 20);*/
                } else {
                	booth.click("Use");
                }
                
            } else {
            	if (ctx.bank.nearest().tile().distanceTo(ctx.players.local()) < 8) {
            		ctx.camera.turnTo(ctx.bank.nearest());
                    ctx.movement.step(ctx.bank.nearest());
            	} else {
            		ctx.camera.turnTo(booth);
                    ctx.movement.step(booth);
            	}
                
            }
        }
    }
}
