
import data.Databaza;
import gui.OsobyOkienko;
public class Main {
    public static void main(String[] args) {

        var db = new Databaza();
        db.vygenerujOsoby();

        var ook = new OsobyOkienko(db);

    }
}