package com.example.viroviticomnevidljivimkoracima;


import android.net.Uri;

public class Prilagodba {
    String idPrilagodbe;
    String opisPrilagodbe;
    String tekstPrilagodbe;

    public String getIdPrilagodbe() {
        return idPrilagodbe;
    }

    public String getOpisPrilagodbe() {
        return opisPrilagodbe;
    }

    public String getTekstPrilagodbe() {
        return tekstPrilagodbe;
    }

    public void setIdPrilagodbe(String idPrilagodbe) {
        this.idPrilagodbe = idPrilagodbe;
    }

    public void setOpisPrilagodbe(String opisPrilagodbe) {
        this.opisPrilagodbe = opisPrilagodbe;
    }

    public void setTekstPrilagodbe(String tekstPrilagodbe) {
        this.tekstPrilagodbe = tekstPrilagodbe;
    }

    @Override
    public String toString() {
        return opisPrilagodbe;
    }
}
