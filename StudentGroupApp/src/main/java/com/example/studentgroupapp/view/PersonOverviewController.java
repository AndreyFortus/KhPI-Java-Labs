package com.example.studentgroupapp.view;
import com.example.studentgroupapp.MainApp;
import com.example.studentgroupapp.model.Person;
import com.example.studentgroupapp.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class PersonOverviewController {
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label groupCodeLabel;
    @FXML
    private Label birthdayLabel;
    //Посилання на головну програму
    private MainApp mainApp;

    /**
     * Конструктор.
     * Конструктор викликається раніше методом initialize().
     */
    public PersonOverviewController() {
    }

    /**
     * Ініціалізація класу-контролера. Цей метод викликається автоматично
     * після того, як файл fxml буде завантажений.
     */
    @FXML
    private void initialize() {
        // Ініціалізація таблиці адресатів із двома стовпцями.
        firstNameColumn.setCellValueFactory(
                cellData->cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(
                cellData->cellData.getValue().lastNameProperty());
        // Очищення додаткової інформації про студента.
        showPersonDetails(null);
        // Слухаємо зміни вибору і при зміні відображаємо додаткову інформацію про студента.

        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable,oldValue,newValue) ->showPersonDetails(newValue));
    }


    /**
     * Викликається головним додатком, який дає він посилання.
     *
     * @parammainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        // Додавання до таблиці даних із спостережуваного списку
        personTable.setItems(mainApp.getPersonData());
    }

    /**
     * Заповнює всі текстові поля, показуючи подробиці про студента.
     * Якщо вказаний студент = null, всі текстові поля очищаються.
     *
     * @paramperson – студент типу Person або null
     */
    private void showPersonDetails(Person person) {
        if (person != null) {
            // Заповнюємо позначки інформацією з об'єкта person.
            firstNameLabel.setText(person.getFirstName());
            lastNameLabel.setText(person.getLastName());
            groupCodeLabel.setText(String.valueOf(person.getGroupCode()));
            birthdayLabel.setText(DateUtil.format(person.getBirthday()));
            // birthdayLabel.setText(...);
        } else {
            // Якщо Person = null, то забираємо весь текст.
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            groupCodeLabel.setText("");
            birthdayLabel.setText("");
        }
    }

    @FXML
    private void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Person selectedPerson = personTable.getItems().get(selectedIndex);

            // Викликаємо метод підтвердження видалення з контролера редагування
            PersonEditDialogController editController = new PersonEditDialogController();
            if (editController.showDeleteConfirmationDialog()) {
                personTable.getItems().remove(selectedIndex);
            }
        } else {
            // Нічого не вибрано
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");
            alert.showAndWait();
        }
    }

    /**
     * Викликається, коли користувач клацає по кнопці New...
     * Відкриває діалогове вікно із додатковою інформацією нового студента.
     */
    @FXML
    private void handleNewPerson() {
        Person tempPerson=new Person();
        boolean okClicked=mainApp.showPersonEditDialog(tempPerson);
        if(okClicked) {
            mainApp.getPersonData().add(tempPerson);
        }
    }
    /**
     * Викликається, коли користувач клацне по кнопка Edit...
     * Відкриває діалогове вікно для зміни вибраного студента.
     */
    @FXML
    private void handleEditPerson() {
        Person
                selectedPerson=personTable.getSelectionModel().getSelectedItem();if(selectedPerson!=null) {
            boolean okClicked=mainApp.showPersonEditDialog(selectedPerson);
            if(okClicked) {
                showPersonDetails(selectedPerson);
            }
        }else{
            // Нічого не вибрано.
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");
            alert.showAndWait();
        }
    }
}
