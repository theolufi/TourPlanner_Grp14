package persistence;

import presentation.model.TourLogCellModel;
import presentation.model.TourModel;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

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
    @Test
    void testSaveTourLogsDate() throws SQLException, FileNotFoundException {
        db.createConnection();
        db.addTour("thisTourDoesNotExist");
        TourLogCellModel log = new TourLogCellModel();
        log.setDate("01/01/1970");
        db.saveTourLogs(log,"thisTourDoesNotExist");
        assertEquals("01/01/1970",db.getAllTourLogs("thisTourDoesNotExist").get(0).getDate());
        TourModel tm = new TourModel();
        tm.setTourName("thisTourDoesNotExist");
        db.removeTour(tm);
    }
    @Test
    void testSaveTourLogsDifficulty() throws SQLException, FileNotFoundException {
        db.createConnection();
        db.addTour("thisTourDoesNotExist");
        TourLogCellModel log = new TourLogCellModel();
        log.setDifficulty("hard");
        db.saveTourLogs(log,"thisTourDoesNotExist");
        assertEquals("hard",db.getAllTourLogs("thisTourDoesNotExist").get(0).getDifficulty());
        TourModel tm = new TourModel();
        tm.setTourName("thisTourDoesNotExist");
        db.removeTour(tm);
    }
    @Test
    void testSaveTourLogsComment() throws SQLException, FileNotFoundException {
        db.createConnection();
        db.addTour("thisTourDoesNotExist");
        TourLogCellModel log = new TourLogCellModel();
        log.setComment("asdvf");
        db.saveTourLogs(log,"thisTourDoesNotExist");
        assertEquals("asdvf",db.getAllTourLogs("thisTourDoesNotExist").get(0).getComment());
        TourModel tm = new TourModel();
        tm.setTourName("thisTourDoesNotExist");
        db.removeTour(tm);
    }
    @Test
    void testSaveTourLogsRating() throws SQLException, FileNotFoundException {
        db.createConnection();
        db.addTour("thisTourDoesNotExist");
        TourLogCellModel log = new TourLogCellModel();
        log.setRating("dsfn");
        db.saveTourLogs(log,"thisTourDoesNotExist");
        assertEquals("dsfn",db.getAllTourLogs("thisTourDoesNotExist").get(0).getRating());
        TourModel tm = new TourModel();
        tm.setTourName("thisTourDoesNotExist");
        db.removeTour(tm);
    }
    @Test
    void testSaveTourLogsExists() throws SQLException, FileNotFoundException {
        db.createConnection();
        db.addTour("thisTourDoesNotExist");
        TourLogCellModel log = new TourLogCellModel();
        log.setDate("01/01/1970");
        db.saveTourLogs(log,"thisTourDoesNotExist");
        assertEquals(true,db.tourLogExists("01/01/1970"));
        TourModel tm = new TourModel();
        tm.setTourName("thisTourDoesNotExist");
        db.removeTour(tm);
    }
    @Test
    void testRemoveLogForTours() throws SQLException, FileNotFoundException {
        db.createConnection();
        db.addTour("thisTourDoesNotExist");
        TourLogCellModel log = new TourLogCellModel();
        log.setDate("01/01/1970");
        db.saveTourLogs(log,"thisTourDoesNotExist");
        log.setDate("02/01/1970");
        db.saveTourLogs(log,"thisTourDoesNotExist");
        assertEquals(2,db.getAllTourLogs("thisTourDoesNotExist").size());
        db.removeLogsForTour(db.getIdFromName("thisTourDoesNotExist"));
        assertEquals(0,db.getAllTourLogs("thisTourDoesNotExist").size());
        TourModel tm = new TourModel();
        tm.setTourName("thisTourDoesNotExist");
        db.removeTour(tm);
    }
    @Test
    void testAddTourWithSpecialCharacters() throws SQLException, FileNotFoundException {
        db.createConnection();
        String tourName = "Tour_@#123";
        db.addTour(tourName);
        assertNotEquals(0, db.getIdFromName(tourName));
        TourModel tm = new TourModel();
        tm.setTourName(tourName);
        db.removeTour(tm);
    }

    @Test
    void testRemoveNonExistentTour() throws SQLException, FileNotFoundException {
        db.createConnection();
        TourModel tm = new TourModel();
        tm.setTourName("nonExistentTour");
        assertDoesNotThrow(() -> db.removeTour(tm));
    }

    @Test
    void testUpdateTourDetailsNonExistentTour() throws SQLException, FileNotFoundException {
        db.createConnection();
        assertDoesNotThrow(() -> db.updateTourDetails("newDesc", "Paris", "Rome", "car", "1200", "10:00", "info", "nonExistentTour", 5));
    }

    @Test
    void testSaveTourLogsInvalidDate() throws SQLException, FileNotFoundException {
        db.createConnection();
        db.addTour("thisTourDoesNotExist");
        TourLogCellModel log = new TourLogCellModel();
        log.setDate("invalid_date");
        assertThrows(SQLException.class, () -> db.saveTourLogs(log, "thisTourDoesNotExist"));
        TourModel tm = new TourModel();
        tm.setTourName("thisTourDoesNotExist");
        db.removeTour(tm);
    }

    @Test
    void testGetAllTours() throws SQLException, FileNotFoundException {
        db.createConnection();
        db.addTour("thisTourDoesNotExist");
        List<TourModel> tours = db.getAllTours();
        assertTrue(tours.size() > 0);
        assertTrue(tours.stream().anyMatch(tour -> "thisTourDoesNotExist".equals(tour.getTourName())));
        TourModel tm = new TourModel();
        tm.setTourName("thisTourDoesNotExist");
        db.removeTour(tm);
    }
}