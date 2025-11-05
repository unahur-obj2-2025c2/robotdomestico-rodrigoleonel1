package ar.edu.unahur.obj2.command.invoker;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.command.Robot;
import ar.edu.unahur.obj2.command.tareas.Tarea;

public class Usuario {
    private List<Tarea> tareas;
    private Robot robot;

    public Usuario(Robot robot) {
        this.robot = robot;
        this.tareas = new ArrayList<>();
    }

    public void agregarTareaAlRobot(Tarea tarea) {
        tareas.add(tarea);
    }

    public void borrarTareasDelRobot() {
        tareas.clear();
    }

    public void indicarARobotQueComienceConLasTareas() {
        robot.ejecutar(tareas);
    }
}
