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
public class Beton {
    
    private String klasa;

    public Beton(String klasa) {
        this.klasa = klasa;
    }

    /**
     *
     * @return Klasa betonu np. 15MPa
     */
    public int getKlasa() {
        int wytrzymaloscNaSciskanie_fck = 0;
        
        if (klasa.equals("B15")) {
           wytrzymaloscNaSciskanie_fck = 12; 
        }else if (klasa.equals("B20")) {
            wytrzymaloscNaSciskanie_fck = 16;
        }else if (klasa.equals("B25")) {
            wytrzymaloscNaSciskanie_fck = 20;
        }else if (klasa.equals("B30")) {
            wytrzymaloscNaSciskanie_fck = 25;
        }else if (klasa.equals("B37")) {
            wytrzymaloscNaSciskanie_fck = 30;
        }else if (klasa.equals("B45")) {
            wytrzymaloscNaSciskanie_fck = 35;
        }else if (klasa.equals("B50")) {
            wytrzymaloscNaSciskanie_fck = 40;
        }else if (klasa.equals("B55")) {
            wytrzymaloscNaSciskanie_fck = 45;
        }else if (klasa.equals("B60")) {
            wytrzymaloscNaSciskanie_fck = 50;
        }
        return wytrzymaloscNaSciskanie_fck;
    } 
}
