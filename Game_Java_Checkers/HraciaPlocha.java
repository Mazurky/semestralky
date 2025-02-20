import fri.shapesge.Text;
import fri.shapesge.FontStyle;

/**
 * Trieda, ktorá je určená na vytvorenie hracej dosky.
 */
public class HraciaPlocha {

    private Tile[][] doska;

    private int strana = Main.getVelkostTileAFigurky();
    private int velkostDosky = Main.getVelkostDosky();

    /**
     * Konštruktor na vytvorenie hracej plochy.
     * Vytvorí a vykreslí sa matica políčok hracej plochy a poradových čísel 
     */
    public HraciaPlocha() {
        this.doska = new Tile[this.velkostDosky][this.velkostDosky];
        for (int x = 0; x < this.velkostDosky; x++) {
            for (int y = 0; y < this.velkostDosky; y++) {
                this.doska[x][y] = new Tile(this.strana, y * this.strana, x * this.strana, (x + y) % 2 == 0);
                if (y + 1 == this.velkostDosky) {
                    Text indexX = new Text(Integer.toString(x + 1), (y + 1) * this.strana + (this.strana / 4), x * this.strana + this.strana - (this.strana / 6));
                    indexX.zmenFont("Arial", FontStyle.PLAIN, this.strana);
                    indexX.zobraz();
                }
                if (x + 1 == this.velkostDosky) {
                    int posunIndex = y + 1 >= 10 ? 15 : 0;
                    Text indexY = new Text(Integer.toString(y + 1), (y * this.strana + (this.strana / 4)) - posunIndex, (x + 1) * this.strana + this.strana - (this.strana / 6));
                    indexY.zmenFont("Arial", FontStyle.PLAIN, this.strana);
                    indexY.zobraz();
                }
            }
        }
    }

    /**
     * Vyznačí políčko v matici požadovanou farbou
     * @param x int riadok matice
     * @param y int stlpec matice
     * @param farba lightred alebo lightblue
     */
    public void vyznacTile(int x, int y, TypFigurky typ) {
        String farba = typ == TypFigurky.BLUE ? "lightblue" : "lightred";
        this.doska[x][y].zmenFarbu(farba);
        this.doska[x][y].setVyznacenyTile(true);
    }

    /**
     * Vyznačí políčko (na ktorom je figúrka)
     * @param x int riadok matice
     * @param y int stlpec matice
     */
    public void vyznacFigurku(int x, int y) {
        this.doska[x][y].zmenFarbu("darkgreen");
        this.doska[x][y].setVyznacenaFigurka(true);
    }

    /**
     * Vymaže všetky vyznačené políčka a nastaví hodnoty vyznačenia na false
     */
    public void zmazVyznaceneTile() {
        for (int x = 0; x < this.doska.length; x++) {
            for (int y = 0; y < this.doska[x].length; y++) {
                if (this.doska[x][y].isVyznacenyTile()) {
                    this.doska[x][y].zmenFarbu("black");
                }
                this.doska[x][y].setVyznacenyTile(false);
            }
        }
    }

    /**
     * Vymaže všetky vyznačené políčka (na ktorom je figúrka) a nastaví hodnoty vyznačenia na false
     */
    public void zmazVyznaceneFigurky() {
        for (int x = 0; x < this.doska.length; x++) {
            for (int y = 0; y < this.doska[x].length; y++) {
                if (this.doska[x][y].isVyznacenaFigurka()) {
                    this.doska[x][y].zmenFarbu("black");
                }
                this.doska[x][y].setVyznacenaFigurka(false);
            }
        }
    }

    /**
     * Vráti, či dané políčko je vyznačené
     * @param x int riadok matice
     * @param y int stlpec matice
     * @return boolean true ak je vyznačené
     */
    public boolean isVyznacenyTile(int x, int y) {
        return this.doska[x][y].isVyznacenyTile();
    }
}