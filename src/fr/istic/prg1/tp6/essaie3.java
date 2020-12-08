package fr.istic.prg1.tp3;

import java.lang.reflect.Array;
import java.util.Arrays;

public class essaie3 {
    private boolean [] tab = new boolean[256];
    public essaie3(){
        for(int i =0; i<= 255;i++){
            tab[i] = false;
        }
    }

    public essaie3(boolean [] t){
        for(int i=0; i<=255; i++){
            tab[i]=t[i];
        }
    }
    public int size(){
        int cpt = 0;
        for(int i=0 ; i<=255;i++){
            if(this.contains(i)){
                cpt++;
            }
        }
        return cpt;
    }

    public boolean contains(int x){
        return (x>=0 && x<=255)&& this.tab[x];
    }

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
    public void supp(int x){
        if(x>=0 && x<= 255){
            this.tab[x]=false;
        }
    }

    public void addInt(int deb, int fin){
       if(deb>=0 && deb < fin && fin <255){
           for(int i = deb; i<fin;i++){
               this.ajout(i);
           }
       }

    }

    public void suppInt(int deb, int fin){
        if(deb>=0 && deb<fin && fin< 255){
            for(int i = deb; i<fin; i++){
                this.supp(i);
            }
        }

    }
    public void union(essaie3 essai){
        if(this!= essai){
            for(int i=0;i<255;i++){
                /*if (this.tab[i]==true || essai.tab[i]==true){
                    this.tab[i]=true;
                }*/
                this.tab[i]= this.tab[i] || essai.tab[i];
            }
        }

    }

    public  void intersection(essaie3 essai){
        if(this!= essai){
            for(int i=0; i<=255;i++){
                this.tab[i]= this.tab[i] && essai.tab[i];
            }
        }
    }
    public void diference (essaie3 essai){
        if(this != essai){
            for(int i =0 ;i<=255;i++){
                this.tab[i]= this.tab[i] && !essai.tab[i];
            }
        }else {
            this.clear();
        }
    }

    public void diffsymetrique(essaie3 essaie3){
        if(this != essaie3){
            for(int i = 0; i<= 255;++i){
                this.tab[i] = this.tab[i] ^ essaie3.tab[i];
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

    public boolean isIncludeIn(essaie3 essaie3){
        boolean inclus = true;
        if(this==essaie3){
            return true;
        }else if(this.size() > essaie3.size()) {
            return false;
        }else{
            for(int i = 0; i<=255;++i){
                if(this.tab[i]!=essaie3.tab[i]){
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
        }else if(o instanceof essaie3){
            return false;
        }else{
            essaie3 essai = (essaie3) o;
            return Arrays.equals(this.tab, essai.tab);
        }
    }

    public void clear(){
        for(int i = 0; i<=255;i++){
            this.tab[i]=false;
        }
    }

}
