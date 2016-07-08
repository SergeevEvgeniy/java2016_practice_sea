public class Car {

    private final Maker _maker;
    private final String _model;
    private final int _year;
    private final String _color;

    public Car(Maker maker, String model, int year, String color) {
        _maker = maker;
        _model = model;
        _year = year;
        _color = color;
    }

    public Maker getMaker() {
        return _maker;
    }

    public String getModel() {
        return _model;
    }

    public String getColor() {
        return _color;
    }

    public int getYear() {
        return _year;
    }
}
