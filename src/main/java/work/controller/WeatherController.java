package work.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import work.exceptionHandler.CityValidationException;
import work.service.WeatherService;
import work.service.YahooService;
import work.view.City;
import work.view.WeatherInfoView;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Контроллер для погоды
 */
@Controller
public class WeatherController {

    private final YahooService yahooService;
    private final WeatherService weatherService;

    /**
     * Конструктор контроллера
     *
     * @param yahooService сервис погоды с Yahoo
     */
    @Autowired
    public WeatherController(YahooService yahooService, WeatherService weatherService) {
        this.yahooService = yahooService;
        this.weatherService = weatherService;
    }

    /**
     * Показать форму для заполнения
     *
     * @return страница с формой
     */
    @RequestMapping(value = "/stady_war_exploded/", method = RequestMethod.GET)
    public ModelAndView showForm() {
        return new ModelAndView("index", "city", new City());
    }

    /**
     * Получить данные из формы и запросить погоду с Yahoo, если такие данные отсутствуют в БД.
     * Если данные есть в БД, то вывести их из БД
     *
     * @param city город и регион
     * @return данные о погоде
     */
    @RequestMapping(value = "/stady_war_exploded/", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public @ResponseBody WeatherInfoView getWeather(@ModelAttribute("city") City city,
                                                    BindingResult result) {
        validateCity(city, result);

        WeatherInfoView weatherInfoView = weatherService.getWeatherFromDB(city.getName().trim());
        if (weatherInfoView == null) {
            return yahooService.getWeather(city.getName().trim().toLowerCase().replaceAll(" ", ""),
                    city.getRegion().trim().toLowerCase().replaceAll(" ", ""));
        }
        return weatherInfoView;
    }

    private void validateCity(City city, BindingResult result) {
        boolean error = false;
        if(city.getName() == null || city.getName().trim().isEmpty()){
            result.rejectValue("name", "error.name");
            error = true;
        }
        if(city.getRegion() == null || city.getRegion().trim().isEmpty()){
            result.rejectValue("region", "error.region");
            error = true;
        }
        if(error) {
            throw new CityValidationException(result);
        }
    }
}
