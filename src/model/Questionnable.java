package model;

public interface Questionnable {
	/**
	 * Affiche les informations d'effets d'une case sur un cavalier
	 * @param r		cavalier concerné
	 * @return		une chaîne détaillant les effets de la case sur le cavalier
	 */
	public String process(Rider r);
	// le cavalier r est éventuellement modifié par la case
	// est retourné : un message qui explicite l’effet de la case // sur le cavalier r.
	// en version console : ce message est affiché dans la console 
	// en version graphique ce message est affiché dans la fenêtre 
	// graphique
}
