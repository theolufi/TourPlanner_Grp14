package persistence;

import service.ConfigurationManager;
import presentation.model.TourModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class PersistencePostGres implements IPersistence {

    private Connection con;
    Logger log = LogManager.getLogger(PersistencePostGres.class);

    public PersistencePostGres(){
    }

    @Override
    //Create connection to DB
    public boolean createConnection() throws FileNotFoundException, SQLException {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "1234";
        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);
        con = DriverManager.getConnection(url, props);
        if(!isConnected()){
            return false;
        }
        return true;
    }

    @Override
    public boolean isConnected() throws SQLException {
        if(this.con != null){
            String sql = "SELECT 1";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.execute();
            ResultSet last_updated_person = ps.getResultSet();
            if(last_updated_person.next()) {
                return true;
            }
        }else{
            return false;
        }
        return false;
    }

    @Override
    //Get last inserted Tours id
    public int getMaxId() {
        try {
            String sql = "SELECT max(id) FROM Tours";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.execute();
            ResultSet last_updated_person = ps.getResultSet();
            if(last_updated_person.next()) {
                return last_updated_person.getInt(1);
            }
        } catch (SQLException e) {
            log.error("Could not get id from last Tour inserted!");
            log.error(e.getMessage());
        }
        return 0;
    }

    @Override
    //Get ID of the Tour with x name
    public int getIdFromName(String name) {
        try {
            String sql = "SELECT id FROM Tours where \"name\" = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.execute();
            ResultSet last_updated_person = ps.getResultSet();
            if(last_updated_person.next()) {
                return last_updated_person.getInt("id");
            }
        } catch (SQLException e) {
            log.error("Could not get id from name of Tour");
            log.error(e.getMessage());
        }
        return 0;
    }

    @Override
    //Remove Tour
    public void removeTour(TourModel tourModel) {
        try {
            int tourId = getIdFromName(tourModel.getTourName());
            String sql = "DELETE FROM Tours WHERE \"name\" = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tourModel.getTourName());
            ps.executeUpdate();
            log.info("Tour " + tourModel.getTourName() + " deleted!");
            log.info("Logs of " + tourModel.getTourName() + " are deleted!");
        } catch (SQLException e) {
            log.error("Could not delete Tour");
            log.error(e.getMessage());
        }
    }

    @Override
    //Update Tour Details after Save button is clicked
    public void updateTourDetails(String tourDesc, String tourFrom, String tourTo, String tourTransport, String tourDistance, String tourEstTime, String tourInfo, String tourName, int tourRating) throws SQLException {
        String sql = "UPDATE tours SET description = ?, \"from\" = ?, \"to\" = ?, transport_type = ?, distance = ?, estimated_time = ?, route_info = ?, ratings = ?  WHERE \"name\" = ?;";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tourDesc);
            ps.setString(2, tourFrom);
            ps.setString(3, tourTo);
            ps.setString(4, tourTransport);
            ps.setString(5, tourDistance);
            ps.setString(6, tourEstTime);
            ps.setString(7, tourInfo);
            ps.setInt(8, tourRating);
            ps.setString(9, tourName);
            ps.executeUpdate();
            log.info("Tour " + tourName + " is updated!");
        } catch (SQLException e) {
            log.error("Could not update Tour");
            log.error(e.getMessage());
        }
    }

    @Override
    //Get all Tours
    public ObservableList<TourModel> getAllTours() {
        try {
            String sql = "SELECT * FROM Tours";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ObservableList<TourModel> tours = FXCollections.observableArrayList();
            TourModel temp;
            while (rs.next()){
                temp = new TourModel();
                temp.setTourName(rs.getString("name"));
                temp.setTourDesc(rs.getString("description"));
                temp.setTourFrom(rs.getString("from"));
                temp.setTourTo(rs.getString("to"));
                temp.setTourTransport(rs.getString("transport_type"));
                temp.setTourDistance(rs.getString("distance"));
                temp.setTourEstTime(rs.getString("estimated_time"));
                temp.setTourInfo(rs.getString("route_info"));
                temp.setTourRating(rs.getInt("ratings"));
                tours.add(temp);
            }
            return tours;
        } catch (SQLException e) {
            log.error("Could not fetch all Tours from DB");
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    //Get all Tour Names
    public List<String> getAllToursNames() {
        try {
            String sql = "SELECT \"name\" FROM Tours";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<String> toursNames = new ArrayList<>();
            while (rs.next()){
                toursNames.add(rs.getString("name"));
            }
            return toursNames;
        } catch (SQLException e) {
            log.error("Could not fetch all Tour Names from DB");
            log.error(e.getMessage());
        }
        return null;
    }
    @Override
    //Add Tour
    public void addTour(String tour) {
        int count = getMaxId() + 1;
        try {
            String sql = "insert into Tours values(?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, count);
            ps.setString(2, tour);
            ps.executeUpdate();
            log.info("Tour " + tour + " is inserted in the DB");
        } catch (SQLException e) {
            log.error("Could not insert Tour in DB");
            log.error(e.getMessage());
        }
    }
}
