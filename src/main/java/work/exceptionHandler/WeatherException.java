package work.exceptionHandler;

/**
 * Ошибка сервиса
 */
public class WeatherException extends RuntimeException {

    /**
     * Конструктор ошибки сервиса
     */
    public WeatherException(String message) {
        super(message);
    }
}
