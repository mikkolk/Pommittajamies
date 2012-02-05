package model;
/**
 * Enumeraatio, joka kuvaa pelimaailman mahdollisia liikesuuntia.
 * 
 * @author Mikko Latva-Käyrä
 *
 */
public enum Suunta {
	VASEN, OIKEA, ALAS, YLOS;
	
	/** 
	 * Suuntaan liikuttaessa palauttaa x-koordinaatin muutoksen.
	 * @return x-koordinaatin muutos
	 */
	public int annaX(){
		switch (this){
		case VASEN: return -1;
		case OIKEA: return 1;
		default: return 0;
		}
	}
	
	/**
	 * Suuntaan liikuttaessa palauttaa y-koordinaatin muutoksen.
	 * @return y-koordinaatin muutos
	 */
	public int annaY(){
		switch (this){
		case ALAS: return 1;
		case YLOS: return -1;
		default: return 0;
		}
	}
}
