package org.example.viento;

public class Viento {
    private int velocidad;
    private int presionAtmosferica;

    public Viento(int presionAtmosferica) {
        this.velocidad = 13000/presionAtmosferica;
        this.presionAtmosferica = presionAtmosferica;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getPresionAtmosferica() {
        return presionAtmosferica;
    }

    public void setPresionAtmosferica(int presionAtmosferica) {
        this.presionAtmosferica = presionAtmosferica;
    }

    public boolean isHigh() {
        return presionAtmosferica < 270;
    }
}
