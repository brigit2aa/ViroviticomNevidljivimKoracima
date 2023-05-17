package com.example.viroviticomnevidljivimkoracima;

public class Ustanova {

    String adresa, email, naziv, telefon, web, idPotkategorije, dodatnaInformacija, dodatnaInformacijaSlika, karta, opisPraga, opisPragaSlika, opisUlaza, opisUlazaSlika, pristupacnost, sirinaVrata, sirinaVrataSlika, sirinaVrataUnutra, sirinaVrataUnutraSlika, slikaUlaza, idVrata, vrstaVrataSlika, nazivVrata;

    public Ustanova(String adresa, String email, String naziv, String telefon, String web, String dodatnaInformacija, String karta, String opisPraga, String opisUlaza, String sirinaVrata, String sirinaVrataUnutra) {
        this.adresa = adresa;
        this.email = email;
        this.naziv = naziv;
        this.telefon = telefon;
        this.web = web;
        //this.idPotkategorije = idPotkategorije;
        this.dodatnaInformacija = dodatnaInformacija;
        this.karta = karta;
        this.opisPraga = opisPraga;
        this.opisUlaza = opisUlaza;
        this.sirinaVrata = sirinaVrata;
        this.sirinaVrataUnutra = sirinaVrataUnutra;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getEmail() {
        return email;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getWeb() {
        return web;
    }

    public String getIdPotkategorije() {
        return idPotkategorije;
    }

    public String getDodatnaInformacija() {
        return dodatnaInformacija;
    }

    public String getDodatnaInformacijaSlika() {
        return dodatnaInformacijaSlika;
    }

    public String getKarta() {
        return karta;
    }

    public String getOpisPraga() {
        return opisPraga;
    }

    public String getOpisPragaSlika() {
        return opisPragaSlika;
    }

    public String getOpisUlaza() {
        return opisUlaza;
    }

    public String getOpisUlazaSlika() {
        return opisUlazaSlika;
    }

    public String getPristupacnost() {
        return pristupacnost;
    }

    public String getSirinaVrata() {
        return sirinaVrata;
    }

    public String getSirinaVrataSlika() {
        return sirinaVrataSlika;
    }

    public String getSirinaVrataUnutra() {
        return sirinaVrataUnutra;
    }

    public String getSirinaVrataUnutraSlika() {
        return sirinaVrataUnutraSlika;
    }

    public String getSlikaUlaza() {
        return slikaUlaza;
    }

    public String getIdVrata() {
        return idVrata;
    }

    public String getVrstaVrataSlika() {
        return vrstaVrataSlika;
    }



}
