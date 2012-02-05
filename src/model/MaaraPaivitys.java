package model;

/** Kuvaa päivitystä, joka aktivoituessaan kasvattaa pelaajan 
 * <code>pommienMaksimiMaaraa</code>
 * 
 * @author Mikko Latva-Käyrä
 *
 */

public class MaaraPaivitys extends Paivitys{
	
	/**
	 * Konstruktori.
	 * @param peliruudukko <code>Peliruudukko</code>-luokan olio, jossa päivitys
	 * sijaitsee.
	 */
	public MaaraPaivitys(Peliruudukko peliruudukko){
		super(peliruudukko);
	}
	
	/**
	 * Aktivoi päivityksen, eli kasvattaa pelaajan <code>pommienMaksimiMaaraa
	 * </code> yhdellä.
	 */
	@Override
	public void aktivoi(Pelaaja pelaaja) {
		pelaaja.asetaPommienMaksimiMaara(pelaaja.annaPommienMaksimiMaara() + 1);
		this.poistaRuudusta(this.annaRuutu());
	}
}
