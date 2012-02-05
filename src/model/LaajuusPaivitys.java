package model;

/**
 * Kuvaa päivitystä, joka aktivoituessaan kasvattaa pelaajan 
 * <code>rajahdysLaajuutta</code> yhdellä. Perii <code>Paivitys</code>-luokan.
 * 
 * @author Mikko Latva-Käyrä
 *
 */
public class LaajuusPaivitys extends Paivitys {
	
	/**
	 * Konstruktori.
	 * @param peliruudukko <code>Peliruudukko</code>-luokan olio, jossa päivitys
	 * sijaitsee.
	 */
	public LaajuusPaivitys(Peliruudukko peliruudukko){
		super(peliruudukko);
	}
	
	/**
	 * Aktivoi päivityksen, eli kasvattaa pelaajan <code>rajahdysLaajuutta
	 * </code> yhdellä.
	 */
	@Override
	public void aktivoi(Pelaaja pelaaja) {
		pelaaja.asetaRajahdyslaajuus(pelaaja.annaRajahdyslaajuus() + 1);
		this.poistaRuudusta(this.annaRuutu());
	}	
}
