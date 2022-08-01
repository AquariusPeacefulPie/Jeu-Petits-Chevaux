package model;

public class CellFree extends Occupable {
	public String process(Rider r) {
		String res = "";
		if(rider!=null) {
			rider.setAction('O',true);
			res += announceCollision(rider);
			res += "\n";
		}
		else {
			if(r.getSymbol()=='R') {
				res = "** Le cavalier ROUGE est sur une case neutre **\n";
			}
			else {
				res = "** Le cavalier BLEU est sur une case neutre **\n";
			}
		}
		addRider(r);
		return res;
	}
	
	public CellFree(int x, int y){
		rider = null;
		symbol = '.';
		coordX = x;
		coordY = y;
	}

	@Override
	public void removeRider() {
		rider = null;
		symbol = '.';
	}

}
