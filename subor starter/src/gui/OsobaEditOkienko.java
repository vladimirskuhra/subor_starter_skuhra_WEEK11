package gui;

import data.Osoba;

import javax.swing.*;
import java.awt.*;

public class OsobaEditOkienko {
    private JTextField textFieldMeno;
    private JTextField textFieldPriez;
    private JTextField textFieldVek;
    private JButton CANCEL;
    private JButton SAVEButton;
    private JPanel MainPanel;
    private Osoba osoba;

    public Boolean getUlozit() {
        return this.ulozit;
    }

    private Boolean ulozit = false;

    public OsobaEditOkienko(JFrame parent, Osoba osoba) {
        this.osoba = osoba;
        var d = new JDialog(parent, Dialog.ModalityType.APPLICATION_MODAL);
        d.setContentPane(this.MainPanel);
        d.pack();

        this.textFieldMeno.setText(this.osoba.getMeno());
        this.textFieldPriez.setText(this.osoba.getPriezvisko());
        this.textFieldVek.setText(String.valueOf(this.osoba.getVek()));

        this.CANCEL.addActionListener( e -> {
            d.setVisible(false);
            d.dispose();
        });

        this.SAVEButton.addActionListener( e -> {
            // zmena sa uklada jedine ak dojde na stlacenie tlacidla ULOZIT
            this.osoba.setMeno(this.textFieldMeno.getText());
            this.osoba.setPriezvisko(this.textFieldPriez.getText());
            this.osoba.setVek(Integer.parseInt(this.textFieldVek.getText()));
            this.ulozit = true;
            d.setVisible(false);
            d.dispose();
        });

        d.setVisible(true);
    }
}
