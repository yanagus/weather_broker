package work.view;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import work.model.Forecast;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Прогноз погоды
 */
public class ForecastView implements Serializable {

    /**
     * Уникальный идентификатор
     */
    @JsonIgnore
    private Integer id;

    /**
     * День недели
     */
    private String day;

    /**
     * Дата
     */
    @JsonProperty("forecastDate")
    private ZonedDateTime zonedDateTime;

    /**
     * Дата в миллисекундах
     */
    private Integer date;

    /**
     * Минимальная температура воздуха для данного дня, в градусах Цельсия
     */
    private Byte low;

    /**
     * Максимальная температура воздуха для данного дня, в градусах Цельсия
     */
    private Byte high;

    /**
     * Текстовое описание состояния
     */
    private String text;

    /**
     * Код состояния
     */
    private Short code;

    /**
     * Местоположение, город
     */
    @JsonBackReference
    private LocationView location;

    public ForecastView() {
    }

    public ForecastView(String day, Integer date, Byte low, Byte high, String text, Short code) {
        this.day = day;
        this.date = date;
        this.low = low;
        this.high = high;
        this.text = text;
        this.code = code;
    }

    public ForecastView(Forecast forecast) {
        this(forecast.getDay(), forecast.getDate(), forecast.getLow(), forecast.getHigh(),
                forecast.getText(), forecast.getCode());
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

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }

    public void setZonedDateTime(ZonedDateTime zonedDateTime) {
        this.zonedDateTime = zonedDateTime;
    }

    public void setZonedDateTime() {
        if (location != null) {
            this.zonedDateTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli((long) date*1000), ZoneId.of(location.getTimezone()));
        }
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
        ForecastView forecast = (ForecastView) o;
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
