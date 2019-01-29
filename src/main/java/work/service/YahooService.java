package work.service;

import work.view.WeatherInfoView;

/**
 * Сервис погоды
 */
public interface YahooService {

    /**
     * Получить данные о погоде с сервиса Yahoo
     *
     * @param city город
     * @param region регион
     */
    WeatherInfoView getWeather(String city, String region);
}
