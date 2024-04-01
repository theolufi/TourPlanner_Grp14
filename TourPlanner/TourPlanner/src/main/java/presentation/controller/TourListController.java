package presentation.controller;

import service.ServiceFactory;
import service.ConfigurationManager;
import service.IService;
import presentation.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.ResourceBundle;

public class TourListController implements Initializable{
    private TourListModel tourListModel;
    private TourDetailsModel tourDetailsModel;


    @FXML
    public ListView<TourModel> listView = new ListView<>();
    private IService manager = ServiceFactory.GetManager();

    public TourListController(TourListModel tourListModel, TourDetailsModel tourDetailsModel) {
        this.tourListModel = tourListModel;
        this.tourDetailsModel = tourDetailsModel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Get all tours from DB on loading
        try {
            this.tourListModel.setTours(manager.getAllTour());
            Iterator<TourModel> allTours = this.tourListModel.getTours().iterator();
            while(allTours.hasNext()){
                TourModel temp = allTours.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.listView.setItems(this.tourListModel.getTours());
        this.listView.setCellFactory(
                ToursListView -> new TourItemModel(p -> this.deleteProduct(p)));
    }

    private void deleteProduct(TourModel model) {
        String path;
        try {
            //Deletes the map file
            path = ConfigurationManager.GetConfigProperty("FileAccessStoragePath");
            File file = new File(path + this.tourDetailsModel.getTourName() + ".jpg");
            file.delete();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.tourListModel.removeTour(model);
    }

    @FXML
    //Tour item is clicked
    private void tourItemClicked(MouseEvent mouseEvent) {
        //Select the model that is clicked
        TourModel tourModelList = listView.getSelectionModel().getSelectedItem();
        //Set TourDetail
        if(tourModelList != null){
            tourDetailsModel.setTourModel(tourModelList);
        }
    }

    public void importData(ActionEvent actionEvent) {
    }

    public void exportData(ActionEvent actionEvent) {
        this.tourListModel.exportData();
    }
}
