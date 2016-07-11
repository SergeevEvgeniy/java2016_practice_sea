package com.epam.cars.model;

public class Maker {

    private final String name;
    private final String adress;
    private final int foundYear;

    public Maker(final String name, final String adress, final int foundYear) {
        this.name = name;
        this.adress = adress;
        this.foundYear = foundYear;
    }

    public String getName() {
        return this.name;
    }

    public String getAdress() {
        return this.adress;
    }

    public int getFoundYear() {
        return this.foundYear;
    }
}
