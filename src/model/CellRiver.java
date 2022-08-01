package model;

public class CellRiver extends Occupable {
	public String process(Rider r) {
		String res = "";
		if(rider!=null) {
			rider.setAction('O',true);
			res += announceCollision(rider);
			res += "\n";
		}
		else {
			if(r.getSymbol()=='R') {
				res = "** Le cavalier ROUGE est tombé dans la rivière **\n";
			}
			else {
				res = "** Le cavalier BLEU est tombé dans la rivière **\n";
			}
		}
		addRider(r);
		rider.setAction('R',true);
		return res;
	}
	
	public CellRiver(int x, int y) {
		rider = null;
		symbol = '~';
		coordX = x;
		coordY = y;
	}

	@Override
	public void removeRider() {
		rider = null;
		symbol = '~';
	}
}
