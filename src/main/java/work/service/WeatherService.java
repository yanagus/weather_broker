package work.service;

import work.view.WeatherInfoView;

/**
 * Сервис для получения погоды из БД
 */
public interface WeatherService {

    /**
     * Получить данные о погоде из БД
     *
     * @param cityName название города
     * @return данные о погоде
     */
    WeatherInfoView getWeatherFromDB(String cityName);
}
