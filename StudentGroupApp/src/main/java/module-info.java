module com.example.studentgroupapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.studentgroupapp.view to javafx.fxml;
    exports com.example.studentgroupapp;
}
