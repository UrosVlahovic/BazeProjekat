module group.raf.zus_pravi {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires static lombok;
    requires java.sql;

    opens group.raf.zus_pravi to javafx.fxml;
    exports group.raf.zus_pravi;
}