package work.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Objects;

/**
 * Текущий обзор погоды
 */
@Entity
@Table(name = "Current_observation")
public class CurrentObservation implements Serializable {

    /**
     * Уникальный идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Служебное поле Hibernate
     */
    @Version
    private Integer version;

    /**
     * Текущая информация о ветре
     */
    @OneToOne(mappedBy = "currentObservation", fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    private Wind wind;

    /**
     * Информация о текущем атмосферном давлении, влажности и видимости
     */
    @OneToOne(mappedBy = "currentObservation", fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    private Atmosphere atmosphere;

    /**
     * Информация о текущих астрономических условиях
     */
    @OneToOne(mappedBy = "currentObservation", fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    private Astronomy astronomy;

    /**
     * Текущее состояние погоды
     */
    @OneToOne(mappedBy = "currentObservation", fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    private Condition condition;

    /**
     * Дата и время публикации этого прогноза
     */
    @Column(name = "pub_date")
    private Integer pubDate;

    /**
     * Местоположение, город
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    public CurrentObservation() {
    }

    public CurrentObservation(Location location, Integer pubDate) {
        this.location = location;
        this.pubDate = pubDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(Atmosphere atmosphere) {
        this.atmosphere = atmosphere;
    }

    public Astronomy getAstronomy() {
        return astronomy;
    }

    public void setAstronomy(Astronomy astronomy) {
        this.astronomy = astronomy;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Integer getPubDate() {
        return pubDate;
    }

    public void setPubDate(Integer pubDate) {
        this.pubDate = pubDate;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrentObservation that = (CurrentObservation) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(wind, that.wind) &&
                Objects.equals(atmosphere, that.atmosphere) &&
                Objects.equals(astronomy, that.astronomy) &&
                Objects.equals(condition, that.condition) &&
                Objects.equals(pubDate, that.pubDate) &&
                Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, wind, atmosphere, astronomy, condition, pubDate, location);
    }

    @Override
    public String toString() {
        return "CurrentObservation{" +
                "id=" + id +
                ", wind=" + wind +
                ", atmosphere=" + atmosphere +
                ", astronomy=" + astronomy +
                ", condition=" + condition +
                ", pubDate=" + pubDate +
                '}';
    }
}
