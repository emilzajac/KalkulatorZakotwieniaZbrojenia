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
public class Zaklad {
    
   private boolean wprzekrojuLaczanychMniejNiz30ProcentPretow;
   private double parametr_a;
   private double parametr_b;
   private boolean czyRozciagany;
   private double srednicaPreta;

    /**
     *
     * @param wprzekrojuLaczanychMniejNiz30ProcentPretow
     * @param parametr_a [cm]
     * @param parametr_b [cm]
     * @param czyRozciagany
     * @param srednicaPreta [cm]
     */
    public Zaklad(boolean wprzekrojuLaczanychMniejNiz30ProcentPretow, double parametr_a, double parametr_b, boolean czyRozciagany, double srednicaPreta) {
        this.wprzekrojuLaczanychMniejNiz30ProcentPretow = wprzekrojuLaczanychMniejNiz30ProcentPretow;
        this.parametr_a = parametr_a;
        this.parametr_b = parametr_b;
        this.czyRozciagany = czyRozciagany;
        this.srednicaPreta = srednicaPreta;
    }

    public double alfa_1(){
       double parametrAlfa_1 = 0;
       
       boolean czyParametr_a_WiekszyRownyOd10Srednic;
       boolean czyParametr_b_WiekszyRownyOd5Srednic;
       
       czyParametr_a_WiekszyRownyOd10Srednic = parametr_a >= 10 * srednicaPreta;
       czyParametr_b_WiekszyRownyOd5Srednic = parametr_b >= 5 * srednicaPreta;
       
       if (!czyRozciagany) {
           parametrAlfa_1 = 1.0;
       }else if (czyRozciagany &
               wprzekrojuLaczanychMniejNiz30ProcentPretow & 
               czyParametr_a_WiekszyRownyOd10Srednic & 
               czyParametr_b_WiekszyRownyOd5Srednic) 
       {
           parametrAlfa_1 = 1.0;
       }else if ((czyRozciagany &
               !wprzekrojuLaczanychMniejNiz30ProcentPretow) || 
               !czyParametr_a_WiekszyRownyOd10Srednic || 
               !czyParametr_b_WiekszyRownyOd5Srednic) 
       {
           parametrAlfa_1 = 1.4;
       }else if ((czyRozciagany
                & !wprzekrojuLaczanychMniejNiz30ProcentPretow)
                & (!czyParametr_a_WiekszyRownyOd10Srednic
                || !czyParametr_b_WiekszyRownyOd5Srednic)) {
            parametrAlfa_1 = 2.0;
        }
       
       return parametrAlfa_1;
   }
   
    /**
     *
     * @param obliczeniowaDlugoscZakotwienia_l_bd [mm]
     * @param podstawowaDlugoscZakotwienia_lb [mm]
     * @param alfa_A
     * @return ls [mm]
     */
   public double dlugoscZakladu_ls(double obliczeniowaDlugoscZakotwienia_l_bd, 
           double podstawowaDlugoscZakotwienia_lb, double alfa_A){
      double ls;
      double ls_min;
      
      ls_min = 0.3 * alfa_A * alfa_1() * podstawowaDlugoscZakotwienia_lb;
      
       if (ls_min <= 200) {
           ls_min = 200;
       }
      
      ls = obliczeniowaDlugoscZakotwienia_l_bd * alfa_1();
      
       if (ls < ls_min) {
           ls = ls_min;
       }
      
      return ls;
   }
    
}
