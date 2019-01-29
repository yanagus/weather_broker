package work.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import work.view.WeatherInfoView;

/**
 * Класс, отправляющий данные о погоде в JMS-очередь
 */
@Component
public class WeatherJmsSender {

    private JmsTemplate jmsTemplate;

    @Autowired
    public WeatherJmsSender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    /**
     * Отправить данные о погоде в JMS-очередь
     * @param weatherInfo данные о погоде
     */
    public void sendWeather(final WeatherInfoView weatherInfo) {
        jmsTemplate.convertAndSend("queue", weatherInfo);
    }
}
