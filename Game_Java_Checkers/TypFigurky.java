/**
 * Enum na typ figúrky.
 * Obsahuje smer pohybu a farbu
 *       1 -> može ísť iba nadol y+1
 *      -1 -> iba hahor y-1
 * 
 */
public enum TypFigurky {
    RED(1, "ČERVENÝ"),
    BLUE(-1, "MODRÝ");

    private int smer;
    private String farba;

    private TypFigurky(int smer, String farba) {
        this.smer = smer;
        this.farba = farba;
    }

    /**
     * Getter na smer pohybu
     * @return int smer
     */
    public int getSmer() {
        return this.smer;
    }

    /**
     * Getter na farbu
     * @return String farba
     */
    public String getFarba() {
        return this.farba;
    }
}
