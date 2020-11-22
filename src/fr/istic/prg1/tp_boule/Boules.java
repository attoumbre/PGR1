package juve;

public class Boules {
    static final int nombreBoules = 10;
    static char[] tableauBoules;

    public static void main(String[] args) {

        System.out.println("suite des " + nombreBoules + " boules : ");
        tableauBoules = lireTableauBoules();
        int r = 0, s = 0, t = nombreBoules - 1;

        while (s <= t) {

            switch (tableauBoules[s]) {
                //s est l'indice du tableau


                case 'v':
                    echange(r, s, tableauBoules);
                    ++r;
                    //++s;

                    break;
                case 'b':
                    ++s;
                    break;
                case 'r':
                    echange(s, t, tableauBoules);
                    --t;
                    //++s;
                    break;
                default:
                    System.out.println("erreur : s = " + s + ", boule = " + tableauBoules[s]);

            }

        }

        photo(r, s, t, tableauBoules);

        System.out.print("resultat du tri : ");
        ecrireTableauBoules(tableauBoules);
        System.out.println("");
        System.exit(0);
    }

    static char[] lireTableauBoules() {

        char[] tab = new char[nombreBoules];
        //int i = 0;
        //while (i < nombreBoules){
           // tab[i] = Lecture.lireString();
           // ++i;
       // }
        for (int i = 0; i < nombreBoules; i++) {
         tab[i] = Lecture.lireChar();
        }
        return tab;
    }

    static void ecrireTableauBoules(char[] tab) {

        for (int i = 0; i < nombreBoules; i++) {
            Ecriture.ecrireChar(tab[i]);
        }

    }

    static void echange(int i, int j, char[] tab) {
        char c = tab[i];
        tab[i] = tab[j];
        tab[j] = c;
        ecrireTableauBoules(tab);
        System.out.println("");
    }

    static void photo(int r, int s, int t, char[] tab) {
        System.out.println("r = " + r + " s = " + s + " t = " + t);
        ecrireTableauBoules(tab);
        System.out.println("");
    }

}


