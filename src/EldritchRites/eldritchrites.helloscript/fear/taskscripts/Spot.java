package fear.taskscripts;

import org.powerbot.script.Tile;
/** Spot: a combination of a Tile with the orientation */
public class Spot {
	public Tile tile;
	public int orientation;
	public Spot(Tile useTile, int useOrientation) {
		this.tile=useTile;
		this.orientation=useOrientation;
	}
	public Spot(int x, int y, int z, int useOrientation) {
		this.tile=new Tile(x,y,z);
		this.orientation=useOrientation;
	}
}
