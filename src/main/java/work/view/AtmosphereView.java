package work.view;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Objects;

/**
 * Информация о текущем атмосферном давлении, влажности и видимости
 */
public class AtmosphereView implements Serializable {

    /**
     * Уникальный идентификатор
     */
    @JsonIgnore
    private Integer id;

    /**
     * Влажность, в процентах
     */
    private Integer humidity;

    /**
     * Видимость, в километрах
     */
    private Float visibility;

    /**
     * Давление, в мбар
     */
    private Float pressure;

    /**
     * Состояние барометрического давления: устойчивое (0), повышение (1) или падение (2)
     */
    private Integer rising;

    /**
     * Текущий обзор погоды
     */
    @JsonBackReference
    private CurrentObservationView currentObservation;

    public AtmosphereView() {
    }

    public AtmosphereView(Integer humidity, Float visibility, Float pressure, Integer rising, CurrentObservationView currentObservation) {
        this.humidity = humidity;
        this.visibility = visibility;
        this.pressure = pressure;
        this.rising = rising;
        this.currentObservation = currentObservation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Float getVisibility() {
        return visibility;
    }

    public void setVisibility(Float visibility) {
        this.visibility = visibility;
    }

    public Float getPressure() {
        return pressure;
    }

    public void setPressure(Float pressure) {
        this.pressure = pressure;
    }

    public Integer getRising() {
        return rising;
    }

    public void setRising(Integer rising) {
        this.rising = rising;
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
        AtmosphereView that = (AtmosphereView) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(humidity, that.humidity) &&
                Objects.equals(visibility, that.visibility) &&
                Objects.equals(pressure, that.pressure) &&
                Objects.equals(rising, that.rising);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, humidity, visibility, pressure, rising);
    }

    @Override
    public String toString() {
        return "Atmosphere{" +
                "id=" + id +
                ", humidity=" + humidity +
                ", visibility=" + visibility +
                ", pressure=" + pressure +
                ", rising=" + rising +
                '}';
    }
}
