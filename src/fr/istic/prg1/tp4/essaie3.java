package fr.istic.prg1.tp6;

import java.lang.reflect.Array;
import java.util.Arrays;

public class SmallSet {
    private boolean [] tab = new boolean[256];
    //contructeur
    public SmallSet(){
        for(int i =0; i<= 255;i++){
            tab[i] = false;
        }
    }
    //constructeur avec parametre
    public SmallSet(boolean [] t){
        for(int i=0; i<=255; i++){
            tab[i]=t[i];
        }
    }
    //taille de notre ensemble
    public int size(){
        int cpt = 0;
        for(int i=0 ; i<=255;i++){
            if(this.contains(i)){
                cpt++;
            }
        }
        return cpt;
    }
    /l'ensemble contient il l element?'
    public boolean contains(int x){
        return (x>=0 && x<=255)&& this.tab[x];
    }

    //savoir si l ensemble est vide
    public boolean vide(){
       /* boolean vide = true;
        for (int i =0; i<= 255;i++){
            if(this.tab[i]==true){
                return !vide;
            }
        }
        return vide;*/
        return this.size()==0;
    }

    //ajouter un element
    public  void ajout(int x){
      /*  for(int i =0; i<=255;i++){
            if(i==x){
                if(!this.contains(x)){
                    tab[i]=true;
                }else{
                    break;
                }
            }
        }*/
        if(x>=0 && x<=255){
            this.tab[x]=true;
        }
    }

    //supprimer un element
    public void supp(int x){
        if(x>=0 && x<= 255){
            this.tab[x]=false;
        }
    }

    //ajout dans un intervalle
    public void addInt(int deb, int fin){
       if(deb>=0 && deb < fin && fin <255){
           for(int i = deb; i<fin;i++){
               this.ajout(i);
           }
       }

    }

   //supprimer dans un intervalle
    public void suppInt(int deb, int fin){
        if(deb>=0 && deb<fin && fin< 255){
            for(int i = deb; i<fin; i++){
                this.supp(i);
            }
        }

    }

    //union de this et un autre ensemble
    public void union(SmallSet small){
        if(this!= small){
            for(int i=0;i<255;i++){
                /*if (this.tab[i]==true || small.tab[i]==true){
                    this.tab[i]=true;
                }*/
                this.tab[i]= this.tab[i] || small.tab[i];
            }
        }

    }


    //intersection this et un autre ensemble
    public  void intersection(SmallSet small){
        if(this!= small){
            for(int i=0; i<=255;i++){
                this.tab[i]= this.tab[i] && small.tab[i];
            }
        }
    }


    public void diference (SmallSet small){
        if(this != small){
            for(int i =0 ;i<=255;i++){
                this.tab[i]= this.tab[i] && !small.tab[i];
            }
        }else {
            this.clear();
        }
    }

    // on peut renommer le parametre
    public void diffsymetrique(SmallSet SmallSet){
        if(this != SmallSet){
            for(int i = 0; i<= 255;++i){
                this.tab[i] = this.tab[i] ^ SmallSet.tab[i];
            }
        }
        else{
            this.clear();
        }

    }

    public void complement(){
        for(int i = 0; i<=255; ++i){
            this.tab[i]= !this.tab[i];
        }
    }

    public boolean isIncludeIn(SmallSet SmallSet){
        boolean inclus = true;
        if(this==SmallSet){
            return true;
        }else if(this.size() > SmallSet.size()) {
            return false;
        }else{
            for(int i = 0; i<=255;++i){
                if(this.tab[i]!=SmallSet.tab[i]){
                    return !inclus;
                }
            }
        }
        return inclus;
    }

    public boolean equals (Object o){
        if (this== o){
            return true;
        }else if(this==null){
            return false;
        }else if(o instanceof SmallSet){
            return false;
        }else{
            SmallSet small = (SmallSet) o;
            return Arrays.equals(this.tab, small.tab);
        }
    }

    public void clear(){
        for(int i = 0; i<=255;i++){
            this.tab[i]=false;
        }
    }

}
