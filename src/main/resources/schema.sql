CREATE TABLE IF NOT EXISTS Location (
    woeid       INTEGER  PRIMARY KEY,
    version     INTEGER NOT NULL,
    city        VARCHAR(25),
    region      VARCHAR(25),
    country     VARCHAR(50),
    lat         DOUBLE,
    long        DOUBLE,
    timezone_id VARCHAR(50)
);

COMMENT ON TABLE Location IS 'Местоположение, город';
COMMENT ON COLUMN Location.woeid IS 'Уникальный идентификатор города (WOEID - Where On Earth IDentifier)';
COMMENT ON COLUMN Location.city IS 'Название города';
COMMENT ON COLUMN Location.region IS 'Регион';
COMMENT ON COLUMN Location.country IS 'Страна';
COMMENT ON COLUMN Location.lat IS 'Широта';
COMMENT ON COLUMN Location.long IS 'Долгода';
COMMENT ON COLUMN Location.timezone_id IS 'Часовой пояс';

CREATE TABLE IF NOT EXISTS Current_observation (
    id     INTEGER PRIMARY KEY AUTO_INCREMENT,
    version         INTEGER NOT NULL,
    pub_date        TIMESTAMP,
    location_id     INTEGER REFERENCES Location (woeid) ON DELETE CASCADE ON UPDATE CASCADE
);

COMMENT ON TABLE Current_observation IS 'Текущий обзор погоды';
COMMENT ON COLUMN Current_observation.id IS 'Уникальный идентификатор текущего обзора погоды';
COMMENT ON COLUMN Current_observation.pub_date IS 'Дата и время публикации этого прогноза';
COMMENT ON COLUMN Current_observation.location_id IS 'Уникальный идентификатор города, внешний ключ';

CREATE TABLE IF NOT EXISTS Wind (
    cur_obs_id  INTEGER  PRIMARY KEY REFERENCES Current_observation (id) ON DELETE CASCADE ON UPDATE CASCADE,
    version     INTEGER NOT NULL,
    chill       INTEGER,
    direction   INTEGER,
    speed       FLOAT
);

COMMENT ON TABLE Wind IS 'Текущая информация о ветре';
COMMENT ON COLUMN Wind.cur_obs_id IS 'Уникальный идентификатор текущего обзора погоды, первичный и внешний ключ';
COMMENT ON COLUMN Wind.chill IS 'Жёсткость погоды (wind chill), в градусах Цельсия';
COMMENT ON COLUMN Wind.direction IS 'Направление ветра, в градусах';
COMMENT ON COLUMN Wind.speed IS 'Скорость ветра, в км/ч';

CREATE TABLE IF NOT EXISTS Atmosphere (
    cur_obs_id  INTEGER  PRIMARY KEY REFERENCES Current_observation (id) ON DELETE CASCADE ON UPDATE CASCADE,
    version     INTEGER NOT NULL,
    humidity    INTEGER,
    visibility  FLOAT,
    pressure    FLOAT,
    rising      INTEGER
);

COMMENT ON TABLE Atmosphere IS 'Информация о текущем атмосферном давлении, влажности и видимости';
COMMENT ON COLUMN Atmosphere.cur_obs_id IS 'Уникальный идентификатор текущего обзора погоды, первичный и внешний ключ';
COMMENT ON COLUMN Atmosphere.humidity IS 'Влажность, в процентах';
COMMENT ON COLUMN Atmosphere.visibility IS 'Видимость, в километрах';
COMMENT ON COLUMN Atmosphere.pressure IS 'Давление, в мбар';
COMMENT ON COLUMN Atmosphere.rising IS
'Состояние барометрического давления: устойчивое (0), повышение (1) или падение (2)';

CREATE TABLE IF NOT EXISTS Astronomy (
    cur_obs_id  INTEGER  PRIMARY KEY REFERENCES Current_observation (id) ON DELETE CASCADE ON UPDATE CASCADE,
    version  INTEGER NOT NULL,
    sunrise  VARCHAR(25),
    sunset   VARCHAR(25)
);

COMMENT ON TABLE Astronomy IS 'Информация о текущих астрономических условиях';
COMMENT ON COLUMN Astronomy.cur_obs_id IS 'Уникальный идентификатор текущего обзора погоды, первичный и внешний ключ';
COMMENT ON COLUMN Astronomy.sunrise IS 'Время вохода';
COMMENT ON COLUMN Astronomy.sunset IS 'Время заката';

CREATE TABLE IF NOT EXISTS Condition (
    cur_obs_id  INTEGER  PRIMARY KEY REFERENCES Current_observation (id) ON DELETE CASCADE ON UPDATE CASCADE,
    version     INTEGER NOT NULL,
    text        VARCHAR(25),
    code        SMALLINT,
    temperature INTEGER
);

COMMENT ON TABLE Condition IS 'Текущее состояние погоды';
COMMENT ON COLUMN Condition.cur_obs_id IS 'Уникальный идентификатор текущего обзора погоды, первичный и внешний ключ';
COMMENT ON COLUMN Condition.text IS 'Текстовое описание состояния';
COMMENT ON COLUMN Condition.code IS 'Код состояния';
COMMENT ON COLUMN Condition.temperature IS 'Температура';

CREATE TABLE IF NOT EXISTS Forecast (
    id          INTEGER  PRIMARY KEY AUTO_INCREMENT,
    version     INTEGER NOT NULL,
    day         VARCHAR(3),
    date        DATE,
    low         TINYINT,
    high        TINYINT,
    text        VARCHAR(25),
    code        SMALLINT,
    location_id INTEGER REFERENCES Location (woeid) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE INDEX IX_Location_City ON Location (city);