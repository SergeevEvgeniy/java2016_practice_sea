/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car_maker_task;

/**
 *
 * @author Женя
 */
public class Car {

    Maker _maker;
    String _mark;
    int _year;
    String _color;

    public Car(Maker maker, String mark, int year, String color) {
        _maker = maker;
        _mark = mark;
        _year = year;
        _color = color;
    }

}
