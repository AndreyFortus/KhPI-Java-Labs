package com.example.studentgroupapp;
import com.example.studentgroupapp.model.Person;
import com.example.studentgroupapp.view.PersonEditDialogController;
import com.example.studentgroupapp.view.PersonOverviewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class MainApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    /**
     * Дані, у вигляді списку адресатів, що спостерігається.
     */
    private ObservableList<Person> personData = FXCollections.observableArrayList();
    /**
     * Конструктор
     */
    public MainApp(){
        personData.add(new Person("Петро","П'яточкін", 221, LocalDate.of(2004, 2, 23)));
        personData.add(new Person("Іван","Зайців", 221, LocalDate.of(2004, 3, 17)));
        personData.add(new Person("Катерина","Васильченка", 221, LocalDate.of(2004, 4, 25)));
        personData.add(new Person("Ольга","Жук", 221, LocalDate.of(2004, 5, 11)));
        personData.add(new Person("Людміла","Алексєєва", 221, LocalDate.of(2004, 6, 19)));
        personData.add(new Person("Даніл","Кац", 221, LocalDate.of(2004, 7, 16)));
        personData.add(new Person("Євген","Васнецов", 221, LocalDate.of(2004, 8, 22)));
        personData.add(new Person("Дмитро","Жуликів", 221, LocalDate.of(2004, 9, 27)));
        personData.add(new Person("Мрат","Алібов", 221, LocalDate.of(2004, 10, 16)));
        personData.add(new Person("Martin","Mueller", 221, LocalDate.of(2004, 11, 11)));
    }
    /**
     * Повертає дані у вигляді спостережуваного списку студентів.
     * @return
     */
    public ObservableList<Person> getPersonData() {
        return personData;
    }

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;
        this.primaryStage.setTitle("StudentGroupApp");

        initRootLayout();
        showPersonOverview();
    }
    /**
     * Ініціалізує кореневий макет.
     */
    public void initRootLayout(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/com/example/studentgroupapp.view/RootLayout.fxml"));
        try {
            rootLayout = (BorderPane) fxmlLoader.load();

            //Відображаємо сцену, яка містить кореневий макет.
            Scene scene=new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Показує у кореневому макеті відомості про студентів.
     */
    public void showPersonOverview(){
        FXMLLoader fxmlLoader = new FXMLLoader();

        // Завантажуємо відомості про студентів.
        fxmlLoader.setLocation(MainApp.class.getResource("/com/example/studentgroupapp.view/PersonOverview.fxml"));
        try {
            AnchorPane personOverview = (AnchorPane) fxmlLoader.load();

            // Поміщаємо відомості про студентів до центру кореневого макета.
            rootLayout.setCenter(personOverview);

            //Даємо контролеру доступ до головної програми.
            PersonOverviewController controller=fxmlLoader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Відкриває діалогове вікно зміни даних зазначеного студента.
     * Якщо користувач натиснув OK, то зміни зберігаються у наданому
     * об'єкт студента і повертається значення true.
     *
     * @paramperson – об'єкт студента, який треба змінити
     * @returntrue, якщо користувач натиснув OK, інакше false.
     */
    public boolean showPersonEditDialog(Person person) {
        try{
            // Завантажуємо файл fxml і створюємо нову сцену для діалогового вікна.
            FXMLLoader loader=new FXMLLoader();

            loader.setLocation(MainApp.class.getResource("/com/example/studentgroupapp.view/ PersonEditDialog.fxml"));
            AnchorPane page= (AnchorPane)loader.load();

            //Створюємо діалогове вікно Stage.
            Stage dialogStage=new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene=new Scene(page);
            dialogStage.setScene(scene);

            // Передаємо студента до контролера.
            PersonEditDialogController controller=loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);

            // Відображаємо діалогове вікно та чекаємо, поки користувач його не закриє
            dialogStage.showAndWait();
            return controller.isOkClicked();
        }catch(IOException e) {e.printStackTrace(); return false;
        }
    }

    /**
     * Повертає головну сцену.
     * @return
     */
    public Stage getPrimaryStage(){
        return primaryStage;
    }
    public static void main(String[] args) {
        launch();
    }
}
