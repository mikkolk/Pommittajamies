package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Kuvaa Pelaajaa, joita voi ohjata pelikentällä. Perii 
 * <code>Peliobjektin</code>.
 * 
 * @author Mikko Latva-Käyrä
 *
 */

public class Pelaaja extends Peliobjekti {
	// onko pelaaja elossa, vai ei
	private boolean elossa;
	/* luku, joka kertoo kuinka suuren räjähdysalueen omaavat pommit pelaajalla
	 * on käytössä. */
	private int rajahdyslaajuus;
	// paljonko pelaaja pystyy jättämään pommeja yhtäaikaa pelikentälle
	private int pommienMaksimiMaara;
	private int pelaajanNumero;

	public Pelaaja(Peliruudukko ruudukko, int pelaajanNumero){
		super(ruudukko);
		this.pelaajanNumero = pelaajanNumero;
		this.rajahdyslaajuus = 3;
		this.elossa = true;
		this.pommienMaksimiMaara = 1;
	}
	
	/**
	 * Palauttaa pelaajan numeron.
	 */
	public int annaPelaajanNumero(){
		return this.pelaajanNumero;
	}

	/**
	 * Palauttaa pelaajan pommien räjähdyslaajuuden.
	 */
	public int annaRajahdyslaajuus(){
		return this.rajahdyslaajuus;
	}

	/**
	 * Asettaa pelaajan pommien räjähdyslaajuuden.
	 * @param laajuus pommien räjähdyslaajuus
	 */
	public void asetaRajahdyslaajuus(int laajuus){
		this.rajahdyslaajuus = laajuus;
	}

	/**
	 * Palauttaa pelaajan pommien maksimimäärän
	 */
	public int annaPommienMaksimiMaara() {
		return pommienMaksimiMaara;
	}
	
	/**
	 * Asetta pelaajan pommien maksimimäärän
	 * @param maara pommien maksimimäärä
	 */
	public void asetaPommienMaksimiMaara(int maara) {
		this.pommienMaksimiMaara = maara;
	}

	/**
	 * Palautta <code>boolean</code>-arvon sen mukaan, onko pelaaja elossa vai ei.
	 * @return <code>true</code>, jos pelaaja on elossa, muulloin 
	 * <code>false</code>
	 */
	public boolean onElossa(){
		return this.elossa;
	}

	/**
	 * Tarkistaa, pystyykö pelaaja liikkumaan suuntaan <code>s</code>,
	 *  vai onko pelaajan edessä jokin este tai maailman reuna.
	 * 
	 * @param s suunta, johon yritetään liikkua
	 * @return <code>true</code>, jos liikkuminen on mahdollista, muulloin
	 * <code>false</code>.
	 */
	public boolean voiLiikkua(Suunta s){
		if (this.annaRuutu().annaNaapuri(s) == null){
			return false;
		}

		for (int i = 0; i < this.annaRuutu().annaNaapuri(s).annaObjektienMaara(); i++){
			Peliobjekti objekti = this.annaRuutu().annaNaapuri(s).annaObjekti(i);
			if (objekti instanceof Pommi || objekti instanceof TuhoutuvaSeina ||
					objekti instanceof TuhoutumatonSeina){
				return false;
			}
		}
		return true;
	}

	/**
	 * Liikuttaa pelaajaa suuntaan <code>s</code>, jos liikkuminen on mahdollista.
	 * @param s suunta, johon liikutaan
	 */
	public void liiku(Suunta s){
		if (this.voiLiikkua(s)){
			this.sijoitaRuutuun(this.annaRuutu().annaNaapuri(s));
		}
		for (int i = 0; i < this.annaRuutu().annaObjektienMaara(); i++){
			Peliobjekti objekti = this.annaRuutu().annaObjekti(i);
			
			// jos ruudussa on Paivitys, se aktivoituu
			if (objekti instanceof Paivitys){
				Paivitys paivitys = (Paivitys) objekti;
				paivitys.aktivoi(this);
			}
			// jos ruudussa on vielä Rajahdys, pelaaja kuolee
			else if (objekti instanceof Rajahdys){
				this.kuole();
			}
		}
	}

	/**
	 * Pelaaja jättää pommin omaan nykyiseen sijaintiinsa.
	 * @return <code>true</code>, jos pommin jättäminen onnistuu, 
	 * <code>false</code> muulloin.
	 */
	public boolean jataPommi(){
		// jos ruudussa on jo pommi, ei uutta pommia voi jättää.
		for (int i = 0; i < this.annaRuutu().annaObjektienMaara(); i++){
			Peliobjekti objekti = this.annaRuutu().annaObjekti(i);
			if (objekti instanceof Pommi){
				return false;
			}
		}
		
		/* väliaikaismuuttuja, joka laskee montako pommia Peliruudukossa on
		 * tällä hetkelle Pelaajan jättämänä
		 */
		int jatetytPommit = 0;

		for (int j = 0; j < this.annaPeliruudukko().annaPommienMaara(); j++){
			Pommi pommi = this.annaPeliruudukko().annaPommiListasta(j);
			if (pommi.annaPelaaja().equals(this)){
				jatetytPommit++;
			}
		}
		
		/* jos pommeja on vähemmän kuin maksimimäärä, jätetään pommi siihen
		 * ruutuun */
		if (jatetytPommit < this.annaPommienMaksimiMaara()){
			Pommi pommi = new Pommi(this);
			pommi.sijoitaRuutuun(this.annaRuutu());
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Tappaa pelaajan eli muuttaa <code>elossa</code> arvoksi <code>false</code>
	 */
	public void kuole(){
		this.elossa = false;
	}
	
	/**
	 * Piirtää pelaajan <code>Ruutuun</code>, jossa se
	 * sijaitsee. Pelaaja 1 ja pelaaja 2 piirretään eri tavalla.
	 * 
	 * @param g2d <code>Graphics2D</code>-luokan olio, joka hoitaa piirtämisen
	 * käytännössä.
	 */
	@Override
	public void piirra(Graphics2D g2d){
		try {
			if (this.annaPelaajanNumero() == 1){
			Image kuva = ImageIO.read(new File("grafiikka\\Pelaaja1.png"));
			g2d.drawImage(kuva, this.annaRuutu().annaSijainti().x*40,
					this.annaRuutu().annaSijainti().y*40, null);
			}
			else if (this.annaPelaajanNumero() == 2){
				Image kuva = ImageIO.read(new File("grafiikka\\Pelaaja2.png"));
				g2d.drawImage(kuva, this.annaRuutu().annaSijainti().x*40,
						this.annaRuutu().annaSijainti().y*40, null);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
