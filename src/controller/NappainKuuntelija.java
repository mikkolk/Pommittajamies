package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.Peliruudukko;
import model.Suunta;

/**
 * Luokka, joka kuuntelee n‰pp‰imi‰ pelin ollessa k‰ynniss‰. T‰m‰n avulla 
 * pelaajat ohjaavat hahmojaan. Toteuttaa <code>KeyListener</code>-rajapinnan.
 * 
 * @author Mikko Latva-K‰yr‰
 *
 */

public class NappainKuuntelija implements KeyListener {
	private Peliruudukko peliruudukko;
	private boolean keskeytetty;

	/**
	 * Konstruktori
	 * @param peliruudukko nappainkuuntelijan is‰nt‰peliruudukko
	 */
	public NappainKuuntelija(Peliruudukko peliruudukko){
		this.peliruudukko = peliruudukko;
		this.keskeytetty = false;
	}
	
	/**
	 * Attribuutin <code>peliruudukko</code> getteri.
	 * @return NappainKuuntelijan peliruudukko
	 */
	public Peliruudukko annaPeliruudukko(){
		return this.peliruudukko;
	}

	/**
	 * Palauttaa <code>boolean</code>-arvon sen mukaan, onko 
	 * nappainkuuntelija keskeytetty (=paused).
	 * @return <code>true</code>, jos keskeytetty; <code>false</code> jos ei.
	 */
	public boolean onKeskeytetty() {
		return this.keskeytetty;
	}

	/**
	 * Asettaa attribuutin <code>keskeytetty</code> arvon.
	 * @param keskeytetty <code>boolean</code>-arvo sille, onko
	 *  nappainkuuntelija keskeytetty.
	 */
	public void asetaKeskeytetty(boolean keskeytetty) {
		this.keskeytetty = keskeytetty;
	}

	/**
	 * M‰‰r‰‰ sen, mit‰ tapahtuu mit‰kin n‰pp‰int‰ painettaessa. K‰ytet‰‰n kun
	 * n‰pp‰int‰ painetaan.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		int koodi = e.getKeyCode();
		if (!this.onKeskeytetty()){
			switch (koodi){
			case KeyEvent.VK_UP:
				this.annaPeliruudukko().annaPelaaja1().liiku(Suunta.YLOS);
				break;
			case KeyEvent.VK_DOWN:
				this.annaPeliruudukko().annaPelaaja1().liiku(Suunta.ALAS);
				break;
			case KeyEvent.VK_LEFT:
				this.annaPeliruudukko().annaPelaaja1().liiku(Suunta.VASEN);
				break;
			case KeyEvent.VK_RIGHT:
				this.annaPeliruudukko().annaPelaaja1().liiku(Suunta.OIKEA);
				break;
			case KeyEvent.VK_CONTROL:
				this.annaPeliruudukko().annaPelaaja1().jataPommi();
				break;
			case KeyEvent.VK_W:
				this.annaPeliruudukko().annaPelaaja2().liiku(Suunta.YLOS);
				break;
			case KeyEvent.VK_S:
				this.annaPeliruudukko().annaPelaaja2().liiku(Suunta.ALAS);
				break;
			case KeyEvent.VK_A:
				this.annaPeliruudukko().annaPelaaja2().liiku(Suunta.VASEN);
				break;
			case KeyEvent.VK_D:
				this.annaPeliruudukko().annaPelaaja2().liiku(Suunta.OIKEA);
				break;
			case KeyEvent.VK_TAB:
				this.annaPeliruudukko().annaPelaaja2().jataPommi();
				break;
			case KeyEvent.VK_ESCAPE:
				this.annaPeliruudukko().pause();
				this.asetaKeskeytetty(true);
				break;
			default:
				break;
			}
		} else {
			// jos peli on pausella, ainut n‰pp‰in, joka on k‰ytˆss‰, on esc
			if (koodi == KeyEvent.VK_ESCAPE){
				this.annaPeliruudukko().pause();
				this.asetaKeskeytetty(false);
			}
		}
		// n‰pp‰int‰ painettaessa p‰ivitet‰‰n pelin‰kym‰
		this.annaPeliruudukko().annaPelinakyma().repaint();
	}

	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}

}
