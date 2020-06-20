package Noyan802;

import org.powerbot.script.*;
import org.powerbot.script.rt4.*;
import org.powerbot.script.rt4.Bank;
import org.powerbot.script.rt4.ClientContext;

import javax.swing.*;
import java.awt.*;

@Script.Manifest(name = "Runecrafter", description ="Creates Runes", properties = "Author = Noyan802; topic =1355202;")
public class RuneCrafter extends PollingScript<ClientContext> implements PaintListener{
    public static final Tile[] tilestoaltar = {new Tile(3011, 3355, 0), new Tile(3012, 3359, 0), new Tile(3008, 3359, 0), new Tile(3008, 3355, 0), new Tile(3008, 3351, 0), new Tile(3008, 3347, 0), new Tile(3008, 3343, 0), new Tile(3008, 3339, 0), new Tile(3008, 3335, 0), new Tile(3008, 3331, 0), new Tile(3008, 3327, 0), new Tile(3008, 3323, 0), new Tile(3008, 3319, 0), new Tile(3007, 3315, 0), new Tile(3007, 3311, 0), new Tile(3007, 3307, 0), new Tile(3007, 3303, 0), new Tile(3006, 3299, 0), new Tile(3006, 3295, 0), new Tile(3002, 3294, 0), new Tile(2998, 3294, 0), new Tile(2994, 3295, 0), new Tile(2990, 3295, 0), new Tile(2986, 3294, 0)};
    public final TilePath pathtoaltar = ctx.movement.newTilePath(tilestoaltar);
    public final TilePath altartobank = ctx.movement.newTilePath(tilestoaltar).reverse();
    public static final Tile[] tilestofirealtar = {new Tile(3269, 3167, 0), new Tile(3273, 3167, 0), new Tile(3275, 3171, 0), new Tile(3276, 3175, 0), new Tile(3279, 3178, 0), new Tile(3280, 3182, 0), new Tile(3280, 3186, 0), new Tile(3280, 3190, 0), new Tile(3283, 3194, 0), new Tile(3283, 3198, 0), new Tile(3282, 3202, 0), new Tile(3285, 3205, 0), new Tile(3289, 3207, 0), new Tile(3293, 3208, 0), new Tile(3297, 3210, 0), new Tile(3298, 3214, 0), new Tile(3300, 3218, 0), new Tile(3301, 3222, 0), new Tile(3301, 3226, 0), new Tile(3298, 3230, 0), new Tile(3298, 3234, 0), new Tile(3298, 3238, 0), new Tile(3297, 3242, 0), new Tile(3301, 3244, 0), new Tile(3305, 3245, 0), new Tile(3308, 3248, 0), new Tile(3311, 3252, 0)};
    public final TilePath pathtofirealtar = ctx.movement.newTilePath(tilestofirealtar);
    public final TilePath firealtartobank = ctx.movement.newTilePath(tilestofirealtar).reverse();
    public static final Tile[] tilestobodyaltar = {new Tile(3094, 3491, 0), new Tile(3090, 3490, 0), new Tile(3087, 3487, 0), new Tile(3083, 3485, 0), new Tile(3081, 3481, 0), new Tile(3080, 3477, 0), new Tile(3080, 3473, 0), new Tile(3079, 3469, 0), new Tile(3083, 3467, 0), new Tile(3086, 3464, 0), new Tile(3086, 3460, 0), new Tile(3082, 3457, 0), new Tile(3079, 3453, 0), new Tile(3075, 3452, 0), new Tile(3072, 3449, 0), new Tile(3072, 3445, 0), new Tile(3072, 3441, 0), new Tile(3068, 3443, 0), new Tile(3065, 3446, 0), new Tile(3062, 3449, 0), new Tile(3058, 3450, 0), new Tile(3058, 3446, 0), new Tile(3055, 3443, 0)};
    public final TilePath pathtobodyaltar = ctx.movement.newTilePath(tilestobodyaltar);
    public final TilePath bodyaltartobank = ctx.movement.newTilePath(tilestobodyaltar).reverse();
    int StartExp = ctx.skills.experience(Constants.SKILLS_RUNECRAFTING);
    int x;
    @Override
    public void start() {
        System.out.println("Starting...");
        String userOptions[] = {"Air Rune", "Fire Rune","Body Rune"};
        String userChoice = (String) JOptionPane.showInputDialog(null, "Choose which rune to craft:", "Runecrafter", JOptionPane.PLAIN_MESSAGE, null, userOptions, userOptions[0]);
        if (userChoice.equals("Air Rune")) {
            x = 1;
        } else if (userChoice.equals("Fire Rune")){
            x = 2 ;
        }
        else if (userChoice.equals("Body Rune")){
            x=3;
        }
    }

    @Override
    public void poll() {
        if (x==1){
        if (ctx.inventory.select().count() > 27) {
            if (!ctx.movement.running() && ctx.movement.energyLevel() > Random.nextInt(25, 45)) {
                ctx.movement.running(true);
            }
            pathtoaltar.traverse();
            GameObject Altar1 = ctx.objects.select().id(29090).poll();
            Altar1.interact("Enter");
            Condition.sleep(1000);
            GameObject Altar2 = ctx.objects.select().id(34760).poll();
            if (Altar2.inViewport()) {
                Altar2.click();
                Condition.sleep(1000);
            } else {
                ctx.camera.turnTo(Altar2);
            }
        }
        else {
            if (!ctx.movement.running() && ctx.movement.energyLevel() > Random.nextInt(25, 45)) {
                ctx.movement.running(true);}
            GameObject Portal = ctx.objects.select().id(34748).poll();
            if (Portal.inViewport()){
            Portal.click();}
            else {
                ctx.camera.turnTo(Portal);
            }
            altartobank.traverse();
            Condition.sleep(5000);
            if (ctx.bank.inViewport()){
                ctx.bank.open();
                ctx.bank.deposit(556,28);
                ctx.bank.withdraw("Pure essence", Bank.Amount.ALL);
            }
        }
    }
        else if (x==2){

            if (ctx.inventory.select().count() > 27) {
                if (!ctx.movement.running() && ctx.movement.energyLevel() > Random.nextInt(25, 45)) {
                    ctx.movement.running(true);
                }
                pathtofirealtar.traverse();
                GameObject Altar1 = ctx.objects.select().id(30372).poll();
                Altar1.interact("Enter");
                Condition.sleep(1000);
                GameObject Altar2 = ctx.objects.select().id(34764).poll();
                if (Altar2.inViewport()) {
                    Altar2.click();
                    Condition.sleep(1000);
                } else {
                    ctx.camera.turnTo(Altar2);
                }
            }
            else {
                if (!ctx.movement.running() && ctx.movement.energyLevel() > Random.nextInt(25, 45)) {
                    ctx.movement.running(true);
                }
                GameObject Portal = ctx.objects.select().id(34752).poll();
                if (Portal.inViewport()) {
                    Portal.click();
                } else {
                    ctx.camera.turnTo(Portal);
                }
                firealtartobank.traverse();
                Condition.sleep(5000);
                if (ctx.bank.inViewport()) {
                    ctx.bank.open();
                    ctx.bank.deposit(554, 28);
                    ctx.bank.withdraw("Pure essence", Bank.Amount.ALL);
                }
            }


        }
        else if (x==3){

            if (ctx.inventory.select().count() > 27) {
                if (!ctx.movement.running() && ctx.movement.energyLevel() > Random.nextInt(25, 45)) {
                    ctx.movement.running(true);
                }
                pathtobodyaltar.traverse();
                GameObject Altar1 = ctx.objects.select().id(31584).poll();
                Altar1.interact("Enter");
                Condition.sleep(2000);
                GameObject Altar2 = ctx.objects.select().id(34765).poll();
                if (Altar2.inViewport()) {
                    Altar2.click();
                    Condition.sleep(1000);
                } else {
                    ctx.camera.turnTo(Altar2);
                }
            }
            else {
                if (!ctx.movement.running() && ctx.movement.energyLevel() > Random.nextInt(25, 45)) {
                    ctx.movement.running(true);
                }
                GameObject Portal = ctx.objects.select().id(34753).poll();
                if (Portal.inViewport()) {
                    Portal.click();
                } else {
                    ctx.camera.turnTo(Portal);
                }
                bodyaltartobank.traverse();
                Condition.sleep(5000);
                if (ctx.bank.inViewport()) {
                    ctx.bank.open();
                    ctx.bank.deposit(559, 28);
                    ctx.bank.withdraw("Pure essence", Bank.Amount.ALL);
                }}
        }
}

    @Override
    public void repaint(Graphics graphics) {
        long mili =this.getTotalRuntime();
        long second = (mili/1000) %60;
        long minute =(mili/1000*60)%60;
        long hours=(mili/1000*60*60)%24;
        int ExpGained = ctx.skills.experience(Constants.SKILLS_RUNECRAFTING)-StartExp;
        int Level = ctx.skills.level(20);
        Graphics2D g = (Graphics2D)graphics;
        g.setColor(new Color(0,0,0,180));
        g.fillRect(0,0,200,130);
        g.setColor(new Color(255,255,255));
        g.drawRect(0,0,200,130);

        g.drawString("Runecrafter",20,20);
        g.drawString("Time Elapsed: "+ ((mili/1000)/60) + " minutes",20,40);
        g.drawString("Exp/Hr: " + (int)(ExpGained *(3600000D/ mili)),20,60);
        g.drawString("Exp Gained: "+ ExpGained,20,80);
        g.drawString("Level: "+ Level,20,100);

    }
}
