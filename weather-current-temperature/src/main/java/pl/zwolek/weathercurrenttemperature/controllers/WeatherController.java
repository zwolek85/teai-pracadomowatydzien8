package pl.zwolek.weathercurrenttemperature.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.zwolek.weathercurrenttemperature.clients.WeatherClient;
import pl.zwolek.weathercurrenttemperature.controllers.dtos.City;
import pl.zwolek.weathercurrenttemperature.controllers.dtos.ConsolidatedWeather;
import pl.zwolek.weathercurrenttemperature.controllers.dtos.WeatherForecas;
import pl.zwolek.weathercurrenttemperature.services.CurrentWeatherService;

import java.util.List;

//http://localhost:8080/api/weather
@Controller
@RequestMapping("/api/weather")
public class WeatherController {

    private WeatherClient weatherClient;
    private CurrentWeatherService currentWeatherService;

    public WeatherController(WeatherClient weatherClient, CurrentWeatherService currentWeatherService) {
        this.weatherClient = weatherClient;
        this.currentWeatherService = currentWeatherService;
    }

    @GetMapping
    public String getWeatherLocationView(Model model) {
        model.addAttribute("city", new City());
        return "weatherLocation";
    }

    @PostMapping("/showWather")
    public String getWeatherForLocation(@ModelAttribute City city, Model model) {
        Integer location = weatherClient.getLocation(city);
        model.addAttribute("city", city);
        if (location != null) {
            WeatherForecas weatherForecast = weatherClient.getWeatherForecast(location);
            List<ConsolidatedWeather> consolidatedWeather = weatherForecast.getConsolidatedWeather();
            model.addAttribute("consolidatedWeathers", consolidatedWeather);
            currentWeatherService.saveCurrentWeather( location, city.getName(), consolidatedWeather);
            return "weatherForecast";
        }
        return "locationDidntExist";
    }
}
