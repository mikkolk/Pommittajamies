package model;


/**
 * Luokka kuvaa peliss‰ pelaajien j‰tt‰mi‰ pommeja ja niiden toimintaa.
 * @author Mikko Latva-K‰yr‰
 *
 */
public class Pommi extends Peliobjekti {
	// Pelaaja, joka j‰tti pommin
	private Pelaaja pelaaja;
	// aika millisekunteina, joka kuluu Pommin r‰j‰ht‰miseen
	private int rajahdysAika;
	// ajankohta millisekunteina, jolloin Pommi on j‰tetty
	private long jattoAjankohta;
	// ajankohta millisekunteina, jolloin Pommi r‰j‰ht‰‰
	private long rajahdysAjankohta;

	/**
	 * Konstruktori.
	 * @param pelaaja pelaaja, joka j‰tti Pommin
	 */
	public Pommi(Pelaaja pelaaja){
		super(pelaaja.annaPeliruudukko());
		this.pelaaja = pelaaja;
		this.rajahdysAika = 2000;
		this.jattoAjankohta = System.currentTimeMillis();
		this.rajahdysAjankohta = this.rajahdysAika + this.jattoAjankohta;
	}

	/**
	 * Getteri attribuutille <code>pelaaja</code>.
	 * @return pelaajan, joka j‰tti Pommin
	 */
	public Pelaaja annaPelaaja(){
		return this.pelaaja;
	}
	
	/**
	 * Getteri attribuutille <code>rajahdysAjankohta</code>.
	 * @return pommin r‰j‰hdysajankohta millisekuntein
	 */
	public long annaRajahdysAjankohta(){
		return this.rajahdysAjankohta;
	}

	/**
	 * Palauttaa <code>boolean</code> arvon sen mukaan, onko pommi valmis
	 * r‰j‰ht‰m‰‰n.
	 * @return <code>true</code>, jos pommin on aika r‰j‰ht‰‰, muulloin
	 * <code>false</code>
	 */
	public boolean rajahtaa(){
		if (System.currentTimeMillis() >= this.annaRajahdysAjankohta()){
			return true;
		} else {
			return false;
		}
	}

	/**
	 * R‰j‰ytt‰‰ pommin. Pommi r‰j‰ht‰‰ nelj‰‰n eri suuntaan: ylˆs, alas,
	 * vasemmalle ja oikealle.
	 */
	public void rajahda(){
		// otetaan ruutu, miss‰ Pommi sijaitsee talteen
		Ruutu sailottyRuutu = this.annaRuutu();
		
		/* poistetaam Pommi ruudustaan, jotta se ei h‰iritse r‰j‰hdyksen
		 * etenemist‰ */
		this.poistaRuudusta(this.annaRuutu());
		this.annaPeliruudukko().poistaPommiListasta(this);
		
		// lis‰t‰‰n r‰j‰hdyksen keskikohta ruutuun, jossa pommi oli
		sailottyRuutu.lisaaPeliobjekti(new Rajahdys(this, this.annaRajahdysAjankohta(), null, false));
		
		/* jos pelaaja j‰‰ pommin kanssa samaan ruutuun, tapetaan se (ruudussa
		 * ei voi olla muita objekteja, joihin r‰j‰hdys vaikuttaisi, koska
		 * pelaajan on t‰ytynyt olla ruudussa, jotta pommi on pystytty j‰tt‰m‰‰n.
		 */
		for (int i = 0; i < sailottyRuutu.annaObjektienMaara(); i++){
			Peliobjekti objekti = sailottyRuutu.annaObjekti(i);
			if (objekti instanceof Pelaaja){
				Pelaaja pelaaja = (Pelaaja) objekti;
				pelaaja.kuole();
			}
		}
		
		Suunta[] suunnat = Suunta.values();
		
		// sitten r‰j‰hdet‰‰n kaikkiin nelj‰‰n suuntaan
		for (int i = 0; i < suunnat.length; i++){
			this.rajahdaSuuntaan(suunnat[i], sailottyRuutu);
		}
		
		// p‰ivitet‰‰n n‰kym‰
		this.annaPeliruudukko().annaPelinakyma().repaint();
		// toistetaan pommin r‰j‰hdys-‰‰ni
		new AePlayWave("aanet\\Pommi.wav").start();
	}
	
	/**
	 * <code>rajahda()</code>-metodin apumetodi, joka lis‰‰ hoitaa pommin 
	 * r‰j‰hdyksen yhteen suuntaan.
	 * 
	 * @param suunta suunta, johon lis‰t‰‰n r‰j‰hdyksi‰
	 * @param sailottyRuutu	ruutu, jossa pommi sijaitsi
	 */
	private void rajahdaSuuntaan(Suunta suunta, Ruutu sailottyRuutu){
		/* R‰j‰hdyksen pituus suuntaan = Pelaajan rajahdyslaajuus - 1. R‰j‰hdys
		 * vaikuttaa alla r‰j‰hdyslaajuuden et‰isyydell‰ oleviin ruutuihin (jos
		 * esteit‰ ei ole)
		 */
		ULOMPI: for (int a = 1; a < this.annaPelaaja().annaRajahdyslaajuus(); a++){
			/* kaikki ruudussa olevat objektit k‰yd‰‰n l‰pi ja r‰j‰hdys toimii
			 * niiden mukaan. */
			for (int i = 0; i < sailottyRuutu.annaRuutuSuunnasta(suunta, a).annaObjektienMaara(); i++){
				Peliobjekti objekti = sailottyRuutu.annaRuutuSuunnasta(suunta, a).annaObjekti(i);
				
				/* jos vastaan tulee TuhoutumatonSeina, r‰j‰hdys siihen suuntaan
				 * katkeaa. */
				if (objekti instanceof TuhoutumatonSeina){
					break ULOMPI;
				}
				
				/* jos vastaan tulee TuhoutuvaSein‰, sein‰ poistetaan ruudusta,
				 * ruutuun lis‰t‰‰n r‰j‰hdys, joka on p‰‰ty ja r‰j‰hdys siihen
				 * suuntaan katkeaa.
				 */
				else if (objekti instanceof TuhoutuvaSeina){
					sailottyRuutu.annaRuutuSuunnasta(suunta, a).lisaaPeliobjekti(new Rajahdys(this, this.annaRajahdysAjankohta(), suunta, true));
					objekti.poistaRuudusta(objekti.annaRuutu());
					break ULOMPI;
				}
				
				// jos vastaan tulee pommi, sekin r‰j‰ht‰‰
				else if (objekti instanceof Pommi){
					Pommi pommi = (Pommi) objekti;
					pommi.rajahda();
				}
				
				// jos vastaan tulee pelaaja, se kuolee
				else if (objekti instanceof Pelaaja){
					Pelaaja pelaaja = (Pelaaja) objekti;
					pelaaja.kuole();
				}
				
				// jos vastaan tulee jokin paivitys, se tuhoutuu
				else if (objekti instanceof Paivitys){
					objekti.poistaRuudusta(objekti.annaRuutu());
				}
			}
			
			/* Jos a on kasvanut suurimpaan arvoon, mihin se kasvaa, lis‰t‰‰n
			 * ko. ruutuun r‰j‰hdyksen p‰‰ty.
			 */
			if (a == this.annaPelaaja().annaRajahdyslaajuus() - 1){
			sailottyRuutu.annaRuutuSuunnasta(suunta, a).lisaaPeliobjekti(new Rajahdys(this, this.annaRajahdysAjankohta(), suunta, true));
			} else {
				// muulloin lis‰t‰‰n r‰j‰dyksen v‰lipala
				sailottyRuutu.annaRuutuSuunnasta(suunta, a).lisaaPeliobjekti(new Rajahdys(this, this.annaRajahdysAjankohta(), suunta, false));
			}
		}
	}
}

