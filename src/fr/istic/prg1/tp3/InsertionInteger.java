package fr.istic.prg1.tp3;

import java.util.Scanner;
import java.util.Arrays;
public class InsertionInteger {

    private static final int SIZE_MAX = 10;

    /**
     * nombre d'entiers présent dans t , 0 <= size <= SIZE_MAX
     */
    private int size;
    private int[] array = new int[SIZE_MAX];


    /**
     * Constructeur
     */
    public InsertionInteger() {
        this.size = 0;
    }



    /**
     *
     * @return copie de la partie remplie du tableau
     */
    public int[] toArray() {
        int[] tab = new int[size];
        for (int i = 0; i < size; i++) {
            tab[i] = array[i];
        }
        return tab;
    }

    /**
     * Si x n'appartient pas à array[0...size-1] et size < SIZE_MAX
     * size est incrémenté de 1, x est inséré dans array et les entiers
     * array[0...size] sont triés par ordre croissant.
     * Sinon array reste inchangé
     *
     *  Exemple :
     *  pour x = 5, size = 3, array [0] = 1, array[1] = 6, array[2] = 8 ,
     *  insertion délivre true, size = 4,
     *  array[0] = 1, array[1] = 5, array[2] = 6, array[3] = 8
     *
     *  @param  value - value à insérer
     *  @pre 	les valeurs de array[0 ... size-1] sont triées par ordre croissant
     *  @return false si x appartient à array[0...size-1] ou si array est complètement rempli
     *  		true si x n'appartient pas à array[0...size-1]
     */
    public boolean insert(int value) {

        if (value >= 0 && this.size < SIZE_MAX) {
            int insertPoint = Arrays.binarySearch(this.array, 0, this.size, value); //recherche de la valeur dans le tableau trié et retourne l'index
            boolean exist = insertPoint >= 0;

            if (!exist) {
                this.array[this.size] = value;// Ajouter value à array si value ne l'appartient pas,
                this.size++;// incrementer size de 1

                Arrays.sort(this.array, 0, this.size);// et trier array
            }

            return !exist;
        }

        return false;
    }
    /**
     * array est rempli, par ordre croissant, en utilisant la fonction
     * insert avec les valeurs lues par scanner
     *
     * @param scanner
     */
    public void createArray(Scanner scanner) {
        while(scanner.hasNext()){
            int value = scanner.nextInt();
            if(value >= 0 && value!=-1){
                insert(value);
            }else{
                break;
            }
        }

    }

    @Override
    public String toString() {
        int[] tab = toArray();
        String str = "Tableau : ";
        for(int i = 0; i < tab.length ; i++) {
            str += Integer.toString(tab[i])+"-";
        };
        return str;
    }


}
