package com.example.viroviticomnevidljivimkoracima;

public class VrstaVrata {
    String idVrata, nazivVrata;

    public String getIdVrata() {
        return idVrata;
    }

    public String getNazivVrata() {
        return nazivVrata;
    }

    public void setIdVrata(String idVrata) {
        this.idVrata = idVrata;
    }

    public void setNazivVrata(String nazivVrata) {
        this.nazivVrata = nazivVrata;
    }

    @Override
    public String toString() {
        return idVrata;
    }
}
