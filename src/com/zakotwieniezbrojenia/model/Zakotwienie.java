/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zakotwieniezbrojenia.model;

/**
 *
 * @author emilz
 */
public class Zakotwienie {
    
    private boolean czyDobreWarunkiPrzyczepnosci;
    private boolean czyZebrowanyPret;
    private boolean czyObciazenieWielokrotnieZmienne;
    private boolean czyPretProsty;
    private boolean czyRozciagany;
    private boolean czyPretyUkladaneParami;
    private double f_ck;
    private int srednicaPreta;
    private int f_yd;
    private double przekrojZbrojeniaPrzyjetego_As_prov;
    private double przekrojZbrojeniaWymaganego_As_req;

    /**
     *
     * @param czyDobreWarunkiPrzyczepnosci
     * @param czyZebrowanyPret
     * @param czyObciazenieWielokrotnieZmienne
     * @param czyPretProsty
     * @param czyRozciagany
     * @param czyPretyUkladaneParami
     * @param f_ck [MPa]
     * @param srednicaPreta [mm]
     * @param f_yd [MPa]
     * @param przekrojZbrojeniaPrzyjetego_As_prov [cm]
     * @param przekrojZbrojeniaWymaganego_As_req [cm]
     */
    public Zakotwienie( 
            boolean czyDobreWarunkiPrzyczepnosci, 
            boolean czyZebrowanyPret, 
            boolean czyObciazenieWielokrotnieZmienne, 
            boolean czyPretProsty, 
            boolean czyRozciagany,
            boolean czyPretyUkladaneParami, 
            double f_ck, 
            int srednicaPreta, 
            int f_yd, 
            double przekrojZbrojeniaPrzyjetego_As_prov, 
            double przekrojZbrojeniaWymaganego_As_req) {
        this.czyDobreWarunkiPrzyczepnosci = czyDobreWarunkiPrzyczepnosci;
        this.czyZebrowanyPret = czyZebrowanyPret;
        this.czyObciazenieWielokrotnieZmienne = czyObciazenieWielokrotnieZmienne;
        this.czyPretProsty = czyPretProsty;
        this.czyRozciagany = czyRozciagany;
        this.czyPretyUkladaneParami = czyPretyUkladaneParami;
        this.f_ck = f_ck;
        this.srednicaPreta = srednicaPreta;
        this.f_yd = f_yd;
        this.przekrojZbrojeniaPrzyjetego_As_prov = przekrojZbrojeniaPrzyjetego_As_prov;
        this.przekrojZbrojeniaWymaganego_As_req = przekrojZbrojeniaWymaganego_As_req;
    }
 
    /**
     *
     * @return f_bd [MPa]
     */
    public double przyczepnoscObliczeniowa_f_bd() {
        double f_bd = 0;
        double gamma_c = 1.5;  //czesciowy wspolczynnik bezpieczenstwa dla betonu

        if (!czyZebrowanyPret) {
            //prety gladkie
            f_bd = (0.36 * Math.sqrt(f_ck)) / gamma_c;
        } else if (czyZebrowanyPret) {
            //prety zebrowane
            f_bd = 0.47 * Math.cbrt(Math.pow(f_ck, 2)) / gamma_c;
        }

        if (czyDobreWarunkiPrzyczepnosci) {
            f_bd = f_bd;
        } else if (!czyDobreWarunkiPrzyczepnosci) {
            f_bd = f_bd * 0.7;
        }
        
        if (srednicaPreta > 32) {
           f_bd = f_bd * (132 - srednicaPreta) / 100;
        }
        
        return f_bd;
    }

    /**
     *
     * @return lb [mm]
     */
    public double podstawowaDlugoscZakotwienia_lb() {
        double lb;
        double f_bd = przyczepnoscObliczeniowa_f_bd();
        if (!czyPretyUkladaneParami) {
            lb = (srednicaPreta / 4) * f_yd / f_bd;
        } else {
            lb = (srednicaPreta * Math.sqrt(2) / 4) * f_yd / f_bd;
        }

        if (czyObciazenieWielokrotnieZmienne) {
            lb = lb + lb * 0.5;
        }

        return lb; 
    }
    
    /**
     *
     * @return alfa_A
     */
    public double alfa_A() {
        double alfa_A;
        if (czyPretProsty) {
            alfa_A = 1;
        } else {
            alfa_A = 0.7;
        }
        return alfa_A;
    }

    /**
     *
     * @return lb_min [mm]
     */
    public double minimalnaDlugoscZakotwienia_lb_min() {
        double lb_min;
        double lb = podstawowaDlugoscZakotwienia_lb();
        if (czyRozciagany) {
            lb_min = 0.3 * lb;
            if (lb_min < 10 * srednicaPreta) {
                lb_min = 10 * srednicaPreta;
                if (10 * srednicaPreta < 100) {
                    lb_min = 100;
                }
            }
        } else {
            lb_min = 0.6 * lb;
            if (lb_min < 10 * srednicaPreta) {
                lb_min = 10 * srednicaPreta;
                if (10 * srednicaPreta < 100) {
                    lb_min = 100;
                }
            }
        }
        return lb_min;
    }
    
    /**
     *
     * @return l_bd [mm]
     */
    public double obliczeniowaDlugoscZakotwienia_l_bd() {
        double l_bd;
        double alfa_A = alfa_A();
        double l_b = podstawowaDlugoscZakotwienia_lb();
        double lb_min = minimalnaDlugoscZakotwienia_lb_min();

        l_bd = alfa_A * l_b * przekrojZbrojeniaWymaganego_As_req / przekrojZbrojeniaPrzyjetego_As_prov;
        if (l_bd < lb_min) {
            l_bd = lb_min;
        }
        return l_bd;
    }

    
    
}
