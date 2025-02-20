import fri.shapesge.Text;
import fri.shapesge.FontStyle;
import fri.shapesge.Stvorec;

/**
 * Trieda hra ktorá zaručuje spojenie ostatných tried do stavu zobrazovania bez možnych zásahov užívateľa.
 */
public class Hra {
    private int velkostDosky = Main.getVelkostDosky();
    private int sirkaFigurky = Main.getVelkostTileAFigurky();
    private int pocetRiadkov = Main.getPocetRiadkovFigurok();
    
    private HraciaPlocha hraciaPlocha;

    private Figurka[][] figurky;

    private TypFigurky naTahu;

    private Text textNaTahu;
    private Text textStav;

    private boolean hratelne;

    private int countCervenyHrac;
    private int countModryHrac;

    private int fromFigurkaX;
    private int fromFigurkaY;

    /**
     * Konštruktor na vytvorenie Hry.
     * Vytvorí sa hracia plocha, inicializujú sa hodnoty a vytvoria sa figúrky s textom.
     */
    public Hra() {
        this.hraciaPlocha = new HraciaPlocha();
        this.figurky = new Figurka[this.velkostDosky][this.velkostDosky];

        this.naTahu = TypFigurky.BLUE;

        int pocetFigurok = (this.velkostDosky / 2) * this.pocetRiadkov;
        this.countCervenyHrac = pocetFigurok;
        this.countModryHrac = pocetFigurok;

        this.fromFigurkaX = 0;
        this.fromFigurkaY = 0;

        this.hratelne = true;

        this.vytvorenieFigurok();
        this.initialText();
    }

    private void vytvorenieFigurok() {
        for (int x = 0; x < velkostDosky; x++) {
            for (int y = 0; y < velkostDosky; y++) {
                if (y <= (this.pocetRiadkov - 1) && (x + y) % 2 != 0) {
                    this.figurky[y][x] = new Figurka(x * this.sirkaFigurky, y * this.sirkaFigurky, TypFigurky.RED);
                }
                int vypocetPozicie = (this.velkostDosky - this.pocetRiadkov);
                if (y >= vypocetPozicie && (x + y) % 2 != 0) {
                    this.figurky[y][x] = new Figurka(x * this.sirkaFigurky, y * this.sirkaFigurky, TypFigurky.BLUE);
                }
            }
        }
    }

    private void initialText() {
        int textYpos = (this.velkostDosky + 2) * this.sirkaFigurky - (this.sirkaFigurky / 2);
        
        this.textNaTahu = new Text("", 6 * sirkaFigurky / 100, textYpos);
        this.textNaTahu.zmenFont("Arial", FontStyle.BOLD, this.sirkaFigurky / 2);
        this.textNaTahu.zobraz();

        this.textNaTahu.changeText("Na ťahu: " + this.naTahu.getFarba());

        this.textStav = new Text("", 6 * sirkaFigurky / 100, textYpos + this.sirkaFigurky);
        this.textStav.zmenFont("Arial", FontStyle.BOLD, this.sirkaFigurky / 2);
        this.textStav.zobraz();
        this.textStav.changeText(String.format("Zostávajúce figúrky M: %d | Č: %d", this.countModryHrac, this.countCervenyHrac));

        Text textRestart = new Text("", 6 * sirkaFigurky / 100, this.velkostDosky * this.sirkaFigurky + this.sirkaFigurky * 3 - 2);
        textRestart.zmenFont("Arial", FontStyle.ITALIC, 12);
        textRestart.zobraz();
        textRestart.changeText(String.format("Stlač R pre reštart."));
    }
    
    /**
     * Manažérom spravovaná metóda na získanie pozície kliku myši
     * @param x int pozicia myši na osi x
     * @param y int pozicia myši na osi y
     */
    public void vyberSuradnice(int x, int y) {
        int indexX = y / this.sirkaFigurky;
        int indexY = x / this.sirkaFigurky;
        if (indexX >= 0 && indexY >= 0 && indexX < this.velkostDosky && indexY < this.velkostDosky) {
            this.vykonajPohyb(indexX, indexY);
        }
    }

    private void vykonajPohyb(int indexX, int indexY) {
        if (!this.hraciaPlocha.isVyznacenyTile(indexX, indexY)) {
            this.hraciaPlocha.zmazVyznaceneTile();
        }
        if (this.figurky[indexX][indexY] != null) {
            this.hraciaPlocha.zmazVyznaceneFigurky();
            if (this.figurky[indexX][indexY].getTyp() == this.naTahu) {

                this.hraciaPlocha.vyznacFigurku(indexX, indexY);
                this.zobrazMozneTahy(indexX, indexY);

                this.fromFigurkaX = indexX;
                this.fromFigurkaY = indexY;
            }
        } else {
            this.hraciaPlocha.zmazVyznaceneFigurky();
        }
        if (this.hraciaPlocha.isVyznacenyTile(indexX, indexY)) {
            this.hraciaPlocha.zmazVyznaceneTile();
            this.hraciaPlocha.zmazVyznaceneFigurky();
            int fromRiadok = this.fromFigurkaX;
            int fromStlpec = this.fromFigurkaY;
            
            TypPohybu vysledokPohnutia = this.skusPohnut(fromRiadok, fromStlpec, indexX, indexY);
            if (this.hratelne) {
                if (vysledokPohnutia == TypPohybu.POSUN ) {
                    this.naTahu = this.naTahu == TypFigurky.BLUE ? TypFigurky.RED : TypFigurky.BLUE;
                }
                this.textNaTahu.changeText("Na ťahu: " + this.naTahu.getFarba());
            }
        }
    }

    private TypPohybu skusPohnut(int fromRiadok, int fromStlpec, int toRiadok, int toStlpec) {
        Figurka figurka = this.figurky[fromRiadok][fromStlpec];
        if (!this.figurky[fromRiadok][fromStlpec].isKing()) {
            if (Math.abs(toStlpec - fromStlpec) == 1 && toRiadok - fromRiadok == figurka.getTyp().getSmer()) {
                this.move(fromRiadok, fromStlpec, toRiadok, toStlpec);
                return TypPohybu.POSUN;
            } else if (Math.abs(toStlpec - fromStlpec) == 2 && toRiadok - fromRiadok == figurka.getTyp().getSmer() * 2) {

                int x1 = fromRiadok + (toRiadok - fromRiadok) / 2;
                int y1 = fromStlpec + (toStlpec - fromStlpec) / 2;

                if (this.figurky[x1][y1] != null && this.figurky[x1][y1].getTyp() != figurka.getTyp()) {
                    this.move(fromRiadok, fromStlpec, toRiadok, toStlpec);
                    this.vyhodFigurku(x1, y1);
                    return TypPohybu.SKOK;
                }
            }
            return TypPohybu.NIC;

        } else if (this.figurky[fromRiadok][fromStlpec].isKing()) {
            int riadokRozdiel = Math.abs(fromRiadok - toRiadok);
            int stlpecRozdiel = Math.abs(fromRiadok - toRiadok);
            this.move(fromRiadok, fromStlpec, toRiadok, toStlpec);

            int poziciaVhodenejFigurkyX = (fromRiadok > toRiadok) ? fromRiadok - (riadokRozdiel - 1) : fromRiadok + (riadokRozdiel - 1);
            int poziciaVhodenejFigurkyY = (fromStlpec > toStlpec) ? fromStlpec - (stlpecRozdiel - 1) : fromStlpec + (stlpecRozdiel - 1);
            if (this.figurky[poziciaVhodenejFigurkyX][poziciaVhodenejFigurkyY] != null) {
                this.vyhodFigurku(poziciaVhodenejFigurkyX, poziciaVhodenejFigurkyY);
                return TypPohybu.SKOK;
            } else {
                return TypPohybu.POSUN;
            }
        }
        return TypPohybu.NIC;
    }

    private void move(int fromRiadok, int fromStlpec, int toRiadok, int toStlpec) {
        this.figurky[fromRiadok][fromStlpec].zmenPolohu(toRiadok, toStlpec);
        this.figurky[toRiadok][toStlpec] = this.figurky[fromRiadok][fromStlpec]; 
        this.figurky[fromRiadok][fromStlpec] = null;

        this.overKrala(toRiadok, toStlpec);
        this.hraciaPlocha.zmazVyznaceneFigurky();
    }

    private void vyhodFigurku(int x, int y) {
        if (this.figurky[x][y].getTyp() == TypFigurky.BLUE) {
            this.countModryHrac--;
            this.textStav.changeText(String.format("Zostávajúce figúrky M: %d | Č: %d", this.countModryHrac, this.countCervenyHrac));
        } else if (this.figurky[x][y].getTyp() == TypFigurky.RED) {
            this.countCervenyHrac--;
            this.textStav.changeText(String.format("Zostávajúce figúrky M: %d | Č: %d", this.countModryHrac, this.countCervenyHrac));
        }
        this.hraciaPlocha.zmazVyznaceneFigurky();
        this.hraciaPlocha.zmazVyznaceneTile();
        this.figurky[x][y].skry();
        this.figurky[x][y] = null;
        this.koniec();
    }

    private void overKrala(int x, int y) {
        if (!this.figurky[x][y].isKing()) {
            if (this.figurky[x][y].getTyp() == TypFigurky.RED) {
                if (x == this.velkostDosky - 1) {
                    this.figurky[x][y].setKing();
                }
            }
            if (this.figurky[x][y].getTyp() == TypFigurky.BLUE) {
                if (x == 0) {
                    this.figurky[x][y].setKing();
                }
            }
        }
    }

    private void koniec() {
        if (this.countCervenyHrac == 0 || this.countModryHrac == 0) {
            this.hratelne = false;
            this.textNaTahu.changeText("");
            this.textStav.changeText("");

            Stvorec prekrytie = new Stvorec(0, 0);
            prekrytie.zmenStranu((this.velkostDosky + 1) * this.sirkaFigurky);
            prekrytie.zmenFarbu("white");
            prekrytie.zobraz();
            if (this.countCervenyHrac == 0) {
                Text finalTextBlue = new Text("Modrý vyhral.", this.sirkaFigurky / 2, this.velkostDosky * this.sirkaFigurky / 2);
                finalTextBlue.zmenFont("Arial", FontStyle.BOLD, this.sirkaFigurky / 2);
                finalTextBlue.zobraz();
            }
            if (this.countModryHrac == 0) {
                Text finalTextRed = new Text("Červený vyhral.", this.sirkaFigurky / 2, this.velkostDosky * this.sirkaFigurky / 2);
                finalTextRed.zmenFont("Arial", FontStyle.BOLD, this.sirkaFigurky / 2);
                finalTextRed.zobraz();
            }
            Text restart = new Text("Stlač R pre reštart.", this.sirkaFigurky / 2, this.velkostDosky * this.sirkaFigurky / 2 + this.sirkaFigurky);
            restart.zmenFont("Arial", FontStyle.PLAIN, this.sirkaFigurky / 2);
            restart.zobraz();
        }
        
        
    }

    public void restartHry() {
        this.textNaTahu.changeText("");
        this.textStav.changeText("");
        Stvorec prekrytie = new Stvorec(0, 0);
        prekrytie.zmenStranu(this.sirkaFigurky * (this.velkostDosky + 1));
        prekrytie.zmenFarbu("white");
        prekrytie.zobraz();
        Main.getManazer().prestanSpravovatObjekt(Main.getHra());
        Main.vytvorenieHry();
    }

    private void zobrazMozneTahy(int riadok, int stlpec) {
        this.lavaHore(riadok, stlpec);
        this.pravaHore(riadok, stlpec);
        this.lavaDole(riadok, stlpec);
        this.pravaDole(riadok, stlpec);
        
    }

    private void lavaHore(int riadok, int stlpec) {
        for (int i = riadok - 1, j = stlpec - 1; i >= 0 && j >= 0; i--, j--) {
            if (!this.figurky[riadok][stlpec].isKing() && this.figurky[riadok][stlpec].getTyp().getSmer() < 0) {
                if (this.figurky[i][j] == null) {
                    if ((i + 1) == riadok && (j + 1) == stlpec) {
                        this.hraciaPlocha.vyznacTile(i, j, this.naTahu);
                        break;
                    }
                } else if (this.figurky[i][j] != null) {
                    if (i - 1 != -1 && j - 1 != -1) {
                        if ((i + 1) == riadok && (j + 1) == stlpec && this.figurky[i - 1][j - 1] == null && this.figurky[i][j].getTyp() != this.naTahu) {
                            this.hraciaPlocha.vyznacTile(i - 1, j - 1, this.naTahu);
                            break;
                        }
                    }
                    break;
                }
            } else if (this.figurky[riadok][stlpec].isKing()) {
                if (this.figurky[i][j] != null) {
                    if (this.figurky[i][j].getTyp() == this.naTahu) {
                        break;
                    }
                    if (this.figurky[i][j].getTyp() != this.naTahu) {
                        if (i - 1 != -1 && j - 1 != -1) {
                            if (this.figurky[i - 1][j - 1] == null) {
                                this.hraciaPlocha.vyznacTile(i - 1, j - 1, this.naTahu);
                            }
                        }
                        break;
                    }
                } else {
                    this.hraciaPlocha.vyznacTile(i, j, this.naTahu);
                }
            }
            
        }
    }

    private void pravaHore(int riadok, int stlpec) {
        for (int i = riadok - 1, j = stlpec + 1; i >= 0 && j < this.velkostDosky; i--, j++) {
            if (!this.figurky[riadok][stlpec].isKing() && this.figurky[riadok][stlpec].getTyp().getSmer() < 0) {
                if (this.figurky[i][j] == null) {
                    if ((i + 1) == riadok && (j - 1) == stlpec) {
                        this.hraciaPlocha.vyznacTile(i, j, this.naTahu);
                        break;
                    }
                } else if (this.figurky[i][j] != null) {
                    if (i - 1 != -1 && j + 1 != this.velkostDosky) {
                        if ((i + 1) == riadok && (j - 1) == stlpec && this.figurky[i - 1][j + 1] == null && this.figurky[i][j].getTyp() != this.naTahu) {
                            this.hraciaPlocha.vyznacTile(i - 1, j + 1, this.naTahu);
                            break;
                        }
                    }
                    break;
                }
            } else if (this.figurky[riadok][stlpec].isKing()) {
                if (this.figurky[i][j] != null) {
                    if (this.figurky[i][j].getTyp() == this.naTahu) {
                        break;
                    }
                    if (this.figurky[i][j].getTyp() != this.naTahu) {
                        if (i - 1 != -1 && j + 1 != this.velkostDosky) {
                            if (this.figurky[i - 1][j + 1] == null) {
                                this.hraciaPlocha.vyznacTile(i - 1, j + 1, this.naTahu);
                            }
                        }
                        break;
                    }
                } else {
                    this.hraciaPlocha.vyznacTile(i, j, this.naTahu);
                }
            }

        }
    }

    private void lavaDole(int riadok, int stlpec) {
        for (int i = riadok + 1, j = stlpec - 1; i < this.velkostDosky && j >= 0; i++, j--) {
            if (!this.figurky[riadok][stlpec].isKing() && this.figurky[riadok][stlpec].getTyp().getSmer() > 0) {
                if (this.figurky[i][j] == null) {
                    if ((i - 1) == riadok && (j + 1) == stlpec) {
                        this.hraciaPlocha.vyznacTile(i, j, this.naTahu);
                        break;
                    }
                } else if (this.figurky[i][j] != null) {
                    if (i + 1 != this.velkostDosky && j - 1 != -1) {
                        if ((i - 1) == riadok && (j + 1) == stlpec && this.figurky[i + 1][j - 1] == null && this.figurky[i][j].getTyp() != this.naTahu) {
                            this.hraciaPlocha.vyznacTile(i + 1, j - 1, this.naTahu);
                            break;
                        }
                    }
                    break;
                }
            } else if (this.figurky[riadok][stlpec].isKing()) {
                if (this.figurky[i][j] != null) {
                    if (this.figurky[i][j].getTyp() == this.naTahu) {
                        break;
                    }
                    if (this.figurky[i][j].getTyp() != this.naTahu) {
                        if (i + 1 != this.velkostDosky && j - 1 != -1) {
                            if (this.figurky[i + 1][j - 1] == null) {
                                this.hraciaPlocha.vyznacTile(i + 1, j - 1, this.naTahu);
                            }
                        }
                        break;
                    }
                } else {
                    this.hraciaPlocha.vyznacTile(i, j, this.naTahu);
                }
            }
        }
    }

    private void pravaDole(int riadok, int stlpec) {
        for (int i = riadok + 1, j = stlpec + 1; i < this.velkostDosky && j < this.velkostDosky; i++, j++) {
            if (!this.figurky[riadok][stlpec].isKing() && this.figurky[riadok][stlpec].getTyp().getSmer() > 0) {
                if (this.figurky[i][j] == null) {
                    if ((i - 1) == riadok && (j - 1) == stlpec) {
                        this.hraciaPlocha.vyznacTile(i, j, this.naTahu);
                        break;
                    }
                } else if (this.figurky[i][j] != null) {
                    if (i + 1 != this.velkostDosky && j + 1 != this.velkostDosky) {
                        if ((i - 1) == riadok && (j - 1) == stlpec && this.figurky[i + 1][j + 1] == null && this.figurky[i][j].getTyp() != this.naTahu) {
                            this.hraciaPlocha.vyznacTile(i + 1, j + 1, this.naTahu);
                            break;
                        }
                    }
                    break;
                }
            } else if (this.figurky[riadok][stlpec].isKing()) {
                if (this.figurky[i][j] != null) {
                    if (this.figurky[i][j].getTyp() == this.naTahu) {
                        break;
                    }
                    if (this.figurky[i][j].getTyp() != this.naTahu) {
                        if (i + 1 != this.velkostDosky && j + 1 != this.velkostDosky) {
                            if (this.figurky[i + 1][j + 1] == null) {
                                this.hraciaPlocha.vyznacTile(i + 1, j + 1, this.naTahu);
                            }
                        }
                        break;
                    }
                } else {
                    this.hraciaPlocha.vyznacTile(i, j, this.naTahu);
                }
            }
        }
    }
}
