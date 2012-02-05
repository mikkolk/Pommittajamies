package view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import model.Peliruudukko;

import controller.NappainKuuntelija;

/**
 * Vastaa pelin‰kym‰n piirt‰misest‰ peliruudukkoa vastaavaksi. Perii <code>
 * JPanelin</code>.
 * 
 * @author Mikko Latva-K‰yr‰
 *
 */
public class Pelinakyma extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private Peliruudukko peliruudukko;
	/* onko pelin‰kym‰ piirretty jo kertaalleen, eli tarviiko koko pelin‰kym‰‰
	 * piirt‰‰ vai piirret‰‰nkˆ vain ne, jotka ovat muuttuneet */
	private boolean piirretty;
	private NappainKuuntelija nappainkuuntelija;
	// onko peli pausella
	private boolean keskeytetty;

	/** 
	 * Konstruktori.
	 * 
	 * @param peliruudukko <code>Peliruudukko</code>-luokan olio, jonka mukaan
	 * pelin‰kym‰ piirtyy.
	 */
	public Pelinakyma(Peliruudukko peliruudukko){
		this.peliruudukko = peliruudukko;
		this.asetaPiirretty(false);
		
		this.setBackground(Color.WHITE);
		this.setSize(600, 520);
		this.setPreferredSize(new Dimension(600, 520));
		this.setLayout(new BorderLayout());

		/* n‰pp‰inten kuuntelemisen mahdollistamiseksi Pelinakyman pit‰‰ saada
		 * focus. */
		this.setFocusable(true);
		// focuksen siirt‰miseen k‰ytett‰v‰t napit pit‰‰ ottaa pois k‰ytˆst‰
		this.setFocusTraversalKeysEnabled(false);
		this.nappainkuuntelija = new NappainKuuntelija(this.annaPeliruudukko());
		this.addKeyListener(this.nappainkuuntelija);
	}
	
	/**
	 * Attribuutin <code>peliruudukko</code> getteri.
	 * @return Pelinakyman peliruudukko
	 */
	public Peliruudukko annaPeliruudukko(){
		return this.peliruudukko;
	}
	
	/**
	 * Palauttaa <code>boolean</code> arvon sen mukaan, onko pelinakyma
	 * jo piirretty.
	 * @return <code>true</code>, jos piirretty; <code>false</code> jos ei.
	 */
	public boolean onPiirretty() {
		return piirretty;
	}
	
	/**
	 * Asettaa attribuutin <code>piirretty</code> arvon.
	 * @param piirretty <code>boolean</code>-arvo sille, onko <code>Pelinakyma
	 * </code> piirretty.
	 */
	public void asetaPiirretty(boolean piirretty) {
		this.piirretty = piirretty;
	}
	
	/**
	 * Attribuutin <code>nappainkuuntelija</code> getteri.
	 * @return Pelinakyman NappainKuuntelijan
	 */
	public NappainKuuntelija annaNappainKuuntelija(){
		return this.nappainkuuntelija;
	}
	
	/**
	 * Palauttaa <code>boolean</code> arvon sen mukaan, onko pelinakyma
	 * keskeytetty (=paused).
	 * @return <code>true</code>, jos keskeytetty; <code>false</code> jos ei.
	 */
	public boolean onKeskeytetty(){
		return this.keskeytetty;
	}
	
	/**
	 * Asettaa attribuutin <code>keskeytetty</code> arvon.
	 * @param keskeytetty <code>boolean</code>-arvo sille, onko pelinakyma
	 * keskeytetty.
	 */
	public void asetaKeskeytetty(boolean keskeytetty){
		this.keskeytetty = keskeytetty;
	}
	
	/**
	 * Piirt‰‰ Pelin‰kym‰n kokonaan uusiksi eli kaikkien ruutujen kaikki
	 * peliobjektit.
	 */
	public void piirraKokonaan(){
		this.asetaPiirretty(false);
		this.repaint();
	}
	
	/** 
	 * Piirt‰‰ kaikkien ruutujen kaikki peliobjektit Graphics-olion avulla.
	 * @param g <code>Graphics</code>-luokan olio, joka hoitaa k‰yt‰nnˆn
	 * piirt‰misen
	 */
	public void piirraKokonaan(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		for (int x = 0; x < this.peliruudukko.annaLeveys(); x++){
			for (int y = 0; y < this.peliruudukko.annaKorkeus(); y++){
				for (int i = 0; i < this.peliruudukko.annaRuutu(x, y).
					annaObjektienMaara(); i++){
					this.peliruudukko.annaRuutu(x, y).annaObjekti(i).piirra(g2d);
				}
			}
		}
	}
	
	/**
	 * Piirt‰‰ pelin‰kym‰n.
	 * @param g <code>Graphics</code>-luokan olio, joka hoitaa k‰yt‰nnˆn
	 * piirt‰misen
	 */
	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D) g;

		if (!this.onPiirretty()){
			// tyhj‰t‰‰n alusta piirt‰m‰ll‰ JPanel itse pohjalle
			super.paintComponent(g2d);
			// piirret‰‰n kaikki Peliruudukon Peliobjektit
			this.piirraKokonaan(g2d);
			this.asetaPiirretty(true);
		} else {
			/* Piirret‰‰n vain niitten ruutujen Peliobjektit, jotka ovat
			 * muuttuneet. 
			 */
			for (int i = 0; i < this.annaPeliruudukko().
					annaMuuttuneidenMaara(); i++){
				for (int j = 0; j < this.annaPeliruudukko().
						annaMuuttunutListasta(i).annaObjektienMaara(); j++){
					this.annaPeliruudukko().annaMuuttunutListasta(i).
					annaObjekti(j).piirra(g2d);
				}
			}
			// poistetaan ne ruudut muuttuneista, jotka juuri piirrettiin
			this.annaPeliruudukko().tyhjaaMuuttuneet();
		}
		
		/* jos peli on pausella, piirret‰‰n lis‰ksi viel‰ "PAUSED"-teksti
		 * keskelle Pelinakymaa.
		 */
		if (this.onKeskeytetty()){
			g2d.setFont(new Font("Trebuchet MS", Font.BOLD, 72));
			g2d.drawString("PAUSED", 172, 260);
		}
	}
}
