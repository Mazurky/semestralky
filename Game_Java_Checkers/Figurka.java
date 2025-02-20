import fri.shapesge.Kruh;

/**
 * Trieda Figurka predstavuje jednu figúrku
 */
public class Figurka {

    private Kruh kruzok;
    private Korunka korunka;
    private int poziciaX;
    private int poziciaY;

    private int priemer = Main.getVelkostTileAFigurky();

    private String farba;

    private TypFigurky typ;

    private boolean king;

    /**
     * Vytvorí krúžok s využitím knižnice shapesGE na požadovanej pozícií s požadovaným typom. 
     * 
     * @param poziciaX int lavy horny box na X-ovej osi
     * @param poziciaY int lavy horny box na y-ovej osi
     * @param typ TypFigurky RED / BLUE
     */
    public Figurka(int poziciaX, int poziciaY, TypFigurky typ) {

        this.poziciaX = poziciaX;
        this.poziciaY = poziciaY;

        this.typ = typ;

        this.king = false;

        this.farba = this.typ.equals(TypFigurky.RED) ? "red" : "blue";

        this.kruzok = new Kruh(this.poziciaX, this.poziciaY);
        this.kruzok.zmenPriemer(this.priemer);
        this.kruzok.zmenFarbu(this.farba);
        this.zobraz();
    }
    /**
     * Metóda na zobrazenie danej figúrky na plátne.
     */
    public void zobraz() {
        this.kruzok.zobraz();
    }

    /**
     * Metóda na skrytie danej figúrky na plátne.
     */
    public void skry() {
        this.kruzok.skry();
        if (this.korunka != null) {
            this.korunka.skry();
        }
    }

    /**
     * Metóda určená na zmenu polohy na plátne.
     * 
     * @param y int index riadku
     * @param x int index stlpca
     */
    public void zmenPolohu(int y, int x) {
        int row = y * this.priemer;
        int col = x * this.priemer;

        this.kruzok.posunVodorovne(col - this.poziciaX);
        this.kruzok.posunZvisle(row - this.poziciaY);
        if (this.king) {
            this.korunka.posunVodorovne(col - this.poziciaX);
            this.korunka.posunZvisle(row - this.poziciaY);
        }
        this.poziciaX += col - this.poziciaX;
        this.poziciaY += row - this.poziciaY;
    }

    /**
     * Metóda na získanie atribútu king (kráľ)
     * @return boolean true ak je kráľ
     */
    public boolean isKing() {
        return this.king;
    }

    /**
     * Metóda na získanie atribútu typ (figúrky)
     * @return TypFigurky typ figúrky
     */
    public TypFigurky getTyp() {
        return this.typ;
    }

    /**
     * Metóda na nastavenie figúrky ako kráľa
     */
    public void setKing() {
        this.king = true;
        if (this.king) {
            this.korunka = new Korunka(this.priemer / 2, this.poziciaX, this.poziciaY, this.farba);
            this.korunka.zobraz();
        }
    }
}