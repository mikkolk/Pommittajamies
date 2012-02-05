package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


/** 
 * Peliruudukon yht� solua kuvaava luokka, joka pit�� listaa ko. solussa olevista
 * Peliobjekteista.
 * @author Mikko Latva-K�yr�
 *
 */
public class Ruutu {
	// lista, joka s�il�� peliobjektit, jotka sijaitsevat ruudussa
	private List<Peliobjekti> objektit;
	private Point sijainti;
	private Peliruudukko peliruudukko;

	/**
	 * Kontruktori. Asettaa ruudun sijainniksi pisteen (-1,-1), koska luotaessa
	 * ruutu ei viel� sijaitse miss��n.
	 */
	public Ruutu(){
		this.objektit = new ArrayList<Peliobjekti>();
		this.sijainti = new Point(-1, -1);
		this.peliruudukko = null;
	}
	
	/**
	 * Palauttaa indeksill� <code>indeksi objektit</code>-listassa olevan
	 * <code>Peliobjekti</code>-luokan olion. 
	 * @param indeksi
	 */
	public Peliobjekti annaObjekti(int indeksi){
		return this.objektit.get(indeksi);
	}

	/**
	 * Palauttaa <code>objektit</code>-listassa olevien objektien m��r�n.
	 */
	public int annaObjektienMaara() {
		return this.objektit.size();
	}
	
	/**
	 * Palauttaa ruudun sijaintia kuvaavan <code>Point</code>-luokan olion.
	 */
	public Point annaSijainti() {
		return this.sijainti;
	}

	/**
	 * Lis�� objektin listaan <code>objektit</code>, jos se ei siell� jo ole.
	 * Lis�� my�s ruudun peliruudukon <code>muuttuneet</code>-listaan.
	 * @param objekti lis�tt�v� peliobjekti
	 */
	public void lisaaPeliobjekti(Peliobjekti objekti){
		if (this.objektit.contains(objekti)){
			return;
		} else {
			objektit.add(objekti);
			objekti.sijoitaRuutuun(this);
			this.peliruudukko.lisaaMuuttunutListaan(this);
		}
	}
	
	/**
	 * Poistaa objektin listasta <code>objektit</code>, jos se on siell�.
	 * Poistaa my�s ruudun peliruudukon <code>muuttuneet</code>-listasta.
	 * @param objekti poistettava peliobjekti
	 */
	public void poistaPeliobjekti(Peliobjekti objekti){
		if (this.objektit.contains(objekti)){
			this.objektit.remove(objekti);
			this.peliruudukko.lisaaMuuttunutListaan(this);
		} else {
			return;
		}	
	}
	
	/**
	 * Asettaa ruudun peliruudukkoon annettujen koordinaattien mukaisesti.
	 * @param x x-koordinaatti
	 * @param y y-koordinaatti
	 * @param peliruudukko peliruudukko, johon ruutu lis�t��n
	 */
	public void asetaPeliruudukkoon(int x, int y, Peliruudukko peliruudukko){
		if (peliruudukko != null && ((x < 0 || y < 0) ||
				(x > peliruudukko.annaLeveys() || y > peliruudukko.annaKorkeus()))){
		}

		else {
			this.sijainti = new Point(x, y);
			this.peliruudukko = peliruudukko;
			this.peliruudukko.asetaRuutu(x, y, this);
		}
	}

	/** 
	 * Palauttaa ruudun naapurin suunnassa <code>suunta</code>
	 * @param suunta suunta, josta naapuri annataan.
	 * @return naapuri ruudun tai <code>null</code>, jos naapuria ei ole tai
	 * peliruudukko on <code>null</code> tai <code>suunta</code> on 
	 * <code>null</code>
	 */
	public Ruutu annaNaapuri(Suunta suunta){
		if (this.peliruudukko != null && suunta != null){
			return this.peliruudukko.annaRuutu(
					this.sijainti.x + suunta.annaX(),
					this.sijainti.y + suunta.annaY());
		} else {
			return null;
		}
	}
	
	/**
	 * Palauttaa ruudun, joka on suunnassa <code>suunta</code> ja et�isyydell�
	 * <code>etaisyys</code> t�st� ruudusta.
	 * @param suunta suunta, josta ruutu annetaan
	 * @param etaisyys et�isyys, jolta ruutu annetaan
	 * @return ruutu tai <code>null</code>, jos <code>suunta</code> on 
	 * <code>null</code> tai jos haluttu ruutu ei ole en�� peliruudukon
	 * rajojen sis�ll�.
	 */
	public Ruutu annaRuutuSuunnasta(Suunta suunta, int etaisyys){
		switch (suunta){
		case YLOS:
			return this.peliruudukko.annaRuutu(this.annaSijainti().x, this.annaSijainti().y - etaisyys);
		case ALAS:
			return this.peliruudukko.annaRuutu(this.annaSijainti().x, this.annaSijainti().y + etaisyys);
		case VASEN:
			return this.peliruudukko.annaRuutu(this.annaSijainti().x - etaisyys, this.annaSijainti().y);
		case OIKEA:
			return this.peliruudukko.annaRuutu(this.annaSijainti().x + etaisyys, this.annaSijainti().y);
		default:
			return null;
		}
	}
}
