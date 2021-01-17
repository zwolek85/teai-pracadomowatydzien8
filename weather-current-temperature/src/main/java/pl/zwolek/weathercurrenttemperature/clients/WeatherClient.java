package pl.zwolek.weathercurrenttemperature.clients;

import pl.zwolek.weathercurrenttemperature.controllers.dtos.City;
import pl.zwolek.weathercurrenttemperature.controllers.dtos.WeatherForecas;

public interface WeatherClient {

    WeatherForecas getWeatherForecast(Integer woeid);

    Integer getLocation(City city);
}
