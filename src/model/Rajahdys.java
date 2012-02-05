package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Luokka, joka kuvaa Pommin r‰j‰ht‰misest‰ syntyneit‰ R‰j‰hdyksi‰.
 * 
 * @author Mikko Latva-K‰yr‰
 */

public class Rajahdys extends Peliobjekti{
	// pommi, joka aiheutti r‰j‰hdyksen
	private Pommi pommi;
	// ajankohta millisekunteina, jolloin r‰j‰hdys syntyi
	private long syntymisajankohta;
	// aika millisekunteina, jonka j‰lkeen r‰j‰hdys katoaa
	private int katoamisaika;
	// ajankohta millisekunteina, jolloin r‰j‰hdys katoaa
	private long katoamisajankohta;
	// suunta, johon r‰j‰hdys etenee
	private Suunta suunta;
	// boolean-arvo sille, on rajahdys paaty vai ei
	private boolean paaty;

	/**
	 * Konstruktori.
	 * @param pommi pommi, joka aihetti r‰j‰hdyksen
	 * @param syntymisajankohta ajankohta, jolloin r‰j‰hdys syntyi
	 * @param suunta suunta, johon r‰j‰hdys etenee
	 * @param paaty <code>boolean</code>-arvo sille, onko ko. r‰j‰hdys
	 * p‰‰ty
	 */
	public Rajahdys(Pommi pommi, long syntymisajankohta, Suunta suunta, boolean paaty){
		super(pommi.annaPeliruudukko());
		this.pommi = pommi;
		this.syntymisajankohta = syntymisajankohta;
		this.katoamisaika = 800;
		this.katoamisajankohta = this.syntymisajankohta + this.katoamisaika;
		this.suunta = suunta;
		this.paaty = paaty;
		this.pommi.annaPeliruudukko().lisaaRajahdysListaan(this);
	}

	/**
	 * Getteri rajahdyksen pommille.
	 * @return pommi, joka synnytti rajahdyksen
	 */
	public Pommi annaPommi(){
		return this.pommi;
	}

	/**
	 * Getteri rajahdyksen suunnalle.
	 * @return suunta, johon rajahdys etenee
	 */
	public Suunta annaSuunta(){
		return this.suunta;
	}

	/**
	 * Tarkistaa, onko rajahdys p‰‰ty.
	 * @return <code>true</code>, jos on p‰‰ty, muulloin <code>false</code>
	 */
	public boolean onPaaty(){
		return this.paaty;
	}
	/**
	 * Palauttaa <code>boolean</code>-arvon sen mukaan, onko rajahdys valmis
	 * katoamaan.
	 * @return <code>true</code>, jos r‰j‰hdyksen on aika kadota, muulloin
	 * <code>false</code>
	 */
	public boolean katoaako(){
		if (System.currentTimeMillis() >= this.katoamisajankohta){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Piirt‰‰ rajahdyksen ruutuun, jossa se
	 * sijaitsee. Ottaa huomioon onko rajahdys keskiosa, onko
	 * se p‰‰ty ja eteneekˆ se horisontaalisesti vai vertikaalisesti.
	 * @param g2d <code>Graphics2D</code>-luokan olio, joka hoitaa piirt‰misen
	 * k‰yt‰nnˆss‰.
	 */
	@Override
	public void piirra(Graphics2D g2d){
		try {
			if (!this.onPaaty() && this.annaSuunta() == null){
				Image kuva = ImageIO.read(new File(
						"grafiikka\\RajahdysKeski.png"));
				g2d.drawImage(kuva, this.annaRuutu().annaSijainti().x*40,
						this.annaRuutu().annaSijainti().y*40, null);
			}
			
			else if (!this.onPaaty() && (this.annaSuunta().equals(Suunta.YLOS) ||
					this.annaSuunta().equals(Suunta.ALAS))){
				
				Image kuva = ImageIO.read(new File(
						"grafiikka\\RajahdysVertikaali.png"));
				
				g2d.drawImage(kuva, this.annaRuutu().annaSijainti().x*40,
						this.annaRuutu().annaSijainti().y*40, null);
			}
			
			else if (!this.onPaaty() && (this.annaSuunta().equals(Suunta.VASEN) ||
					this.annaSuunta().equals(Suunta.OIKEA))){
				
				Image kuva = ImageIO.read(
						new File("grafiikka\\RajahdysHorisontaali.png"));
				
				g2d.drawImage(kuva, this.annaRuutu().annaSijainti().x*40,
						this.annaRuutu().annaSijainti().y*40, null);
			}
			
			else if (this.onPaaty() && this.annaSuunta().equals(Suunta.YLOS)){
				Image kuva = ImageIO.read(new File(
						"grafiikka\\RajahdysPaatyYlos.png"));
				g2d.drawImage(kuva, this.annaRuutu().annaSijainti().x*40,
						this.annaRuutu().annaSijainti().y*40, null);
			}
			
			else if (this.onPaaty() && this.annaSuunta().equals(Suunta.ALAS)){
				Image kuva = ImageIO.read(new File(
						"grafiikka\\RajahdysPaatyAlas.png"));
				g2d.drawImage(kuva, this.annaRuutu().annaSijainti().x*40,
						this.annaRuutu().annaSijainti().y*40, null);
			}
			
			else if (this.onPaaty() && this.annaSuunta().equals(Suunta.VASEN)){
				Image kuva = ImageIO.read(new File(
						"grafiikka\\RajahdysPaatyVasen.png"));
				g2d.drawImage(kuva, this.annaRuutu().annaSijainti().x*40,
						this.annaRuutu().annaSijainti().y*40, null);
			}
			
			else if (this.onPaaty() && this.annaSuunta().equals(Suunta.OIKEA)) {
				Image kuva = ImageIO.read(new File(
						"grafiikka\\RajahdysPaatyOikea.png"));
				g2d.drawImage(kuva, this.annaRuutu().annaSijainti().x*40,
						this.annaRuutu().annaSijainti().y*40, null);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
