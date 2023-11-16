package com.example.studentgroupapp.view;
import com.example.studentgroupapp.model.Person;
import com.example.studentgroupapp.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Optional;

public class PersonEditDialogController {
    /**
     * Вікно для зміни інформації про студента.
     */
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField groupCodeField;
    @FXML
    private TextField birthdayField;
    private Stage dialogStage;
    private Person person;
    private boolean okClicked=false;
    /**
     * Ініціалізує клас-контролер. Цей метод викликається автоматично
     * після того, як файл fxml буде завантажений.
     */
    @FXML
    private void initialize() {
    }
    /**
     * Встановлює сцену цього вікна.
     *
     * @paramdialogStage
     */
    public void setDialogStage(Stage dialogStage) {this.dialogStage=dialogStage;
    }
    /**
     * Задає адресата, інформацію про який змінюватимемо.
     *
     * @paramperson
     */
    public void setPerson(Person person) {
        this.person=person;
        firstNameField.setText(person.getFirstName());
        lastNameField.setText(person.getLastName());
        groupCodeField.setText(String.valueOf(person.getGroupCode()));
        birthdayField.setText(DateUtil.format(person.getBirthday()));
        birthdayField.setPromptText("dd.mm.yyyy");
    }
    /**
     * Returns true, якщо користувач клацнув OK, інакше false.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }
    /**
     * Викликається, коли користувач клацнув по кнопці OK.
     */
    @FXML
    private void handleOk() {
        if(isInputValid()) {
            person.setFirstName(firstNameField.getText());
            person.setLastName(lastNameField.getText());
            person.setGroupCode(Integer.parseInt(groupCodeField.getText()));
            person.setBirthday(DateUtil.parse(birthdayField.getText()));
            okClicked=true;
            dialogStage.close();
        }
    }
    /**
     * Викликається, коли користувач клацнув по кнопці Cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
    /**
     * Перевіряє введення тексту в текстових полях.
     *
     * @returntrue, якщо введення користувача коректне
     */
    private boolean isInputValid() {
        String errorMessage="";
        if(firstNameField.getText() ==null|| firstNameField.getText().isEmpty()) {
            errorMessage+="No valid first name!\n";
        }
        if(lastNameField.getText() ==null|| lastNameField.getText().isEmpty())
        { errorMessage+="No valid last name!\n";
        }
        if(groupCodeField.getText() ==null|| groupCodeField.getText().isEmpty()) {
            errorMessage+="No valid postal code!\n";
        }else{
// намагаємося перетворити номер групи на int.
            try{
                Integer.parseInt(groupCodeField.getText());
            }catch(NumberFormatException e) {
                errorMessage+="No valid group code (must be an integer)!\n";
            }
        }
        if(birthdayField.getText() ==null|| birthdayField.getText().isEmpty())
        { errorMessage+="No valid birthday!\n";
        }else{
            if(!DateUtil.validDate(birthdayField.getText())) {
                errorMessage+="No valid birthday. Use the format dd.mm.yyyy!\n";
            }
        }
        if(errorMessage.isEmpty()) {
            return true;
        }else{
// Показуємо повідомлення про помилку.
            Alert alert=new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }

    // запит підтвердження видалення.
    public boolean showDeleteConfirmationDialog() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.initOwner(dialogStage);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Delete Confirmation");
        alert.setContentText("Are you sure you want to delete this entry?");

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }
}
