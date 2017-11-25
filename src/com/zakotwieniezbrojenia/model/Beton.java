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
        int klasaInt = Integer.valueOf(klasa.substring(1));
        return klasaInt;
    }
    
    
    
}
