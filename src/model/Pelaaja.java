package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Kuvaa Pelaajaa, joita voi ohjata pelikent‰ll‰. Perii 
 * <code>Peliobjektin</code>.
 * 
 * @author Mikko Latva-K‰yr‰
 *
 */

public class Pelaaja extends Peliobjekti {
	// onko pelaaja elossa, vai ei
	private boolean elossa;
	/* luku, joka kertoo kuinka suuren r‰j‰hdysalueen omaavat pommit pelaajalla
	 * on k‰ytˆss‰. */
	private int rajahdyslaajuus;
	// paljonko pelaaja pystyy j‰tt‰m‰‰n pommeja yht‰aikaa pelikent‰lle
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
	 * Palauttaa pelaajan pommien r‰j‰hdyslaajuuden.
	 */
	public int annaRajahdyslaajuus(){
		return this.rajahdyslaajuus;
	}

	/**
	 * Asettaa pelaajan pommien r‰j‰hdyslaajuuden.
	 * @param laajuus pommien r‰j‰hdyslaajuus
	 */
	public void asetaRajahdyslaajuus(int laajuus){
		this.rajahdyslaajuus = laajuus;
	}

	/**
	 * Palauttaa pelaajan pommien maksimim‰‰r‰n
	 */
	public int annaPommienMaksimiMaara() {
		return pommienMaksimiMaara;
	}
	
	/**
	 * Asetta pelaajan pommien maksimim‰‰r‰n
	 * @param maara pommien maksimim‰‰r‰
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
	 * Tarkistaa, pystyykˆ pelaaja liikkumaan suuntaan <code>s</code>,
	 *  vai onko pelaajan edess‰ jokin este tai maailman reuna.
	 * 
	 * @param s suunta, johon yritet‰‰n liikkua
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
			// jos ruudussa on viel‰ Rajahdys, pelaaja kuolee
			else if (objekti instanceof Rajahdys){
				this.kuole();
			}
		}
	}

	/**
	 * Pelaaja j‰tt‰‰ pommin omaan nykyiseen sijaintiinsa.
	 * @return <code>true</code>, jos pommin j‰tt‰minen onnistuu, 
	 * <code>false</code> muulloin.
	 */
	public boolean jataPommi(){
		// jos ruudussa on jo pommi, ei uutta pommia voi j‰tt‰‰.
		for (int i = 0; i < this.annaRuutu().annaObjektienMaara(); i++){
			Peliobjekti objekti = this.annaRuutu().annaObjekti(i);
			if (objekti instanceof Pommi){
				return false;
			}
		}
		
		/* v‰liaikaismuuttuja, joka laskee montako pommia Peliruudukossa on
		 * t‰ll‰ hetkelle Pelaajan j‰tt‰m‰n‰
		 */
		int jatetytPommit = 0;

		for (int j = 0; j < this.annaPeliruudukko().annaPommienMaara(); j++){
			Pommi pommi = this.annaPeliruudukko().annaPommiListasta(j);
			if (pommi.annaPelaaja().equals(this)){
				jatetytPommit++;
			}
		}
		
		/* jos pommeja on v‰hemm‰n kuin maksimim‰‰r‰, j‰tet‰‰n pommi siihen
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
	 * Piirt‰‰ pelaajan <code>Ruutuun</code>, jossa se
	 * sijaitsee. Pelaaja 1 ja pelaaja 2 piirret‰‰n eri tavalla.
	 * 
	 * @param g2d <code>Graphics2D</code>-luokan olio, joka hoitaa piirt‰misen
	 * k‰yt‰nnˆss‰.
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
