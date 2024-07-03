package org.example.auto_acciones;

public class AutoAcciones {
    public String activatarSistemaRiego(boolean riego) {
        return riego ? "Activar Sistema de Riego" : "No activar riego";
    }

    public String cerrarPersianas(boolean vientoEsFuerte) {
        return vientoEsFuerte ? "Cerrar persianas" : "No necesidad";
    }
}
