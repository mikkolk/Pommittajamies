package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Abstrakti yl‰luokka, jonka kaikki Peliruudukkoon sijoitettavat objektit
 * periv‰t. M‰‰ritt‰‰ kaikille peliobjekteille yhteiset ominaisuudet: ruutuun
 * sijoittaminen, ruudusta poistaminen ja piirt‰minen
 * 
 * @author Mikko Latva-K‰yr‰
 *
 */

public abstract class Peliobjekti {
	// ruutu, jossa Peliobjekti sijaitsee
	private Ruutu ruutu;
	private Peliruudukko peliruudukko;
	
	/**
	 * Konstruktori.
	 * @param peliruudukko peliruudukko, jossa peliobjekti sijaitsee.
	 */
	public Peliobjekti(Peliruudukko peliruudukko) {
		this.ruutu = null;
		this.peliruudukko = peliruudukko;
	}

	/**
	 * Getteri peliobjektin ruudulle.
	 * @return ruutu, jossa peliobjekti sijaitsee
	 */
	public Ruutu annaRuutu() {
		return ruutu;
	}
	
	/**
	 * Asettaa attribuutin <code>ruutu</code>.
	 * @param ruutu ruutu, joka asetetaan attribuuttiin.
	 */
	public void asetaRuutu(Ruutu ruutu){
		this.ruutu = ruutu;
	}

	/**
	 * Getteri peliobjektin peliruudukolle.
	 * @return ruutu, jossa peliobjekti sijaitsee
	 */
	public Peliruudukko annaPeliruudukko(){
		return this.peliruudukko;
	}

	/**
	 * Sijoittaa peliobjektin ruutuun. Poistaa samalla peliobjektin ruudusta,
	 * jossa se aikaisemmin sijaitsi.
	 * 
	 * @param ruutu ruutu, johon peliobjekti sijoitetaan.
	 */
	public void sijoitaRuutuun(Ruutu ruutu) {
		/* jos objekti sijaitsee jo ruudussa parametrina annetussa ruudussa, ei
		 * tehd‰ mit‰‰n. */
		if (ruutu == this.annaRuutu()){
			return;
		} else {
			// jos objekti sijaitsee jossain valmiiksi, poistetaan se sielt‰
			if (this.annaRuutu() != null){
				this.annaRuutu().poistaPeliobjekti(this);
			}
			this.asetaRuutu(ruutu);
			this.annaRuutu().lisaaPeliobjekti(this);
			
			/* jos objekti on pommi, se lis‰ksi lis‰t‰‰n Peliruudukon 
			 * pommit-listaan */
			if (this instanceof Pommi){
				Pommi pommi = (Pommi) this;
				this.annaPeliruudukko().lisaaPommiListaan(pommi);
			}
		}
	}
	
	/**
	 * Poistaa peliobjektin ruudusta
	 * @param ruutu ruutu, josta poistetaan.
	 */
	public void poistaRuudusta(Ruutu ruutu) {
		if (this.annaRuutu() == ruutu){
			this.annaRuutu().poistaPeliobjekti(this);
		}
	}
	
	/**
	 * Piirt‰‰ peliobjektin ruutuun, jossa se sijaitsee.
	 * @param g2d <code>Graphics2D</code>-luokan olio, joka hoitaa piirt‰misen
	 * k‰yt‰nnˆss‰.
	 */
	public void piirra(Graphics2D g2d){
		try {
			// haetaan kuva tiedostosta luokan nimen perusteella
			Image kuva = ImageIO.read(new File("grafiikka\\" + this.getClass().
					getSimpleName() +  ".png"));
			
			// piirret‰‰n kuva objektin sijainnin mukaan paikalleen
			g2d.drawImage(kuva, this.annaRuutu().annaSijainti().x*40,
					this.annaRuutu().annaSijainti().y*40, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
