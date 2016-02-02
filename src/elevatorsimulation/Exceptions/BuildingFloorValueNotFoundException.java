package elevatorsimulation.Exceptions;

/**
 * Created by andrewlincoln on 1/30/16.
 */
public class BuildingFloorValueNotFoundException extends Throwable {
    @Override
    public void printStackTrace() {
        System.err.println("We found a key for the Building Floor you tried to access, but this value is null");
        super.printStackTrace();

    }
}
