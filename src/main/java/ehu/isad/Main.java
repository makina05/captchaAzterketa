package ehu.isad;

import ehu.isad.controllers.SampleController;
import ehu.isad.db.SampleDBKud;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception{

    FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample.fxml"));
    Parent root = (Parent) loader.load();

    SampleController controller = loader.getController();
    //controller.setMainApp(this);
    controller.kargatuTaula();

    primaryStage.setTitle("Hello World");
    primaryStage.setScene(new Scene(root, 600, 390));
    primaryStage.show();
  }



  public static void main(String[] args) {
    launch(args);
  }
}
