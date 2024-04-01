package service;

import persistence.IPersistence;
import persistence.PersistenceFactory;
import presentation.Main;
import presentation.model.TourEntryModel;
import presentation.model.TourModel;
import javafx.collections.ObservableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class ServiceImp implements IService {

    //DB Layer
    private IPersistence dataLayer;
    //MapQuest
    Logger log = LogManager.getLogger(ServiceImp.class);

    public ServiceImp(){
        dataLayer = PersistenceFactory.getDatabase();
    }

    @Override
    public void createTourItem(TourEntryModel tourItem) throws SQLException, FileNotFoundException {
        dataLayer.createConnection();
        dataLayer.addTour(tourItem.getTourName());
    }

    @Override
    public void deleteTourItem(TourModel tourModel) throws SQLException, FileNotFoundException {
        dataLayer.createConnection();
        dataLayer.removeTour(tourModel);
    }

    @Override
    public void updateTourDetails(String tourDesc, String tourFrom, String tourTo, String tourTransport, String tourDistance, String tourEstTime, String tourInfo, String tourName, int tourRating) throws SQLException, FileNotFoundException {
        dataLayer.createConnection();
        dataLayer.updateTourDetails(tourDesc, tourFrom, tourTo, tourTransport, tourDistance, tourEstTime, tourInfo, tourName, tourRating);
    }

    @Override
    public ObservableList<TourModel> getAllTour() throws SQLException, FileNotFoundException {
        dataLayer.createConnection();
        return dataLayer.getAllTours();
    }

    @Override
    public List<String> getAllTourNames() throws SQLException, FileNotFoundException {
        dataLayer.createConnection();
        return dataLayer.getAllToursNames();
    }


    @Override
    public void savePDF() {
        PDDocument document = new PDDocument();
        try {
            PDPage page = new PDPage();
            document.addPage(page);
            document.save("test.pdf");
            document.close();
            Main.getImg();
            log.info("PDF is saved");
        } catch (IOException e) {
            log.error("PDF could not be saved");
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }
}

