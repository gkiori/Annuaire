module fr.isika.cda15.projet1.annuaire {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;
	requires org.controlsfx.controls;
	requires javafx.base;
	requires itextpdf;

    opens fr.isika.cda15.projet1.annuaire to javafx.fxml;
    exports fr.isika.cda15.projet1.annuaire;
}
