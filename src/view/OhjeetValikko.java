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
 * Ohjeet-valikon muodostava luokka. Perii <code>JPanelin</code>.
 * 
 * @author Mikko
 *
 */

public class OhjeetValikko extends JPanel {
	private static final long serialVersionUID = 1L;
	
	/* Ohjeet-valikon isäntänä toimiva Pommittajamies-luokan olio. */
	private Pommittajamies pommittajamies;
	
	private JLabel otsikkolabel;
	private JLabel tekstilabel;
	private JLabel nappaimetlabel;
	private JLabel tekstilabel2;
	private JLabel tekstilabel3;
	private JLabel symbolitlabel;
	private JLabel kuvalabel;
	private JLabel kuvauslabel;
	private JLabel kuvalabel2;
	private JLabel kuvauslabel2;
	private JLabel kuvalabel3;
	private JLabel kuvauslabel3;
	private JLabel kuvalabel4;
	private JLabel kuvauslabel4;
	private JButton oknappi;
	
	/** 
	 * Konstruktori.
	 * @param pommittajamies ohjeet-valikon isäntänä toimiva 
	 * <code>Pommittajamies</code>-luokan olio, jolla alustetaan <code>
	 * pommittajamies</code> attribuutti
	 */
	public OhjeetValikko(Pommittajamies pommittajamies){
		this.pommittajamies = pommittajamies;
		this.setSize(new Dimension(600, 520));
		this.setPreferredSize(new Dimension(600, 520));
		this.setLayout(null);
		this.setBackground(new Color(181, 230, 29));
		
		this.otsikkolabel = new JLabel();
		this.otsikkolabel.setText("OHJEET");
		this.otsikkolabel.setFont(new Font("Aharoni", Font.PLAIN, 30));
		this.otsikkolabel.setBounds(new Rectangle(40, 10, 150, 30));
		this.add(this.otsikkolabel);
		
		this.tekstilabel = new JLabel();
		// html-tagien avulla teksti rivittyy automaattisesti
		this.tekstilabel.setText("<html>Pommittajamies perustuu Hudson Softin" +
				" kehittämään ja useille alustoille julkaistuun Bombermaniin," +
				" joka julkaistiin ensimmäisen kerran vuonna 1983." +
				" Pommittajamies on 90-luvun freewaremoninpelien tapaan" +
				" yhdeltä näppäimistöltä pelattava kaksinpeli. Pelin" +
				" tarkoituksena on on tuhota vastapelaajan hahmo räjäyttämällä" +
				" tämä pommilla. Aluksi pelaajat pystyvät jättämään vain yhden" +
				" pommin pelikentälle ja pommin räjähdyksen koko on pieni," +
				" mutta pelaajien keräämät päivitykset kyllä auttavat tähän." +
				" Pommittajamiehen on kehittänyt Mikko Latva-Käyrä kurssin" +
				" Studio 1 projektina talvella 2011-12.</html>");
		this.tekstilabel.setBounds(new Rectangle(40, 40, 520, 120));
		this.add(this.tekstilabel);
		
		this.nappaimetlabel = new JLabel();
		this.nappaimetlabel.setText("Näppäimet:");
		this.nappaimetlabel.setBounds(new Rectangle(40, 180, 150, 18));
		this.add(this.nappaimetlabel);
		
		this.tekstilabel2 = new JLabel();
		// <br> = rivinvaihto
		this.tekstilabel2.setText("<html>Pelaaja 1:<br>" +
				"nuoli ylös  -  liiku ylöspäin<br>" +
				"nuoli alas  -  liiku alaspäin<br>" +
				"nuoli oikea  -  liiku oikealle<br>" +
				"nuoli vasen  -  liiku vasemmalle<br>" +
				"ctrl  -  jätä pommi<br>" +
				"esc  -  pause</html>");
		this.tekstilabel2.setBounds(new Rectangle(40, 192, 520, 120));
		this.add(this.tekstilabel2);
		
		this.tekstilabel3 = new JLabel();
		this.tekstilabel3.setText("<html>Pelaaja 2:<br>" +
				"w  -  liiku ylöspäin<br>" +
				"s  -  liiku alaspäin<br>" +
				"d  -  liiku oikealle<br>" +
				"a  -  liiku vasemmalle<br>" +
				"tab  -  jätä pommi</html>");
		this.tekstilabel3.setBounds(new Rectangle(300, 192, 520, 110));
		this.add(this.tekstilabel3);
		
		this.symbolitlabel = new JLabel();
		this.symbolitlabel.setText("Symbolit:");
		this.symbolitlabel.setBounds(new Rectangle(40, 330, 150, 18));
		this.add(symbolitlabel);
		
		this.kuvalabel = new JLabel();
		this.kuvalabel.setIcon(new ImageIcon("grafiikka\\Pelaaja1.png"));
		this.kuvalabel.setBounds(new Rectangle(40, 360, 40, 40));
		this.add(this.kuvalabel);
		
		this.kuvauslabel = new JLabel();
		this.kuvauslabel.setText("Pelaaja 1");
		this.kuvauslabel.setBounds(new Rectangle(90, 370, 60, 18));
		this.add(this.kuvauslabel);
		
		this.kuvalabel2 = new JLabel();
		this.kuvalabel2.setIcon(new ImageIcon("grafiikka\\Pelaaja2.png"));
		this.kuvalabel2.setBounds(new Rectangle(40, 410, 40, 40));
		this.add(this.kuvalabel2);
		
		this.kuvauslabel2 = new JLabel();
		this.kuvauslabel2.setText("Pelaaja 2");
		this.kuvauslabel2.setBounds(new Rectangle(90, 420, 60, 18));
		this.add(this.kuvauslabel2);
		
		this.kuvalabel3 = new JLabel();
		this.kuvalabel3.setIcon(new ImageIcon("grafiikka\\LaajuusPaivitys.png"));
		this.kuvalabel3.setBounds(new Rectangle(220, 410, 40, 40));
		this.add(this.kuvalabel3);
		
		this.kuvauslabel3 = new JLabel();
		this.kuvauslabel3.setText("Kasvattaa pommien räjähdyksen laajutta");
		this.kuvauslabel3.setBounds(new Rectangle(270, 420, 250, 18));
		this.add(this.kuvauslabel3);
		
		this.kuvalabel4 = new JLabel();
		this.kuvalabel4.setIcon(new ImageIcon("grafiikka\\MaaraPaivitys.png"));
		this.kuvalabel4.setBounds(new Rectangle(220, 360, 40, 40));
		this.add(this.kuvalabel4);
		
		this.kuvauslabel4 = new JLabel();
		this.kuvauslabel4.setText("Pelaaja voi jättää useamman pommin");
		this.kuvauslabel4.setBounds(new Rectangle(270, 370, 250, 18));
		this.add(this.kuvauslabel4);
		
		this.oknappi = new JButton();
		this.oknappi.setText("OK");
		this.oknappi.setBounds(new Rectangle(250, 470, 100, 30));
		this.oknappi.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				OhjeetValikko.this.annaPommittajamies().vaihdaKorttia(
						"alkuvalikko");
			}
			
		});
		this.add(this.oknappi);
		
	}
	
	/**
	 * Palauttaa ohjeet-valikon "isäntänä" toimivan 
	 * <code>Pommittajamies</code>-luokan olion.
	 */
	public Pommittajamies annaPommittajamies() {
		return pommittajamies;
	}
}
