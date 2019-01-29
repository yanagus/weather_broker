package work.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Общие данные о погоде
 */
public class WeatherInfo implements Serializable {

    private static final long serialVersionUID = 803745049486954915L;

    /**
     * Местоположение
     */
    private Location location;

    /**
     * Текущие данные о погоде
     */
    private CurrentObservation currentObservation;

    /**
     * Прогноз на 10 дней
     */
    private List<Forecast> forecasts;

    public WeatherInfo() {
    }

    public WeatherInfo(Location location, CurrentObservation currentObservation, List<Forecast> forecasts) {
        this.location = location;
        this.currentObservation = currentObservation;
        this.forecasts = forecasts;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public CurrentObservation getCurrentObservation() {
        return currentObservation;
    }

    public void setCurrentObservation(CurrentObservation currentObservation) {
        this.currentObservation = currentObservation;
    }

    public List<Forecast> getForecasts() {
        if (forecasts == null) {
            forecasts = new ArrayList<>();
        }
        return forecasts;
    }

    public void setForecasts(List<Forecast> forecasts) {
        this.forecasts = forecasts;
    }

    @Override
    public String toString() {
        return "WeatherInfo{" +
                "location=" + location +
                ", currentObservation=" + currentObservation +
                ", forecasts=" + forecasts +
                '}';
    }
}
