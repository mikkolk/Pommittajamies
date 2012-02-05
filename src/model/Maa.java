package model;

/**
 * Maata kuvaava luokka. Jokaisessa ruudussa on <code>Maa</code>-luokan olio.
 * 
 * @author Mikko Latva-Käyrä
 *
 */

public class Maa extends Peliobjekti{
	
	/**
	 * Konstruktori.
	 * @param peliruudukko <code>Peliruudukko</code>-luokan olio, jossa maa
	 * sijaitsee.
	 */
	public Maa(Peliruudukko peliruudukko){
		super(peliruudukko);
	}
}
