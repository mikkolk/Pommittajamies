package model;

import java.util.Random;

/**
 * Kuvaa seinää, jonka voi tuhota pommilla. Vastaa myös päivitysten luomisesta.
 * 
 * @author Mikko Latva-Käyrä
 *
 */

public class TuhoutuvaSeina extends Peliobjekti {	
	private static Random rand = new Random();
	
	/**
	 * Konstruktori.
	 * @param peliruudukko peliruudukko, jossa tuhoutuva seinä sijaitsee.
	 */
	public TuhoutuvaSeina(Peliruudukko peliruudukko) {
		super(peliruudukko);
	}
	
	/**
	 * Poistaa peliobjektin ruudusta ja 25 % todennäköisyydellä luo uuden
	 * päivityksen.
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
	 * Luo uuden päivityksen samaan ruutuun, missä tuhoutumaton seinä
	 * sijaitsee. 50 % tn:llä <code>MaaraPaivitys</code> ja 50% tn:llä 
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
