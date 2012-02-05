package model;

/** 
 * Kuvaa sein‰‰, jota ei voi tuhota pommilla.
 * 
 * @author Mikko Latva-K‰yr‰
 *
 */

public class TuhoutumatonSeina extends Peliobjekti {
	/**
	 * Konstruktori.
	 * @param peliruudukko peliruudukko, jossa tuhoutumaton sein‰ sijaitsee.
	 */
	public TuhoutumatonSeina(Peliruudukko peliruudukko) {
		super(peliruudukko);
	}
}
