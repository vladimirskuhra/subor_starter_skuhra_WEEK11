package data;

import gui.OsobyOkienko;

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


    public void uloz() throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("osoby.dat"))) {
            out.writeObject(this.osoby);  // Uloží celú databázu do súboru
        }
    }

    public void nacitaj() throws IOException {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("osoby.dat"))) {
            var data = (ArrayList<Osoba>)inputStream.readObject();
            this.osoby = data;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
