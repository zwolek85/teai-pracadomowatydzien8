package pl.zwolek.weathercurrenttemperature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zwolek.weathercurrenttemperature.entities.CurrentWeather;

@Repository
public interface CurrentWeatherRepository extends JpaRepository<CurrentWeather, Long> {

}
