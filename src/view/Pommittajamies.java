package view;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


import model.Peliruudukko;

/**
 * Pommittajamies-pelin p��ohjelmaluokka. Perii JFrame-luokan. Luo peliss�
 * olevat valikot.
 * 
 * @author Mikko Latva-K�yr�
 */

public class Pommittajamies extends JFrame {
	private static final long serialVersionUID = 1L;
	/** JPanel, joka s�il�� CardLayoutin kortit, eli eri n�kym�t. */
	private JPanel kortit;
	/** S�il�� kortit-jpanelin CardLayoutin. */
	private CardLayout cardlayout;
	/** S�il�� pelin alkuvalikon. */
	private AlkuValikko alkuvalikko;
	/** S�il�� pelin ohjeet-valikon*/
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
	 * Vaihtaa <code>kortit</code>-paneelin p��llimm�isint� korttia.
	 * 
	 * @param kortinNimi kortin nimi, joka vaihdetaan p��llimm�iseksi
	 */
	public void vaihdaKorttia(String kortinNimi){
		this.cardlayout.show(this.kortit, kortinNimi);
		// Pelinakyman pit�� saada focus, jotta NappainKuuntelija toimisi.
		if (kortinNimi.equals("pelinakyma")){
			this.pelinakyma.requestFocus();
		}
	}
	
	/**
	 * Luo uuden peliruudukon ja sit� kuvaavan pelinakyman. Lis�ksi lis�� 
	 * pelinakyman <code>kortit</code>-paneeliin.
	 * 
	 * @param kentta tekstitiedosto, jonka mukaan Peliruudukko t�ytet��n
	 */
	public void luoPeliruudukko(String kentta){
		this.peliruudukko = new Peliruudukko(kentta, this);
		this.pelinakyma = new Pelinakyma(this.peliruudukko);
		this.peliruudukko.asetaPelinakyma(this.pelinakyma);
		this.kortit.add(this.pelinakyma, "pelinakyma");
	}
	
	/**
	 * P��ohjelmametodi, joka k�ynnist�� Pommittajamies-pelin.
	 * @param args k�ynnistett�ess� otettavat argumentit, ei k�ytet� t�ss�
	 * peliss�
	 */
	public static void main(String[] args){
		Pommittajamies pm = new Pommittajamies();
		pm.pack();
		pm.setLocationRelativeTo(null);
		pm.setVisible(true);
	}

}
