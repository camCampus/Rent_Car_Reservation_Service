package com.example.rent_car_reservation_service.Exception;

public class DateOnPastException extends Exception{
    public DateOnPastException(String message)
    {
        super(message);
    }

}
