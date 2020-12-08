package fr.istic.prg1.tp3;

import fr.istic.prg1.tp5.SubSet;
import fr.istic.prg1.tp5.list_util.List;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Scanner;

public class Essaie4 extends List<SubSet> {
    /**
     * Borne superieure pour les rangs des sous-ensembles.
     */
    private static final int MAX_RANG = 128;
    /**
     * Sous-ensemble de rang maximal a mettre dans le drapeau de la liste.
     */
    private static final SubSet FLAG_VALUE = new SubSet(MAX_RANG,
            new SmallSet());
    /**
     * Entree standard.
     */
    private static final Scanner standardInput = new Scanner(System.in);

    public MySet() {
        super();
        setFlag(FLAG_VALUE);
    }

    /**
     * Fermer tout (actuellement juste l'entree standard).
     */
    public static void closeAll() {
        standardInput.close();
    }

    private static Comparison compare(int a, int b) {
        if (a < b) {
            return Comparison.INF;
        } else if (a == b) {
            return Comparison.EGAL;
        } else {
            return Comparison.SUP;
        }
    }

    /**
     * Afficher a l'ecran les entiers appartenant a this, dix entiers par ligne
     * d'ecran.
     */
    public void print() {
        System.out.println(" [version corrigee de contenu]");
        this.print(System.out);
    }

    // //////////////////////////////////////////////////////////////////////////////
    // //////////// Appartenance, Ajout, Suppression, Cardinal
    // ////////////////////
    // //////////////////////////////////////////////////////////////////////////////

    /**
     * Ajouter a this toutes les valeurs saisies par l'utilisateur et afficher
     * le nouveau contenu (arret par lecture de -1).
     */
    public void add() {
        System.out.println(" valeurs a ajouter (-1 pour finir) : ");
        this.add(System.in);
        System.out.println(" nouveau contenu :");
        this.printNewState();
    }

    /**
     * Ajouter a this toutes les valeurs prises dans is.
     * C'est une fonction auxiliaire pour add() et restore().
     *
     * @param is
     *            flux d'entree.
     */
    public void add(InputStream is){
        Scanner scanner = new Scanner(is);
        while (scanner.hasNext()){
            int value = scanner.nextInt();
            if(value>=0 && value <= 32767){
                addNumber(value);
            }else{
                break;
            }
        }
        scanner.close();
    }
    private void addNumber(int value){
        if(value >=0 && value <= 32767){
            int rang = value/256;
            int element = value%256;
            Iterator<SubSet> iterator = this.iterator();

            while (Essaie4.compare(iterator.getValue().rank,rank)== Comparison.INF && !iterator.isOnflag()){
                iterator.getValue() = iterator.nextValue();
            }
            switch (Essaie4.compare(iterator.getValue().rank,rank)){
                case EGAL: iterator.getValue().set.add(element);
                break;
                case SUP: Smallset small = new Smallset();
                small.add(element);
                iterator.addLeft(new SubSet(rank,small));
                break;
                default:
                    SmallSet small = new SmallSet();
                    small.add(element);
                    iterator.Tail(new SubSet(rank, smallSet));
            }
        }

    }

    public void remove() {
        System.out.println(" valeurs a ajouter (-1 pour finir) : ");
        this.remove(System.in);
        System.out.println(" nouveau contenu :");
        this.printNewState();
    }

    public void remove(InputStream is){
        Scanner scanner = new Scanner(is);
        while (scanner.hasNext()){
            int value = scanner.nextInt();
            if(value>=0 && value <= 32767){
                removeNumber(value);
            }else{
                break;
            }
        }
        scanner.close();
    }

    public void removeNumber(int value){
        if(value>=0 && value<32767){
            int rank = value /256;
            int element = value % 256;
            Iterator <SubSet> iterator = this.iterator();
            while (Essaie4.compare(iterator.getValue().rank, rank)==Comparison.INF && !iterator.isOnflag){
                iterator.getValue()= iterator.nextValue();
            }

            iterator.getValue().set.remove(element);
            if(iterator.getValue().set.isEmpty()){
                iterator.remove();
            }
        }
    }

    public int size(){
        Iterator<SubSet> iterator= this.iterator();
        int cpt=0;
        while (!iterator.isOnflag()){
            iterator.goForward();
            cpt= cpt + iterator.getValue().set.size();;
        }
        return cpt;
    }
    public void contains(){
        System.out.println(" valeur cherchee : ");
        int value = readValue(standardInput, 0);
        return this.contains(value);
    }
    public boolean contains(int value){
        if(value>=0 && value<=32767){
            return false;
        }
        int rank = value/256;
        int element = value %256;
        Iterator<SubSet> iterator = this.iterator();

        while (!iterator.isOnflag()){

            if (iterator.getValue().rank==rank){
                SmallSet small = new SmallSet();
                return small.contains(element);
            }
            iterator.nextValue();
        }
        return false;
    }

    public void difference (Essaie4 essaie4) {
        if (this == essaie4) {
            this.clear();
        }

        Iterator<SubSet> iterator = this.iterator();
        Iterator<SubSet> iterator1 = essaie4.iterator();

        while (!iterator1.isOnflag()) {
            SubSet cur = iterator.getValue();
            SubSet cur1 = iterator1.getValue();
            switch (Essaie4.compare(cur.rank, cur1.rank)) {
                case INF:
                    iterator.goForward();
                    break;
                case SUP:
                    iterator2.goForward();
                    break;
                default:
                    cur.set.difference(cur1.set);
                    if (cur.isEmpty()) {
                        iterator.remove();
                    } else {
                        iterator.goForward();
                    }
                    iterator1.goForward();


            }
        }
    }
        public void difSym(Essaie4 essaie4){
            if(this.equals(essaie4)){
                this.clear();
            }
            Iterator<SubSet> iterator = this.iterator();
            Iterator<SubSet> iterator1 = essaie4.iterator();
            while (!iterator.isOnflag()){
                SubSet cur = iterator.getValue();
                SubSet cur1 = iterator1.getValue();
                switch (Essaie4.compare(cur.rank, cur1.rank)){
                    case INF:
                        iterator.goForward();
                        break;
                    case SUP:
                        iterator.addLeft(cur1.clone());
                        iterator1.goForward();
                        iterator.goForward();
                        break;
                    default:
                        cur.set.difSym(cur1.set);
                        if(cur.isEmpty()){
                            iterator.remove();
                        }else{
                            iterator.goForward();
                        }
                        iterator1.goforward();
                }
            }
            while (!iterator1.isOnflag()){
                iterator.addLeft(iterator1.getValue());
                iterator1.goForward();
            }

        }

        public void intersection ( Essaie4 essaie4){
        if(!this.equals(essaie4)){
            Iterator<SubSet> iterator = this.iterator();
            Iterator<SubSet> iterator1 = essaie4.iterator();
            while (iterator1.isOnFlag()){
                SubSet cur = iterator.getValue();
                SubSet cur1 = iterator1.getValue();
                switch (Essaie4.compare(cur.rank, cur1.rank){
                    case INF:
                        iterator.goForward();
                    case SUP:
                        iterator1.goForward();
                    case EQAL :
                        cur.set.intersection(cur1.set);
                        if(iterator.isEmpty()){
                            iterator.remove()
                        }else{
                            iterator.goForward();
                        }
                        iterator1.goForward();
                }
            }
            while (!iterator.isOnflag()){
                iterator.remove();
            }
        }


        }

}
