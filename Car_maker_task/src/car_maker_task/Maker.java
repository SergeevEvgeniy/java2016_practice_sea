package car_maker_task;

public class Maker {

    private final String _name;
    private final String _adress;
    private final int _found_year;

    public Maker(String name, String adress, int found_year) {
        _name = name;
        _adress = adress;
        _found_year = found_year;
    }

    public String getName() {
        return _name;
    }

    public String getAdress() {
        return _adress;
    }

    public int getFoundYear() {
        return _found_year;
    }
}
