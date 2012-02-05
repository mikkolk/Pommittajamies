package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Alkuvalikon muodostava luokka. Perii <code>JPanelin</code>.
 * 
 * @author Mikko
 *
 */

public class AlkuValikko extends JPanel{
	private static final long serialVersionUID = 1L;
	
	/* Alkuvalikon isäntänä toimiva Pommittajamies-luokan olio. */
	private Pommittajamies pommittajamies;
	
	private JLabel kuvalabel;
	private JButton aloitabutton;
	private JButton ohjeetbutton;
	private JButton lopetabutton;
	
	/** 
	 * Konstruktori.
	 * @param pommittajamies alkuvalikon isäntänä toimiva 
	 * <code>Pommittajamies</code>-luokan olio, jolla alustetaan <code>
	 * pommittajamies</code> attribuutti
	 */
	public AlkuValikko(Pommittajamies pommittajamies){
		this.pommittajamies = pommittajamies;
		this.setSize(new Dimension(600, 520));
		this.setPreferredSize(new Dimension(600, 520));
		this.setLayout(null);
		this.setBackground(new Color(181, 230, 29));
		
		this.kuvalabel = new JLabel();
		this.kuvalabel.setIcon(new ImageIcon("grafiikka\\AlkuValikonKuva.png"));
		this.kuvalabel.setBounds(new Rectangle(0, 0, 600, 300));
		this.add(this.kuvalabel);
		
		this.aloitabutton = new JButton();
		this.aloitabutton.setText("Aloita peli");
		this.aloitabutton.setFont(new Font("Aharoni", Font.PLAIN, 24));
		this.aloitabutton.setBounds(new Rectangle(200, 320, 200, 30));
		this.aloitabutton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AlkuValikko.this.annaPommittajamies().vaihdaKorttia(
						"kenttavalikko");
			}
			
		});
		this.add(this.aloitabutton);
		
		this.ohjeetbutton = new JButton();
		this.ohjeetbutton.setText("Ohjeet");
		this.ohjeetbutton.setFont(new Font("Aharoni", Font.PLAIN, 24));
		this.ohjeetbutton.setBounds(new Rectangle(200, 370, 200, 30));
		this.ohjeetbutton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AlkuValikko.this.annaPommittajamies().vaihdaKorttia(
						"ohjeetvalikko");
			}
			
		});
		this.add(this.ohjeetbutton);
		
		this.lopetabutton = new JButton();
		this.lopetabutton.setText("Lopeta");
		this.lopetabutton.setFont(new Font("Aharoni", Font.PLAIN, 24));
		this.lopetabutton.setBounds(new Rectangle(200, 420, 200, 30));
		this.lopetabutton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		this.add(this.lopetabutton);
	}
	
	/**
	 * Palauttaa alkuvalikon "isäntänä" toimivan 
	 * <code>Pommittajamies</code>-luokan olion.
	 */
	public Pommittajamies annaPommittajamies() {
		return pommittajamies;
	}
}
