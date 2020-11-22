package juve;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Ecriture {
    public Ecriture() {
    }

    private static void erreur(IOException var0) {
        System.out.println(var0.getMessage());
        System.out.println("Erreur fatale");
        System.exit(1);
    }

    public static OutputStream ouvrir(String var0) {
        DataOutputStream var1;
        try {
            var1 = new DataOutputStream(new FileOutputStream(var0));
        } catch (IOException var3) {
            var1 = null;
        }

        return var1;
    }

    public static void fermer(OutputStream var0) {
        try {
            var0.close();
        } catch (IOException var2) {
            erreur(var2);
        }

    }

    public static void ecrireChar(OutputStream var0, char var1) {
        try {
            var0.write(var1);
        } catch (IOException var3) {
            erreur(var3);
        }

    }

    public static void ecrireChar(char var0) {
        System.out.print(var0);
    }

    public static void ecrireString(OutputStream var0, String var1) {
        try {
            for(int var2 = 0; var2 < var1.length(); ++var2) {
                var0.write(var1.charAt(var2));
            }
        } catch (IOException var3) {
            erreur(var3);
        }

    }

    public static void ecrireString(String var0) {
        System.out.print(var0);
    }

    public static void ecrireStringln(OutputStream var0, String var1) {
        ecrireString(var0, var1 + "\r\n");
    }

    public static void ecrireStringln(String var0) {
        System.out.println(var0);
    }

    public static void ecrireStringln(OutputStream var0) {
        ecrireString(var0, "\r\n");
    }

    public static void ecrireStringln() {
        System.out.println();
    }

    public static void ecrireInt(OutputStream var0, int var1) {
        ecrireString(var0, Integer.toString(var1));
    }

    public static void ecrireInt(int var0) {
        System.out.print(var0);
    }

    private static String chInt(int var0, int var1) {
        String var2 = Integer.toString(var0);
        String var3 = "";
        int var4 = var1 - var2.length();

        for(int var5 = 0; var5 < var4; ++var5) {
            var3 = var3 + ' ';
        }

        return var3 + var2;
    }

    public static void ecrireInt(OutputStream var0, int var1, int var2) {
        ecrireString(var0, chInt(var1, var2));
    }

    public static void ecrireInt(int var0, int var1) {
        System.out.print(chInt(var0, var1));
    }

    public static void ecrireDouble(OutputStream var0, double var1) {
        ecrireString(var0, Double.toString(var1));
    }

    public static void ecrireDouble(double var0) {
        System.out.print(var0);
    }
}
