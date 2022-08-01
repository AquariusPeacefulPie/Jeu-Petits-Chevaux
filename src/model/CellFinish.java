package model;

public class CellFinish extends Occupable {

	public CellFinish(int i, int j) {
		coordX = i;
		coordY = j;
		symbol = '*';
		rider = null;
	}

	public String process(Rider r) {
		String res = "";
		if(r.getSymbol()=='R') {
			res = "** Le cavalier ROUGE vient d'atteindre l'arrivé **\n";
		}
		else {
			res = "** Le cavalier BLEU vient d'atteindre l'arrivé **\n";
		}
		addRider(r);
		return res;
	}

	@Override
	public void removeRider() {
		symbol = '*';
		rider = null;
	}

	

}
