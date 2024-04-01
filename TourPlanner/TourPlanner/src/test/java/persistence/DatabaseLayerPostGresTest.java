package persistence;

import presentation.model.TourModel;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseLayerPostGresTest {

    IPersistence db = new PersistencePostGres();

    @Test
    void testCreateConnection() throws SQLException, FileNotFoundException {
        assertEquals(db.createConnection(), true);
    }

    @Test
    void testAddTour() throws SQLException, FileNotFoundException {
        db.createConnection();
        db.addTour("thisTourDoesNotExist");
        assertNotEquals(0, db.getIdFromName("thisTourDoesNotExist"));
        assertEquals(0, db.getIdFromName("thisTourDoesNotExist2"));
        TourModel temp = new TourModel();
        temp.setTourName("thisTourDoesNotExist");
        db.removeTour(temp);
    }

    @Test
    void testRemoveTour() throws SQLException, FileNotFoundException {
        db.createConnection();
        db.addTour("thisTourDoesNotExist");
        assertNotEquals(0, db.getIdFromName("thisTourDoesNotExist"));
        assertEquals(0, db.getIdFromName("thisTourDoesNotExist2"));
        TourModel tm = new TourModel();
        tm.setTourName("thisTourDoesNotExist");
        db.removeTour(tm);
        assertEquals(0, db.getIdFromName("thisTourDoesNotExist"));
    }

    @Test
    void testUpdateTourDetailsDesc() throws SQLException, FileNotFoundException {
        db.createConnection();
        db.addTour("thisTourDoesNotExist");
        db.updateTourDetails("asdf", "Berlin", "Vienna", "as", "2134", "01:00", "asf", "thisTourDoesNotExist", 4);
        assertEquals("asdf", db.getAllTours().get(db.getMaxId()-1).getTourDesc());
        TourModel tm = new TourModel();
        tm.setTourName("thisTourDoesNotExist");
        db.removeTour(tm);
    }
    @Test
    void testUpdateTourDetailsFrom() throws SQLException, FileNotFoundException {
        db.createConnection();
        db.addTour("thisTourDoesNotExist");
        db.updateTourDetails("asdf", "Berlin", "Vienna", "as", "2134", "01:00", "asf", "thisTourDoesNotExist", 4);
        assertEquals("Berlin", db.getAllTours().get(db.getMaxId()-1).getTourFrom());
        TourModel tm = new TourModel();
        tm.setTourName("thisTourDoesNotExist");
        db.removeTour(tm);
    }
    @Test
    void testUpdateTourDetailsTo() throws SQLException, FileNotFoundException {
        db.createConnection();
        db.addTour("thisTourDoesNotExist");
        db.updateTourDetails("asdf", "Berlin", "Vienna", "as", "2134", "01:00", "asf", "thisTourDoesNotExist", 4);
        assertEquals("Vienna", db.getAllTours().get(db.getMaxId()-1).getTourTo());
        TourModel tm = new TourModel();
        tm.setTourName("thisTourDoesNotExist");
        db.removeTour(tm);
    }
    @Test
    void testUpdateTourDetailsTransport() throws SQLException, FileNotFoundException {
        db.createConnection();
        db.addTour("thisTourDoesNotExist");
        db.updateTourDetails("asdf", "Berlin", "Vienna", "as", "2134", "01:00", "asf", "thisTourDoesNotExist", 4);
        assertEquals("as", db.getAllTours().get(db.getMaxId()-1).getTourTransport());
        TourModel tm = new TourModel();
        tm.setTourName("thisTourDoesNotExist");
        db.removeTour(tm);
    }
    @Test
    void testUpdateTourDetailsDistance() throws SQLException, FileNotFoundException {
        db.createConnection();
        db.addTour("thisTourDoesNotExist");
        db.updateTourDetails("asdf", "Berlin", "Vienna", "as", "2134", "01:00", "asf", "thisTourDoesNotExist", 4);
        assertEquals("2134", db.getAllTours().get(db.getMaxId()-1).getTourDistance());
        TourModel tm = new TourModel();
        tm.setTourName("thisTourDoesNotExist");
        db.removeTour(tm);
    }
    @Test
    void testUpdateTourDetailsEstTime() throws SQLException, FileNotFoundException {
        db.createConnection();
        db.addTour("thisTourDoesNotExist");
        db.updateTourDetails("asdf", "Berlin", "Vienna", "as", "2134", "01:00", "asf", "thisTourDoesNotExist", 4);
        assertEquals("01:00", db.getAllTours().get(db.getMaxId()-1).getTourEstTime());
        TourModel tm = new TourModel();
        tm.setTourName("thisTourDoesNotExist");
        db.removeTour(tm);
    }
    @Test
    void testUpdateTourDetailsInfo() throws SQLException, FileNotFoundException {
        db.createConnection();
        db.addTour("thisTourDoesNotExist");
        db.updateTourDetails("asdf", "Berlin", "Vienna", "as", "2134", "01:00", "asf", "thisTourDoesNotExist", 4);
        assertEquals("asf", db.getAllTours().get(db.getMaxId()-1).getTourInfo());
        TourModel tm = new TourModel();
        tm.setTourName("thisTourDoesNotExist");
        db.removeTour(tm);
    }
    @Test
    void testUpdateTourDetailsRating() throws SQLException, FileNotFoundException {
        db.createConnection();
        db.addTour("thisTourDoesNotExist");
        db.updateTourDetails("asdf", "Berlin", "Vienna", "as", "2134", "01:00", "asf", "thisTourDoesNotExist", 4);
        assertEquals(4, db.getAllTours().get(db.getMaxId()-1).getTourRating());
        TourModel tm = new TourModel();
        tm.setTourName("thisTourDoesNotExist");
        db.removeTour(tm);
    }

}