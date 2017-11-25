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
public class PretZbrojeniowy {
    
    private int srednica;
    private String klasaStali;

    /**
     *
     * @param srednica
     * @param klasaStali
     */
    public PretZbrojeniowy(int srednica, String klasaStali) {
        this.srednica = srednica;
        this.klasaStali = klasaStali;
    }

    public int getSrednica() {
        return srednica;
    }

    public String getKlasaStali() {
        return klasaStali;
    }

    /**
     *
     * @return Granica plastycznosci stali fyd, MPa
     */
    public int getGranicaPlastycznosci_fyd() {
        int fyd = 0;
        if (klasaStali.equals("A-0")) {
            fyd = 190;    
        }else if (klasaStali.equals("A-I")) {
            fyd = 210;
        }else if (klasaStali.equals("A-II")) {
            fyd = 310;
        }else if (klasaStali.equals("A-III")) {
            fyd = 350;
        }else if (klasaStali.equals("A-IIIN")) {
            fyd = 420;
        } 
        return fyd;
    }
    
    
    
    
}
