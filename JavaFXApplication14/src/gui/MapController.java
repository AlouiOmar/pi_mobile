/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import javafx.scene.control.TextField;
import org.json.simple.parser.ParseException;

/**
 * FXML Controller class
 *
 * @author elbrh
 */
public class MapController implements Initializable, MapComponentInitializedListener {

    @FXML
    private GoogleMapView mapView;

    @FXML
    private TextField addressTextField;

    static GoogleMap map;

    private GeocodingService geocodingService;

    private StringProperty address = new SimpleStringProperty();

    static double lonng = 0;
    static double lat = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        mapView.addMapInializedListener(this);
        address.bind(addressTextField.textProperty());
    }

    @Override
    public void mapInitialized() {
        geocodingService = new GeocodingService();

        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(36.8332721, 10.2019332))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(12);
        map = mapView.createMap(mapOptions);

    }

    private static final String USER_AGENT = "Mozilla/5.0";

    private static String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }
    }

    private static void map(String locality) throws IOException {

        String GET_URL = "http://dev.virtualearth.net/REST/v1/Locations?locality=" + encodeValue(locality) + "&key=Akkmi3HCXEZnGOpqbd5m9opjhEA11BVeMiXptuaiyc06YXnyfKuzlkddwgm51Bwi";
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            JSONParser parser = new JSONParser();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));) {
                JSONObject jsonObject = (JSONObject) parser.parse(in);
                JSONArray msg = (JSONArray) jsonObject.get("resourceSets");
                JSONObject resourceJsonObject = (JSONObject) msg.get(0);
                JSONArray ressourcesget = (JSONArray) resourceJsonObject.get("resources");
                long estimatedTotalget = (long) resourceJsonObject.get("estimatedTotal");
                System.err.println(estimatedTotalget);
                if (estimatedTotalget > 0) {
                    JSONObject courdinateJsonObject = (JSONObject) ressourcesget.get(0);
                    JSONArray geocodePointsget = (JSONArray) courdinateJsonObject.get("geocodePoints");
                    JSONObject courdinateObject = (JSONObject) geocodePointsget.get(0);
                    JSONArray courdinates = (JSONArray) courdinateObject.get("coordinates");
                    LatLong ltl = new LatLong((double) courdinates.get(0), (double) courdinates.get(1));
                    lat = (double) courdinates.get(0);
                    lonng = (double) courdinates.get(1);
                    map.setCenter(ltl);
                } else {
                    Alert a = new Alert(Alert.AlertType.WARNING);
                    a.setHeaderText("WARNING");
                    a.setContentText("adresse invalide");
                    a.show();
                }

            } catch (IOException e) {
                //e.printStackTrace(
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setHeaderText("ERROR");
                a.setContentText("adresse invalide");
                a.show();
            } catch (ParseException e) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setHeaderText("ERROR");
                a.setContentText("adresse invalide");
                a.show();
                //e.printStackTrace();
            }
            //System.out.println(response.toString());
        } else {
            System.err.println("GET request not worked");
        }
    }

    @FXML
    public void addressTextFieldAction() throws IOException {
        map(address.get());
        System.out.println("latitude : " + lat);
        System.out.println("longitude : " + lonng);

    }
}
