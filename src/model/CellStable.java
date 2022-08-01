package model;

public class CellStable extends Occupable {

	public CellStable(int i, int j, char symbRider, Rider rider) {		
		coordX = i;
		coordY = j;
		symbol = symbRider;
		this.rider = rider;
		rider.setCoordinates(i,j);
	}

	@Override
	public String process(Rider r) {
		return null;
	}

	@Override
	public void removeRider() {
		symbol = '+';
		rider = null;
	}
	
}
