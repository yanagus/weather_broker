package work.view;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Objects;

/**
 * Текущее состояние погоды
 */
public class ConditionView implements Serializable {

    /**
     * Уникальный идентификатор
     */
    @JsonIgnore
    private Integer id;

    /**
     * Текстовое описание состояния
     */
    private String text;

    /**
     * Код состояния
     */
    private Short code;

    /**
     * Температура
     */
    private Integer temperature;

    /**
     * Текущий обзор погоды
     */
    @JsonBackReference
    private CurrentObservationView currentObservation;

    public ConditionView() {
    }

    public ConditionView(String text, Short code, Integer temperature, CurrentObservationView currentObservation) {
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
        ConditionView condition = (ConditionView) o;
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
