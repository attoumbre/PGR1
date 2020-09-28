package fr.istic.prg1;

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


        return Arrays.copyOf(this.array, this.size); //copie du tableau avec sa nouvelle taille
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

          /*  boolean insert = false;

            if(size == SIZE_MAX) {
                return insert;
            }

            for (int i = 0; i < array.length-1; i++){
                if(array[i] < value ) {
                    if(size == 0) {
                        array[i] = value;
                        size++; // size = 1
                        insert = true;
                    }
                    if(array[i+1] > value) {
                        for(int j = i+1; j < size-1 ;j++) {
                            int k = array[j];
                            array[j] = value;
                            array[j+1] = k;
                        }
                        insert = true;
                    }
                }
            }

            return insert;
        }
*/
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
        while (scanner.hasNext()) {
        int value = scanner.nextInt();
        if (value >= 0) { // Seulement les valeurs positives
            this.insert(value);
        }

    }
    }
    @Override
    public String toString() {
        return null;
    }


}
