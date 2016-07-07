package car_maker_task;

public class Output {

    public void write(Car car) {
        System.out.println(car.getMaker().getName() + " " 
                + car.getModel() + " "
                + car.getYear() + " " + car.getColor());
    }
}