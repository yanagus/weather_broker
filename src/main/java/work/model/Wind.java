package work.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Objects;

/**
 * Текущая информация о ветре
 */
@Entity
public class Wind implements Serializable {

    /**
     * Уникальный идентификатор
     */
    @Id
    private Integer id;

    /**
     * Служебное поле Hibernate
     */
    @Version
    private Integer version;

    /**
     * Жёсткость погоды (wind chill), в градусах Цельсия
     */
    @Column(name = "chill")
    private Integer chill;

    /**
     * Направление ветра, в градусах
     */
    @Column(name = "direction")
    private Integer direction;

    /**
     * Скорость ветра, в км/ч
     */
    @Column(name = "speed")
    private Float speed;

    /**
     * Текущий обзор погоды
     */
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "cur_obs_id")
    private CurrentObservation currentObservation;

    public Wind() {
    }

    public Wind(Integer chill, Integer direction, Float speed, CurrentObservation currentObservation) {
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

    public CurrentObservation getCurrentObservation() {
        return currentObservation;
    }

    public void setCurrentObservation(CurrentObservation currentObservation) {
        this.currentObservation = currentObservation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wind wind = (Wind) o;
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
