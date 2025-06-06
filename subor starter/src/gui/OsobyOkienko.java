package gui;

import data.Databaza;
import data.Osoba;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class OsobyOkienko {
    private final DefaultListModel<Osoba> model;
    private JFrame okno;
    private JList osobyList;
    private JPanel panel1;
    private JButton NovyBtn;
    private JButton ZmazatBtn;
    private JButton UlozitBtn;
    private JButton NacitatBtn;
    private Databaza db;

    public OsobyOkienko(Databaza db) {
        this.db = db;


        this.okno = new JFrame("OSOBEEE");
        this.okno.setContentPane(this.panel1);
        this.okno.pack();
        this.okno.setVisible(true);
        this.okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.model = new DefaultListModel<Osoba>();
        this.osobyList.setModel(this.model);
        this.nacitajDataZDb();

        this.osobyList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    Osoba s = (Osoba)OsobyOkienko.this.osobyList.getSelectedValue();
                    var d = new OsobaEditOkienko(OsobyOkienko.this.okno, s);

                    OsobyOkienko.this.osobyList.revalidate();
                }
            }
        });

        this.NovyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Osoba no = new Osoba();
                var d = new OsobaEditOkienko(OsobyOkienko.this.okno, no);

                if (d.getUlozit()) {
                    OsobyOkienko.this.db.pridajOsobu(no);
                    OsobyOkienko.this.model.addElement(no);
                }

                OsobyOkienko.this.osobyList.revalidate();
            }
        });

        this.ZmazatBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Osoba s = (Osoba)OsobyOkienko.this.osobyList.getSelectedValue();
                OsobyOkienko.this.model.removeElement(s);
                OsobyOkienko.this.db.zmazOsobu(s);

                OsobyOkienko.this.osobyList.revalidate();
            }
        });


        UlozitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    OsobyOkienko .this.db.uloz();
                    JOptionPane.showMessageDialog(OsobyOkienko.this.okno, "Ulozenie sa podarilo");
                } catch (Exception z) {
                    JOptionPane.showMessageDialog(OsobyOkienko.this.okno, "Ulozenie sa nepodarilo");
                }
            }
        });
        NacitatBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    OsobyOkienko.this.db.nacitaj();
                    OsobyOkienko.this.model.clear();
                    OsobyOkienko.this.nacitajDataZDb();
                } catch ( IOException ex) {
                    JOptionPane.showMessageDialog(OsobyOkienko.this.okno,"Nepodarilo sa nacitat subor");
                }

            }
        });
    }

    public void nacitajDataZDb() {
        for (Osoba o : this.db) {
            this.model.addElement(o);
        }

    }
}
