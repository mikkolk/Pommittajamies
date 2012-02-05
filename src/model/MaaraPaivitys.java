package model;

/** Kuvaa p�ivityst�, joka aktivoituessaan kasvattaa pelaajan 
 * <code>pommienMaksimiMaaraa</code>
 * 
 * @author Mikko Latva-K�yr�
 *
 */

public class MaaraPaivitys extends Paivitys{
	
	/**
	 * Konstruktori.
	 * @param peliruudukko <code>Peliruudukko</code>-luokan olio, jossa p�ivitys
	 * sijaitsee.
	 */
	public MaaraPaivitys(Peliruudukko peliruudukko){
		super(peliruudukko);
	}
	
	/**
	 * Aktivoi p�ivityksen, eli kasvattaa pelaajan <code>pommienMaksimiMaaraa
	 * </code> yhdell�.
	 */
	@Override
	public void aktivoi(Pelaaja pelaaja) {
		pelaaja.asetaPommienMaksimiMaara(pelaaja.annaPommienMaksimiMaara() + 1);
		this.poistaRuudusta(this.annaRuutu());
	}
}
