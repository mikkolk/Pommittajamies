package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.Timer;

import controller.TimerKuuntelija;

import view.Pelinakyma;
import view.TulosPopup;
import view.Pommittajamies;

/**
 * Luokka, jonka ensisijainen teht‰v‰ on ruutujen s‰ilˆminen. Huolehtii myˆs
 * pommi-listan, rajahdys-listan ja muuttuneiden ruutujen listan yll‰pidosta.
 * Vastaa pelin loppumisesta ja keskeytt‰misest‰. Peliruudukko t‰ytet‰‰n
 * aina aluksi kentt‰‰ vastaavaksi tekstitiedostoa lukemalla.
 * 
 * @author Mikko Latva-K‰yr‰
 *
 */
public class Peliruudukko {
	/** 2-uloitteinen taulukko, joka s‰ilˆˆ ruudut, jotka s‰ilˆv‰t
	 * piirrett‰v‰t objektit */
	private Ruutu[][] peliruudukko;
	private int leveys;
	private int korkeus;

	// onko peli pausellla
	private boolean keskeytetty;

	private Pelaaja pelaaja1;
	private Pelaaja pelaaja2;
	private Pelinakyma pelinakyma;
	private Pommittajamies pommittajamies;

	// lista, jossa on kaikki peliruudussa olevat pommit
	private List<Pommi> pommit;
	// lista, jossa on kaikki peliruudussa olevat rajahdykset
	private List<Rajahdys> rajahdykset;
	/* lista, jossa on kaikki ruudut, jotka ovat muuttuneet viime pelinakyman
	 * piirt‰misen j‰lkeen */
	private List<Ruutu> muuttuneet;

	private Timer timer;
	
	/**
	 * Konstruktori. T‰ytt‰‰ peliruudukon tiedostosta.
	 * @param tiedosto tiedosto, jonka mukaan peliruudukko t‰ytet‰‰n.
	 * @param pommittajamies2 <code>Pommittajamies</code>-luokan olio, joka
	 * on peliruudukon "is‰nt‰n‰"
	 */
	public Peliruudukko(String tiedosto, Pommittajamies pommittajamies2){
		this.pommittajamies = pommittajamies2;
		this.pommit = new ArrayList<Pommi>();
		this.rajahdykset = new ArrayList<Rajahdys>();
		this.muuttuneet = new ArrayList<Ruutu>();
		this.keskeytetty = false;

		this.pelinakyma = null;
		this.timer = new Timer(100, new TimerKuuntelija(this));
		this.timer.start();
		try {
			this.taytaRuudukkoTiedostosta(tiedosto);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Palauttaa peliruudukon "is‰nt‰n‰" toimivan 
	 * <code>Pommittajamies</code>-luokan olion.
	 */
	public Pommittajamies annaPommittajamies(){
		return this.pommittajamies;
	}

	/**
	 * Palauttaa peliruudukon leveyden.
	 */
	public int annaLeveys(){
		return this.leveys;
	}

	/**
	 * Palauttaa peliruudukon korkeuden.
	 */
	public int annaKorkeus(){
		return this.korkeus;
	}

	/**
	 * Palauttaa 1. pelaajana olevan <code>Pelaaja</code>-luokan olion.
	 */
	public Pelaaja annaPelaaja1(){
		return this.pelaaja1;
	}

	/**
	 * Palauttaa 2. pelaajana olevan <code>Pelaaja</code>-luokan olion.
	 */
	public Pelaaja annaPelaaja2(){
		return this.pelaaja2;
	}

	/**
	 * Palauttaa peliruudukkoa kuvaavan pelinakyman.
	 */
	public Pelinakyma annaPelinakyma(){
		return this.pelinakyma;
	}

	/**
	 * Asettaa peliruudukon pelinakyman.
	 * @param pelinakyma <code>Pelinakyma</code>-luokan olio, joka asetetaan
	 * peliruudukon pelinakymaksi
	 */
	public void asetaPelinakyma(Pelinakyma pelinakyma){
		this.pelinakyma = pelinakyma;
	}
	
	/**
	 * Palauttaa peliruudukon timerin.
	 */
	public Timer annaTimer(){
		return this.timer;
	}
	
	/**
	 * Palauttaa <code>boolean</code>-arvon sen mukaan, onko peli keskeytetty
	 * (=pausella) vai ei.
	 * @return <code>true</code>, jos on keskeytetty, <code>false</code>, jos
	 * ei
	 */
	public boolean onKeskeytetty(){
		return this.keskeytetty;
	}
	
	public void asetaKeskeytetty(boolean keskeytetty){
		this.keskeytetty = keskeytetty;
	}

	/**
	 * Palauttaa ruudun koordinaateista <code>x</code> ja <code>y</code>.
	 * @param x x-koordinaatti
	 * @param y y-koordinaatti
	 * @return ruutu tai <code>null</code>, jos piste (x, y) on peliruudukon
	 * rajojen ulkopuolella
	 */
	public Ruutu annaRuutu(int x, int y){
		if ((x < 0 || y < 0) || (x >= this.leveys || y >= this.korkeus)){
			System.out.println("annaRuutu(" + x +", " + y + "), jokin ei " +
					"rajojen sis‰ll‰");
			return null;
		} else {
			return this.peliruudukko[x][y];
		}
	}

	/**
	 * Asettaa <code>ruudun</code> koordinaatteihin <code>x</code> ja 
	 * <code>y</code>.
	 * @param x x-koordinaatti
	 * @param y y-koordinaatti
	 * @param ruutu asetettava ruutu
	 */
	public void asetaRuutu(int x, int y, Ruutu ruutu){
		this.peliruudukko[x][y] = ruutu;
	}
	
	/**
	 * Lis‰‰ <code>objektin</code> koordinaateissa <code>x</code> ja 
	 * <code>y</code> olevaan ruutuun.
	 * @param x x-koordinaatti
	 * @param y y-koordinaatti
	 * @param objekti lis‰tt‰v‰ <code>Peliobjekti</code>-luokan olio
	 */
	public void lisaaPeliobjekti(int x, int y, Peliobjekti objekti){
		this.annaRuutu(x, y).lisaaPeliobjekti(objekti);
	}

	/**
	 * Poistaa <code>objektin</code> koordinaateissa <code>x</code> ja 
	 * <code>y</code> olevasta ruudusta.
	 * @param x x-koordinaatti
	 * @param y y-koordinaatti
	 * @param objekti poistettava <code>Peliobjekti</code>-luokan olio
	 */
	public void poistaPeliobjekti(int x, int y, Peliobjekti objekti){
		this.annaRuutu(x, y).poistaPeliobjekti(objekti);
	}
	
	/**
	 * Lis‰‰ pommin listaan <code>pommit</code>.
	 * @param pommi pommi, joka lis‰t‰‰n
	 */
	public void lisaaPommiListaan(Pommi pommi){
		this.pommit.add(pommi);
	}

	/**
	 * Poistaa pommin listasta <code>pommit</code>.
	 * @param pommi pommi, joka poistetaan
	 */
	public void poistaPommiListasta(Pommi pommi){
		this.pommit.remove(pommi);
	}

	/**
	 * Palauttaa indeksill‰ <code>i</code> olevan pommin.
	 * @param i indeksi
	 */
	public Pommi annaPommiListasta(int i){
		return this.pommit.get(i);
	}

	/**
	 * Palauttaa pommien m‰‰r‰n <code>pommit</code>-listassa.
	 */
	public int annaPommienMaara(){
		return this.pommit.size();
	}

	/**
	 * Lis‰‰ r‰j‰hdyksen listaan <code>rajahdykset</code>.
	 * @param rajahdys r‰j‰hdys, joka lis‰t‰‰n
	 */
	public void lisaaRajahdysListaan(Rajahdys rajahdys){
		this.rajahdykset.add(rajahdys);
	}

	/**
	 * Poistaa r‰j‰hdyksen listasta <code>rajahdykset</code>.
	 * @param rajahdys r‰j‰hdys, joka poistetaan
	 */
	public void poistaRajahdysListasta(Rajahdys rajahdys){
		this.rajahdykset.remove(rajahdys);
	}

	/**
	 * Palauttaa indeksill‰ <code>i</code> olevan r‰j‰hdyksen.
	 * @param i indeksi
	 */
	public Rajahdys annaRajahdysListasta(int i){
		return this.rajahdykset.get(i);
	}

	/**
	 * Palauttaa r‰j‰hdysten m‰‰r‰n <code>rajahdykset</code>-listassa.
	 */
	public int annaRajahdystenMaara(){
		return this.rajahdykset.size();
	}

	/**
	 * Lis‰‰ ruudun listaan <code>muuttuneet</code>. Yksi ruutu voi olla listalla
	 * vain kerran.
	 * @param ruutu ruutu, joka lis‰t‰‰n
	 */
	public void lisaaMuuttunutListaan(Ruutu ruutu){
		if (!this.muuttuneet.contains(ruutu)){
			this.muuttuneet.add(ruutu);
		}
	}
	/**
	 * Poistaa ruudun listasta <code>muuttuneet</code>.
	 * @param ruutu ruutu, joka poistetaan
	 */
	public void poistaMuuttunutListasta(Ruutu ruutu){
		this.muuttuneet.remove(ruutu);
	}

	/**
	 * Palauttaa indeksill‰ <code>i</code> olevan ruudun.
	 * @param i indeksi
	 */
	public Ruutu annaMuuttunutListasta(int i){
		return this.muuttuneet.get(i);
	}

	/**
	 * Palauttaa ruutujen m‰‰r‰n <code>muuttuneer</code>-listassa.
	 */
	public int annaMuuttuneidenMaara(){
		return this.muuttuneet.size();
	}

	/**
	 * Tyhjent‰‰ <code>muuttuneet</code>-listan alkioista.
	 */
	public void tyhjaaMuuttuneet(){
		this.muuttuneet.clear();
	}

	/**
	 * T‰ytt‰‰ peliruudukon tekstitiedoston pohjalta.
	 * @param tiedosto tiedoston nimi, jonka pohjalta peliruudukko t‰ytet‰‰n
	 */
	public void taytaRuudukkoTiedostosta(String tiedosto) throws FileNotFoundException {
		Scanner skanneri = new Scanner(new File(tiedosto));
		
		// kahdella ensimm‰isell‰ rivill‰ on ruudukon leveys ja korkeus
		this.leveys = Integer.parseInt(skanneri.nextLine());
		this.korkeus = Integer.parseInt(skanneri.nextLine());

		this.peliruudukko = new Ruutu[this.leveys][this.korkeus];

		// jokaiseen alkioon tulee ruutu ja jokaiseen ruutuun tulee maa
		for (int x = 0; x < this.leveys; x++){
			for (int y = 0; y < this.korkeus; y++){
				Ruutu ruutu = new Ruutu();
				ruutu.asetaPeliruudukkoon(x, y, this);
				ruutu.lisaaPeliobjekti(new Maa(this));
			}
		}
		/* y = -1, koska sit‰ pit‰‰ kasvattaa joka rivill‰ ja y = 0 on
		 * ensimm‰inen rivi.
		 */
		int y = -1;
		while (skanneri.hasNext()){
			String rivi = skanneri.nextLine();
			if (rivi == null){
				return;
			}
			y++;
			for (int x = 0; x < rivi.length(); x++){
				if (rivi.charAt(x) == '#'){
					TuhoutumatonSeina seina = new TuhoutumatonSeina(this);
					seina.sijoitaRuutuun(this.annaRuutu(x, y));
				}
				else if (rivi.charAt(x) == '@'){
					Pelaaja pelaaja1 = new Pelaaja(this, 1);
					this.pelaaja1 = pelaaja1;
					this.pelaaja1.sijoitaRuutuun(this.annaRuutu(x, y));
				}
				else if (rivi.charAt(x) == '%'){
					TuhoutuvaSeina seina = new TuhoutuvaSeina(this);
					seina.sijoitaRuutuun(this.annaRuutu(x,  y));
				}
				else if (rivi.charAt(x) == '&'){
					Pelaaja pelaaja2 = new Pelaaja(this, 2);
					this.pelaaja2 = pelaaja2;
					this.pelaaja2.sijoitaRuutuun(this.annaRuutu(x, y));
				}
			}
		}
	}
	
	/**
	 * Asettaa pelin pauselle tai jos se on pausella, se jatkaa peli‰.
	 */
	public void pause(){
		if (!this.onKeskeytetty()){
			this.annaTimer().stop();
			this.asetaKeskeytetty(true);
			this.annaPelinakyma().asetaKeskeytetty(true);		
		} else {
			this.annaTimer().start();
			this.asetaKeskeytetty(false);
			this.annaPelinakyma().asetaKeskeytetty(false);
			this.annaPelinakyma().piirraKokonaan();
		}
	}

	/**
	 * Luo tulospopupin, joka ilmoittaa pelin lopputuloksen.
	 */
	public void lopetaPeli(){
		this.annaPelinakyma().piirraKokonaan();
		this.annaTimer().stop();
		TulosPopup tulospopup = new TulosPopup(this);
		if (this.annaPelaaja1().onElossa() && !this.annaPelaaja2().onElossa()){
			tulospopup.asetaTulosteksti("Pelaaja1 voitti, iha vitu jepa!");
		} else if (!this.annaPelaaja1().onElossa() && this.annaPelaaja2().onElossa()){
			tulospopup.asetaTulosteksti("Pelaaja2 voitti, helmee!");
		} else {
			tulospopup.asetaTulosteksti("Tasapeli! Pelatkaa paremmin!");
		}
		tulospopup.setVisible(true);
	}
}