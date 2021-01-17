package pl.zwolek.weathercurrenttemperature.clients;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.zwolek.weathercurrenttemperature.controllers.dtos.City;
import pl.zwolek.weathercurrenttemperature.controllers.dtos.Location;
import pl.zwolek.weathercurrenttemperature.controllers.dtos.WeatherForecas;

@Component
class WeatherClientImpl implements WeatherClient {

    RestTemplate restTemplate;

    WeatherClientImpl() {
        this.restTemplate = new RestTemplate();
    }

    public WeatherForecas getWeatherForecast(Integer woeid) {
        WeatherForecas weatherForecas = restTemplate
                .getForObject("https://www.metaweather.com/api/location/" + woeid, WeatherForecas.class);
        return weatherForecas;
    }

    public Integer getLocation(City city) {
        Location[] locations = restTemplate
                .getForObject("https://www.metaweather.com/api/location/search/?query=" + city.getName(), Location[].class);
        if (locations.length > 0) {
            return locations[0].getWoeid();
        }

        return null;
    }
}
