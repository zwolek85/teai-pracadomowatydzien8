package pl.zwolek.weathercurrenttemperature.schedulers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.zwolek.weathercurrenttemperature.clients.WeatherClient;
import pl.zwolek.weathercurrenttemperature.controllers.dtos.WeatherForecas;
import pl.zwolek.weathercurrenttemperature.entities.CurrentWeather;
import pl.zwolek.weathercurrenttemperature.services.CurrentWeatherService;

import java.util.Set;

@Component
class SaveWeatherCurrentTemperatureScheduled {

    private WeatherClient weatherClient;
    private CurrentWeatherService currentWeatherService;

    @Autowired
    public SaveWeatherCurrentTemperatureScheduled(WeatherClient weatherClient, CurrentWeatherService currentWeatherService) {
        this.weatherClient = weatherClient;
        this.currentWeatherService = currentWeatherService;
    }

    @Scheduled(fixedDelay = 3_600_000)
    void run() {

        Set<CurrentWeather> distinctByPlace = currentWeatherService.findDistinctByPlace();
        distinctByPlace.stream().forEach(currentWeather -> {
            WeatherForecas weatherForecast = weatherClient.getWeatherForecast(currentWeather.getWoeid());
            currentWeatherService.saveCurrentWeather(
                    currentWeather.getWoeid(),
                    currentWeather.getPlace(),
                    weatherForecast.getConsolidatedWeather());
        });


    }


}
