package com.example.androidapp.model;

public class TerrariumData {
    private int id;
    private double temperature;
    private double carbonDioxideLevel;
    private double humidityLevel;
    private double naturalLightLevel;
    private boolean isVentOn;
    private boolean isArtificialLightOn;

    public TerrariumData(int terrariumDataId, double temperature, double carbonDioxideLevel, double humidityLevel, double naturalLightLevel, boolean isVentOn, boolean isArtificialLightOn) {
        this.id = terrariumDataId;
        this.temperature = temperature;
        this.carbonDioxideLevel = carbonDioxideLevel;
        this.humidityLevel = humidityLevel;
        this.naturalLightLevel = naturalLightLevel;
        this.isVentOn = isVentOn;
        this.isArtificialLightOn = isArtificialLightOn;
    }

    public TerrariumData() {
    }

    public int getTerrariumDataId() {
        return id;
    }

    public void setTerrariumDataId(int terrariumDataId) {
        this.id = terrariumDataId;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getCarbonDioxideLevel() {
        return carbonDioxideLevel;
    }

    public void setCarbonDioxideLevel(double carbonDioxideLevel) {
        this.carbonDioxideLevel = carbonDioxideLevel;
    }

    public double getHumidityLevel() {
        return humidityLevel;
    }

    public void setHumidityLevel(double humidityLevel) {
        this.humidityLevel = humidityLevel;
    }

    public double getNaturalLightLevel() {
        return naturalLightLevel;
    }

    public void setNaturalLightLevel(double naturalLightLevel) {
        this.naturalLightLevel = naturalLightLevel;
    }

     public boolean isVentOn() {
       return isVentOn;
    }

    public void setVentOn(boolean ventOn) {
        isVentOn = ventOn;
    }

    public boolean isArtificialLightOn() {
        return isArtificialLightOn;
    }

    public void setArtificialLightOn(boolean artificialLightOn) {
        isArtificialLightOn = artificialLightOn;
    }
}
