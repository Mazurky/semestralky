import fri.shapesge.Obdlznik;
import fri.shapesge.Trojuholnik;

/**
 * Trieda ktorej úlohou je vytvoriť korunku z geomentrických tvarov
 */

public class Korunka {
    private Obdlznik obdlznik;

    private Trojuholnik stred;
    private Trojuholnik lavy;
    private Trojuholnik pravy;

    /**
     * Konštruktor na vytvorenie korunky.
     * Vytvorí sa korunka na požadovanú pozíciu s požadovanou farbou
     * 
     * @param velkost int polovica sirky z figurky
     * @param poziciaX int pozícia na plátne
     * @param poziciaY int pozícia na plátne
     * @param farba String farba vyplnenia  
     */
    public Korunka(int velkost, int poziciaX, int poziciaY, String farba) {

        this.obdlznik = new Obdlznik(poziciaX + velkost / 2, poziciaY + (((velkost * 2) * 40) / 100));
        this.obdlznik.zmenFarbu("yellow");
        this.obdlznik.zmenStrany(velkost, velkost / 2);
        
        this.stred = new Trojuholnik(poziciaX + velkost, poziciaY + (((velkost * 2) * 30) / 100));
        this.stred.zmenFarbu("yellow");
        this.stred.zmenRozmery(velkost / 2, velkost / 2);
        
        this.pravy = new Trojuholnik(poziciaX + velkost + ((velkost * 30) / 100), poziciaY + (((velkost * 2) * 60) / 100));
        this.pravy.zmenFarbu(farba);
        this.pravy.zmenRozmery(-velkost / 2, velkost / 2);
        
        this.lavy = new Trojuholnik(poziciaX + velkost - ((velkost * 30) / 100), poziciaY + (((velkost * 2) * 60) / 100));
        this.lavy.zmenFarbu(farba);
        this.lavy.zmenRozmery(-velkost / 2, velkost / 2);
    }

    /**
     * Vodorovné posunutie tvarov
     * 
     * @param vzdialenost int vzdialenost o ktorú sa to posunie na osi x
     */
    public void posunVodorovne(int vzdialenost) {
        this.obdlznik.posunVodorovne(vzdialenost);
        this.stred.posunVodorovne(vzdialenost);
        this.pravy.posunVodorovne(vzdialenost);
        this.lavy.posunVodorovne(vzdialenost);
    }

    /**
     * Zvislé posunutie tvarov
     * 
     * @param vzdialenost int vzdialenost o ktorú sa to posunie na osi y
     */
    public void posunZvisle(int vzdialenost) {
        this.obdlznik.posunZvisle(vzdialenost);
        this.stred.posunZvisle(vzdialenost);
        this.pravy.posunZvisle(vzdialenost);
        this.lavy.posunZvisle(vzdialenost);
    }

    /**
     * Skryje všetky tvary, resp. skryje korunku
     */
    public void skry() {
        this.obdlznik.skry();
        this.stred.skry();
        this.pravy.skry();
        this.lavy.skry();
    }

    /**
     * Zobrazí všetky tvary, resp. zobrazí korunku
     */
    public void zobraz() {
        this.obdlznik.zobraz();
        this.stred.zobraz();
        this.pravy.zobraz();
        this.lavy.zobraz();
    }
}