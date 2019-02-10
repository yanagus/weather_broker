package work.model;

import work.view.ForecastView;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Objects;

/**
 * Прогноз погоды
 */
@Entity
public class Forecast implements Serializable {

    /**
     * Уникальный идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Integer id;

    /**
     * Служебное поле Hibernate
     */
    @Version
    private Integer version;

    /**
     * День недели
     */
    @Column(name = "day", length = 3)
    private String day;

    /**
     * Дата
     */
    @Column(name = "date")
    private Integer date;

    /**
     * Минимальная температура воздуха для данного дня, в градусах Цельсия
     */
    @Column(name = "low")
    private Byte low;

    /**
     * Максимальная температура воздуха для данного дня, в градусах Цельсия
     */
    @Column(name = "high")
    private Byte high;

    /**
     * Текстовое описание состояния
     */
    @Column(name = "text", length = 25)
    private String text;

    /**
     * Код состояния
     */
    @Column(name = "code")
    private Short code;

    /**
     * Местоположение, город
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    public Forecast() {
    }

    public Forecast(String day, Integer date, Byte low, Byte high, String text, Short code) {
        this.day = day;
        this.date = date;
        this.low = low;
        this.high = high;
        this.text = text;
        this.code = code;
    }

    public Forecast(ForecastView forecastView) {
        this(forecastView.getDay(), forecastView.getDate(), forecastView.getLow(), forecastView.getHigh(),
                forecastView.getText(), forecastView.getCode());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }


    public Byte getLow() {
        return low;
    }

    public void setLow(Byte low) {
        this.low = low;
    }

    public Byte getHigh() {
        return high;
    }

    public void setHigh(Byte high) {
        this.high = high;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Short getCode() {
        return code;
    }

    public void setCode(Short code) {
        this.code = code;
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
        Forecast forecast = (Forecast) o;
        return Objects.equals(id, forecast.id) &&
                Objects.equals(day, forecast.day) &&
                Objects.equals(date, forecast.date) &&
                Objects.equals(low, forecast.low) &&
                Objects.equals(high, forecast.high) &&
                Objects.equals(text, forecast.text) &&
                Objects.equals(code, forecast.code) &&
                Objects.equals(location, forecast.location);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, day, date, low, high, text, code, location);
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "id=" + id +
                ", day='" + day + '\'' +
                ", date=" + date +
                ", low=" + low +
                ", high=" + high +
                ", text='" + text + '\'' +
                ", code=" + code +
                '}';
    }
}
