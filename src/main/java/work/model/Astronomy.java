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
 * Информация о текущих астрономических условиях
 */
@Entity
public class Astronomy implements Serializable {

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
     * Время вохода
     */
    @Column(name = "sunrise", length = 25)
    private String sunrise;

    /**
     * Время заката
     */
    @Column(name = "sunset", length = 25)
    private String sunset;

    /**
     * Текущий обзор погоды
     */
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "cur_obs_id")
    private CurrentObservation currentObservation;

    public Astronomy() {
    }

    public Astronomy(String sunrise, String sunset, CurrentObservation currentObservation) {
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
        Astronomy astronomy = (Astronomy) o;
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
