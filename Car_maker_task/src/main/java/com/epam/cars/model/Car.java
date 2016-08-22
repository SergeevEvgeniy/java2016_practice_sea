package com.epam.cars.model;

public class Car {

    private long id;
    private final Maker maker;
    private final String model;
    private final int year;
    private final String color;

    public Car(final Maker maker, final String model, final int year, final String color) {
        this.maker = maker;
        this.model = model;
        this.year = year;
        this.color = color;
    }

    public Maker getMaker() {
        return this.maker;
    }

    public String getModel() {
        return this.model;
    }

    public String getColor() {
        return this.color;
    }

    public int getYear() {
        return this.year;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
