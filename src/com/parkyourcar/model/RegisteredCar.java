package com.parkyourcar.model;

public class RegisteredCar {
	
	private int id;
    private String carBrand;
    private String carParkZone;
    public String getCarParkZone() {
		return carParkZone;
	}
	public void setCarParkZone(String carParkZone) {
		this.carParkZone = carParkZone;
	}
	public RegisteredCar(int id, String carBrand, String carPlate ,String carDriver, String carColor, String carParkZone) {
		super();
		this.id = id;
		this.carBrand = carBrand;
		this.carPlate = carPlate;
		this.carDriver = carDriver;
		this.carColor = carColor;
		this.carParkZone = carParkZone;
	}
	
	public RegisteredCar() {
		// TODO Auto-generated constructor stub
	}

	private String carPlate;
    private String carDriver;
    private String carColor;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCarBrand() {
		return carBrand;
	}
	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}
	public String getCarPlate() {
		return carPlate;
	}
	public void setCarPlate(String carPlate) {
		this.carPlate = carPlate;
	}
	public String getCarDriver() {
		return carDriver;
	}
	public void setCarDriver(String carDriver) {
		this.carDriver = carDriver;
	}
	public String getCarColor() {
		return carColor;
	}
	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}
    
    

}
