package model;

public abstract class Occupable extends Cell implements Questionnable{
	protected Rider rider;
	
	/**
	 * Ajoute un cavalier sur une case
	 * @param r		le cavalier à ajouter sur la case
	 */
	public void addRider(Rider r) {
		rider = r;
		symbol = r.getSymbol();
		r.setCoordinates(coordX, coordY);
	}
	
	/**
	 * Retire un cavalier d'une case et restaure le symbole d'origine de celle-ci
	 */
	public abstract void removeRider();
	
	/**
	 * Annonce la colision d'un cavalier avec un autre
	 * @param r		cavalier percuté
	 * @return		chaîne annonçant la colision
	 */
	public String announceCollision(Rider r) {
		String res = "";
		if(r.getSymbol()=='R') {
			res = "** Le cavalier ROUGE retourne à la case départ **";
		}
		else {
			res = "** Le cavalier BLEU retourne à la case départ **";		
		}
		return res;
	}
	
}