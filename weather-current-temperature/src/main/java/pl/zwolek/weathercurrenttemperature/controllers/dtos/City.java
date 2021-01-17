package pl.zwolek.weathercurrenttemperature.controllers.dtos;

public class City {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameToUpperCase(){
        if(name != null){
            return name.toUpperCase();
        }
        return null;
    }
}
