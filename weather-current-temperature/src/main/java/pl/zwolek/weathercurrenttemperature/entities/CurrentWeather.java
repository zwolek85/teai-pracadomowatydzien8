package pl.zwolek.weathercurrenttemperature.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
public class CurrentWeather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String place;
    private double temp;
    private int woeid;

    public CurrentWeather() {
    }

    public CurrentWeather(Date date, String place, double temp, int woeid) {
        this.date = date;
        this.place = place;
        this.temp = temp;
        this.woeid = woeid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getWoeid() {
        return woeid;
    }

    public void setWoeid(int woeid) {
        this.woeid = woeid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrentWeather that = (CurrentWeather) o;
        return Double.compare(that.temp, temp) == 0 && woeid == that.woeid && id.equals(that.id) && Objects.equals(date, that.date) && Objects.equals(place, that.place);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, place, temp, woeid);
    }

    @Override
    public String toString() {
        return "CurrentWeather{" +
                "id=" + id +
                ", date=" + date +
                ", place='" + place + '\'' +
                ", temp=" + temp +
                ", woeid=" + woeid +
                '}';
    }
}
