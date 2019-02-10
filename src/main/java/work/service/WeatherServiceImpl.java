package work.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import work.dao.AstronomyRepository;
import work.dao.AtmosphereRepository;
import work.dao.ConditionRepository;
import work.dao.CurrentObservationRepository;
import work.dao.ForecastRepository;
import work.dao.LocationRepository;
import work.dao.WindRepository;
import work.model.CurrentObservation;
import work.model.Forecast;
import work.model.Location;
import work.view.AstronomyView;
import work.view.AtmosphereView;
import work.view.ConditionView;
import work.view.CurrentObservationView;
import work.view.ForecastView;
import work.view.LocationView;
import work.view.WeatherInfoView;
import work.view.WindView;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class WeatherServiceImpl implements WeatherService {

    private LocationRepository locationRepository;
    private ForecastRepository forecastRepository;
    private CurrentObservationRepository currentObservationRepository;
    private AstronomyRepository astronomyRepository;
    private AtmosphereRepository atmosphereRepository;
    private ConditionRepository conditionRepository;
    private WindRepository windRepository;

    @Autowired
    public WeatherServiceImpl(LocationRepository locationRepository,
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
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Override
    public WeatherInfoView getWeatherFromDB(String cityName) {
        Location location = locationRepository.findByCity(cityName);
        if(location == null || location.getCurrentObservations() == null || location.getCurrentObservations().isEmpty()) {
            return null;
        }
        int size = location.getCurrentObservations().size();
        CurrentObservation currentObservation = location.getCurrentObservations().get(size - 1);
        if(currentObservation.getPubDate() == null || comparePubDates(currentObservation.getPubDate(), location) > 6) {
            return null;
        }

        WeatherInfoView weatherInfo = new WeatherInfoView();
        weatherInfo.setLocation(new LocationView(location));
        weatherInfo.setCurrentObservation(getCurrentObservationView(currentObservation, weatherInfo.getLocation()));
        List<Forecast> forecasts = location.getForecasts();
        for (Forecast forecast : forecasts) {
            ForecastView forecastView = new ForecastView(forecast);
            forecastView.setLocation(weatherInfo.getLocation());
            forecastView.setZonedDateTime();
            weatherInfo.getForecasts().add(forecastView);
        }
        return weatherInfo;
    }

    private int comparePubDates(Integer lastDate, Location location) {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of(location.getTimezone()));
        long difference = now.toEpochSecond() - lastDate;
        return (int)(difference / (60 * 60 * 1000));
    }

    private CurrentObservationView getCurrentObservationView(CurrentObservation currentObservation, LocationView location) {
        CurrentObservationView currentObservationView = new CurrentObservationView(currentObservation.getPubDate(),
                                                                                    null);

        AstronomyView astronomy = new AstronomyView(currentObservation.getAstronomy().getSunrise(),
                currentObservation.getAstronomy().getSunset(),
                currentObservationView);
        currentObservationView.setAstronomy(astronomy);

        AtmosphereView atmosphere = new AtmosphereView(currentObservation.getAtmosphere().getHumidity(),
                currentObservation.getAtmosphere().getVisibility(),
                currentObservation.getAtmosphere().getPressure(),
                currentObservation.getAtmosphere().getRising(),
                currentObservationView);
        currentObservationView.setAtmosphere(atmosphere);

        ConditionView condition = new ConditionView(currentObservation.getCondition().getText(),
                currentObservation.getCondition().getCode(),
                currentObservation.getCondition().getTemperature(),
                currentObservationView);
        currentObservationView.setCondition(condition);

        WindView wind = new WindView(currentObservation.getWind().getChill(),
                currentObservation.getWind().getDirection(),
                currentObservation.getWind().getSpeed(),
                currentObservationView);
        currentObservationView.setWind(wind);

        currentObservationView.setLocation(location);
        currentObservationView.setDate();

        return currentObservationView;
    }

}
