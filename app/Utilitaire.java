 // Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   Utilitaire.java
package utilitaire;

import bean.CGenUtil;
import bean.ClassMAPTable;
import bean.TypeObjet;
import java.awt.image.BufferedImage;
import java.io.File;
import com.xuggle.xuggler.IContainer;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;
import java.util.Vector;
import java.text.DecimalFormat;
import java.lang.Integer;
import java.lang.reflect.Method;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import static java.util.Calendar.DATE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import rallye.Speciale;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import org.apache.commons.fileupload.disk.DiskFileItem;
import service.WriteFileService;
import vente.InfoLivraison;
import VV.annonce.*;
import java.awt.*;

// Referenced classes of package utilitaire:
//            Parametre, UtilDB, Log
public class Utilitaire extends Parametre {
    public static String idrallye;
    public static Map<String, String> rubriqueFoot;
    static String listeChiffre="01234567890";

    static {
        rubriqueFoot = new ConcurrentHashMap<>();
        rubriqueFoot.put("corners", "Corners");
        rubriqueFoot.put("fouls", "Fautes");
        rubriqueFoot.put("shots off target", "Tirs non cadr&eacute;s");
        rubriqueFoot.put("shots on target", "Tirs cadr&eacute;s");
        rubriqueFoot.put("yellow cards", "Carton jaune");
        rubriqueFoot.put("red cards", "Carton rouge");
        rubriqueFoot.put("possession (%)", "Possession (%)");
        rubriqueFoot.put("goal kicks", "D&eacute;gagement");
        rubriqueFoot.put("offsides", "Hors-jeu");
    }

    public static Map<String, String> idrubriqueFoot;

    static {
        idrubriqueFoot = new ConcurrentHashMap<>();
        idrubriqueFoot.put("corners", Constante.idCorner);
        idrubriqueFoot.put("shots off target", Constante.shots_off_target);
        idrubriqueFoot.put("shots on target", Constante.shots_on_target);
        idrubriqueFoot.put("possession (%)", Constante.possession);
        idrubriqueFoot.put("score", Constante.score);
        idrubriqueFoot.put("Score", Constante.score);
        idrubriqueFoot.put("fouls", Constante.fouls);
        idrubriqueFoot.put("yellow cards", Constante.yellow_cards);
        idrubriqueFoot.put("red cards", Constante.red_cards);
        idrubriqueFoot.put("goal kicks", Constante.goal_kicks);
    }

    public static Map<String, String> libelleRubrique;

    static {
        libelleRubrique = new ConcurrentHashMap<>();
        libelleRubrique.put(Constante.idCorner, "Corners");
        libelleRubrique.put(Constante.shots_off_target, "Tirs non cadr&eacute;s");
        libelleRubrique.put(Constante.shots_on_target, "Tirs cadr&eacute;s");
        libelleRubrique.put(Constante.possession, "Possession (%)");
        libelleRubrique.put(Constante.score, "Scores");
        libelleRubrique.put(Constante.score, "Scores");
        libelleRubrique.put(Constante.fouls, "Fautes");
        libelleRubrique.put(Constante.yellow_cards, "Carton jaune");
        libelleRubrique.put(Constante.red_cards, "Carton rouge");
        libelleRubrique.put(Constante.goal_kicks, "Tirs attrap&eacute;s");
        libelleRubrique.put("RBQJEU01", "Jeu de point");
        libelleRubrique.put("RUB000035", "Qualification 1");
        libelleRubrique.put("RUB000036", "Qualification 2");
        libelleRubrique.put("RUB000037", "Qualification 3");
        libelleRubrique.put("RUB000038", "Course");
        libelleRubrique.put("RBQ00001","Generale");
        if(idrallye!=null && !idrallye.equals("")){
           Speciale[] liste =  getSpecialeRallye();
           for(int i = 0;i<liste.length;i++){
                 libelleRubrique.put(""+liste[i].getId(),""+liste[i].getNumero());
           }
        }
      
    }
    
    private static List<String> SPECIFIC_FOOTER_LINKS;
    private static String VV_DEFAULT_FOOTER_URL="include/footer.jsp";
    private static String VV_CONTACT_FOOTER="include/footerContact.jsp";
    static{
        SPECIFIC_FOOTER_LINKS = new ArrayList<>();
        SPECIFIC_FOOTER_LINKS.add("information/tarifs");
        SPECIFIC_FOOTER_LINKS.add("information/info");
        SPECIFIC_FOOTER_LINKS.add("information/notre-equipe");
    }
    public static String getVVFooter(String but){
        String footer= VV_DEFAULT_FOOTER_URL;
        if(but==null) return footer;
        for(String item : SPECIFIC_FOOTER_LINKS){
            if( item.compareTo(but)==0){
                footer =  VV_CONTACT_FOOTER;
                break;
            }
        }
        return footer;
    }
    
    public static String getHeureFromTimestamp(java.sql.Timestamp heure) {
        String ora = completerInt(2, heure.getHours());
        String min = completerInt(2, heure.getMinutes());
        String sec = completerInt(2, heure.getSeconds());
        return ora + ":" + min + ":" + sec;
    }
    
    public static boolean isValidDate(String input, String format) {
        boolean valid = false;
        try {
            if (!input.isEmpty()) {
                SimpleDateFormat dateFormat = new SimpleDateFormat(format);
                java.util.Date output = dateFormat.parse(input);
                valid = dateFormat.format(output).equals(input);
            }
        } catch (Exception ex) {
            valid = false;
        }
        return valid;
    }
    
    public static Speciale[] getSpecialeRallye(){
        Speciale[] tempspeciale = new Speciale[0];
        try {
            tempspeciale = (Speciale[]) CGenUtil.rechercher(new Speciale() , null, null, null, " and rallye='"+idrallye+"'");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("erreur gettempspeciale : "+ex);
        }
        return tempspeciale;
          
    }
    
    public static Map<String, String> tournoiTranslate;

    static {
        tournoiTranslate = new ConcurrentHashMap<>();
        tournoiTranslate.put("Round of 16", "8&egrave;me de finale");
        tournoiTranslate.put("Quarter-finals", "Quarts de finale");

    }

    public static String gmt = "GMT";

    public Utilitaire() {
    }

    public static String[] formerTableauGroupe(String[] val) throws Exception {
        String retour[] = null;
        Vector r = new Vector();
        for (int i = 0; i < val.length; i++) {
            if (val[i] != null && val[i].compareToIgnoreCase("") != 0 && val[i].compareToIgnoreCase("-") != 0) {
                r.add(val[i]);
            }
        }
        if (r.size() > 0) {
            retour = new String[r.size()];
            r.copyInto(retour);
        }
        return retour;
    }

    public static String[] formerTableauGroupe(String val1, String val2) throws Exception {
        String retour[] = null;
        if ((val1 == null || val1.compareToIgnoreCase("") == 0 || val1.compareToIgnoreCase("-") == 0) && (val2 != null && val2.compareToIgnoreCase("") != 0)) {
            retour = new String[1];
            retour[0] = val2;
            return retour;
        } else if ((val2 == null || val2.compareToIgnoreCase("") == 0) && (val1 != null && val1.compareToIgnoreCase("") != 0)) {
            retour = new String[1];
            retour[0] = val1;
            return retour;
        } else if ((val2 == null || val2.compareToIgnoreCase("") == 0) && (val1 == null || val1.compareToIgnoreCase("") == 0)) {
            return null;
        } else {
            retour = new String[2];
            retour[0] = val1;
            retour[1] = val2;
            return retour;
        }
    }

    public static String[] ajouterTableauString(String[] s1, String[] s2) {
        String retour[] = new String[s1.length + s2.length];
        int i = 0;
        for (i = 0; i < s1.length; i++) {
            retour[i] = s1[i];
        }
        for (int j = 0; j < s2.length; j++) {
            retour[i + j] = s2[j];
        }
        return retour;
    }

    public static String[] ajouterTableauString(String[] s1, String[] s2, String[] s3) {
        String retour[] = new String[s1.length + s2.length + s3.length];
        int i = 0;
        for (i = 0; i < s1.length; i++) {
            retour[i] = s1[i];
        }
        int j = 0;
        for (j = 0; j < s2.length; j++) {
            retour[i + j] = s2[j];
        }
        for (int k = 0; k < s3.length; k++) {
            retour[i + j + k] = s3[k];
        }
        return retour;
    }

    public static String replaceChar(String s) {
        //s = s.replace(''', '''');
        s = s.replace('*', '%');
        s = s.replace(',', '%');
        return s;
    }

    public static String getDebutAnnee(String annee) {
        return "01/01/" + annee;
    }

    public static String[] split(String mot, String sep) {
        java.util.StringTokenizer tokenizer = new java.util.StringTokenizer(mot, sep);
        Vector v = new Vector();
        while (tokenizer.hasMoreTokens()) {
            v.add(tokenizer.nextToken());
        }
        String retour[] = new String[v.size()];
        v.copyInto(retour);
        return retour;
    }

    public static String getFinAnnee(String annee) {
        return "31/12/" + annee;
    }

    public static String[] getDebutFinAnnee() {
        Parametre.getParametre();
        String[] retour = new String[2];
        retour[0] = getDebutAnnee(String.valueOf(getAneeEnCours()));
        retour[1] = getFinAnnee(String.valueOf(getAneeEnCours()));
        return retour;
    }

    public static ClassMAPTable extraire(ClassMAPTable c[], int numCol, String val) {
        try {
            for (int i = 0; i < c.length; i++) {
                String valeur = String.valueOf(c[i].getValField(c[i].getFieldList()[numCol]));
                if (valeur.compareToIgnoreCase(val) == 0) {
                    return c[i];
                }
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String[] remplacerNullParBlanc(String[] val, String remplacant) {
        for (int i = 0; i < val.length; i++) {
            if (val[i] == null) {
                val[i] = remplacant;
            }
        }
        return val;
    }

    public static ClassMAPTable extraire(Vector v, int numCol, String val) {
        try {
            for (int i = 0; i < v.size(); i++) {
                ClassMAPTable c = (ClassMAPTable) v.elementAt(i);
                String valeur = (String) c.getValField(c.getFieldList()[numCol]);
                if (valeur.compareToIgnoreCase(val) == 0) {
                    return c;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static ClassMAPTable extraireMultiple(Vector v, int numColVect, int[] numCol, String[] val) {
        try {
            for (int i = 0; i < v.size(); i++) {
                ClassMAPTable c = (ClassMAPTable) v.elementAt(i);
                int test = 1;
                String[] valeurT = (String[]) (c.getValField(c.getFieldList()[numColVect]));
                for (int j = 0; j < numCol.length; j++) {
                    String valeur = valeurT[j];
                    if (valeur.compareToIgnoreCase(val[j]) != 0) {
                        test = 0;
                        break;
                    }
                }
                if (test == 1) {
                    return c;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static int estIlDedans(ClassMAPTable c[], int numCol, String val) {
        try {
            for (int i = 0; i < c.length; i++) {
                String valeur = (String) c[i].getValField(c[i].getFieldList()[numCol]);
                if (valeur.compareToIgnoreCase(val) == 0) {
                    return 1;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public static String[] concatener(String[] t1, String[] t2) {
        int taille = t1.length + t2.length;
        String retour[] = new String[taille];
        for (int i = 0; i < t1.length; i++) {
            retour[i] = t1[i];
        }
        for (int j = t1.length; j < taille; j++) {
            retour[j] = t2[j - t1.length];
        }
        return retour;
    }

    public static int estIlDedans(String test, String c[]) {
        try {
            if (c == null) {
                return -1;
            }
            for (int i = 0; i < c.length; i++) {
                if (c[i].compareToIgnoreCase(test) == 0) {
                    return i;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public static String convertDebutMajuscule(String autre) {
        char[] c = autre.toCharArray();
        c[0] = Character.toUpperCase(c[0]);
        return new String(c);
    }

    public static String[] getvalCol(String nomTable, String col) {
        UtilDB util = new UtilDB();
        Connection c = null;
        PreparedStatement cs = null;
        ResultSet rs = null;
        String[] retour = null;
        try {
            try {
                c = util.GetConn();
                cs = c.prepareStatement("select distinct(" + col + ") from " + nomTable);
                rs = cs.executeQuery();
                Vector v = new Vector();
                while (rs.next()) {
                    v.add(rs.getString(1));
                }
                retour = new String[v.size()];
                v.copyInto(retour);
                return retour;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        } finally {
            try {
                if (c != null) {
                    c.close();
                }
                if (cs != null) {
                    cs.close();
                }
                if (rs != null) {
                    rs.close();
                }
                util.close_connection();
            } catch (SQLException e) {
                System.out.println("Erreur Fermeture SQL RechercheType ".concat(String.valueOf(String.valueOf(e.getMessage()))));
            }
        }
    }

    public static int[] getBornePage(int page, Object list[]) {
        int ret[] = new int[2];
        ret[0] = (page - 1) * Parametre.getNbParPage();
        if ((ret[0] + Parametre.getNbParPage()) - 1 < list.length) {
            ret[1] = ret[0] + Parametre.getNbParPage();
        } else {
            ret[1] = list.length;
        }
        return ret;
    }

    public static int[] getBornePage(String page, Object list[]) {
        return getBornePage(stringToInt(page), list);
    }

    public static int calculNbPage(double tailleObjet) {
        int ret = 0;
        Double d = new Double(tailleObjet);
        ret = d.intValue() / Parametre.getNbParPage();
        if (d.intValue() % Parametre.getNbParPage() > 0) {
            ret++;
        }
        return ret;
    }

    public static int calculNbPage(double tailleObjet, int nbParPage) {
        int ret = 0;
        int nb = Parametre.getNbParPage();
        if (nbParPage > 0) {
            nb = nbParPage;
        }
        Double d = new Double(tailleObjet);
        ret = d.intValue() / nb;
        if (d.intValue() % nb > 0) {
            ret++;
        }
        return ret;
    }/*
     public static int calculNbPage(int tailleObjet)
     {
     int ret = 0;
     ret = tailleObjet / Parametre.getNbParPage();
     if(tailleObjet % Parametre.getNbParPage() > 0)
     ret++;
     return ret;
     }*/


    public static int calculNbPage(Object list[]) {
        return calculNbPage(list.length);
    }

    public static double calculSomme(String[] val) throws Exception {
        double retour = 0;
        for (int i = 0; i < val.length; i++) {
            retour = retour + Utilitaire.stringToDouble(val[i]);
        }
        return retour;
    }

    public static double calculSomme(double[] val) throws Exception {
        double retour = 0;
        for (int i = 0; i < val.length; i++) {
            retour = retour + (val[i]);
        }
        return retour;
    }

    public static int stringToInt(String s) {
        int j;
        try {
            Integer ger = new Integer(s);
            int i = ger.intValue();
            int k = i;
            return k;
        } catch (NumberFormatException ex) {
            j = 0;
        }
        return j;
    }

    public static String remplacerNull(String valNull) {
        if ((valNull == null) || valNull.compareToIgnoreCase("null") == 0) {
            return "";
        }
        return valNull;
    }

    public static String getValPourcentage(String valeur) {
        return null;
    }

    public static String remplacerUnderscore(String mot) {
        String nouveau = new String(mot.toCharArray());
        nouveau.replace('_', '-');
        return nouveau;
    }

    /*public static String remplaceEspace(String valeur){
     String retour="";
     char val[] = new char[valeur.length()];
     val = valeur.toCharArray();
     for(int i=0;i<val.length;i++){
     if(val[i]==' '){
     retour=valeur.substring(0,i)+"%20"+valeur.substring(i+1,valeur.length());
     }
     }
     System.out.print(retour);
     return retour;
     }*/
    public static String remplaceMot(String valeur, String mot1, String mot2) {
        StringBuffer result = new StringBuffer();
        int startIdx = 0;
        int idxOld = 0;
        while ((idxOld = valeur.indexOf(mot1, startIdx)) >= 0) {
            result.append(valeur.substring(startIdx, idxOld));
            result.append(mot2);
            startIdx = idxOld + mot1.length();
        }
        result.append(valeur.substring(startIdx));
        return result.toString();
    }

    public static int getRang(char[] liste, char c) {
        for (int i = 0; i < liste.length; i++) {
            if (Character.toLowerCase(liste[i]) == Character.toLowerCase(c)) {
                return i;
            }
        }
        return -1;
    }

    public static String coderPwd(String entree) {
        char[] listeMot = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        char[] chiffre = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        char[] retr = new char[entree.length() + 1];
        retr[0] = listeMot[entree.length() % 5];
        char[] entr = entree.toCharArray();
        for (int i = 0; i < entr.length; i++) {
            int rL = getRang(listeMot, entr[i]);
            int rC = getRang(chiffre, entr[i]);
            if (rL > -1) {
                retr[i + 1] = listeMot[(listeMot.length - rL - i)];
            } else if (rC > -1) {
                retr[i + 1] = chiffre[(chiffre.length + rC - i)];
            } else {
                retr[i + 1] = entr[i];
            }
        }
        return new String(retr);
    }

    public static String decode(String entree) {
        char[] listeMot = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        char[] chiffre = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        return null;
    }

    public static String remplacePourcentage(String valeur) {
        String retour = "";
        if (valeur == null) {
            return "";
        }
        char val[] = new char[valeur.length()];
        val = valeur.toCharArray();
        int taille = val.length;
        if (valeur.compareToIgnoreCase("") == 0) {
            return "";
        }
        if (valeur.compareToIgnoreCase("%") == 0 || valeur == null) {
            return "%25";
        }
        /*if(val[0] == '%' && val[taille - 1] == '%')
         {
         retour = retour.concat("%25");
         retour = retour.concat(valeur.substring(1, taille - 1));
         retour = retour.concat("%25");
         }
         if(val[0] != '%' && val[taille - 1] == '%')
         {
         retour = valeur.substring(0, taille - 1);
         retour = retour.concat("%25");
         }
         if(val[0] == '%' && val[taille - 1] != '%')
         {
         retour = retour.concat("%25");
         retour = retour.concat(valeur.substring(1, taille));
         }*/
        retour = remplaceMot(valeur, "%", "%25");
        retour = remplaceMot(retour, " ", "%20");
        return retour;
    }

    public static String getDebutmot(String mot) {
        String retour = "";
        char motChar[] = new char[mot.length()];
        motChar = mot.toCharArray();
        retour = retour.concat(String.valueOf(motChar[0]));
        int i = 0;
        do {
            if (i >= mot.length()) {
                break;
            }
            if (motChar[i] == ' ') {
                retour = retour.concat(String.valueOf(motChar[i + 1]));
                break;
            }
            i++;
        } while (true);
        return retour.toUpperCase();
    }

    public static String getDebutmot(String mot, int nombre) {
        if (mot == null) {
            return "";
        }

        if (nombre >= mot.length()) {
            return mot.toUpperCase();
        }

        String retour = "";
        if (nombre <= 0) {
            return retour;
        }

        char motChar[] = new char[mot.length()];
        motChar = mot.toCharArray();
        //retour = retour.concat(String.valueOf(motChar[0]));

        for (int n = 0; n < nombre; n++) {
            if (motChar[n] == ' ') {
                retour = retour;
                System.out.println("retour=============" + retour + n);
            } else {
                retour = retour.concat(String.valueOf(motChar[n]));
                System.out.println("retour=============" + retour + n);
            }
        }
        return retour.toUpperCase();
    }

    /**
     * Prend les 3 premieres lettres d'un String si c'est compose d'un seul mot,
     * sinon prend les premieres lettres de chaque mot
     *
     */
    public static String getDebutMots(String mot) {
        String retour = "";
        if (mot.compareTo("-") == 0) {
            return "NON";
        }
        int multiple = 0;
        int indice = 3;
        if (mot.length() < 3) {
            indice = 2;
        }
        char[] motChar = new char[mot.length()];
        motChar = mot.toCharArray();
        //retour=retour.concat(String.valueOf(motChar[0]));
        for (int i = 0; i < mot.length(); i++) {
            if (motChar[i] == ' ') {
                multiple = 1;
                break;
            }
        }
        if (multiple == 1) {
            retour = getDebutmot(mot);
        } else {
            for (int i = 0; i < indice; i++) {
                retour = retour.concat(String.valueOf(motChar[i]));
            }
        }
        return retour.toUpperCase();
    }

    public static double getPvente(int pu, int marge) {
        return (double) pu * ((double) 1 + (double) marge / (double) 100);
    }

    public static float stringToFloat(String s) {
        float f1;
        try {
            Float ger = new Float(s);
            float f = ger.floatValue();
            return f;
        } catch (NumberFormatException ex) {
            f1 = 0.0F;
        }
        return f1;
    }

    public static String[] getBorneAnneeEnCours() {
        return null;
    }

    public static java.sql.Date[] convertIntervaleToListDate(java.sql.Date dmin, java.sql.Date dmax) {
        java.sql.Date[] ret = new java.sql.Date[1];
        ret[0] = dmin;
        return ret;
    }

    public static String[] getBorneDatyMoisAnnee(String mois, String an) {
        String retour[] = new String[2];
        GregorianCalendar eD = new GregorianCalendar();
        GregorianCalendar eD2 = new GregorianCalendar();
        retour[0] = "01/" + mois + "/" + an;
        Date daty1 = string_date("dd/MM/yyyy", retour[0]);
        eD.setTime(daty1);
        eD2.setTime(daty1);
        eD2.add(5, 26);
        do {
            eD2.add(5, 1);
        } while (eD.get(2) == eD2.get(2));
        eD2.add(5, -1);
        retour[1] = String.valueOf(String.valueOf(completerInt(2, eD2.get(5)))).concat("/");
        retour[1] = String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(retour[1])))).append(completerInt(2, eD2.get(2) + 1)).append("/")));
        retour[1] = String.valueOf(retour[1]) + String.valueOf(completerInt(4, eD2.get(1)));
        return retour;
    }

    public static int getAneeEnCours() {
        Calendar a = Calendar.getInstance();
        return a.get(1);
    }

    public static int compterCar(String lettre, char c) {
        char[] mot = lettre.toCharArray();
        int nb = 0;
        for (int i = 0; i < mot.length; i++) {
            if (mot[i] == c) {
                nb++;
            }
        }
        return nb;
    }

    public static String[] split(String lettre, char sep) {
        Vector v = new Vector();
        char[] mot = lettre.toCharArray();
        char part[] = new char[100];
        int indicePart = 0;
        for (int i = 0; i < mot.length; i++) {
            if (mot[i] == sep || (i == mot.length - 1)) {
                indicePart = 0;
                v.add(String.valueOf(part));
                part = new char[100];
            } else {
                part[indicePart] = mot[i];
                indicePart++;
            }
        }
        String[] retour = new String[v.size()];
        v.copyInto(retour);
        return retour;
    }

    public static String getAnnee(String daty) {
        //daty.
        //GregorianCalendar eD = new GregorianCalendar();
        //eD.setTime(string_date("dd/MM/yyyy", daty));
        //return String.valueOf(eD.get(1));
        return split(daty, "/")[2];
    }

    public static int getAnnee(Date daty) {
        GregorianCalendar eD = new GregorianCalendar();
        eD.setTime(daty);
        return eD.get(1);
    }

    public static int getMois(Date daty) {
        GregorianCalendar eD = new GregorianCalendar();
        eD.setTime(daty);
        return eD.get(2) + 1;
    }

    public static int getJour(Date daty) {
        GregorianCalendar eD = new GregorianCalendar();
        eD.setTime(daty);
        return eD.get(3);
    }

    public static String getMois(String daty) {
        //GregorianCalendar eD = new GregorianCalendar();
        //eD.setTime(string_date("dd/MM/yyyy", daty));
        //return completerInt(2, eD.get(2) + 1);
        return completerInt(2, split(daty, "/")[1]);
    }

    public static String getJour(String daty) {
        //GregorianCalendar eD = new GregorianCalendar();
        //eD.setTime(string_date("dd/MM/yyyy", daty));
        //return completerInt(2, eD.get(5));
        return completerInt(2, split(daty, "/")[0]);
    }

    public static int getMoisEnCours() {
        Calendar a = Calendar.getInstance();
        return a.get(2);
    }

    public static int compareDaty(Date supe, Date infe) {
        GregorianCalendar eD = new GregorianCalendar();
        GregorianCalendar eD2 = new GregorianCalendar();
        Date sup = string_date("dd/MM/yyyy", formatterDaty(supe));
        Date inf = string_date("dd/MM/yyyy", formatterDaty(infe));
        eD.setTime(sup);
        eD2.setTime(inf);
        if (eD.getTime().getTime() > eD2.getTime().getTime()) {
            return 1;
        }
        return eD.getTime().getTime() >= eD2.getTime().getTime() ? 0 : -1;
    }

    public static int diffJourDaty(Date dMaxe, Date dMine) {
        GregorianCalendar eD = new GregorianCalendar();
        GregorianCalendar eD2 = new GregorianCalendar();
        Date dMax = string_date("dd/MM/yyyy", formatterDaty(dMaxe));
        Date dMin = string_date("dd/MM/yyyy", formatterDaty(dMine));
        eD.setTime(dMax);
        eD2.setTime(dMin);
        double resultat = eD.getTime().getTime() - eD2.getTime().getTime();
        BigDecimal result = new BigDecimal(String.valueOf(eD.getTime().getTime() - eD2.getTime().getTime()));
        BigDecimal retour = result.divide(new BigDecimal(String.valueOf(0x5265c00)), 4);
        return 1 + retour.intValue();
    }

    public static int diffJourDaty(String dMax, String dMin) {
        return diffJourDaty(string_date("dd/MM/yyyy", dMax), string_date("dd/MM/yyyy", dMin));
    }

    public static double stringToDouble(String s) {
        double d1;
        try {
            if(s == null) return 0;
            Double ger = new Double(s);
            double d = ger.doubleValue();
            return d;
        } catch (NumberFormatException ex) {
            d1 = 0.0D;
        }
        return d1;
    }

    public static long stringToLong(String s) {
        try {
            Long ger = new Long(s);
            long l = ger.longValue();
            return l;
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        long l1 = 0L;
        return l1;
    }
    
    public static String formaterAr(String montant) {
        return formaterAr(stringToDouble(montant));
    }
    
    public static String formaterAr(double montant, int minimumFractionDigit) {
        try {
            if (montant == 0) {
                return "0";
            }
            NumberFormat nf = NumberFormat.getInstance(Locale.FRENCH);
            //nf = new DecimalFormat("### ###,##");
            //nf.setMaximumFractionDigits(2);
            nf.setMinimumFractionDigits(minimumFractionDigit);
            //nf.setMaximumIntegerDigits(0);
            String s = nf.format(montant);
            String ret;
            ret = s;
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
        }
        String s1 = null;
        return s1;
    }
    
    public static String formaterAr(double montant) {
        try {
            if (montant == 0) {
                return "0";
            }
            NumberFormat nf = NumberFormat.getInstance(Locale.FRENCH);
            //nf = new DecimalFormat("### ###,##");
            //nf.setMaximumFractionDigits(2);
            //nf.setMinimumFractionDigits(2);
            //nf.setMaximumIntegerDigits(0);
            String s = nf.format(montant);
            String ret;
            ret = s;
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
        }
        String s1 = null;
        return s1;
    }

    public static String formaterAr(long montant) {
        return formaterAr(String.valueOf(montant));
    }

    public static String formatterDaty(String daty) {
        String retour = null;
        if ((daty == null) || (daty.equals(""))) {
            return "";
        }
        return (daty.substring(8, 10) + "/" + (daty.substring(5, 7)) + "/" + (daty.substring(0, 4)));
    }

    public static double arrondir(double a, int apr) {
        double d;
        try {
            if (apr == 0) {
                NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
                nf.setMaximumFractionDigits(apr);
                Number retour = nf.parse(nf.format(a));
                return retour.intValue();
            }
            NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
            nf.setMaximumFractionDigits(apr);
            Number retour = nf.parse(nf.format(a));
            double d1 = retour.doubleValue();
            return d1;
        } catch (Exception e) {
            d = 1.0D;
        }
        return d;
    }

    public static int arrondirInt(double a) {
        int d;
        try {

            return (int) a;

        } catch (Exception e) {
            e.printStackTrace();
            d = 1;
        }
        return d;
    }

    public static String formatterDaty(Date daty) {
        String retour = null;
        return formatterDaty(String.valueOf(daty));
    }

    public static String formatterDatySql(java.sql.Date daty) {
        String retour = null;
        return formatterDaty(String.valueOf(daty));
    }

    public static Date ajoutJourDateOuvrable(Date aDate, int nbDay) {
        try {
            Date date = string_date("dd/MM/yyyy", ajoutJourDateStringOuvrable(aDate, nbDay));
            return date;
        } catch (Exception e) {
            System.out.println("Error string_date :".concat(String.valueOf(String.valueOf(e.getMessage()))));
        }
        Date date1 = null;
        return date1;
    }

    public static String ajoutJourDateStringOuvrable(Date aDatee, int nbDay) {
        try {
            GregorianCalendar eD = new GregorianCalendar();
            Date aDate = string_date("dd/MM/yyyy", formatterDaty(aDatee));
            eD.setTime(aDate);
            int offset = 1;
            int offsetSunday = 1;
            int offsetSaturday = 2;
            if (nbDay < 0) {
                offset = -1;
                offsetSunday = -2;
                offsetSaturday = -1;
            }
            for (int i = 1; i <= Math.abs(nbDay); i++) {
                eD.add(5, offset);
                if (eD.get(7) == 7) {
                    eD.add(5, offsetSaturday);
                    continue;
                }
                if (eD.get(7) == 1) {
                    eD.add(5, offsetSunday);
                }
            }

            String retour = null;
            retour = String.valueOf(String.valueOf(completerInt(2, eD.get(5)))).concat("/");
            retour = String.valueOf(retour) + String.valueOf(completerInt(2, String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(eD.get(2) + 1)))).append("/")))));
            retour = String.valueOf(retour) + String.valueOf(completerInt(4, eD.get(1)));
            String s1 = retour;
            return s1;
        } catch (Exception e) {
            System.out.println("Error string_date :".concat(String.valueOf(String.valueOf(e.getMessage()))));
        }
        String s = null;
        return s;
    }

    public static String ajoutMoisDateString(Date aDatee, int nbMois) {
        try {
            GregorianCalendar eD = new GregorianCalendar();
            GregorianCalendar eD2 = new GregorianCalendar();
            Date aDate = string_date("dd/MM/yyyy", formatterDaty(aDatee));
            eD.setTime(aDate);
            int offset = 1;
            int offsetSunday = 1;
            int offsetSaturday = 2;
            if (nbMois < 0) {
                offset = -1;
                offsetSunday = -2;
                offsetSaturday = -1;
            }
            for (int i = 1; i <= Math.abs(nbMois); i++) {
                eD.add(2, offset);
            }

            eD2.setTime(eD.getTime());
            if (eD.get(2) == eD2.get(2) && testFinDuMois(aDate)) {
                do {
                    eD2.add(5, 1);
                } while (eD.get(2) == eD2.get(2));
                eD2.add(5, -1);
            }
            String retour = null;
            retour = String.valueOf(String.valueOf(completerInt(2, eD2.get(5)))).concat("/");
            retour = String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(retour)))).append(completerInt(2, eD2.get(2) + 1)).append("/")));
            retour = String.valueOf(retour) + String.valueOf(completerInt(4, eD2.get(1)));
            String s1 = retour;
            return s1;
        } catch (Exception e) {
            System.out.println("Error string_date :".concat(String.valueOf(String.valueOf(e.getMessage()))));
        }
        String s = null;
        return s;
    }

    public static boolean testFinDuMois(Date aDatee) {
        GregorianCalendar eD = new GregorianCalendar();
        Date aDate = string_date("dd/MM/yyyy", formatterDaty(aDatee));
        eD.setTime(aDate);
        GregorianCalendar eD2 = new GregorianCalendar();
        eD2.setTime(eD.getTime());
        eD2.add(5, 1);
        return eD.get(2) != eD2.get(2);
    }

    public static String ajoutJourDateString(Date aDatee, int nbDay) {
        try {
            GregorianCalendar eD = new GregorianCalendar();
            Date aDate = string_date("dd/MM/yyyy", formatterDaty(aDatee));
            eD.setTime(aDate);
            int offset = 1;
            int offsetSunday = 1;
            int offsetSaturday = 2;
            if (nbDay < 0) {
                offset = -1;
                offsetSunday = -2;
                offsetSaturday = -1;
            }
            for (int i = 1; i <= Math.abs(nbDay); i++) {
                eD.add(5, offset);
            }

            String retour = null;
            retour = String.valueOf(String.valueOf(completerInt(2, eD.get(5)))).concat("/");
            retour = String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(retour)))).append(completerInt(2, eD.get(2) + 1)).append("/")));
            retour = String.valueOf(retour) + String.valueOf(completerInt(4, eD.get(1)));
            String s1 = retour;
            return s1;
        } catch (Exception e) {
            System.out.println("Error ajoutJourDateString :".concat(String.valueOf(String.valueOf(e.getMessage()))));
        }
        String s = null;
        return s;
    }

    public static Date ajoutJourDateOuv(Date aDate, int nbDay){
        Date retour = ajoutJourDate(aDate, nbDay);
        if(retour.getDay()==6){
            retour = Utilitaire.ajoutJourDate(retour, 2);
        }
        if(retour.getDay()==0){
            retour = Utilitaire.ajoutJourDate(retour, 1);
        }
        return retour;
    }
    
    public static Date ajoutJourDate(Date aDate, int nbDay) {
        try {
            Date date = string_date("dd/MM/yyyy", ajoutJourDateString(aDate, nbDay));
            return date;
        } catch (Exception e) {
            System.out.println("Error ajoutJourDate :".concat(String.valueOf(String.valueOf(e.getMessage()))));
        }
        Date date1 = null;
        return date1;
    }

    public static Date ajoutMoisDate(Date aDate, int nbMois) {
        try {
            Date date = string_date("dd/MM/yyyy", ajoutMoisDateString(aDate, nbMois));
            return date;
        } catch (Exception e) {
            System.out.println("Error ajoutMoisDate :".concat(String.valueOf(String.valueOf(e.getMessage()))));
        }
        Date date1 = null;
        return date1;
    }

    public static Date ajoutJourDate(String daty, int jour) {
        try {
            Date date = ajoutJourDate(string_date("dd/MM/yyyy", daty), jour);
            return date;
        } catch (Exception e) {
            System.out.println("Error ajoutJourDate :".concat(String.valueOf(String.valueOf(e.getMessage()))));
        }
        Date date1 = null;
        return date1;
    }

    public static Date string_date(String patterne, String daty) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(patterne);
            formatter.applyPattern(patterne);
            formatter.setTimeZone(TimeZone.getTimeZone("EUROPE"));
            String annee = getAnnee(daty);
            int anneecours = getAneeEnCours();
            int siecl = anneecours / 100;
            if (annee.toCharArray().length == 2) {
                annee = String.valueOf(siecl) + annee;
            }
            daty = getJour(daty) + "/" + getMois(daty) + "/" + annee;
            Date hiredate = new Date(formatter.parse(daty).getTime());
            Date date1 = hiredate;
            return date1;
        } catch (Exception e) {
            System.out.println("Error string_date :".concat(String.valueOf(String.valueOf(e.getMessage()))));
        }
        Date date = dateDuJourSql();
        return date;
    }

    public static java.util.Date stringToDate(String pattern, String daty) {
        try {
            //System.out.println("DATY UTILITAIRE ".concat(String.valueOf(String.valueOf(pattern))));
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            java.util.Date hiredate = formatter.parse(daty);
            java.util.Date date1 = hiredate;
            return date1;
        } catch (Exception e) {
            System.out.println("Error stringTodate :".concat(String.valueOf(String.valueOf(e.getMessage()))));
        }
        java.util.Date date = null;
        return date;
    }

    public int randomizer(int max) {
        int retour;
        for (retour = 0; retour <= 0; retour = r.nextInt(max));
        return retour;
    }

    public String randomizer_daty(int annee) {
        int mois = r.nextInt(13);
        int jour = r.nextInt(31);
        String retour = String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(jour)))).append("/").append(mois).append("/").append(annee)));
        return retour;
    }

    public static int getNbTuple(String nomTable) {
        Connection c = null;
        UtilDB util = new UtilDB();
        try {
            try {
                c = util.GetConn();
                String param = "select count(*) from ".concat(String.valueOf(String.valueOf(nomTable)));
                Statement sta = c.createStatement();
                ResultSet rs = sta.executeQuery(param);
                rs.next();
                int i = rs.getInt(1);
                return i;
            } catch (SQLException e) {
                System.out.println("getNbTuple : ".concat(String.valueOf(String.valueOf(e.getMessage()))));
            }
            int j = 0;
            return j;
        } finally {
            util.close_connection();
        }
    }

    public static int getMaxColonneFactFin(String daty) {
        UtilDB util = new UtilDB();
        Connection c = null;
        PreparedStatement cs = null;
        ResultSet rs = null;
        try {
            try {
                String an = getAnnee(daty);
                c = null;
                c = util.GetConn();
                cs = c.prepareStatement(String.valueOf(String.valueOf((new StringBuffer("select * from  seqFact where daty<='31/12/")).append(an).append("' and daty>='01/01/").append(an).append("'"))));
                rs = cs.executeQuery();
                int i = 0;
                if (rs.next()) {
                    i++;
                }
                if (i == 0) {
                    int k = 0;
                    return k;
                }
                int l = (new Integer(rs.getString(1))).intValue();
                return l;
            } catch (SQLException e) {
                System.out.println("getMaxSeq : ".concat(String.valueOf(String.valueOf(e.getMessage()))));
            }
            int j = 0;
            return j;
        } finally {
            try {
                if (c != null) {
                    c.close();
                }
                if (cs != null) {
                    cs.close();
                }
                if (rs != null) {
                    rs.close();
                }
                util.close_connection();
            } catch (SQLException e) {
                System.out.println("Erreur Fermeture SQL RechercheType ".concat(String.valueOf(String.valueOf(e.getMessage()))));
            }
        }
    }

    public static int getMaxSeq(String nomProcedure, Connection c) throws Exception {
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
//            cs = c.prepareCall(String.valueOf(String.valueOf((new StringBuffer("select ")).append(nomProcedure).append(" from dual"))));
            cs = c.prepareCall(String.valueOf(String.valueOf((new StringBuffer("select ")).append("nextval('" + nomProcedure + "')"))));
            //cs = c.prepareCall(String.valueOf(String.valueOf((new StringBuffer("select * from ")).append(nomProcedure + "() "))));
            
            rs = cs.executeQuery();
            rs.next();
            int i = rs.getInt(1);
            return i;
        } catch (Exception e) {
            throw e;
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }
    
    public static int getMaxSeqJejoo(String nomProcedure, Connection c) throws Exception {
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
//            cs = c.prepareCall(String.valueOf(String.valueOf((new StringBuffer("select ")).append(nomProcedure).append(" from dual"))));
//            cs = c.prepareCall(String.valueOf(String.valueOf((new StringBuffer("select ")).append("nextval('" + nomProcedure + "')"))));
            cs = c.prepareCall(String.valueOf(String.valueOf((new StringBuffer("select * from ")).append(nomProcedure + "() "))));
            
            rs = cs.executeQuery();
            rs.next();
            int i = rs.getInt(1);
            return i;
        } catch (Exception e) {
            throw e;
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }

    public static int getMaxSeq(String nomProcedure) {
        UtilDB util = new UtilDB();
        Connection c = null;
        try {
            c = util.GetConn();
            return getMaxSeq(nomProcedure, c);
        } catch (Exception eu) {
            eu.printStackTrace();
        } finally {
            try {
                if (c != null) {
                    c.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public static int getMaxNum(String nomTable, String nomColonne) throws Exception {
        Connection c = null;
        UtilDB util = new UtilDB();
        Statement sta = null;
        ResultSet rs = null;
        try {
            try {
                c = util.GetConn();
                String param = String.valueOf(String.valueOf((new StringBuffer("select max(")).append(nomColonne).append(") from ").append(nomTable)));
                sta = c.createStatement();
                rs = sta.executeQuery(param);
                rs.next();
                int i = 1 + rs.getInt(1);
                return i;
            } catch (SQLException e) {
                System.out.println("getNbTuple : ".concat(String.valueOf(String.valueOf(e.getMessage()))));
            }
            int j = 0;
            return j;
        } finally {
            if (sta != null) {
                sta.close();
            }
            if (rs != null) {
                rs.close();
            }
            util.close_connection();
        }
    }

    public static int getNombreJourMois(String mois, String an) {
        try {
            String datyInf = getBorneDatyMoisAnnee(mois, an)[0];
            String datySup = getBorneDatyMoisAnnee(mois, an)[1];
            int j = diffJourDaty(datySup, datyInf);
            return j;
        } catch (Exception e) {
            System.out.println("getNombreJourMois : ".concat(String.valueOf(String.valueOf(e.getMessage()))));
        }
        int i = 0;
        return i;
    }

    public static int getNombreJourMois(String daty) {
        try {
            String mois = getMois(daty);
            String an = getAnnee(daty);
            int j = getNombreJourMois(mois, an);
            return j;
        } catch (Exception e) {
            System.out.println("getNombreJourMois : ".concat(String.valueOf(String.valueOf(e.getMessage()))));
        }
        int i = 0;
        return i;
    }

    public static String completerInt(int longuerChaine, int nombre) {
        String zero = null;
        zero = "";
        for (int i = 0; i < longuerChaine - String.valueOf(nombre).length(); i++) {
            zero = String.valueOf(String.valueOf(zero)).concat("0");
        }

        return String.valueOf(zero) + String.valueOf(String.valueOf(nombre));
    }

    public static String completerInt(int longuerChaine, String nombre2) {
        int nombre = stringToInt(nombre2);
        return completerInt(longuerChaine, nombre);
    }

    public static String heureCourante() {
        java.util.Date currentTime = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone(gmt));
        return sdf.format(currentTime);
    }

    public static java.sql.Time heureCouranteTime() {
        java.util.Date currentTime = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone(gmt));
        return new java.sql.Time(currentTime.getTime());
    }
    public static java.sql.Time heureCouranteTimeGMT() throws Exception{
        java.util.Date currentTime = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        String time = sdf.format(currentTime);
        return Time.valueOf(time);
    } 

    public static java.sql.Timestamp dateTimeCourante() {
        return new java.sql.Timestamp(new java.util.Date().getTime());
    }

    public static String heureCouranteHMS() {
        return heureCourante();
    }

    public static String dateDuJour() {
        final java.util.Date currentTime = new java.util.Date();
        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone(gmt));
        return sdf.format(currentTime);

    }
    
    public static String dateDuJourProd() { 
        final java.util.Date currentTime = new java.util.Date();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone(gmt));
        return sdf.format(currentTime);

    }

    public static String getAnneEnCours() {
        Calendar c = Calendar.getInstance();
        long d = c.get(Calendar.YEAR);
        return Long.toString(d);
    }

    public static Date dateDuJourSql() {
        return string_date("dd/MM/yyyy", dateDuJour());
    }

    public static String annulerZero(int nombre) {
        if (nombre == 0) {
            return " ";
        } else {
            return String.valueOf(nombre);
        }
    }

    public static Vector intersecter(ClassMAPTable objet1[], ClassMAPTable objet2[]) {
        Vector retour = new Vector();
        int dim1 = objet1.length;
        int dim2 = objet2.length;
        int nbEgaux = 0;
        for (int i = 0; i < dim1; i++) {
            String cle1 = objet1[i].getTuppleID();
            for (int j = 0; j < dim2; j++) {
                String cle2 = objet2[j].getTuppleID();
                if (cle1.compareTo(cle2) == 0) {
                    retour.add(nbEgaux, objet2[j]);
                    nbEgaux++;
                }
            }

        }
        return retour;
    }

    public static Vector intersecter(Vector objet1, Vector objet2) {
        Vector retour = new Vector();
        int dim1 = objet1.size();
        int dim2 = objet2.size();
        int nbEgaux = 0;
        for (int i = 0; i < dim1; i++) {
            ClassMAPTable temp = (ClassMAPTable) objet1.elementAt(i);
            String cle1 = temp.getTuppleID();
            for (int j = 0; j < dim2; j++) {
                ClassMAPTable temp2 = (ClassMAPTable) objet2.elementAt(j);
                String cle2 = temp2.getTuppleID();
                if (cle1.compareTo(cle2) == 0) {
                    retour.add(nbEgaux, temp2);
                    nbEgaux++;
                }
            }

        }

        return retour;
    }

    public static boolean intersecterIgnoreCase(String nomChamp, String valeur, ClassMAPTable objet1[]) {
        int dim1 = objet1.length;
        int nbEgaux = 0;
        for (int i = 0; i < dim1; i++) {
            String cle1 = objet1[i].getTuppleID();
            if (ref.compareTo(cle1) == 0) {
                return true;
            }
        }

        return false;
    }

    public static boolean intersecter(String ref, ClassMAPTable objet1[]) {
        int dim1 = objet1.length;
        int nbEgaux = 0;
        for (int i = 0; i < dim1; i++) {
            String cle1 = objet1[i].getTuppleID();
            if (ref.compareTo(cle1) == 0) {
                return true;
            }
        }

        return false;
    }

    public static boolean intersecter(String ref, Vector objet1) {
        int dim1 = objet1.size();
        int nbEgaux = 0;
        if (objet1 != null) {
            for (int i = 0; i < dim1; i++) {
                ClassMAPTable temp = (ClassMAPTable) objet1.elementAt(i);
                String cle1 = temp.getTuppleID();
                if (ref.compareTo(cle1) == 0) {
                    return true;
                }
            }

        }
        return false;
    }

    public static Object[] toArray(Vector v) {
        Object retour[] = new Object[v.size()];
        for (int i = 0; i < v.size(); i++) {
            retour[i] = v.elementAt(i);
        }

        return retour;
    }

    public static String getRequest(String temp) {
        if (temp == null || temp.compareTo("") == 0) {
            return "";
        } else {
            return temp;
        }
    }

    public static String getValeurNonNull(String temp) {
        if (temp == null || temp.compareTo("") == 0) {
            return "%";
        } else {
            return temp;
        }
    }

    public static String makePK(int longPK, String indPk, String nomProcedureSequence) throws Exception {
        int maxSeq = getMaxSeq(nomProcedureSequence);
        String nombre = completerInt(longPK, maxSeq);
        return String.valueOf(indPk) + String.valueOf(nombre);
    }

    public static String[] getNomColonne(Object a) {
        String retour[] = null;
        Field f[] = a.getClass().getDeclaredFields();
        retour = new String[f.length];
        for (int i = 0; i < f.length; i++) {
            retour[i] = f[i].getName();
        }

        return retour;
    }

    public static String[] getNomColonne(Object a, String typ) {
        String retour[] = null;
        Field f[] = a.getClass().getFields();
        Vector v = new Vector();
        for (int i = 0; i < f.length; i++) {
            if (typ.compareToIgnoreCase("nombre") == 0) {
                if ((f[i].getType().getName().compareToIgnoreCase("int") == 0) || (f[i].getType().getName().compareToIgnoreCase("double") == 0) || (f[i].getType().getName().compareToIgnoreCase("float") == 0) || (f[i].getType().getName().compareToIgnoreCase("short") == 0)) {
                    v.add(f[i].getName());
                }
            }
            if (typ.compareToIgnoreCase("chaine") == 0) {
                if (f[i].getType().getName().compareToIgnoreCase("String") == 0) {
                    v.add(f[i].getName());
                }
            }
        }
        retour = new String[v.size()];
        v.copyInto(retour);
        return retour;
    }
    public static String cripter(String mot)throws Exception
    {
        String listeCaract="abcdefghijklmnopqrstuvwxyz";
        char[]listeCarMot=mot.toCharArray();
        char[]listeCarCaract=listeCaract.toCharArray();
        char[]listeCarChiffre=listeChiffre.toCharArray();
        char[]retour=new char[listeCarMot.length];
        for(int compteur=0;compteur<listeCarMot.length;compteur++)
        {
            retour[compteur]=listeCarMot[listeCarMot.length-compteur-1];
            //ador
            int indiceCarMot=getIndiceCar(retour[compteur],listeCarCaract);            
            if(isNumerique(retour[compteur]))
            {
                retour[compteur]=listeCarChiffre[listeCarChiffre.length-getIndiceCar(retour[compteur],listeCarChiffre)-1];
            }
            else if(indiceCarMot>=0)
            {
                retour[compteur]=listeCarCaract[listeCarCaract.length-indiceCarMot-1];
            }
        }
        return new String(retour);
    }
    
    public static boolean isNumerique(char c)
    {
        for(int i=0;i<listeChiffre.length();i++)
        {
            char[]listeCarChiffre=listeChiffre.toCharArray();
            if(c==listeCarChiffre[i])return true;
        }
        return false;
    }
    public static int getIndiceCar(char car,char[]listeCar)
    {
        for(int i=0;i<listeCar.length;i++)
        {
            if(listeCar[i]==car)return i;
        }
        return -1;
    }
    public static String cryptWord(String mot) {
        int niveau = (int) Math.round(Math.random() * 10);
        int sens = (int) Math.round(Math.random());
        if (niveau == 0) {
            niveau = -5;
        }
        return (cryptWord(mot, niveau, sens));
    }

    public static String cryptWord(String mot, int niveauCrypt, int croissante) {
        if (croissante == 0) {
            return cryptWord(mot, niveauCrypt, true);
        } else {
            return cryptWord(mot, niveauCrypt, false);
        }
    }

    public static String cryptWord(String mot, int niveauCrypt, boolean croissante) {
        char[] ar = mot.toCharArray();
        char[] retour = new char[ar.length];

        if (croissante) {
            for (int i = 0; i < ar.length; i++) {
                int k = Character.getNumericValue(ar[i]);
                if (k < (Character.MAX_RADIX - niveauCrypt)) {
                    retour[i] = Character.forDigit(k + niveauCrypt, Character.MAX_RADIX);
                } else {
                    retour[i] = ar[i];
                }
            }
        } else {
            for (int i = 0; i < ar.length; i++) {
                int k = Character.getNumericValue(ar[i]);
                if (k > (niveauCrypt - 1)) {
                    retour[i] = Character.forDigit(k - niveauCrypt, Character.MAX_RADIX);
                } else {
                    retour[i] = ar[i];
                }
            }
        }

        return new String(retour);
    }

    public static String unCryptWord(String mot, int niveauCrypt, boolean croissante) {
        char[] ar = mot.toCharArray();
        char[] retour = new char[ar.length];

        if (croissante) {
            for (int i = 0; i < ar.length; i++) {
                int k = Character.getNumericValue(ar[i]);
                if (k < (Character.MAX_RADIX - niveauCrypt)) {
                    retour[i] = Character.forDigit(k - niveauCrypt, Character.MAX_RADIX);
                } else {
                    retour[i] = ar[i];
                }
            }
        } else {
            for (int i = 0; i < ar.length; i++) {
                int k = Character.getNumericValue(ar[i]);
                if (k > (niveauCrypt - 1)) {
                    retour[i] = Character.forDigit(k + niveauCrypt, Character.MAX_RADIX);
                } else {
                    retour[i] = ar[i];
                }
            }
        }
        return new String(retour);
    }

    public static String nbToMois(int nombre) {
        String mois = null;
        switch (nombre) {
            case 1: // '\001'
                mois = "janvier";
                break;

            case 2: // '\002'
                mois = "fevrier";
                break;

            case 3: // '\003'
                mois = "mars";
                break;

            case 4: // '\004'
                mois = "avril";
                break;

            case 5: // '\005'
                mois = "mai";
                break;

            case 6: // '\006'
                mois = "juin";
                break;

            case 7: // '\007'
                mois = "juillet";
                break;

            case 8: // '\b'
                mois = "aout";
                break;

            case 9: // '\t'
                mois = "septembre";
                break;

            case 10: // '\n'
                mois = "octobre";
                break;

            case 11: // '\013'
                mois = "novembre";
                break;

            case 12: // '\f'
                mois = "decembre";
                break;

            default:
                mois = null;
                break;
        }
        return mois;
    }

    static Random r = new Random();

    public static java.sql.Date convertStringToSqlDate(String pattern, String daty) {
        java.util.Date dateActuUtil = stringToDate(pattern, daty);
        java.sql.Date ret = UtilitaireDate.convertUtilDateToSqlDate(dateActuUtil);
        return ret;
    }

    public static java.sql.Date stringToSqlDate(String pattern, String daty) {
        java.util.Date dateUtil = stringToDate(pattern, daty);
        java.sql.Date ret = new java.sql.Date(dateUtil.getTime());
        return ret;
    }

    public static Integer[] convertIntToInteger(int[] intval) {
        Integer[] ret = new Integer[intval.length];
        for (int i = 0; i < intval.length; i++) {
            ret[i] = new Integer(intval[i]);
        }
        return ret;
    }

    public static int comparerObjectArray(Object[] l1, Object[] l2) {
        if (l1.length != l2.length) {
            return 0;
        }
        for (int i = 0; i < l1.length; i++) {
            if (l1[i].equals(l2[i]) == false) {
                return 0;
            }
        }
        return 1;
    }

    public static int comparerIntArray(int[] l1, int[] l2) {
        int ret = 0;
        Integer[] l1integer = convertIntToInteger(l1);
        Integer[] l2integer = convertIntToInteger(l2);
        if (l1.length != l2.length) {
            return ret;
        } else {
            ret = comparerObjectArray(l1integer, l2integer);
        }
        return ret;
    }

    public static Object calculSomme(Object[] liste, String nomField) {
        return null;
    }

    public static Vector convertResultSetToVector(ResultSet rset) throws Exception {
        Vector ret = new Vector();
        while (rset.next()) {
            ret.add(rset);
        }
        return ret;
    }

    public static Object[] convertResultSetToObjectArray(ResultSet rset) throws Exception {
        Vector vect = convertResultSetToVector(rset);
        return vect.toArray();
    }

    public static void ajouterDansVector(Vector vect, Object[] listeaAjoute) {
        for (int i = 0; i < listeaAjoute.length; i++) {
            vect.add(listeaAjoute[i]);
        }
    }

    public static void addAnObjectArrayToVect(Vector vect, Object[] listeObj) {
        boolean add;
        for (int i = 0; i < listeObj.length; i++) {
            add = vect.add(listeObj[i]);
        }
    }

    public static Object[] fusionnerObjectArrays(Object[] obj1, Object[] obj2) {
        Vector retVect = new Vector();
        boolean add;
        addAnObjectArrayToVect(retVect, obj1);
        addAnObjectArrayToVect(retVect, obj1);
        return retVect.toArray();
    }

    public static void completeForeignKey(ClassMAPTable contenantFK, ClassMAPTable angalanaPK) {
        contenantFK.setINDICE_PK(angalanaPK.getINDICE_PK());
    }

    public static double deformatterAr(String ar) throws Exception {
        java.text.NumberFormat nf = java.text.NumberFormat.getInstance(Locale.FRENCH);
        java.lang.Number numb = nf.parse(ar);
        double strToDb = numb.doubleValue();
        return strToDb;
    }

    public static String getMaxId(String nomTable) throws Exception {
        Connection c = null;
        UtilDB util = new UtilDB();
        Statement sta = null;
        ResultSet rs = null;
        try {
            try {
                c = util.GetConn();
                //String param = String.valueOf(String.valueOf((new StringBuffer("select max(")).append(nomColonne).append(") from ").append(nomTable)));
                String param = "select max(id) as max from " + nomTable + "";
                System.out.println("ID = " + param);
                sta = c.createStatement();
                rs = sta.executeQuery(param);
                rs.next();

                String i = rs.getString(1);
                return i;
            } catch (SQLException e) {
                System.out.println("getNbTuple : ".concat(String.valueOf(String.valueOf(e.getMessage()))));
            }
            String j = " ";
            return j;
        } finally {
            if (sta != null) {
                sta.close();
            }
            if (rs != null) {
                rs.close();
            }
            util.close_connection();
        }
    }

    public static String getMax(String nomTable) throws Exception {
        Connection c = null;
        UtilDB util = new UtilDB();
        Statement sta = null;
        ResultSet rs = null;
        try {
            try {
                c = util.GetConn();
                //String param = String.valueOf(String.valueOf((new StringBuffer("select max(")).append(nomColonne).append(") from ").append(nomTable)));
                String param = "select max(id) as max from " + nomTable + "";
                System.out.println("ID = " + param);
                sta = c.createStatement();
                rs = sta.executeQuery(param);
                rs.next();

                String i = rs.getString(1);
                return i;
            } catch (SQLException e) {
                System.out.println("getNbTuple : ".concat(String.valueOf(String.valueOf(e.getMessage()))));
            }
            String j = " ";
            return j;
        } finally {
            if (sta != null) {
                sta.close();
            }
            if (rs != null) {
                rs.close();
            }
            util.close_connection();
        }
    }

    public static int compteMoisQuiPasse(Date dateInf, Date dateSup) throws Exception {
        int ret = 0;
        int moisInf = getMois(dateInf);
        int anneeInf = getAnnee(dateInf);
        int moisSup = getMois(dateSup);
        int anneeSup = getAnnee(dateSup);
        System.out.println("Mois Inf : " + moisInf);
        System.out.println("Mois sup : " + moisSup);
        int j = 0;

        if (dateInf.getTime() > dateSup.getTime()) {
            throw new Exception("La comparaison ne peut se faire");
        }
        for (int i = anneeInf; i <= anneeSup; i++) {
            if (i == anneeSup) {
                System.out.println("On est maintenant au meme moment");
                while (j <= moisSup) {
                    ret = ret + 1;
                    j++;
                }
            } else {
                for (j = moisInf; j <= 12; j++) {
                    ret = ret + 1;
                }
                j = 1;
            }
        }
        return ret;
    }

    public static String urlNew(String url, String mois, String annee) {
        String ret = null;
        char[] u = url.toCharArray();
        int c = 0;
        for (int i = 0; i < u.length; i++) {
            if (u[i] == '&' && c < 1) {
                u[i] = '?';
                c++;
            }
        }
        ret = new String(u);
        if (mois != null && annee != null) {
            ret = ret + "&mois=" + mois + "&annee=" + annee;
        }

        return ret;

    }

    public static java.sql.Timestamp convertStringToTimestamp(String stringDate) throws ParseException {
        DateFormat formatter;
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = (Date) formatter.parse(stringDate);
        java.sql.Timestamp timeStampDate = new java.sql.Timestamp(date.getTime());
        return timeStampDate;
    }

    public static String convertAnglaisToFrenchRubrique(String mot) throws Exception {
        String retour = null;
        try {
            retour = rubriqueFoot.get(mot);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return retour;
    }

    public static String getIdrubriqueStat(String mot) throws Exception {
        String retour = null;
        try {
            retour = idrubriqueFoot.get(mot);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return retour;
    }

    public static String limiterString(String value, int length) {
        if (value == null) {
            return "";
        }
        StringBuilder buf = new StringBuilder(value);
        if (buf.length() > length) {
            buf.setLength(length);
            buf.append("...");
        }

        return buf.toString();
    }

    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }

    public static int getDiffYears(Date first, Date last) {
        Calendar a = getCalendar(first);
        Calendar b = getCalendar(last);
        int diff = b.get(YEAR) - a.get(YEAR);
        if (a.get(MONTH) > b.get(MONTH)
                || (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
            diff--;
        }
        return diff;
    }

    public static int diffDeuxheures(String heuredebut, String heurefin) {
        int result = 0;

        String[] debut = split(heuredebut, ":");
        String[] fin = split(heurefin, ":");

        int hmsd = Utilitaire.stringToInt(debut[0]) * 3600;
        int mmsd = Utilitaire.stringToInt(debut[1]) * 60;

        int hmsf = Utilitaire.stringToInt(fin[0]) * 3600;
        int mmsf = Utilitaire.stringToInt(fin[1]) * 60;

        result = (hmsf + mmsf) - (hmsd + mmsd);
        return result;
    }

    public static String secondesToTime(int msecondes) {
        int secondes = 0;
        int minutes = 0;
        int heures = 0;

        secondes = msecondes % 60;
        msecondes = msecondes / 60;
        minutes = msecondes % 60;
        msecondes = msecondes / 60;
        heures = msecondes;
        return heures + ":" + minutes + ":" + secondes;
    }

    public static Integer[] secondesToTimeTab(int msecondes) {
        Integer[] time = new Integer[3];
        int secondes = 0;
        int minutes = 0;
        int heures = 0;

        secondes = msecondes % 60;
        msecondes = msecondes / 60;
        minutes = msecondes % 60;
        msecondes = msecondes / 60;
        heures = msecondes;

        time[0] = heures;
        time[1] = minutes;
        time[2] = secondes;
        return time;
    }

    public static String checkOperateur(String num) throws Exception {
        String retour = null;
        if (num.equals("paypal") || num.equals("carte")) {
            return num;
        }
        try {
            String num1 = num.replace(" ", "").replace("+", "");
            Pattern pattern261 = Pattern.compile("261 ?\\d{2} ?\\d{2} ?\\d{3} ?\\d{2}");
            Pattern pattern0 = Pattern.compile("0?\\d{2} ?\\d{2} ?\\d{3} ?\\d{2}");

            if (pattern261.matcher(num1).matches() || pattern0.matcher(num1).matches()) {
                int taille = num1.length();
                if (9 <= taille) {
                    int i = taille - 8;
                    char ref = num1.charAt(i);
                    if (ref == '2') {
                        retour = "orange";
                    }
                    if (ref == '3') {
                        retour = "airtel";
                    }
                    if (ref == '4') {
                        retour = "telma";
                    }
                }
            } else {
                throw new Exception("Erreur numero invalide pour : " + num);
            }
//            throw new Exception("Operateur inconnu");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return retour;

    }

    public static String formaterSansVirgule(double montant) {
        try {
            if (montant == 0) {
                return "0";
            }
            NumberFormat nf = NumberFormat.getInstance(Locale.FRENCH);
            //nf = new DecimalFormat("### ###,##");
            //nf.setMaximumFractionDigits(2);
            nf.setMinimumFractionDigits(0);
            String s = nf.format(montant);
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        String s1 = null;
        return s1;
    }

    public static String tabToString(String[] s, String quote, String virgule) {
        String res = "";
        try {
            res = quote + s[0] + quote;
            for (int i = 1; i < s.length; i++) {
                res = res + virgule + quote + s[i] + quote;
            }
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        return res;
    }
    public static boolean estChiffreOuLettre(char c) throws Exception
    {
        if(Character.isDigit(c)==true||Character.isLetter(c)==true)return true;
        return false;
    }
    public static String enleverCarSpec(String nom)throws Exception
    {
        //nom.replace("-", " ");
		if(nom!=null){

			nom.replaceAll("-", " ");
			nom.replaceAll("'", " ");
			char[] listeCar=nom.toCharArray();
			while(estChiffreOuLettre(listeCar[nom.length()-1])==false)
			{
				nom=nom.substring(0, nom.length()-1);
			}
		}
        return nom;
    }
    public static String tabToString(ClassMAPTable[] s, String quote, String virgule) {
        String res = "";
        try {
            res = quote + s[0].getTuppleID() + quote;
            for (int i = 1; i < s.length; i++) {
                res = res + virgule + quote + s[i].getTuppleID() + quote;
            }
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public static String verifNumerique(String s) {
        String res = s;
        res = res.replace(',', '.');

        try {
            String[] temp = split(res, ' ');
            res = "";
            for (int i = 0; i < temp.length; i++) {
                res += temp[i];
            }
            Float.valueOf(res);
            return res;
        } catch (Exception e) {
            return s;
        }

    }

    public static String convertDatyFormtoRealDatyFormat(String daty) {
        if (daty == null || daty.compareToIgnoreCase("") == 0) {
            return "";
        }
        String[] tableau = new String[3];
        tableau = split(daty, "-");
        String result = tableau[2] + "/" + tableau[1] + "/" + tableau[0];
        return result;
    }

    public static boolean isStringNumeric(String str) {
        if (str == null || str.compareTo("") == 0) {
            return false;
        }
        DecimalFormatSymbols currentLocaleSymbols = DecimalFormatSymbols.getInstance();
        char localeMinusSign = currentLocaleSymbols.getMinusSign();

        boolean isDecimalSeparatorFound = false;
        char localeDecimalSeparator = currentLocaleSymbols.getDecimalSeparator();
//        System.out.println("VALEUR : "+str);
        for (char c : str.substring(1).toCharArray()) {
            if (!Character.isDigit(c)) {
                if (c == localeDecimalSeparator && !isDecimalSeparatorFound && c != ' ') {
                    isDecimalSeparatorFound = true;
                    continue;
                }
                return false;
            }
        }
        return true;

    }

    public static String enleverEspaceDoubleBase(String montantBase) {
        String montant = "";
        for (int i = 0; i < montantBase.length(); ++i) {
            char c = montantBase.charAt(i);
            int j = (int) c;
            //System.out.println("ASCII value of " + c + " is " + j + ".");
            if (j != 160) {
                montant += c;
            }
        }
        return montant;
    }

    public static String champNull(String nul) {
        if (nul == null) {
            return "";
        } else if (nul.compareToIgnoreCase("null") == 0) {
            return "";
        } else if (nul.compareToIgnoreCase("") == 0) {
            return "";
        } else {
            return nul;
        }
    }

    public static java.sql.Date stringDate(String daty) {
        if (daty == null || daty.compareTo("") == 0) {
            return null;
        }
        java.sql.Date sqlDate = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date date = sdf.parse(daty);
            sqlDate = new Date(date.getTime());
        } catch (Exception e) {
            System.out.println("Error stringDate :" + e.getMessage());
        }
        return sqlDate;
    }

    public static boolean checkToken(String token, String tokenToCompare, long timeToken, long timeToCompare, long validity) {
        boolean res = false;
        if (token.compareTo(tokenToCompare) == 0) {
            if (timeToken < timeToCompare + validity) {
                res = true;

            } else {
                res = false;
            }
        } else {
            res = false;
        }
        return res;
    }

    public static java.util.Date convertToUtilDate(java.sql.Date daty, java.sql.Time temps) {
        java.util.Date dd = new java.util.Date(daty.getTime());
        dd.setHours(temps.getHours());
        dd.setMinutes(temps.getMinutes());
        dd.setSeconds(temps.getSeconds());
        return dd;
    }

    public static String[] getMailcc() throws Exception {
        String[] retour = null;
        try {
            List<String> temp = new ArrayList<String>();
            TypeObjet mail = new TypeObjet();
            mail.setNomTable("mailcc");
            TypeObjet[] tabMail = (TypeObjet[]) CGenUtil.rechercher(mail, null, null, "");
            if (tabMail.length > 0) {
                for (int i = 0; i < tabMail.length; i++) {
                    temp.add(tabMail[i].getVal());
                }
                retour = new String[temp.size()];
                for (int i = 0; i < temp.size(); i++) {
                    retour[i] = temp.get(i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return retour;
    }
    public static String[] getMailcc(Connection c) throws Exception {
        String[] retour = null;
        try {
            List<String> temp = new ArrayList<String>();
            TypeObjet mail = new TypeObjet();
            mail.setNomTable("mailcc");
            TypeObjet[] tabMail = (TypeObjet[]) CGenUtil.rechercher(mail, null, null,c, "");
            if (tabMail.length > 0) {
                for (int i = 0; i < tabMail.length; i++) {
                    temp.add(tabMail[i].getVal());
                }
                retour = new String[temp.size()];
                for (int i = 0; i < temp.size(); i++) {
                    retour[i] = temp.get(i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return retour;
    }

    public static Map<String, String> getVersionAssets() throws Exception {
        Map<String, String> retour = null;
        try {
            retour = new ConcurrentHashMap<String, String>();
            TypeObjet[] tab = datastatic.HeaderStatic.getAssetsVersion();
            if (tab.length > 0) {
                for (int i = 0; i < tab.length; i++) {
                    retour.put(tab[i].getVal(), tab[i].getDesce());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return retour;
    }

    public static boolean isWeekEnd(Date date)throws Exception{
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return Calendar.SATURDAY==dayOfWeek || Calendar.SUNDAY==dayOfWeek;
    }
    
    /*public String dateEtheureLivraison(Time heure, Date date, int checkProvince) throws Exception {
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        String dateLivraison = "";
        String heureLivraison = "";
        String retour = "";
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");
        //SI Heure_achat BETWEEN 00h00 AND 12h 
        String jour = simpleDateformat.format(date);
        int heure_liv = heure.getHours();
        //System.out.println("-------"+heure_liv+"----"+dayOfWeek);
        
        if (heure_liv < 13) { //inferieur a 16h
            if (checkProvince == 0) {
                if(dayOfWeek == 1){
                    dateLivraison = "" + Utilitaire.ajoutJourDateString(date, 1);
                    heureLivraison = "14h";
                }else if(dayOfWeek > 1 && dayOfWeek < 6){
                    if(dayOfWeek == 2 && heure_liv < 3){ //inferieur a 6h
                        dateLivraison = "" + Utilitaire.string_date("dd/MM/yyyy", formatterDaty(date));
                        heureLivraison = "14h";
                    }else{
                        dateLivraison = "" + Utilitaire.ajoutJourDateString(date, 1);
                        heureLivraison = "9h";
                    }
                }else if(dayOfWeek == 6){
                    dateLivraison = "" + Utilitaire.ajoutJourDateString(date, 1);
                    heureLivraison = "9h";
                }else if(dayOfWeek > 6){
                    dateLivraison = "" + Utilitaire.ajoutJourDateString(date, 2);
                    heureLivraison = "14h";
                }
            }else{
                if(dayOfWeek > 1 && dayOfWeek <= 3){
                    dateLivraison = "" + Utilitaire.ajoutJourDateString(date, 3);
                }else if(dayOfWeek > 3){
                    dateLivraison = "" + Utilitaire.ajoutJourDateString(date, 5);
                }
                heureLivraison = "9h";
            }
        }else{
            //System.out.println("checkProvince = "+checkProvince);
            if (checkProvince == 0) {
                if(dayOfWeek == 1){
                    dateLivraison = "" + Utilitaire.ajoutJourDateString(date, 1);
                    heureLivraison = "14h";
                }else if(dayOfWeek > 1 && dayOfWeek <= 5){
                    if(dayOfWeek == 2 && heure_liv < 3){ //inferieur a 6h
                        dateLivraison = "" + Utilitaire.string_date("dd/MM/yyyy", formatterDaty(date));
                        heureLivraison = "14h";
                    }else{
                        dateLivraison = "" + Utilitaire.ajoutJourDateString(date, 2);
                        heureLivraison = "9h";
                    }
                }else if(dayOfWeek == 6){
                    dateLivraison = "" + Utilitaire.ajoutJourDateString(date, 3);
                    heureLivraison = "14h";
                }else if(dayOfWeek == 7){
                    dateLivraison = "" + Utilitaire.ajoutJourDateString(date, 2);
                    heureLivraison = "14h";
                }
            }else{
                if(dayOfWeek > 1 && dayOfWeek <= 2){
                    dateLivraison = "" + Utilitaire.ajoutJourDateString(date, 4);
                }else if(dayOfWeek > 2){
                    dateLivraison = "" + Utilitaire.ajoutJourDateString(date, 6);
                }
                heureLivraison = "9h";
            }
        }
        retour = dateLivraison + " à " + heureLivraison;
        return retour;
    }*/

    public static int timeToMillisecond(java.sql.Time time) throws Exception {
        int retour = 0;
        try {
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
            timeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            java.util.Date date = timeFormat.parse(time.toString());
//            System.out.println(timeFormat.format(date));
            retour = (int) date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return retour;
    }

    public static int stringTimeToMillisecond(String time) throws Exception {
        int retour = 0;
        try {
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
            timeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            java.util.Date date = timeFormat.parse(time.toString());
//            System.out.println(timeFormat.format(date));
            retour = (int) date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return retour;
    }

    public static String millisecondToTimeString(int time) throws Exception {
        String retour = "00:00:00";
        try {
            Date date = new Date(time);
            DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            retour = formatter.format(date);
//            System.out.println("dateFormatted = " + dateFormatted);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return retour;
    }

    public static String replaceDateApresWhere(ClassMAPTable arg) throws Exception {
        String result = "";
        Field[] fields = arg.getFieldList();
        for (Field column : fields) {
            column.setAccessible(true);
            if (column.getType().equals(Date.class)) {
                if (column.get(arg) != null) {
                    result += " and " + column.getName() + " = '" + Utilitaire.formatterDaty((Date) column.get(arg)) + "'";
                    column.set(arg, null);
                }
            } else if (column.getType().equals(Time.class)) {
                if (column.get(arg) != null) {
                    result += " and " + column.getName() + " = '" + column.get(arg) + "'";
                    column.set(arg, null);
                }
            }
        }
        return result;
    }

    public static String doubleWithoutExponential(double val) {
        String vals = String.format("%.2f", val);
        String[] temp = vals.split(",");
        if (temp.length > 1 && temp[1].compareToIgnoreCase("00") == 0) {
            vals = temp[0];
        } else if (temp.length > 1 && temp[1].endsWith("0")) {
            vals = temp[0] + "," + temp[1].substring(0, 1);
        }
        return Utilitaire.replaceChar(vals, ",", ".");
    }

    public static String replaceChar(String text, String toReplace, String substitute) {
        //s = s.replace(''', '''');
        String ret = text.replace(toReplace.charAt(0), substitute.charAt(0));
        return ret;
    }

    public static double jetonAr(double prix) {
        double val = 100 * prix;
        return val;
    }
    
     public static double jetonAr(Double prix) {
        double val = 100 * prix;
        return val;
    }

    public static String jetonArString(double prix) {
        NumberFormat nf = NumberFormat.getInstance(Locale.FRENCH);
        nf.setMinimumFractionDigits(2);
        double val = Constante.jeton * prix;
        String s = nf.format(val);
        String ret;
        ret = s;

        return ret;
    }

    public static String replaceEspacePourcentage(String texte) throws Exception {
        String result = "";
        try {
            result = texte.replaceAll("%20", " ");
            System.out.println("replace: " + result);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }

    public static String returnMedia(String texte) throws Exception {
//        System.out.println("text : "+text);
        String result = "";
        texte = texte == null ? " " : texte;
//        System.out.println("texte = " + texte);
        String[] temp = texte.split(";");

        if (temp.length < 20) {
            int taille = 20 - temp.length;
            for (int i = 0; i < temp.length; i++) {
                result += temp[i]+";";
            }
            for (int i = 0; i < taille; i++) {
                result += " ;";
            }
        } else {
            for (int i = 0; i < 20; i++) {
                result += temp[i]+";";
            }
        }
        return result;
    }
    
    public static String formatDateRecherche(String date){
        String result = "";
        if(date.contains("/") & stringDate(date) != null){
            String[] split = date.split("/");
            result = split[2]+"-"+split[1]+"-"+split[0];
            return result;
        }else return date;
    }
    
    public static String escapeSQLString(String s) {
        if (s == null) {
            return null;
        }
        StringBuilder escapedText = new StringBuilder();
        char currentChar;
        for (int i = 0; i < s.length(); i++) {
            currentChar = s.charAt(i);
            switch (currentChar) {
                case '\'':
                    escapedText.append("''");
                    break;
                default:
                    escapedText.append(currentChar);
            }
        }
        return escapedText.toString();
    }
    
    public static int arTojeton(int prix) {
        int val = prix / 100;
        return val;
    }
    
    
    public static String replaceVirgule(String mot){
        try{
            mot = mot.replace(";", "','");
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
        return mot;
    }
    
    public static int getTailleString(String mot){
        int taille = 0;
        try{
            String[]temp = mot.split(";");
            taille = temp.length;
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
        return taille;
    }
    
    public static String getMilisecondeEnString(double temps,String separateur){
        String result = "";
        int heure = 0;
        int minute = 0;
        int seconde = 0;
        int milliseconde = 0;
        
        while(temps>=3600000 && temps-3600000>=0){
            heure++;
            temps = temps - 3600000;
        }
        while(temps>=60000 && temps-60000>=0){
            minute++;
            temps = temps - 60000;
        }
        while(temps>=1000 && temps-1000>=0){
            seconde++;
            temps = temps - 1000;
        }
        milliseconde = (int)temps;
       /* if(heure>0){
            result += chiffreToString(heure);
        }
        if(minute>0){
            if(heure>0) result += separateur;
            result += chiffreToString(minute);
        }
        if(seconde>0){
            if(minute>0) result += separateur;
            result += chiffreToString(seconde);
        }
        if(milliseconde>0){
            if(seconde>0) result += separateur;
            result += chiffreToString(milliseconde);
        }*/
            result += chiffreToString(heure);
        
            result += separateur;
            result += chiffreToString(minute);
       
      
            result += separateur;
            result += chiffreToString(seconde);
            
            result += separateur;
            result += chiffreToString(milliseconde);
       
        return result;
    }
    
    public static double getStringEnMiliseconde(String temps,String separateur){
        double result = 0;
        int heure = 0;
        int minute = 0;
        int seconde = 0;
        int milliseconde = 0;
        
        String[] nombres = temps.split(separateur);
        int taille = nombres.length;
        int isany = 1;
        for(int compteur=taille-1;compteur>=0;compteur--){
            if(isany==1){
                milliseconde = Integer.parseInt(nombres[compteur]);
                result += milliseconde;
            }
            if(isany==2){
                seconde = Integer.parseInt(nombres[compteur]);
                result += seconde*1000;
            }
            if(isany==3){
                minute = Integer.parseInt(nombres[compteur]);
                result += minute*60000;
            }
            if(isany==4){
                heure = Integer.parseInt(nombres[compteur]);
                result += heure*3600000;
            }
            isany++;
        }
        
        return result;
    }
    
    public static String getMilisecondeEnString(double temps,String separateurHeure, String separateurMinute, String separateurSeconde, String separateurMilliSeconde){
        String result = "";
        int heure = 0;
        int minute = 0;
        int seconde = 0;
        int milliseconde = 0;
        
        while(temps>=3600000 && temps-3600000>=0){
            heure++;
            temps = temps - 3600000;
        }
        while(temps>=60000 && temps-60000>=0){
            minute++;
            temps = temps - 60000;
        }
        while(temps>=1000 && temps-1000>=0){
            seconde++;
            temps = temps - 1000;
        }
        milliseconde = (int)temps;
        if(heure>0){
            result += chiffreToString(heure)+separateurHeure;
        }
        if(minute>0){
            result += chiffreToString(minute)+separateurMinute;
        }
        if(seconde>0){
            result += chiffreToString(seconde)+separateurSeconde;
        }
        if(milliseconde>0){
            result += chiffreToString(milliseconde)+separateurMilliSeconde;
        }
        return result;
    }
    
    public static double getStringEnMiliseconde(String temps,String separateurHeure, String separateurMinute, String separateurSeconde, String separateurMilliSeconde){
        double result = 0;
        int heure = 0;
        int minute = 0;
        int seconde = 0;
        int milliseconde = 0;
        
        String[] nombres = temps.split(separateurHeure);
        int taille = nombres.length;
        if(taille>0){
            if(estNombre(nombres[0])){
                heure = Integer.parseInt(nombres[0]);
                result += heure*3600000;
            }
            else temps = nombres[0];
            if(taille>1) temps = nombres[1];
        }
        nombres = temps.split(separateurMinute);
        taille = nombres.length;
        if(taille>0){
            if(estNombre(nombres[0])){
                minute = Integer.parseInt(nombres[0]);
                result += minute*60000;
            }
            else temps = nombres[0];
            if(taille>1) temps = nombres[1];
        }
        nombres = temps.split(separateurSeconde);
        taille = nombres.length;
        if(taille>0){
            if(estNombre(nombres[0])){
                seconde = Integer.parseInt(nombres[0]);
                result += seconde*1000;
            }
            else temps = nombres[0];
            if(taille>1) temps = nombres[1];          
        }
        nombres = temps.split(separateurMilliSeconde);
        taille = nombres.length;
        if(taille>0){
            if(estNombre(nombres[0])){
                milliseconde = Integer.parseInt(nombres[0]);
                result += milliseconde;
            }
        }
        
        return result;
    }
    
    public static double getStringEnMiliseconde(String temps) throws Exception{
        double result = 0;
        
        if(temps.contains(":")){
            result = getStringEnMiliseconde(temps,":");
        }
        else if(temps.contains("h") || temps.contains("m") || temps.contains("s") || temps.contains("ms")){
            result = getStringEnMiliseconde(temps,"h","m","s","ms");
        }
        else{
            throw new Exception("Format du temps non reconnu");
        }
        return result;
    }
    
    public static double getMilliSecondeEnSeconde(double tempsMs){
        double result = 0;
        result = tempsMs/1000;
        return result;
    }
    
    public static String chiffreToString(int nombre){
        String result = "";
        if(nombre<10){
            result = "0"+String.valueOf(nombre);
        }
        else{
            result = String.valueOf(nombre);
        }
        return result;
    }
    
    public static boolean estNombre(String s){
        boolean result = true;
        try{
            Integer.parseInt(s);
        }catch(Exception ex){
            try{
               Double.parseDouble(s);
            }catch(Exception exp){
                result = false;
            }
        }
        return result;
    }
    public static String transformeHeure(String heure) throws Exception {
        String[] str = heure.split(":");
        if (str == null || str.length < 2) {
            throw new Exception("Heure non valide.");
        } else {
            int hr = -1;
            int min = -1;
            try {
                hr = Integer.parseInt(str[0]);
                min = Integer.parseInt(str[1]);
            } catch (Exception e) {
                throw new Exception("Heure non valide.");
            }
            if (hr >= 24 || hr < 0 || min < 0 || min >= 60) {
                throw new Exception("Heure non valide.");
            }

            for (int i = 0; i < str.length; i++) {
                if (str[i].length() < 2) {
                    str[i] = "0" + str[i];
                }
            }
            heure = str[0] + ":" + str[1];
        }
        return heure;
    }
    

    public static String format(java.util.Date date) {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MMM/yyyy");
        String dateFormatted = fmt.format(date);
        return dateFormatted;
    }
    
    public static String dateDuJourSansSeparateur() {
        final java.util.Date currentTime = new java.util.Date();
        final SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
        sdf.setTimeZone(TimeZone.getTimeZone(gmt));
        return sdf.format(currentTime);

    }
    public static void main(String[] args) {
        System.out.println(" -- "+dateDuJourSansSeparateur());
    }
    
    public static String detecterBalise(String mot){
        String valiny=mot.replaceAll("\\<.*?>"," ");
      
        return valiny;
    }
    
    public static String[] stringToTab(String text, String separateur) {
        String temps = text.trim();
        String[] ret = split(temps, separateur.charAt(0));
        return ret;
    }
    
    public static String datetostring(java.sql.Date d) {
        String daty = null;
        SimpleDateFormat dateJava = new SimpleDateFormat("dd/MM/yyyy");
        daty = dateJava.format(d);
        return daty;
    }

    public static String datetostring(java.util.Date d) {
        String daty = null;
        SimpleDateFormat dateJava = new SimpleDateFormat("dd/MM/yyyy");
        daty = dateJava.format(d);
        return daty;
    }

    public static Date getDateAvant(Date d, int ajout) {
        GregorianCalendar c = new GregorianCalendar(d.getYear() + 1900, d.getMonth(), d.getDate());
        c.set(GregorianCalendar.DATE, c.get(GregorianCalendar.DATE) + ajout);
        java.util.Date dt = c.getTime();
        return new Date(dt.getTime());
    }

    public static boolean checkNumber(String requested) throws Exception {
        boolean numeric = true;
        try {
            Double num = Double.parseDouble(requested);
        } catch (NumberFormatException e) {
            numeric = false;
        }
        return numeric;
    }
    
    public static String getStringAC(String ac){
        String finals  = "";
        if(ac.indexOf("_")>0){
            String[] tab = ac.split("_");
            for(int i = 0;i<tab.length-1;i++){
                finals+=tab[i]+"_";
            }
        }
        return finals;
    }
    
    public static String escapeEmpty(String str){
        if(str == null) return null;
        str = str.trim();
        if(str.isEmpty()) return null;
        return str;
    }
    
    public static Timestamp convertStringToTimestamp(String val, String separator)throws Exception{
        String[] tab = val.split(separator);
        if(tab.length<2)throw new Exception("Format Timestamp invalide");
        tab[1] += ":00";
        return Timestamp.valueOf(tab[0]+" "+tab[1]);
    }

    public static String getUTCDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(new java.util.Date());
    }
    
    /* changement 01-08-2023 */
    private static String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex == -1 || dotIndex == 0 || dotIndex == fileName.length() - 1)
            throw new IllegalArgumentException("Invalid file name: " + fileName);
        return fileName.substring(dotIndex + 1).toLowerCase();
    }

    public static void resizeImage(String imagePath, String outputFolder, int targetWidth)throws Exception{
        File inputFile = new File(imagePath);
        String fileName= inputFile.getName();
        BufferedImage inputImage = ImageIO.read(inputFile);
        String extension = getFileExtension(fileName);
        // Resize the image
        int targetHeight = (int) (inputImage.getHeight() * ((double) targetWidth / inputImage.getWidth()));
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_3BYTE_BGR); //TYPE_INT_RGB
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(inputImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        // Compress and save the resized image
        File outputFile = new File(outputFolder + fileName);
        ImageIO.write(resizedImage, extension, outputFile);

        System.out.println("Image resized successfully.");
    }

    public static String uploadMedia(DiskFileItem item, ArrayList<String> imageList, String name, String nameImage, String chemin) throws Exception{
        WriteFileService wfs = new WriteFileService();
        java.util.Date actuelle = new java.util.Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String dat = dateFormat.format(actuelle);
        String nomupload = item.getName().replaceAll("[^\\p{ASCII}-/[^A-Za-z0-9.\\-]/]", "");
        String filename = new File(nomupload).getName();
        String extension = "";
        String[] filenamesplit = filename.split("\\.");
        if (filenamesplit.length <= 1) {
            throw new Exception("Le fichier n'a pas d'extension");
        } else {
            extension = filenamesplit[filenamesplit.length - 1];
        }
        String prename = getTheFirstLetters(filenamesplit[0], 10);
        name = dat + "-" + prename+"."+extension;
        nameImage += chemin + name;
        
//        item.write(new File(Constante.url_product_VV + name));
        item.write(new File(Constante.url_product_VV + name));
        String fullpath_upload = AnnonceImageRepositoryEnum.ORIGINAL.getLocalRepos()+name;
        String fullpath_minify = AnnonceImageRepositoryEnum.MINI.getLocalRepos()+name;
        String fullpath_card= AnnonceImageRepositoryEnum.CARD.getLocalRepos()+name;
        File imgFile = new File(fullpath_upload);
        if (item.getFieldName().startsWith("video")) {
            IContainer container = IContainer.make();
            int result = container.open(fullpath_upload, IContainer.Type.READ, null);

            if (result < 0) {
                throw new Exception("Impossible de charger votre video");
            }
            long ms = container.getDuration(); //micro seconde
            long secondeVideo = ms / 1000 / 1000;
            long minuteVideo = secondeVideo / 60;
            System.out.println("sizeVIdeo=" + container.getFileSize());
            long taillevideo = container.getFileSize();
            long sizeVideo = taillevideo / 1048576;
            System.out.println("sizeVideo relle = " + sizeVideo);
            if (sizeVideo > 75) {
                throw new Exception("Votre video est trop lourd , veuillez la compresser");
            }
            System.out.println("microVideo = " + ms + " secondeVideo = " + secondeVideo + " minuteVideo = " + minuteVideo);
            if (minuteVideo >= 1 && secondeVideo > 0) {
                throw new Exception("Votre video est trop longue");
            }
        } else {
           /* resizeImage(fullpath_card,AnnonceImageRepositoryEnum.CARD.getRemoteRepos(),AnnonceImageRepositoryEnum.CARD.getWidth());
            resizeImage(fullpath_minify,AnnonceImageRepositoryEnum.MINI.getRemoteRepos(),AnnonceImageRepositoryEnum.MINI.getWidth());
            wfs.uploadFileToFTPVV(AnnonceImageRepositoryEnum.CARD.getRemoteRepos()+name,name,"/card/");
            wfs.uploadFileToFTPVV(AnnonceImageRepositoryEnum.MINI.getRemoteRepos()+name,name,"/mini/");*/
            System.out.println("JAVA SYSTEM=" + System.getProperty("java.runtime.name"));
            String javaname = System.getProperty("java.runtime.name");

            if (!imageList.contains(extension)) {
                throw new Exception("Extension non prise en charge");
            }

            BufferedImage originalImage = ImageIO.read(imgFile);

            System.out.println("extension = " + extension);
            int type = originalImage.getType();
            if (type != 0) {
                if (javaname.contains("OpenJDK")) {
                    type = BufferedImage.TYPE_3BYTE_BGR;
                } else {
                    type = BufferedImage.TYPE_INT_ARGB;
                }
            }
            // int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
            BufferedImage resizeImageHintJpg = originalImage;//resizeImageWithHint(originalImage, type);
            ImageIO.write(resizeImageHintJpg, extension, new File(fullpath_minify));
            wfs.uploadImageToFTPVVAnnonce(fullpath_minify, name); //DECOMMENTENA ITY AVY EO
        }
        System.out.println("fullpath_uploa = " + fullpath_upload);
        wfs.uploadImageToFTPVVAnnonceOriginal(fullpath_upload, name); //DECOMMENTENA ITY AVY EO
        imgFile.delete();
        
        return nameImage;
    }
    public static String getTheFirstLetters(String word, int nbrOfLetters)throws Exception{
        if(word.length()<nbrOfLetters){
            return word;
        }
        return word.substring(0, nbrOfLetters);
    }
}
