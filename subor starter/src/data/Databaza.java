package data;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Databaza implements Iterable<Osoba> {
    private ArrayList<Osoba> osoby;



    public Databaza() {
        this.osoby = new ArrayList<>();
    }

    @Override
    public Iterator iterator() {
        return this.osoby.iterator();
    }

    public void pridajOsobu(Osoba osoba) {
        this.osoby.add(osoba);
    }

    public void zmazOsobu(Osoba osoba) {
        this.osoby.remove(osoba);
    }

    public void vygenerujOsoby() {
        this.osoby.add(new Osoba("Janko", "aaaa", 2));
        this.osoby.add(new Osoba("Janko", "aaaa", 3));
        this.osoby.add(new Osoba("Janko", "aaaa", 4));
    }

}
