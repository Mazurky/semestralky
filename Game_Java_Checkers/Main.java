import java.io.File;
import java.io.FileWriter;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import fri.shapesge.Manazer;
/**
 * Trieda na spúštanie programu a priradenia manažéra na ovládanie
 */
public class Main {
    private static Manazer manazer;
    private static Hra hra;

    private static int pocetRiadkovFigurok;
    private static int velkostTileAFigurky;
    private static int velkostDosky;
    private static int velkostPlatna;
    /**
     * Hlavná metóda na spustenie
     * @param args String[] parametre zadávané cez konzolu -> ignore
     */
    public static void main(String[] args) {
        if (nastavenieHry()) {
            vytvorenieConfigSuboru();
            vytvorenieHry();

            File file = new File("./sbge.ini");
            file.delete();
        }
    }

    /**
     * Vytvorenie hry a priradenie manažéra
     */
    public static void vytvorenieHry() {
        manazer = new Manazer();
        hra = new Hra();
        manazer.spravujObjekt(hra);
    }

    private static boolean nastavenieHry() {
        JTextField pocetRiadkovFInput = new JTextField();
        JTextField velkostTileInput = new JTextField();
        JTextField velkostDoskyInput = new JTextField();

        Object[] inputNastavenia = {
            "Ponechaj prázdne pre predvolené hodnoty.",
            "Šírka figúrky (50):", velkostTileInput,
            "Rozmer dosky (8)", velkostDoskyInput,
            "Počet radov figúrok (3):", pocetRiadkovFInput,
        };

        int option = JOptionPane.showConfirmDialog(null, inputNastavenia, "Nastavenia hry", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            int inputVelkostTile = velkostTileInput.getText().equals("") ? 50 : Integer.valueOf(velkostTileInput.getText());
            int inputVelkostDosky = velkostDoskyInput.getText().equals("") ? 8 : Integer.valueOf(velkostDoskyInput.getText());

            velkostTileAFigurky = inputVelkostTile < 30 || inputVelkostTile > 1000 ? 50 : inputVelkostTile;
            velkostDosky = inputVelkostDosky < 4 ? 8 : inputVelkostDosky % 2 == 0 ? inputVelkostDosky : inputVelkostDosky - 1;
            
            int inputPocetRiadkov = pocetRiadkovFInput.getText().equals("") ? (velkostDosky / 2 - 1) : Integer.valueOf(pocetRiadkovFInput.getText());
            pocetRiadkovFigurok = inputPocetRiadkov <= 0 || inputPocetRiadkov > (velkostDosky / 2 - 1) ? (velkostDosky / 2 - 1) : inputPocetRiadkov;
            velkostPlatna = velkostDosky * velkostTileAFigurky + velkostTileAFigurky;
            return true;
        }
        return false;

    }

    /**
     * Vráti manazera spravujúceho hru
     * @return Manazer
     */
    public static Manazer getManazer() {
        return manazer;
    }

    /**
     * Vráti hru
     * @return Hra
     */
    public static Hra getHra() {
        return hra;
    }
    
    /**
     * Getter na  pocet radov figurok
     * @return int pocet radov figurok
     */
    public static int getPocetRiadkovFigurok() {
        return pocetRiadkovFigurok;
    }

    /**
     * Getter na velkost figurky / policka
     * @return int velkost figurky / policka
     */
    public static int getVelkostTileAFigurky() {
        return velkostTileAFigurky;
    }

    /**
     * Getter na velkost dosky
     * @return int velkost dosky
     */
    public static int getVelkostDosky() {
        return velkostDosky;
    }

    private static void vytvorenieConfigSuboru() {
        FileWriter zapis = null;
        String text = null;
        try {
            zapis = new FileWriter(new File("./sbge.ini"));
            text = "[Window]\n";
            text += "Width = " + (velkostPlatna + 20) + "\n";
            text += "Height = " + (velkostPlatna + velkostTileAFigurky * 2) + "\n";
            text += "Title = Dáma\n" +
                    "Background = white\n" +
                    "FPS = 165\n" +
                    "ShowInfo = false\n" +
                    "Fullscreen = false\n" +
                    "ExitOnClose = true\n" +
                    "\n" +
                    "[Colors]\n" +
                    "yellow = #FFFF00\n" +
                    "lightred = #FF8B8B\n" +
                    "lightblue = #6464FF\n" +
                    "red = #FF0000\n" +
                    "blue = #0000FF\n" +
                    "white = #FFFFFF\n" +
                    "lightgrey = #DBDBDB\n" +
                    "black = #000000\n" +
                    "darkgreen = #02a81b\n\n" +
                    "[Keyboard]\n" +
                    "restartHry = pressed R";
            zapis.write(text);
            zapis.close();

        } catch (Exception e) {

        }
    }
}