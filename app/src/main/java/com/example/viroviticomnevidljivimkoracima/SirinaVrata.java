package com.example.viroviticomnevidljivimkoracima;

public class SirinaVrata {
    String idSirinaVrata, sirinaVrataCm;

    public String getIdSirinaVrata() {
        return idSirinaVrata;
    }

    public String getSirinaVrataCm() {
        return sirinaVrataCm;
    }

    public void setIdSirinaVrata(String idSirinaVrata) {
        this.idSirinaVrata = idSirinaVrata;
    }

    public void setSirinaVrataCm(String sirinaVrataCm) {
        this.sirinaVrataCm = sirinaVrataCm;
    }

    @Override
    public String toString() {
        return sirinaVrataCm;
    }
}
