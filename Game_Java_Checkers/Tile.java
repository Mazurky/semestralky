import fri.shapesge.Stvorec;
/**
 * Trieda Tile je určená na vytvorenie jedného štvorčeka/dlaždice s požadovanými vlastnostami
 * ako umiestnenie farba a veľkosť
 * 
 */
public class Tile {
    private Stvorec tile;
    private boolean vyznaceny;
    private boolean vyznacenaFigurka;
    private String farba;

    /**
     * Vytvorí "dlazdicku" s využitím knižnice shapesGE na požadovanej pozícií. 
     * @param strana int dlžka strany danej dlaždice
     * @param lavyHornyX int lavy horny box na X-ovej osi
     * @param lavyHornyY int lavy horny box na y-ovej osi
     * @param white boolean rozohdnutie, ci je "tile" biely(true) alebo cierny(false)
     */
    public Tile(int strana, int lavyHornyX, int lavyHornyY, boolean svetla) {
        this.vyznaceny = false;
        this.vyznacenaFigurka = false;
        this.tile = new Stvorec(lavyHornyX, lavyHornyY);
        this.farba = svetla ? "lightgrey" : "black";
        this.tile.zmenStranu(strana);
        this.tile.zmenFarbu(this.farba);
        this.tile.zobraz();
    }

    /**
     * Metóda na zmenu farby dlaždice.
     * 
     * @param farba String s názvom farby dostupnými v súbore sgbe.ini v sekcií [Colors]
     */
    public void zmenFarbu(String farba) {
        this.tile.zmenFarbu(farba);
    }

    /**
     * Metóda ktorá vráti stav (ne/vyznačená) dlaždice.
     * 
     * @return boolean true->Vyznacena, false->Nevyznacena
     */
    public boolean isVyznacenyTile() {
        return this.vyznaceny;
    }

    /**
     * Metóda ktorá vráti stav (ne/vyznačená) figurky.
     * 
     * @return boolean true->Vyznacena, false->Nevyznacena
     */
    public boolean isVyznacenaFigurka() {
        return this.vyznacenaFigurka;
    }
    

    /**
     * Metóda ktorá vráti farbu dlaždice.
     * 
     * @return String farba
     */
    public String getFarba() {
        return this.farba;
    }
    
    /**
     * Metóda, pomocou ktorej nastavíme danej dlaždici stav (ne/vyznačená).
     * 
     * @param vyznaceny boolean true->Vyznacena, false->Nevyznacena
     */
    public void setVyznacenyTile(boolean vyznaceny) {
        this.vyznaceny = vyznaceny;
    }

    /**
     * Metóda, pomocou ktorej nastavíme danej figurke stav (ne/vyznačená).
     * 
     * @param vyznaceny boolean true->Vyznacena, false->Nevyznacena
     */
    public void setVyznacenaFigurka(boolean vyznaceny) {
        this.vyznacenaFigurka = vyznaceny;
    }
}
