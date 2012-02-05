package model;

/**
 * Kuvaa p�ivityst�, joka aktivoituessaan kasvattaa pelaajan 
 * <code>rajahdysLaajuutta</code> yhdell�. Perii <code>Paivitys</code>-luokan.
 * 
 * @author Mikko Latva-K�yr�
 *
 */
public class LaajuusPaivitys extends Paivitys {
	
	/**
	 * Konstruktori.
	 * @param peliruudukko <code>Peliruudukko</code>-luokan olio, jossa p�ivitys
	 * sijaitsee.
	 */
	public LaajuusPaivitys(Peliruudukko peliruudukko){
		super(peliruudukko);
	}
	
	/**
	 * Aktivoi p�ivityksen, eli kasvattaa pelaajan <code>rajahdysLaajuutta
	 * </code> yhdell�.
	 */
	@Override
	public void aktivoi(Pelaaja pelaaja) {
		pelaaja.asetaRajahdyslaajuus(pelaaja.annaRajahdyslaajuus() + 1);
		this.poistaRuudusta(this.annaRuutu());
	}	
}
