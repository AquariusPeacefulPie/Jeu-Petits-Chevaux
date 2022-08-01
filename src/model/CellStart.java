package model;

public class CellStart extends Occupable{

	public CellStart(int i, int j) {
		coordX = i;
		coordY = j;
		symbol = '#';
		rider = null;
	}

	@Override
	public String process(Rider r) {
		String res = "";
		if(rider!=null) {
			rider.setAction('O',true);
			res += announceCollision(rider);
			res += "\n";
		}
		else {
			if(r.getSymbol()=='R') {
				res = "** Le cavalier ROUGE est sur la case départ **\n";
			}
			else {
				res = "** Le cavalier BLEU est sur la case départ **\n";		
			}
		}
		addRider(r);
		return res;
	}

	@Override
	public void removeRider() {
		symbol = '#';
		rider = null;
	}

}
