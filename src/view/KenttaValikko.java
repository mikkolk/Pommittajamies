package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Kentt‰valikon muodostava luokka. Perii <code>JPanelin</code>.
 * 
 * @author Mikko
 *
 */

public class KenttaValikko extends JPanel {
	private static final long serialVersionUID = 1L;
	
	/* Kentt‰valikon is‰nt‰n‰ toimiva Pommittajamies-luokan olio. */
	private Pommittajamies pommittajamies;
	/* Nappi, josta p‰‰st‰‰n 1. kentt‰‰n. */
	private JButton kentta1button;
	/* 1. kent‰n luonnehdinnan esitt‰v‰ JLabel */
	private JLabel kentta1label;
	private JButton kentta2button;
	private JLabel kentta2label;
	private JButton kentta3button;
	private JLabel kentta3label;
	private JLabel ohjelabel;
	/* Nappi, josta p‰‰see takasin p‰‰valikkoon. */
	private JButton takaisinbutton;
	
	/** 
	 * Konstruktori.
	 * @param pommittajamies kentt‰valikon is‰nt‰n‰ toimiva 
	 * <code>Pommittajamies</code>-luokan olio, jolla alustetaan <code>
	 * pommittajamies</code> attribuutti
	 */
	public KenttaValikko(Pommittajamies pommittajamies){
		this.pommittajamies = pommittajamies;
		this.setSize(new Dimension(600, 520));
		this.setPreferredSize(new Dimension(600, 520));
		this.setLayout(null);
		this.setBackground(new Color(181, 230, 29));
		
		// nappi, jolla p‰‰st‰‰n 1. kentt‰‰n
		this.kentta1button = new JButton();
		this.kentta1button.setIcon(new ImageIcon("grafiikka\\Kentta1.png"));
		this.kentta1button.setBounds(new Rectangle(40, 12, 240, 208));
		this.kentta1button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// luodaan uusi peliruudukko kentta1.txt:n perusteella
				KenttaValikko.this.annaPommittajamies().luoPeliruudukko(
						"kentat\\kentta1.txt");
				// vaihdetaan pelinakyma n‰kyviin
				KenttaValikko.this.annaPommittajamies().vaihdaKorttia(
						"pelinakyma");
			}
		});
		this.add(this.kentta1button);
		
		this.kentta1label = new JLabel();
		this.kentta1label.setText("Peruskentt‰");
		this.kentta1label.setBounds(new Rectangle(40, 225, 150, 20));
		this.add(kentta1label);
			
		this.kentta2button = new JButton();
		this.kentta2button.setIcon(new ImageIcon("grafiikka\\Kentta2.png"));
		this.kentta2button.setBounds(new Rectangle(320, 12, 240, 208));
		this.kentta2button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				KenttaValikko.this.annaPommittajamies().luoPeliruudukko(
						"kentat\\kentta2.txt");
				KenttaValikko.this.annaPommittajamies().vaihdaKorttia(
						"pelinakyma");
			}
		});
		this.add(this.kentta2button);
		
		this.kentta2label = new JLabel();
		this.kentta2label.setText("H‰mment‰v‰ kentt‰");
		this.kentta2label.setBounds(new Rectangle(320, 225, 150, 20));
		this.add(this.kentta2label);
		
		this.kentta3button = new JButton();
		this.kentta3button.setIcon(new ImageIcon("grafiikka\\Kentta3.png"));
		this.kentta3button.setBounds(new Rectangle(40, 265, 240, 208));
		this.kentta3button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				KenttaValikko.this.annaPommittajamies().luoPeliruudukko(
						"kentat\\kentta3.txt");
				KenttaValikko.this.annaPommittajamies().vaihdaKorttia(
						"pelinakyma");
			}
		});

		this.add(this.kentta3button);
		
		this.kentta3label = new JLabel();
		this.kentta3label.setText("Ahdas kentt‰");
		this.kentta3label.setBounds(new Rectangle(40, 478, 150, 20));
		this.add(this.kentta3label);
		
		this.ohjelabel = new JLabel();
		this.ohjelabel.setText("Valitse kentt‰ klikkaamalla kuvaa.");
		this.ohjelabel.setBounds(new Rectangle(336, 330, 200, 20));
		this.add(this.ohjelabel);
		
		this.takaisinbutton = new JButton();
		this.takaisinbutton.setText("Takaisin");
		this.takaisinbutton.setBounds(new Rectangle(386, 443, 100, 30));
		this.takaisinbutton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				KenttaValikko.this.annaPommittajamies().vaihdaKorttia(
						"alkuvalikko");
			}
			
		});
		this.add(this.takaisinbutton);

	}
	
	/**
	 * Palauttaa kentt‰ valikon "is‰nt‰n‰" toimivan 
	 * <code>Pommittajamies</code>-luokan olion.
	 */
	public Pommittajamies annaPommittajamies() {
		return pommittajamies;
	}
}
