package atrem.connect4.remote;

import java.awt.Point;
import java.rmi.Remote;
import java.util.List;

public interface RemoteGameController extends Remote {

	public abstract void wakeUpGcr();

	public abstract List<Point> getWinningCoordinates();

	public abstract int move(int slot);

	public abstract void wakeUpGCr();

}
