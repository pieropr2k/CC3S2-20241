package org.example.lluvia;

public class Lluvia {
    private int quantity;
    private double humidity;

    public Lluvia(double humidity){
        this.humidity = humidity;
        this.quantity = (int) (humidity * 100);
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isIntense() {
        return humidity > 0.4;
    }
}
