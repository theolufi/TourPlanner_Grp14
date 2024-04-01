package presentation.controller;

import presentation.model.*;

public class ControllerFactory {
    //create Models
    private final TourEntryModel tourEntryModel;
    private final TourListModel tourListModel;
    private final TourDetailsModel tourDetailsModel;



    public ControllerFactory(){
        //initialize Models
        this.tourEntryModel = new TourEntryModel();
        this.tourListModel = new TourListModel();
        this.tourDetailsModel = new TourDetailsModel();
    }

    public Object create(Class controllerClass) throws Exception {
        //for each controller, assign the Model
        if (controllerClass == TourEntryController.class) {
            return new TourEntryController(this.tourEntryModel);
        }else if(controllerClass == TourListController.class){
            return new TourListController(this.tourListModel, this.tourDetailsModel);
        }else if(controllerClass == TourDetailsController.class){
            return new TourDetailsController(this.tourDetailsModel);
        }else if(controllerClass == MainController.class){
            return new MainController(this.tourListModel);
        }else {
            throw new Exception("Controller not supported " + controllerClass.getName());
        }
    }


}
