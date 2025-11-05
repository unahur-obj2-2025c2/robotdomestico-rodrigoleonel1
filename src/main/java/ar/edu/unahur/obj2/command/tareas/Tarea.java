package ar.edu.unahur.obj2.command.tareas;

import ar.edu.unahur.obj2.command.Robot;

public interface Tarea {
    void ejecutar(Robot robot);

    Double getDuracion();
}
