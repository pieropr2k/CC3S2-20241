package org.example.alertas;

public class Alertas {
    public String intenseRain(boolean isIntense){
        return isIntense ? "Alerta de lluvia intensa" : "Lluvia suave hoy";
    }

    public String vientoFuerte(boolean isWindy){
        return isWindy ? "Alerta de viento fuerte" : "Viento normal";
    }
}
