package model;

import java.util.Random;

/**
 * Kuvaa sein��, jonka voi tuhota pommilla. Vastaa my�s p�ivitysten luomisesta.
 * 
 * @author Mikko Latva-K�yr�
 *
 */

public class TuhoutuvaSeina extends Peliobjekti {	
	private static Random rand = new Random();
	
	/**
	 * Konstruktori.
	 * @param peliruudukko peliruudukko, jossa tuhoutuva sein� sijaitsee.
	 */
	public TuhoutuvaSeina(Peliruudukko peliruudukko) {
		super(peliruudukko);
	}
	
	/**
	 * Poistaa peliobjektin ruudusta ja 25 % todenn�k�isyydell� luo uuden
	 * p�ivityksen.
	 * @param ruutu ruutu, josta poistetaan.
	 */
	@Override
	public void poistaRuudusta(Ruutu ruutu){
		if (rand.nextDouble() < 0.25){
			this.luoPaivitys();
		}
		super.poistaRuudusta(ruutu);
	}
	
	/**
	 * Luo uuden p�ivityksen samaan ruutuun, miss� tuhoutumaton sein�
	 * sijaitsee. 50 % tn:ll� <code>MaaraPaivitys</code> ja 50% tn:ll� 
	 * <code>LaajuusPaivitys</code>.
	 */
	public void luoPaivitys(){
		double arpa = rand.nextDouble();
		
		if (arpa < 0.5){
			MaaraPaivitys p = new MaaraPaivitys(this.annaPeliruudukko());
			p.sijoitaRuutuun(this.annaRuutu());
		}
		else {
			LaajuusPaivitys p = new LaajuusPaivitys(this.annaPeliruudukko());
			p.sijoitaRuutuun(this.annaRuutu());
		}
	}
}
