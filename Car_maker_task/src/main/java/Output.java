public class Output {

    public void Write(Car car) {
        System.out.println(car.getMaker().getName() + " " 
                + car.getModel() + " "
                + car.getYear() + " " + car.getColor());
    }
}