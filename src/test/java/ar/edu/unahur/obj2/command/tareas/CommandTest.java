package ar.edu.unahur.obj2.command.tareas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.command.Habitacion;
import ar.edu.unahur.obj2.command.Robot;
import ar.edu.unahur.obj2.command.invoker.Usuario;

public class CommandTest {

    Robot robotito;
    Usuario pepe;

    @BeforeEach
    @DisplayName("valores iniciales")
    void setUp() {
        robotito = new Robot(80.0);
        pepe = new Usuario(robotito);
        pepe.borrarTareasDelRobot();
    }

    @Test
    @DisplayName("Poner a cargar el robot hasta que se cargue toda la bateria")
    void testCargar() {
        Tarea cargar = new Carge(Robot.getMinutosParaCargaCompleta());
        Double cargaActual = robotito.getNivelDeCarga();
        pepe.agregarTareaAlRobot(cargar);

        pepe.indicarARobotQueComienceConLasTareas();

        assertEquals(100.0, robotito.getNivelDeCarga());
        assertEquals(duracionParaCargaCompleta(cargaActual), cargar.getDuracion());
        assertEquals(1500.0, cargar.getDuracion());
    }

    private Double duracionParaCargaCompleta(Double cargaActual) {
        Double bateriaFaltante = Robot.getCargaMaxima() - cargaActual; // 100 - 80 = 20
        Double cargaCompletaEnSegundos = Robot.getMinutosParaCargaCompleta() * 60; // 125 * 60 = 7500
        return bateriaFaltante * cargaCompletaEnSegundos / Robot.getCargaMaxima(); // 20 * 7500 / 100 = 1500
    }

    @Test
    @DisplayName("Poner a cargar el robot unos 5 minutos y no se carga toda la batreia")
    void testCargar2() {
        Tarea cargar = new Carge(5.0);
        pepe.agregarTareaAlRobot(cargar);

        pepe.indicarARobotQueComienceConLasTareas();

        assertNotEquals(100.0, robotito.getNivelDeCarga());
        assertEquals(5.0 * 60, cargar.getDuracion());
    }

    @Test
    @DisplayName("Limpiar el piso de una habitacion")
    void testClean() {
        Habitacion h = new Habitacion(Boolean.FALSE, 9.0);
        Tarea limpiar = new Clean(h);
        pepe.agregarTareaAlRobot(limpiar);

        pepe.indicarARobotQueComienceConLasTareas();

        assertEquals(Boolean.TRUE, h.getPisoLimpio());
        assertEquals(80.0 - (9.0 * 5), robotito.getNivelDeCarga());
        assertEquals(180 * 9.0, limpiar.getDuracion());
    }

    @Test
    @DisplayName("Encender luz si esta encendida")
    void testLigOn() {
        Habitacion h = new Habitacion(Boolean.TRUE, 9.0);
        Tarea encenderLuz = new LigOn(h);
        pepe.agregarTareaAlRobot(encenderLuz);

        pepe.indicarARobotQueComienceConLasTareas();

        assertEquals(80.0 - (1.0), robotito.getNivelDeCarga());
        assertEquals(25.0, encenderLuz.getDuracion());
    }

    @Test
    @DisplayName("Encender luz si esta apagada")
    void testLigOn2() {
        Habitacion h = new Habitacion(Boolean.FALSE, 9.0);
        Tarea encenderLuz = new LigOn(h);
        pepe.agregarTareaAlRobot(encenderLuz);

        pepe.indicarARobotQueComienceConLasTareas();

        assertEquals(80.0 - (5.0), robotito.getNivelDeCarga());
        assertEquals(90.0, encenderLuz.getDuracion());
    }

    @Test
    @DisplayName("Apagar luz si esta encendida")
    void testLigOff() {
        Habitacion h = new Habitacion(Boolean.TRUE, 9.0);
        Tarea apagarLuz = new LigOff(h);
        pepe.agregarTareaAlRobot(apagarLuz);

        pepe.indicarARobotQueComienceConLasTareas();

        assertEquals(80.0 - (5.0), robotito.getNivelDeCarga());
        assertEquals(90.0, apagarLuz.getDuracion());
    }

    @Test
    @DisplayName("Apagar luz si esta apagada")
    void testLigOff2() {
        Habitacion h = new Habitacion(Boolean.FALSE, 9.0);
        Tarea apagarLuz = new LigOff(h);
        pepe.agregarTareaAlRobot(apagarLuz);

        pepe.indicarARobotQueComienceConLasTareas();

        assertEquals(80.0 - (1.0), robotito.getNivelDeCarga());
        assertEquals(25.0, apagarLuz.getDuracion());
    }

    @Test
    @DisplayName("Ver el promedio de tiempo de las tareas ejecutadas, si esta vacio dará cero")
    void testInfo() {
        Tarea info = new Info();
        pepe.agregarTareaAlRobot(info);

        pepe.indicarARobotQueComienceConLasTareas();

        assertEquals(80.0, robotito.getNivelDeCarga());
        assertEquals(0.0, info.getDuracion());
    }
}
