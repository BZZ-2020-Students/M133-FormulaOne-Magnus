package ch.formula.one.data;

import ch.formula.one.model.Fahrer;
import ch.formula.one.model.Saison;
import ch.formula.one.model.Team;
import ch.formula.one.model.User;
import ch.formula.one.service.Config;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataHandler {
    private static DataHandler instance = null;
    private List<Fahrer> fahrerList;
    private List<Saison> saisonList;
    private List<Team> teamList;
    private List<User> userList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
//        setPublisherList(new ArrayList<>());
//        readPublisherJSON();
        setFahrerList(new ArrayList<>());
        readFahrerJSON();
    }

    /**
     * gets the only instance of this class
     * @return
     */
    public static DataHandler getInstance() {
        if (instance == null)
            instance = new DataHandler();
        return instance;
    }


    /**
     * reads all fahrers
     * @return list of fahrers
     */
    public List<Fahrer> readAllFahrers() {
        return getFahrerList();
    }

    /**
     * reads a fahrer by its uuid
     * @param fahrerUUID
     * @return the Fahrer (null=not found)
     */
    public Fahrer readFahrerByUUID(String fahrerUUID) {
        Fahrer fahrer = null;
        for (Fahrer entry : getFahrerList()) {
            if (entry.getFahrerUUID().equals(fahrerUUID)) {
                fahrer = entry;
            }
        }
        return fahrer;
    }

//    /**
//     * reads all Publishers
//     * @return list of publishers
//     */
//    public List<Publisher> readAllPublishers() {
//
//        return getPublisherList();
//    }
//
//    /**
//     * reads a publisher by its uuid
//     * @param publisherUUID
//     * @return the Publisher (null=not found)
//     */
//    public Publisher readPublisherByUUID(String publisherUUID) {
//        Publisher publisher = null;
//        for (Publisher entry : getPublisherList()) {
//            if (entry.getPublisherUUID().equals(publisherUUID)) {
//                publisher = entry;
//            }
//        }
//        return publisher;
//    }

    /**
     * reads the fahrers from the JSON-file
     */
    private void readFahrerJSON() {
        try {
            String path = Config.getProperty("fahrerJSON");

            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Fahrer[] fahrers = objectMapper.readValue(jsonData, Fahrer[].class);
            for (Fahrer fahrer : fahrers) {
                getFahrerList().add(fahrer);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
//
//    /**
//     * reads the publishers from the JSON-file
//     */
//    private void readPublisherJSON() {
//        try {
//            byte[] jsonData = Files.readAllBytes(
//                    Paths.get(
//                            Config.getProperty("publisherJSON")
//                    )
//            );
//            ObjectMapper objectMapper = new ObjectMapper();
//            Publisher[] publishers = objectMapper.readValue(jsonData, Publisher[].class);
//            for (Publisher publisher : publishers) {
//                getPublisherList().add(publisher);
//            }
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }
    /**
     * gets fahrerList
     *
     * @return value of fahrerList
     */
    private List<Fahrer> getFahrerList() {
        return fahrerList;
    }

    /**
     * sets fahrerList
     *
     * @param fahrerList the value to set
     */
    private void setFahrerList(List<Fahrer> fahrerList) {
        this.fahrerList = fahrerList;
    }

//    /**
//     * gets publisherList
//     *
//     * @return value of publisherList
//     */
//    private List<Publisher> getPublisherList() {
//        return publisherList;
//    }
//
//    /**
//     * sets publisherList
//     *
//     * @param publisherList the value to set
//     */
//    private void setPublisherList(List<Publisher> publisherList) {
//        this.publisherList = publisherList;
//    }
}
