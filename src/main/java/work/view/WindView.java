package work.view;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Objects;

/**
 * Текущая информация о ветре
 */
public class WindView implements Serializable {

    /**
     * Уникальный идентификатор
     */
    @JsonIgnore
    private Integer id;

    /**
     * Жёсткость погоды (wind chill), в градусах Цельсия
     */
    private Integer chill;

    /**
     * Направление ветра, в градусах
     */
    private Integer direction;

    /**
     * Скорость ветра, в км/ч
     */
    private Float speed;

    /**
     * Текущий обзор погоды
     */
    @JsonBackReference
    private CurrentObservationView currentObservation;

    public WindView() {
    }

    public WindView(Integer chill, Integer direction, Float speed, CurrentObservationView currentObservation) {
        this.chill = chill;
        this.direction = direction;
        this.speed = speed;
        this.currentObservation = currentObservation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getChill() {
        return chill;
    }

    public void setChill(Integer chill) {
        this.chill = chill;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public Float getSpeed() {
        return speed;
    }

    public void setSpeed(Float speed) {
        this.speed = speed;
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
        WindView wind = (WindView) o;
        return Objects.equals(id, wind.id) &&
                Objects.equals(chill, wind.chill) &&
                Objects.equals(direction, wind.direction) &&
                Objects.equals(speed, wind.speed);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, chill, direction, speed);
    }

    @Override
    public String toString() {
        return "Wind{" +
                "id=" + id +
                ", chill=" + chill +
                ", direction=" + direction +
                ", speed=" + speed +
                '}';
    }
}
