package pl.zwolek.weathercurrenttemperature.services;

import pl.zwolek.weathercurrenttemperature.controllers.dtos.ConsolidatedWeather;
import pl.zwolek.weathercurrenttemperature.entities.CurrentWeather;

import java.util.List;
import java.util.Set;

public interface CurrentWeatherService {

    void saveCurrentWeather(int location, String place,  List<ConsolidatedWeather> consolidatedWeather);

    Set<CurrentWeather> findDistinctByPlace();
}
