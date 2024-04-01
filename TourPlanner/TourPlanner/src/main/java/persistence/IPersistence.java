package persistence;

import presentation.model.TourModel;
import javafx.collections.ObservableList;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public interface IPersistence {

    //Functions for the DB Layer
    boolean createConnection() throws FileNotFoundException, SQLException;
    void addTour(String tour);
    int getMaxId();
    void removeTour(TourModel tourModel);
    void updateTourDetails(String tourDesc, String tourFrom, String tourTo, String tourTransport, String tourDistance, String tourEstTime, String tourInfo, String tourName, int tourRating) throws SQLException;
    ObservableList<TourModel> getAllTours();
    List<String> getAllToursNames();
    int getIdFromName(String name);
    boolean isConnected() throws SQLException;

}
