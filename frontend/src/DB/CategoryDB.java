package DB;

import java.sql.SQLException;

import application.Model.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CategoryDB {
	public static ObservableList<Category> getCategoryList() throws SQLException {
		ObservableList<Category> categoryList = FXCollections.observableArrayList(
				new Category(1, "Femenino"),
				new Category(2, "Masculino")			
				);
		return categoryList;
	}
	
}
