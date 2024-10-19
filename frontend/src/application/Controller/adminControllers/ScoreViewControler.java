package application.Controller.adminControllers;


import application.Model.Score;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;

public class ScoreViewControler {

    @FXML
    private TableView<Score> scoreTableView; // Score es un objeto personalizado (ver más abajo)
    @FXML
    private TableColumn<Score, Integer> scoreidColumn;
    @FXML
    private TableColumn<Score, String> eventColumn;
    @FXML
    private TableColumn<Score, String> teamColumn;
    @FXML
    private TableColumn<Score, Integer> olympicYearColumn;
    @FXML
    private TableColumn<Score, String> medalColumn;

    // Lista para almacenar los datos de la tabla
    private ObservableList<Score> scoreList;

    @FXML
    public void initialize() {
        // Configurar columnas con el objeto Score
        scoreidColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        eventColumn.setCellValueFactory(new PropertyValueFactory<>("event"));
        teamColumn.setCellValueFactory(new PropertyValueFactory<>("team"));
        olympicYearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        medalColumn.setCellValueFactory(new PropertyValueFactory<>("medal"));

        // Inicializar la lista con algunos datos de ejemplo
        /*scoreList = FXCollections.observableArrayList(
            new Score(1, new Event(""), "Team USA", 2020, "Gold"),
            new Score(2, "200m Sprint", "Team Jamaica", 2020, "Silver")
        );*/

        // Añadir la lista a la tabla
        scoreTableView.setItems(scoreList);
    }

    @FXML
    private void addItem(ActionEvent event) {
        // Añadir un nuevo ítem a la tabla (aquí se puede abrir un formulario si es necesario)
        //scoreList.add(new Score(3, "400m Relay", "Team Canada", 2020, "Bronze"));
    }

    @FXML
    private void editItem(ActionEvent event) {
        // Editar el ítem seleccionado en la tabla
        Score selectedScore = scoreTableView.getSelectionModel().getSelectedItem();
        if (selectedScore != null) {
            // Aquí puedes abrir un diálogo para editar, por ahora solo cambiaremos el evento
            //selectedScore.setEvent("Edited Event");
            scoreTableView.refresh(); // Refrescar la tabla para mostrar cambios
        } else {
            showAlert("Error", "No item selected", AlertType.ERROR);
        }
    }

    @FXML
    private void deleteItem(ActionEvent event) {
        // Eliminar el ítem seleccionado
        Score selectedScore = scoreTableView.getSelectionModel().getSelectedItem();
        if (selectedScore != null) {
            scoreList.remove(selectedScore);
        } else {
            showAlert("Error", "No item selected", AlertType.ERROR);
        }
    }

    // Método para mostrar alertas
    private void showAlert(String title, String message, AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait().filter(response -> response == ButtonType.OK);
    }
}


