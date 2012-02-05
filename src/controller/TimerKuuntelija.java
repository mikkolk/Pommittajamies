package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Peliruudukko;
import model.Rajahdys;

/**
 * Peliruudukossa olevaa Timeri‰ kuunteleva kuuntelija, joka toteuttaa
 * <code>ActionListener</code>-rajapinnan. Vastaa j‰tettyjen pommien
 * r‰j‰ytt‰misest‰, r‰j‰hdysten poistosta ja pelin lopettamisesta.
 * 
 * @author Mikko Latva-K‰yr‰
 *
 */

public class TimerKuuntelija implements ActionListener {
	private Peliruudukko peliruudukko;
	
	/**
	 * Konstruktori.
	 * @param peliruudukko timerkuuntelijan is‰nt‰n‰ toimiva 
	 * peliruudukko
	 */
	public TimerKuuntelija(Peliruudukko peliruudukko){
		this.peliruudukko = peliruudukko;
	}
	
	/**
	 * Attribuutin <code>peliruudukko</code> getteri.
	 * @return TimerKuuntelijan peliruudukko
	 */
	public Peliruudukko annaPeliruudukko(){
		return this.peliruudukko;
	}
	
	/** 
	 * M‰‰r‰‰, mit‰ tehd‰‰n Timerin pyˆr‰ht‰ess‰. R‰j‰ytt‰‰ pommit, jotka ovat
	 * r‰j‰ht‰m‰ss‰, poistaa r‰j‰hdykset, jotka ovat poistumassa ja
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		for (int i = 0; i < this.annaPeliruudukko().annaPommienMaara(); i++){
			if (this.annaPeliruudukko().annaPommiListasta(i).rajahtaa()){
				this.annaPeliruudukko().annaPommiListasta(i).rajahda();
			}
		}
		
		if (this.annaPeliruudukko().annaRajahdystenMaara() > 0){
			Rajahdys rajahdys = this.annaPeliruudukko().annaRajahdysListasta(0);
			if (rajahdys.katoaako()){
				for (int i = 0; i < this.annaPeliruudukko().annaRajahdystenMaara(); i++){
					Rajahdys rajahdys2 = this.annaPeliruudukko().annaRajahdysListasta(i);
					if (rajahdys2.annaPommi().equals(rajahdys.annaPommi())){
						rajahdys2.poistaRuudusta(rajahdys2.annaRuutu());
					}
				}
				/* Listaa l‰pik‰ydess‰ sielt‰ ei voi poistaa alkioita, koska
				 * kun poistetaan alkio ja kasvatetaan i:t‰, skipataan siin‰
				 * jokin alkio. Siksi poistetaan peliruudukon rajahdys-listasta
				 * poistettavat alkiot omalla loopillaan. 
				 */
				for (int i = 0; i < this.annaPeliruudukko().annaRajahdystenMaara(); i++){
					Rajahdys rajahdys2 = this.annaPeliruudukko().annaRajahdysListasta(i);
					if (rajahdys2.annaPommi().equals(rajahdys.annaPommi())){
						this.annaPeliruudukko().poistaRajahdysListasta(rajahdys2);
					}
				}
				// kun r‰j‰hdykset on poistettu, pit‰‰ pelin‰kym‰ p‰ivitt‰‰
				this.annaPeliruudukko().annaPelinakyma().repaint();
			}
		}
		
		// jos jompi kumpi pelaajista (tai molemmat) on kuollut, lopetetaan peli.
		if (!this.annaPeliruudukko().annaPelaaja1().onElossa() || 
				!this.annaPeliruudukko().annaPelaaja2().onElossa()){
			this.annaPeliruudukko().lopetaPeli();
		}
	}
}
