package presentation.controller;

import service.ServiceFactory;
import service.ConfigurationManager;
import service.IService;
import presentation.model.TourDetailsModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.controlsfx.control.Rating;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;

public class TourDetailsController implements Initializable {

    private final TourDetailsModel tourDetailsModel;

    @FXML
    private ImageView imageView;
    @FXML
    private Button editButton;
    @FXML
    private Button saveButton;

    @FXML
    private TextField tourName;
    @FXML
    private TextField tourDesc;
    @FXML
    private TextField tourFrom;
    @FXML
    private TextField tourTo;
    @FXML
    private TextField tourTransport;
    @FXML
    private TextField tourEstTime;
    @FXML
    private TextField tourInfo;
    @FXML
    private Label tourNameLabel;
    @FXML
    private Label tourDescLabel;
    @FXML
    private Label tourFromLabel;
    @FXML
    private Label tourToLabel;
    @FXML
    private Label tourTransportLabel;
    @FXML
    private Label tourDistanceLabel;
    @FXML
    private Label tourEstTimeLabel;
    @FXML
    private Label tourInfoLabel;
    @FXML
    private Rating tourRating;

    private IService manager = ServiceFactory.GetManager();

    public TourDetailsController(TourDetailsModel tourDetailsModel) {
        this.tourDetailsModel = tourDetailsModel;
    }

    Logger log = LogManager.getLogger(TourDetailsController.class);

    public void editMode(ActionEvent actionEvent) {
        //Change to edit mode when the button is clicked
        if(this.tourDetailsModel.getEditMode() == true){
            this.tourDetailsModel.setEditMode(false);
            this.tourDetailsModel.setEditButton("Edit");
            this.tourDetailsModel.setWorkMode(true);
            this.tourDetailsModel.resetTourModel();
        }else{
            this.tourDetailsModel.setEditMode(true);
            this.tourDetailsModel.setEditButton("Cancel");
            this.tourDetailsModel.setWorkMode(false);
        }
        log.info("State of Tour Details is flipped");
    }

    public void saveTour(ActionEvent actionEvent) throws IOException, SQLException {
        //Save tour when the save button is clicked
        //with validation
        if(!inputAreValid()){
            log.info("Input from Fields is not valid");
            inputNotValidBox(true);
        }else{
            log.info("Update Details fron Tour");
            if(tourDetailsModel.getTourName() != null && !tourDetailsModel.getTourName().equals("") ){
                //Save tour
                this.tourDetailsModel.saveTourModel();
                if(this.tourDetailsModel.getTourTo() == null || this.tourDetailsModel.getTourFrom() == null) {
                    //Update Tour in DB
                    //Revert to Working mode
                    manager.updateTourDetails(this.tourDetailsModel.getTourDesc(), this.tourDetailsModel.getTourFrom(), this.tourDetailsModel.getTourTo(), this.tourDetailsModel.getTourTransport(), this.tourDetailsModel.getTourDistance(), this.tourDetailsModel.getTourEstTime(), this.tourDetailsModel.getTourInfo(), this.tourDetailsModel.getTourName(), this.tourDetailsModel.getTourRating());
                    this.tourDetailsModel.setEditMode(false);
                    this.tourDetailsModel.setEditButton("Edit");
                    this.tourDetailsModel.setWorkMode(true);
                }else{

                        log.info("Input from Fields is not valid");
                        //Show new Screen, input was not valid
                        TourDetailsController.inputNotValidBox(false);
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Binding text value


    }

    //Show new scene depending on if the input is valid
    //If true  -> Inputed value not req format
    //If false -> Locations do not exist
    public static void inputNotValidBox(boolean type){

    }

    //Makes checks if the inputs are valid
    public boolean inputAreValid(){
        String time = this.tourDetailsModel.getTourEstTime();
        try{
            if(this.tourDetailsModel.getTourEstTime() != null){
                LocalTime.parse(time);
            }
            return true;
        }
        catch (DateTimeParseException | NullPointerException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
