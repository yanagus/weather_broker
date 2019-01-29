package work.exceptionHandler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import work.view.City;

/**
 * Обработчик ошибок
 */
@ControllerAdvice
public class WeatherExceptionHandler {

    /**
     * Обработчик ошибки при заполнении формы
     *
     * @param ex ошибка при заполнении формы
     * @return JSP-страница с текстом ошибки
     */
    @ExceptionHandler(CityValidationException.class)
    public ModelAndView handleCityValidationException(CityValidationException ex) {
        return new ModelAndView("index", ex.getResult().getModel());
    }

    /**
     * Обработчик других ошибок сервиса
     * @param ex ошибка сервиса
     * @return JSP-страница с текстом ошибки
     */
    @ExceptionHandler(WeatherException.class)
    public ModelAndView handleWeatherException(WeatherException ex) {
        ModelAndView view = new ModelAndView("index", "city", new City());
        view.addObject("exception", ex);
        return view;
    }

}
