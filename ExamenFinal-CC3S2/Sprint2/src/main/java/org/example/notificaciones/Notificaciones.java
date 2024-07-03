package org.example.notificaciones;

import org.example.alertas.Alertas;
import org.example.auto_acciones.AutoAcciones;
import org.example.data_server.DataServer;

import java.util.ArrayList;
import java.util.List;

public class Notificaciones {
    private AutoAcciones autoAcciones;
    private Alertas alertas;
    private DataServer dataServer;

    public Notificaciones(AutoAcciones autoAcciones, Alertas alertas, DataServer dataServer){
        this.autoAcciones = autoAcciones;
        this.alertas = alertas;
    }

    public List<String> notifyAlerts(){
        List<String> notifies = new ArrayList<>();
        notifies.add(alertas.intenseRain(dataServer.getLluvia().isIntense()));
        notifies.add(alertas.vientoFuerte(dataServer.getViento().isHigh()));
        return notifies;
    }

    public List<String> notifyActions(){
        List<String> actions = new ArrayList<>();
        actions.add(autoAcciones.activatarSistemaRiego(dataServer.getLluvia().isIntense()));
        actions.add(autoAcciones.cerrarPersianas(dataServer.getViento().isHigh()));
        return actions;
    }
}
