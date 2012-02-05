package model;

/** Abstrakti yl�luokka, jonka kaikki p�ivitykset periv�t. Perii 
 * <code>Peliobjektin</code>.
 * 
 * @author Mikko Latva-K�yr�
 *
 */

public abstract class Paivitys extends Peliobjekti {
	
	/**
	 * Konstruktori.
	 * @param peliruudukko <code>Peliruudukko</code>-luokan olio, jossa p�ivitys
	 * sijaitsee.
	 */
	public Paivitys(Peliruudukko peliruudukko) {
		super(peliruudukko);
	}
	
	/**
	 * Aktivoi p�ivityksen.
	 * @param pelaaja pelaaja, johon p�ivitys vaikuttaa.
	 */
	public abstract void aktivoi(Pelaaja pelaaja);

}
