package work.exceptionHandler;

import org.springframework.validation.BindingResult;

/**
 * Ошибка при заполнении формы
 */
public class CityValidationException extends RuntimeException {

    private BindingResult result;

    public CityValidationException() {
    }

    /**
     * Конструктор ошибки при заполнении формы
     *
     * @param result данные с указанием ошибки валидации
     */
    public CityValidationException(BindingResult result) {
        this.result = result;
    }

    public BindingResult getResult() {
        return result;
    }

    public void setResult(BindingResult result) {
        this.result = result;
    }

}
