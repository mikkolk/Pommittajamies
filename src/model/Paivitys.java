package model;

/** Abstrakti yläluokka, jonka kaikki päivitykset perivät. Perii 
 * <code>Peliobjektin</code>.
 * 
 * @author Mikko Latva-Käyrä
 *
 */

public abstract class Paivitys extends Peliobjekti {
	
	/**
	 * Konstruktori.
	 * @param peliruudukko <code>Peliruudukko</code>-luokan olio, jossa päivitys
	 * sijaitsee.
	 */
	public Paivitys(Peliruudukko peliruudukko) {
		super(peliruudukko);
	}
	
	/**
	 * Aktivoi päivityksen.
	 * @param pelaaja pelaaja, johon päivitys vaikuttaa.
	 */
	public abstract void aktivoi(Pelaaja pelaaja);

}
