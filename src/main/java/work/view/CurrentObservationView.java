package work.view;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Текущий обзор погоды
 */
public class CurrentObservationView implements Serializable {

    /**
     * Уникальный идентификатор
     */
    @JsonIgnore
    private Integer id;

    /**
     * Текущая информация о ветре
     */
    private WindView wind;

    /**
     * Информация о текущем атмосферном давлении, влажности и видимости
     */
    private AtmosphereView atmosphere;

    /**
     * Информация о текущих астрономических условиях
     */
    private AstronomyView astronomy;

    /**
     * Текущее состояние погоды
     */
    private ConditionView condition;

    /**
     * Дата и время публикации этого прогноза
     */
    private ZonedDateTime date;

    /**
     * Дата и время публикации этого прогноза в миллисекундах
     */
    private Integer pubDate;

    /**
     * Местоположение, город
     */
    @JsonBackReference
    private LocationView location;

    public CurrentObservationView() {
    }

    public CurrentObservationView(Integer pubDate, LocationView location) {
        this.pubDate = pubDate;
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public WindView getWind() {
        return wind;
    }

    public void setWind(WindView wind) {
        this.wind = wind;
    }

    public AtmosphereView getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(AtmosphereView atmosphere) {
        this.atmosphere = atmosphere;
    }

    public AstronomyView getAstronomy() {
        return astronomy;
    }

    public void setAstronomy(AstronomyView astronomy) {
        this.astronomy = astronomy;
    }

    public ConditionView getCondition() {
        return condition;
    }

    public void setCondition(ConditionView condition) {
        this.condition = condition;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public void setDate() {
        if (location != null) {
            this.date = ZonedDateTime.ofInstant(Instant.ofEpochMilli((long) pubDate*1000), ZoneId.of(location.getTimezone()));
        }
    }

    public Integer getPubDate() {
        return pubDate;
    }

    public void setPubDate(Integer pubDate) {
        this.pubDate = pubDate;
    }

    public LocationView getLocation() {
        return location;
    }

    public void setLocation(LocationView location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrentObservationView that = (CurrentObservationView) o;
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
