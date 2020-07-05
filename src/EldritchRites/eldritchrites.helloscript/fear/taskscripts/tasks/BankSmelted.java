package fear.taskscripts.tasks;

import fear.taskscripts.constants.PathConstants;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;

import fear.taskscripts.Task;
//import fear.taskscripts.constants.ItemConstants;
//import fear.taskscripts.constants.PathConstants;
import fear.taskscripts.constants.ItemConstants;
import java.util.concurrent.Callable;

public class BankSmelted extends Task<ClientContext> {
	public int[][] withdrawItems;
	public int[] processItems;
    public BankSmelted(ClientContext ctx, int[][] takeItems, int[] itemsToProcess) {
        super(ctx);
        this.withdrawItems=takeItems;
        this.processItems=itemsToProcess;
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
    	/**System.out.println(PathConstants.IGNORE_BANK == ctx.bank.nearest().tile());
    	System.out.println(ctx.bank.nearest().tile().distanceTo(ctx.players.local())<6);
    	System.out.println(ctx.bank.nearest().tile().compareTo(PathConstants.IGNORE_BANK));*/
    	//System.out.println(ctx.inventory.select().id(this.processItems).count()==0);
    	boolean useBank = ctx.inventory.select().id(this.processItems).count()==0  
        		&& ctx.bank.nearest().tile().distanceTo(ctx.players.local())<20
        		//&& (ctx.bank.nearest().tile().matrix(ctx).reachable()
        		//		|| ctx.objects.select().id(24101).nearest().poll().inViewport())
        		&& ctx.bank.nearest().tile().compareTo(PathConstants.IGNORE_BANK) != 0;
    	if (ctx.bank.opened() && !useBank) {
    		ctx.bank.close();
    	}
        return useBank;
    }

    @Override
    public void execute() {
    	System.out.println("Executing bank");
        if(ctx.bank.opened()){
        	System.out.println("BankSmelted Opened!");
            if(ctx.bank.depositInventory()){
            	System.out.println("Deposited?");
                final int inventCount = ctx.inventory.select().count();
                Condition.wait(new Callable<Boolean>(){
                    @Override
                    public Boolean call() throws Exception {
                        //return ctx.inventory.select().count() != inventCount;
                        return withdraw();
                    }
                }, 250, 20);
             System.out.println("Finished withdraw");   
            }
        } else {
            if(ctx.bank.inViewport()) {
                if(ctx.bank.open()){
                	System.out.println("BankSmelted Opened? "+ctx.bank.opened());
                	this.execute();
                    /*Condition.wait(new Callable<Boolean>(){
                        @Override
                        public Boolean call() throws Exception {
                            return ctx.bank.opened();
                        }
                    }, 250, 20);*/
                }
            } else {
                ctx.camera.turnTo(ctx.bank.nearest());
                ctx.movement.step(ctx.bank.nearest());
            }
        }
    }
}
