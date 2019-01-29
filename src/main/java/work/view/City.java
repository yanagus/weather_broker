package work.view;

/**
 * Класс для JSP-формы
 */
public class City {

    /**
     * Название города
     */
    private String name;

    /**
     * Регион
     */
    private String region;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
