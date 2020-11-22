package juve;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Lecture {
    public Lecture() {
    }

    public static InputStream ouvrir(String var0) {
        DataInputStream var1;
        try {
            var1 = new DataInputStream(new FileInputStream(var0));
        } catch (IOException var3) {
            var1 = null;
        }

        return var1;
    }

    public static boolean finFichier(InputStream var0) {
        try {
            return var0 != System.in && var0.available() == 0;
        } catch (IOException var2) {
            System.out.println("pb test finFichier");
            System.exit(1);
            return true;
        }
    }

    public static void fermer(InputStream var0) {
        try {
            var0.close();
        } catch (IOException var2) {
            System.out.println("pb fermeture fichier");
        }

    }

    public static char lireChar(InputStream var0) {
        char var1 = ' ';

        try {
            int var2 = var0.read();
            if (var2 == -1) {
                System.out.println("lecture après fin de fichier");
                System.exit(2);
            }

            var1 = (char) var2;
        } catch (IOException var3) {
            System.out.println(var3.getMessage());
            System.out.println("Erreur fatale");
            System.exit(3);
        }

        return var1;
    }

    public static char lireChar() {
        return lireChar(System.in);
    }

    public static String lireString(InputStream var0) {
        String var1 = "";

        for (char var2 = lireChar(var0); var2 != '\n'; var2 = finFichier(var0) ? 10 : lireChar(var0)) {
            if (var2 != '\r') {
                var1 = var1 + var2;
            }
        }

        return var1;
    }

    public static String lireString() {
        return lireString(System.in);
    }

    public static int lireInt(InputStream var0) throws NumberFormatException {
        return Integer.valueOf(lireUnite(var0, false));
    }

    public static int lireInt() throws NumberFormatException {
        return lireInt(System.in);
    }

    public static int lireIntln(InputStream var0) throws NumberFormatException {
        return Integer.valueOf(lireUnite(var0, true));
    }

    public static int lireIntln() throws NumberFormatException {
        return lireIntln(System.in);
    }

    public static double lireDouble(InputStream var0) throws NumberFormatException {
        return Double.valueOf(lireUnite(var0, false));
    }

    public static double lireDouble() throws NumberFormatException {
        return lireDouble(System.in);
    }

    public static double lireDoubleln(InputStream var0) throws NumberFormatException {
        return Double.valueOf(lireUnite(var0, true));
    }

    public static double lireDoubleln() throws NumberFormatException {
        return lireDoubleln(System.in);
    }

    public static String lireUnite(InputStream var0, boolean var1) {
        String var2 = "";

        char var3;
        for (var3 = lireChar(var0); Character.isWhitespace(var3); var3 = lireChar(var0)) {
        }

        while (!Character.isWhitespace(var3)) {
            var2 = var2 + var3;
            var3 = finFichier(var0) ? 10 : lireChar(var0);
        }

        if (var3 == '\r') {
            var3 = lireChar(var0);
        }

        if (var1) {
            while (!finFichier(var0) && var3 != '\n') {
                var3 = lireChar(var0);
            }
        }

        return var2;
    }

    public static String lireUnite(boolean var0) {
        return lireUnite(System.in, var0);
    }
}
