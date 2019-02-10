package work.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jms.annotation.JmsListener;
import work.dao.AstronomyRepository;
import work.dao.AtmosphereRepository;
import work.dao.ConditionRepository;
import work.dao.CurrentObservationRepository;
import work.dao.ForecastRepository;
import work.dao.LocationRepository;
import work.dao.WindRepository;

import work.model.Atmosphere;
import work.model.Astronomy;
import work.model.Condition;
import work.model.CurrentObservation;
import work.model.Forecast;
import work.model.Location;
import work.model.Wind;
import work.view.CurrentObservationView;
import work.view.ForecastView;
import work.view.WeatherInfoView;

import java.util.List;
import java.util.Optional;

/**
 * Класс, получающий данные о погоде из JMS-очереди
 * и сохраняющий их в БД
 */
@Component
public class WeatherJmsListener {

    private final Logger log = LoggerFactory.getLogger(JmsListener.class);

    private LocationRepository locationRepository;
    private ForecastRepository forecastRepository;
    private CurrentObservationRepository currentObservationRepository;
    private AstronomyRepository astronomyRepository;
    private AtmosphereRepository atmosphereRepository;
    private ConditionRepository conditionRepository;
    private WindRepository windRepository;

    @Autowired
    public WeatherJmsListener(LocationRepository locationRepository,
                       ForecastRepository forecastRepository,
                       CurrentObservationRepository currentObservationRepository,
                       AstronomyRepository astronomyRepository,
                       AtmosphereRepository atmosphereRepository,
                       ConditionRepository conditionRepository,
                       WindRepository windRepository) {
        this.locationRepository = locationRepository;
        this.forecastRepository = forecastRepository;
        this.currentObservationRepository = currentObservationRepository;
        this.astronomyRepository = astronomyRepository;
        this.atmosphereRepository = atmosphereRepository;
        this.conditionRepository = conditionRepository;
        this.windRepository = windRepository;
    }

    /**
     * Получить данные о погоде из JMS-очереди
     * и сохранить их в БД
     *
     * @param weatherInfo данные о погоде
     */
    @JmsListener(destination = "queue")
    @Transactional
    public void receiveWeatherCondition(final WeatherInfoView weatherInfo) {
        log.info("Jms listener received WeatherCondition");
        saveWeatherInfo(weatherInfo);
        log.info("WeatherCondition was saved in DB");
    }

    private void saveWeatherInfo(WeatherInfoView weatherInfo) {
        Optional<Location> locationOptional = locationRepository.findById(weatherInfo.getLocation().getWoeid());
        if (locationOptional.isPresent()) {
            Location locationFromDB = locationOptional.get();
            saveCurrentObservation(weatherInfo.getCurrentObservation(), locationFromDB);
            locationFromDB.getForecasts().clear();
            saveForecasts(weatherInfo.getForecasts(), locationFromDB);
        } else {
            Location location = new Location(weatherInfo.getLocation());
            locationRepository.save(location);
            saveCurrentObservation(weatherInfo.getCurrentObservation(), location);
            saveForecasts(weatherInfo.getForecasts(), location);
        }
    }

    private void saveForecasts(List<ForecastView> forecasts, Location location) {
        for (ForecastView forecastView : forecasts) {
            Forecast forecast = new Forecast(forecastView);
            forecast.setLocation(location);
            forecastRepository.save(forecast);
        }
    }

    private void saveCurrentObservation(CurrentObservationView currentObservationView, Location location) {
        if(currentObservationView.getAstronomy() != null && currentObservationView.getAtmosphere() != null &&
                currentObservationView.getCondition() != null && currentObservationView.getWind() != null) {
            CurrentObservation newCurrentObservation = new CurrentObservation(location, currentObservationView.getPubDate());
            currentObservationRepository.save(newCurrentObservation);

            Astronomy astronomy = new Astronomy(currentObservationView.getAstronomy().getSunrise(),
                    currentObservationView.getAstronomy().getSunset(),
                    newCurrentObservation);
            astronomyRepository.save(astronomy);

            Atmosphere atmosphere = new Atmosphere(currentObservationView.getAtmosphere().getHumidity(),
                    currentObservationView.getAtmosphere().getVisibility(),
                    currentObservationView.getAtmosphere().getPressure(),
                    currentObservationView.getAtmosphere().getRising(),
                    newCurrentObservation);
            atmosphereRepository.save(atmosphere);

            Condition condition = new Condition(currentObservationView.getCondition().getText(),
                    currentObservationView.getCondition().getCode(),
                    currentObservationView.getCondition().getTemperature(),
                    newCurrentObservation);
            conditionRepository.save(condition);

            Wind wind = new Wind(currentObservationView.getWind().getChill(),
                    currentObservationView.getWind().getDirection(),
                    currentObservationView.getWind().getSpeed(),
                    newCurrentObservation);
            windRepository.save(wind);
        }
    }
}
