package DB;

import java.sql.SQLException;

import application.Model.DocumentType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DocumentTypeDB {
	public static ObservableList<DocumentType> getDocumentTypeList() throws SQLException {
		ObservableList<DocumentType> countryList = FXCollections.observableArrayList(
				new DocumentType(1, "ID Card"),
				new DocumentType(2, "Passport"),
				new DocumentType(3, "License")
				);
		return countryList;
	}
}
