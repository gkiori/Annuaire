module fr.isika.cda15.projet1.annuaire {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;

    opens fr.isika.cda15.projet1.annuaire to javafx.fxml;
    exports fr.isika.cda15.projet1.annuaire;
}
