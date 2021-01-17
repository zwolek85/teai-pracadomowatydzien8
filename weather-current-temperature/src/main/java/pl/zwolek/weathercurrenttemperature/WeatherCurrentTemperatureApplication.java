package pl.zwolek.weathercurrenttemperature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WeatherCurrentTemperatureApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherCurrentTemperatureApplication.class, args);
    }

}
