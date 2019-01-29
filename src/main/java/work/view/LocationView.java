package work.view;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import work.model.Location;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Местоположение, город
 */
public class LocationView implements Serializable {

    /**
     * Уникальный идентификатор
     */
    private Integer woeid;

    /**
     * Название города
     */
    private String city;

    /**
     * Регион, область, штат
     */
    private String region;

    /**
     * Страна
     */
    private String country;

    /**
     * Широта
     */
    @JsonProperty("lat")
    private Double latitude;

    /**
     * Долгота
     */
    @JsonProperty("long")
    private Double longitude;

    /**
     * Часовой пояс
     */
    @JsonProperty("timezone_id")
    private String timezone;

    /**
     * Текущее состояние погоды
     */
    @JsonIgnore
    private List<CurrentObservationView> currentObservations;

    /**
     * Прогноз погоды на 10 дней
     */
    @JsonIgnore
    private List<ForecastView> forecasts;

    public LocationView() {
    }

    public LocationView(Integer woeid, String city, String region, String country, Double latitude, Double longitude, String timezone) {
        this.woeid = woeid;
        this.city = city;
        this.region = region;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timezone = timezone;
    }

    public LocationView(Location location) {
        this(location.getWoeid(), location.getCity(), location.getRegion(), location.getCountry(),
                location.getLatitude(), location.getLongitude(), location.getTimezone());
    }

    public Integer getWoeid() {
        return woeid;
    }

    public void setWoeid(Integer woeid) {
        this.woeid = woeid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public List<CurrentObservationView> getCurrentObservations() {
        if (currentObservations == null) {
            currentObservations = new ArrayList<>();
        }
        return currentObservations;
    }

    public void setCurrentObservations(List<CurrentObservationView> currentObservations) {
        this.currentObservations = currentObservations;
    }

    public void addCurrentObservations(CurrentObservationView currentObservation) {
        getCurrentObservations().add(currentObservation);
        currentObservation.setLocation(this);
    }

    public void removeCurrentObservations(CurrentObservationView currentObservation) {
        getCurrentObservations().remove(currentObservation);
        currentObservation.setLocation(null);
    }

    public List<ForecastView> getForecasts() {
        if (forecasts == null) {
            forecasts = new ArrayList<>();
        }
        return forecasts;
    }

    public void setForecasts(List<ForecastView> forecasts) {
        this.forecasts = forecasts;
    }

    public void addForecast(ForecastView forecast) {
        getForecasts().add(forecast);
        forecast.setLocation(this);
    }

    public void removeForecast(ForecastView forecast) {
        getForecasts().remove(forecast);
        forecast.setLocation(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationView location = (LocationView) o;
        return Objects.equals(woeid, location.woeid) &&
                Objects.equals(city, location.city) &&
                Objects.equals(region, location.region) &&
                Objects.equals(country, location.country) &&
                Objects.equals(latitude, location.latitude) &&
                Objects.equals(longitude, location.longitude) &&
                Objects.equals(timezone, location.timezone);
    }

    @Override
    public int hashCode() {

        return Objects.hash(woeid, city, region, country, latitude, longitude, timezone);
    }

    @Override
    public String toString() {
        return "Location{" +
                "woeid=" + woeid +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", country='" + country + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", timezone=" + timezone +
                '}';
    }
}
