package model;

public class CellHedge extends Occupable{
	public String process(Rider r) {
		String res = "";
		if(rider!=null) {
			rider.setAction('O',true);
			res += announceCollision(rider);
			res += "\n";
		}
		else {
			if(r.getSymbol()=='R') {
				res = "** Le cavalier ROUGE est bloqué dans une haie **\n";
			}
			else {
				res = "** Le cavalier BLEU est bloqué dans une haie **\n";
			}
		}
		addRider(r);
		rider.setAction('H',true);
		return res;
	}
	
	public CellHedge(int x, int y) {
		symbol = '|';
		rider = null;
		coordX = x;
		coordY = y;
	}

	@Override
	public void removeRider() {
		symbol = '|';
		rider = null;		
	}
}