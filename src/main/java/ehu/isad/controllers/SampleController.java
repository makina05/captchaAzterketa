package ehu.isad.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import ehu.isad.Main;
import ehu.isad.db.SampleDBKud;
import ehu.isad.model.CustomImage;
import ehu.isad.utils.Sarea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

public class SampleController {
    private List<CustomImage> captchaZerr = new ArrayList<CustomImage>();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<CustomImage> tbId;

    @FXML
    private TableColumn<CustomImage, Integer> idcolID;

    @FXML
    private TableColumn<CustomImage, String> pathColID;

    @FXML
    private TableColumn<CustomImage, String> contColID;

    @FXML
    private TableColumn<CustomImage, String> dateColID;

    @FXML
    private TableColumn<CustomImage, Image> iruColID;

    @FXML
    private Button txertId;

    @FXML
    private Button gordId;


    @FXML
    void onClickGorde(ActionEvent event) {
        for (int i=0; i<captchaZerr.size(); i++) {
            SampleDBKud.getInstance().dbEguneratu(captchaZerr.get(i).getId(), captchaZerr.get(i).getValue());

        }
    }
    @FXML
    void onClickTxert(ActionEvent event){
        int random = (int) (Math.random() * 90000000 + 1);
        String izena = "captcha" + random;
        Sarea.getInstance().irudiaGorde("http://45.32.169.98/captcha.php", izena);
        SampleDBKud.getInstance().captchaGehitu(izena + ".png");
        datuaKargatu();
        }

    @FXML
    void initialize () {
        this.kargatuTaula();
    }
    public void kargatuTaula () {
        //SampleDBKud kudeatzaileari eskatuko diogu DBtik taula sortzeko datuak hartzea
        tbId.setEditable(true);
        List<CustomImage> lagak = SampleDBKud.getInstance().lortuEskaneatutakoak();
        ObservableList<CustomImage> data = FXCollections.observableArrayList(lagak);

        idcolID.setCellValueFactory(new PropertyValueFactory<>("id"));
        pathColID.setCellValueFactory(new PropertyValueFactory<>("filename"));
        contColID.setCellValueFactory(new PropertyValueFactory<>("value"));
        dateColID.setCellValueFactory(new PropertyValueFactory<>("date"));
            //irudiak jartzeko
            iruColID.setCellValueFactory(new PropertyValueFactory<>("irudia"));
            iruColID.setCellFactory(p -> new TableCell<>() {
                public void updateItem(Image image, boolean empty) {
                    if (image != null && !empty) {
                        final ImageView imageview = new ImageView();
                        imageview.setFitHeight(20);
                        imageview.setFitWidth(50);
                        imageview.setImage(image);
                        setGraphic(imageview);
                        setAlignment(Pos.CENTER);
                    } else {
                        setGraphic(null);
                        setText(null);
                    }
                }
            });

            //Balioa gorde
            contColID.setOnEditCommit(
                    t -> {
                        t.getTableView().getItems().get(t.getTablePosition().getRow())
                                .setValue(t.getNewValue());
                    });
            //Balioa editatu
            Callback<TableColumn<CustomImage, String>, TableCell<CustomImage, String>> defaultTextFieldCellFactoryIzena
                    = TextFieldTableCell.forTableColumn();

            contColID.setCellFactory(col -> {
                TableCell<CustomImage, String> cell = defaultTextFieldCellFactoryIzena.call(col);
                return cell;
            });

            //add your data to the table here.
            datuaKargatu();
            //tbId.setItems(data);
        }

        private void datuaKargatu () {
            captchaZerr = SampleDBKud.getInstance().lortuEskaneatutakoak();
            ObservableList<CustomImage> captchas = FXCollections.observableArrayList(captchaZerr);
            tbId.setItems(captchas);
        }
    }
