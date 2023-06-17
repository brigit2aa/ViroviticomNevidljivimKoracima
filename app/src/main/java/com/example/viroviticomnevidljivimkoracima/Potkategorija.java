package com.example.viroviticomnevidljivimkoracima;

public class Potkategorija {
    String idKategorije;
    String getIdKategorije;
    String nazivPotkategorije;
    String idPotkategorije;

    public String getIdKategorije() {
        return idKategorije;
    }

    public String getGetIdKategorije() {
        return getIdKategorije;
    }

    public String getNazivPotkategorije() {
        return nazivPotkategorije;
    }

    public String getIdPotkategorije() {
        return idPotkategorije;
    }

    public void setIdKategorije(String idKategorije) {
        this.idKategorije = idKategorije;
    }

    public void setGetIdKategorije(String getIdKategorije) {
        this.getIdKategorije = getIdKategorije;
    }

    public void setNazivPotkategorije(String nazivPotkategorije) {
        this.nazivPotkategorije = nazivPotkategorije;
    }

    public void setIdPotkategorije(String idPotkategorije) {
        this.idPotkategorije = idPotkategorije;
    }

    @Override
    public String toString() {
        return  idPotkategorije;
    }
}
