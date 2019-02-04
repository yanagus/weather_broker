package work.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Objects;

/**
 * Текущее состояние погоды
 */
@Entity
@Table(name = "Condit")
public class Condition implements Serializable {

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
     * Температура
     */
    @Column(name = "temperature")
    private Integer temperature;

    /**
     * Текущий обзор погоды
     */
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "cur_obs_id")
    private CurrentObservation currentObservation;

    public Condition() {
    }

    public Condition(String text, Short code, Integer temperature, CurrentObservation currentObservation) {
        this.text = text;
        this.code = code;
        this.temperature = temperature;
        this.currentObservation = currentObservation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
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
        Condition condition = (Condition) o;
        return Objects.equals(id, condition.id) &&
                Objects.equals(text, condition.text) &&
                Objects.equals(code, condition.code) &&
                Objects.equals(temperature, condition.temperature);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, text, code, temperature);
    }

    @Override
    public String toString() {
        return "Condition{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", code=" + code +
                ", temperature=" + temperature +
                '}';
    }
}
