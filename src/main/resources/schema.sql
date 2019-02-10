CREATE TABLE IF NOT EXISTS Location (
    woeid       INTEGER  PRIMARY KEY COMMENT 'Уникальный идентификатор города (WOEID - Where On Earth IDentifier)',
    version     INTEGER NOT NULL,
    city        VARCHAR(25) COMMENT 'Название города',
    region      VARCHAR(25) COMMENT 'Регион',
    country     VARCHAR(50) COMMENT 'Страна',
    lat         DOUBLE COMMENT 'Широта',
    lon         DOUBLE COMMENT 'Долгота',
    timezone_id VARCHAR(50) COMMENT 'Часовой пояс'
)
COMMENT 'Местоположение, город';

CREATE TABLE IF NOT EXISTS Current_observation (
    id     INTEGER PRIMARY KEY AUTO_INCREMENT COMMENT 'Уникальный идентификатор текущего обзора погоды',
    version         INTEGER NOT NULL,
    pub_date        INTEGER COMMENT 'Дата и время публикации этого прогноза в миллисекундах',
    location_id     INTEGER COMMENT 'Уникальный идентификатор города, внешний ключ'
    REFERENCES Location (woeid) ON DELETE CASCADE ON UPDATE CASCADE
)
COMMENT 'Текущий обзор погоды';

CREATE TABLE IF NOT EXISTS Wind (
    cur_obs_id  INTEGER  PRIMARY KEY COMMENT 'Уникальный идентификатор текущего обзора погоды, первичный и внешний ключ'
    REFERENCES Current_observation (id) ON DELETE CASCADE ON UPDATE CASCADE,
    version     INTEGER NOT NULL,
    chill       INTEGER COMMENT 'Жёсткость погоды (wind chill), в градусах Цельсия',
    direction   INTEGER COMMENT 'Направление ветра, в градусах',
    speed       FLOAT COMMENT 'Скорость ветра, в км/ч'
)
COMMENT 'Текущая информация о ветре';

CREATE TABLE IF NOT EXISTS Atmosphere (
    cur_obs_id  INTEGER  PRIMARY KEY COMMENT 'Уникальный идентификатор текущего обзора погоды, первичный и внешний ключ'
    REFERENCES Current_observation (id) ON DELETE CASCADE ON UPDATE CASCADE,
    version     INTEGER NOT NULL,
    humidity    INTEGER COMMENT 'Влажность, в процентах',
    visibility  FLOAT COMMENT 'Видимость, в километрах',
    pressure    FLOAT COMMENT 'Давление, в мбар',
    rising      INTEGER COMMENT 'Состояние барометрического давления: устойчивое (0), повышение (1) или падение (2)'
)
COMMENT 'Информация о текущем атмосферном давлении, влажности и видимости';

CREATE TABLE IF NOT EXISTS Astronomy (
    cur_obs_id  INTEGER  PRIMARY KEY COMMENT 'Уникальный идентификатор текущего обзора погоды, первичный и внешний ключ'
    REFERENCES Current_observation (id) ON DELETE CASCADE ON UPDATE CASCADE,
    version  INTEGER NOT NULL,
    sunrise  VARCHAR(25) COMMENT 'Время вохода',
    sunset   VARCHAR(25) COMMENT 'Время заката'
)
COMMENT 'Информация о текущих астрономических условиях';

CREATE TABLE IF NOT EXISTS Condit (
    cur_obs_id  INTEGER  PRIMARY KEY COMMENT 'Уникальный идентификатор текущего обзора погоды, первичный и внешний ключ'
    REFERENCES Current_observation (id) ON DELETE CASCADE ON UPDATE CASCADE,
    version     INTEGER NOT NULL,
    text        VARCHAR(25) COMMENT 'Текстовое описание состояния',
    code        SMALLINT COMMENT 'Код состояния',
    temperature INTEGER COMMENT 'Температура'
)
COMMENT 'Текущее состояние погоды';

CREATE TABLE IF NOT EXISTS Forecast (
    id          INTEGER  PRIMARY KEY AUTO_INCREMENT COMMENT 'Уникальный идентификатор прогноза погоды',
    version     INTEGER NOT NULL,
    day         VARCHAR(3) COMMENT 'День недели',
    date        INTEGER COMMENT 'Дата в миллисекундах',
    low         TINYINT COMMENT 'Минимальная температура',
    high        TINYINT COMMENT 'Максимальная температура',
    text        VARCHAR(25) COMMENT 'Текстовое описание состояния',
    code        SMALLINT COMMENT 'Код состояния',
    location_id INTEGER COMMENT 'Уникальный идентификатор города, внешний ключ'
    REFERENCES Location (woeid) ON DELETE CASCADE ON UPDATE CASCADE
)
COMMENT 'Прогноз погоды';

CREATE INDEX IX_Location_City ON Location (city);