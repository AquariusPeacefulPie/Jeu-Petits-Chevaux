package model;

public class CellHole extends Occupable {
	public String process(Rider r) {
		String res = "";
		if(rider!=null) {
			rider.setAction('O',true);
			res += announceCollision(rider);
			res += "\n";
		}
		else {
			if(r.getSymbol()=='R') {
				res = "** Le cavalier ROUGE est tombé dans un trou **\n";
			}
			else {
				res = "** Le cavalier BLEU est tombé dans un trou **\n";
			}
		}
		addRider(r);
		rider.setAction('F',true);
		return res;
	}
	
	public CellHole(int x, int y){
		symbol = '@';
		rider = null;
		coordX = x;
		coordY = y;
	}

	@Override
	public void removeRider() {
		symbol = '@';
		rider = null;
	}

	
}
