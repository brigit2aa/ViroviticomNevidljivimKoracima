package com.example.viroviticomnevidljivimkoracima;

public class OpisPraga {
    String idPraga, opisPraga;


    public String getIdPraga() {
        return idPraga;
    }

    public String getOpisPraga() {
        return opisPraga;
    }

    public void setIdPraga(String idPraga) {
        this.idPraga = idPraga;
    }

    public void setOpisPraga(String opisPraga) {
        this.opisPraga = opisPraga;
    }


    @Override
    public String toString() {
        return opisPraga;
    }
}
