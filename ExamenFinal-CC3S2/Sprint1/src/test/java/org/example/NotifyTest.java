package org.example;

import org.example.alertas.Alertas;
import org.example.auto_acciones.AutoAcciones;
import org.example.data_server.DataServer;
import org.example.lluvia.Lluvia;
import org.example.notificaciones.Notificaciones;
import org.example.viento.Viento;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NotifyTest {
    @Mock
    private DataServer dataServerMock;
    @Mock
    private Alertas alertasMock;
    @Mock
    private AutoAcciones autoAcciones;
    @InjectMocks
    private Notificaciones notificaciones;

    @Test
    public void notifyAlerts() {
        /*
        // Configurar stub
        when(dataServerMock.getLluvia()).thenReturn(new Lluvia(0.6));
        //when(dataServerMock.getLluvia().isIntense()).thenReturn(true);
        when(dataServerMock.getViento()).thenReturn(new Viento(100));
        //when(dataServerMock.getViento().isHigh()).thenReturn(true);
        assertEquals(notificaciones.notifyAlerts().get(0), true);
        assertEquals(notificaciones.notifyAlerts().get(1), true);
        */
    }

    @Test
    public void notifyActions() {
        /*
        // Configurar stub
        when(dataServerMock.getLluvia().isIntense()).thenReturn(true);
        when(dataServerMock.getViento().isHigh()).thenReturn(true);
        assertEquals(notificaciones.notifyActions().get(0), true);
        assertEquals(notificaciones.notifyActions().get(1), true);
        */
    }
}