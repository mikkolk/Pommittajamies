package view;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


import model.Peliruudukko;

/**
 * Pommittajamies-pelin pääohjelmaluokka. Perii JFrame-luokan. Luo pelissä
 * olevat valikot.
 * 
 * @author Mikko Latva-Käyrä
 */

public class Pommittajamies extends JFrame {
	private static final long serialVersionUID = 1L;
	/** JPanel, joka säilöö CardLayoutin kortit, eli eri näkymät. */
	private JPanel kortit;
	/** Säilöö kortit-jpanelin CardLayoutin. */
	private CardLayout cardlayout;
	/** Säilöö pelin alkuvalikon. */
	private AlkuValikko alkuvalikko;
	/** Säilöö pelin ohjeet-valikon*/
	private OhjeetValikko ohjeetvalikko;
	private Pelinakyma pelinakyma;
	private KenttaValikko kenttavalikko;
	private Peliruudukko peliruudukko;
	
	/**
	 * Konstruktrori. Luo pelin valikot.
	 */
	public Pommittajamies(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Pommittajamies");
		
		this.kortit = new JPanel();
		this.cardlayout = new CardLayout();
		this.kortit.setLayout(this.cardlayout);
		this.add(this.kortit);
		
		this.alkuvalikko = new AlkuValikko(this);
		this.kortit.add(this.alkuvalikko, "alkuvalikko");
		
		this.ohjeetvalikko = new OhjeetValikko(this);
		this.kortit.add(this.ohjeetvalikko, "ohjeetvalikko");
		
		this.kenttavalikko = new KenttaValikko(this);
		this.kortit.add(this.kenttavalikko, "kenttavalikko");
		
	}
	
	/** 
	 * Vaihtaa <code>kortit</code>-paneelin päällimmäisintä korttia.
	 * 
	 * @param kortinNimi kortin nimi, joka vaihdetaan päällimmäiseksi
	 */
	public void vaihdaKorttia(String kortinNimi){
		this.cardlayout.show(this.kortit, kortinNimi);
		// Pelinakyman pitää saada focus, jotta NappainKuuntelija toimisi.
		if (kortinNimi.equals("pelinakyma")){
			this.pelinakyma.requestFocus();
		}
	}
	
	/**
	 * Luo uuden peliruudukon ja sitä kuvaavan pelinakyman. Lisäksi lisää 
	 * pelinakyman <code>kortit</code>-paneeliin.
	 * 
	 * @param kentta tekstitiedosto, jonka mukaan Peliruudukko täytetään
	 */
	public void luoPeliruudukko(String kentta){
		this.peliruudukko = new Peliruudukko(kentta, this);
		this.pelinakyma = new Pelinakyma(this.peliruudukko);
		this.peliruudukko.asetaPelinakyma(this.pelinakyma);
		this.kortit.add(this.pelinakyma, "pelinakyma");
	}
	
	/**
	 * Pääohjelmametodi, joka käynnistää Pommittajamies-pelin.
	 * @param args käynnistettäessä otettavat argumentit, ei käytetä tässä
	 * pelissä
	 */
	public static void main(String[] args){
		Pommittajamies pm = new Pommittajamies();
		pm.pack();
		pm.setLocationRelativeTo(null);
		pm.setVisible(true);
	}

}
