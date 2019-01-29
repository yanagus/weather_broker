package work.view;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Objects;

/**
 * Информация о текущих астрономических условиях
 */
public class AstronomyView implements Serializable {

    /**
     * Уникальный идентификатор
     */
    @JsonIgnore
    private Integer id;

    /**
     * Время вохода
     */
    private String sunrise;

    /**
     * Время заката
     */
    private String sunset;

    /**
     * Текущий обзор погоды
     */
    @JsonBackReference
    private CurrentObservationView currentObservation;

    public AstronomyView() {
    }

    public AstronomyView(String sunrise, String sunset, CurrentObservationView currentObservation) {
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.currentObservation = currentObservation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public CurrentObservationView getCurrentObservation() {
        return currentObservation;
    }

    public void setCurrentObservation(CurrentObservationView currentObservation) {
        this.currentObservation = currentObservation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AstronomyView astronomy = (AstronomyView) o;
        return Objects.equals(id, astronomy.id) &&
                Objects.equals(sunrise, astronomy.sunrise) &&
                Objects.equals(sunset, astronomy.sunset);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, sunrise, sunset);
    }

    @Override
    public String toString() {
        return "Astronomy{" +
                "id=" + id +
                ", sunrise='" + sunrise + '\'' +
                ", sunset='" + sunset + '\'' +
                '}';
    }
}
