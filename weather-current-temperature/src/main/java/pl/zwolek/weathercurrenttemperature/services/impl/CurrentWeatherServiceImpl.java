package pl.zwolek.weathercurrenttemperature.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zwolek.weathercurrenttemperature.controllers.dtos.ConsolidatedWeather;
import pl.zwolek.weathercurrenttemperature.entities.CurrentWeather;
import pl.zwolek.weathercurrenttemperature.repositories.CurrentWeatherRepository;
import pl.zwolek.weathercurrenttemperature.services.CurrentWeatherService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CurrentWeatherServiceImpl implements CurrentWeatherService {

    private CurrentWeatherRepository currentWeatherRepository;

    @Autowired
    public CurrentWeatherServiceImpl(CurrentWeatherRepository currentWeatherRepository) {
        this.currentWeatherRepository = currentWeatherRepository;
    }


    @Override
    public void saveCurrentWeather(int location, String place, List<ConsolidatedWeather> consolidatedWeather) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateNow = new Date();
        String dateNowAsString = simpleDateFormat.format(dateNow);
        consolidatedWeather.stream()
                .filter(cw -> dateNowAsString.equals(cw.getApplicableDate()))
                .findFirst()
                .ifPresent(currentWeather -> {
                    currentWeatherRepository.save(new CurrentWeather(dateNow, place, currentWeather.getTheTemp(), location));
                });

    }

    @Override
    public Set<CurrentWeather> findDistinctByPlace() {
        return new HashSet<>(currentWeatherRepository.findAll());
    }
}
