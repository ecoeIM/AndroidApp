package com.example.androidapp.Model;

public class TerrariumData {
    private int terrariumDataId;
    private double temperature;
    private double carbonDioxideLevel;
    private double humidityLevel;
    private double naturalLightLevel;
    private boolean isVentOn;
    private boolean isArtificialLightOn;

    public TerrariumData(int terrariumDataId, double temperature, double carbonDioxideLevel, double humidityLevel, double naturalLightLevel, boolean isVentOn, boolean isArtificialLightOn) {
        this.terrariumDataId = terrariumDataId;
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
        return terrariumDataId;
    }

    public void setTerrariumDataId(int terrariumDataId) {
        this.terrariumDataId = terrariumDataId;
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
