package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Peliruudukko;

/**
 * Pop-up, joka ilmestyy pelin p‰‰ttyess‰. Kertoo pelin tuloksen.
 * 
 * @author Mikko Latva-K‰yr‰
 *
 */

public class TulosPopup extends JFrame {
	private static final long serialVersionUID = 1L;
	private Peliruudukko peliruudukko;
	
	private JPanel paneeli;
	private JLabel tuloslabel;
	private JButton oknappi;
	
	/** 
	 * Konstruktori.
	 * 
	 * @param peliruudukko peliruudukko, joka toimii is‰nt‰n‰
	 */
	public TulosPopup(Peliruudukko peliruudukko){
		this.peliruudukko = peliruudukko;
		
		this.setResizable(false);
		this.setTitle("Pommittajamies");
		
		this.addWindowListener(new WindowListener(){
			public void windowActivated(WindowEvent e) {}
			public void windowClosed(WindowEvent e) {}
			public void windowClosing(WindowEvent e) {
				TulosPopup.this.annaPeliruudukko().annaPommittajamies().vaihdaKorttia("alkuvalikko");
				TulosPopup.this.setVisible(false);
			}
			public void windowDeactivated(WindowEvent e) {}
			public void windowDeiconified(WindowEvent e) {}
			public void windowIconified(WindowEvent e) {}
			public void windowOpened(WindowEvent e) {}
		});
		
		this.paneeli = new JPanel();
		this.paneeli.setPreferredSize(new Dimension(280, 150));
		this.paneeli.setBackground(new Color(181, 230, 29));
		this.paneeli.setLayout(null);
		this.add(this.paneeli);
		
		this.tuloslabel = new JLabel();
		this.tuloslabel.setBounds(new Rectangle(20, 40, 260, 30));
		this.tuloslabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		this.paneeli.add(this.tuloslabel);
		

		this.oknappi = new JButton();
		this.oknappi.setText("OK");
		this.oknappi.setBounds(new Rectangle(90, 100, 100, 30));
		this.oknappi.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				TulosPopup.this.annaPeliruudukko().annaPommittajamies().vaihdaKorttia("alkuvalikko");
				TulosPopup.this.setVisible(false);
			}
		});
		this.paneeli.add(this.oknappi, BorderLayout.SOUTH);
		
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	/**
	 * Palauttaa TulosPopupin is‰nt‰n‰ toimivan peliruudukon.
	 */
	public Peliruudukko annaPeliruudukko(){
		return this.peliruudukko;
	}

	/**
	 * Asettaa tuloslabeliin tulostekstin.
	 * @param tulosteksti teksti, joka labeliin kirjoitetaan
	 */
	public void asetaTulosteksti(String tulosteksti) {
		this.tuloslabel.setText(tulosteksti);
	}
}
