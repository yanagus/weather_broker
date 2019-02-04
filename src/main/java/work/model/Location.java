package work.model;

import work.view.LocationView;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Местоположение, город
 */
@Entity
public class Location implements Serializable {

    /**
     * Уникальный идентификатор
     */
    @Id
    @Column(name = "woeid", unique = true)
    private Integer woeid;

    /**
     * Служебное поле Hibernate
     */
    @Version
    private Integer version;

    /**
     * Название города
     */
    @Column(name = "city", length = 25)
    private String city;

    /**
     * Регион, область, штат
     */
    @Column(name = "region", length = 25)
    private String region;

    /**
     * Страна
     */
    @Column(name = "country", length = 50)
    private String country;

    /**
     * Широта
     */
    @Column(name = "lat")
    private Double latitude;

    /**
     * Долгота
     */
    @Column(name = "lon")
    private Double longitude;

    /**
     * Часовой пояс
     */
    @Column(name = "timezone_id")
    private String timezone;

    /**
     * Текущее состояние погоды
     */
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CurrentObservation> currentObservations;

    /**
     * Прогноз погоды на 10 дней
     */
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Forecast> forecasts;

    public Location() {
    }

    public Location(Integer woeid, String city, String region, String country, Double latitude, Double longitude, String timezone) {
        this.woeid = woeid;
        this.city = city;
        this.region = region;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timezone = timezone;
    }

    public Location(LocationView locationView) {
        this(locationView.getWoeid(), locationView.getCity(), locationView.getRegion(), locationView.getCountry(),
                locationView.getLatitude(), locationView.getLongitude(), locationView.getTimezone());
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

    public List<CurrentObservation> getCurrentObservations() {
        if (currentObservations == null) {
            currentObservations = new ArrayList<>();
        }
        return currentObservations;
    }

    public void setCurrentObservations(List<CurrentObservation> currentObservations) {
        this.currentObservations = currentObservations;
    }

    public void addCurrentObservations(CurrentObservation currentObservation) {
        getCurrentObservations().add(currentObservation);
        currentObservation.setLocation(this);
    }

    public void removeCurrentObservations(CurrentObservation currentObservation) {
        getCurrentObservations().remove(currentObservation);
        currentObservation.setLocation(null);
    }

    public List<Forecast> getForecasts() {
        if (forecasts == null) {
            forecasts = new ArrayList<>();
        }
        return forecasts;
    }

    public void setForecasts(List<Forecast> forecasts) {
        this.forecasts = forecasts;
    }

    public void addForecast(Forecast forecast) {
        getForecasts().add(forecast);
        forecast.setLocation(this);
    }

    public void removeForecast(Forecast forecast) {
        getForecasts().remove(forecast);
        forecast.setLocation(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
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
