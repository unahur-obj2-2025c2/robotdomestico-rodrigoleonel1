package ar.edu.unahur.obj2.command.tareas;

import ar.edu.unahur.obj2.command.Robot;

public abstract class Command implements Tarea {
    @Override
    public void ejecutar(Robot robot) {
        doEjecutar(robot);
        robot.disminuirBateria(consumoDeBateria());
    }

    protected abstract void doEjecutar(Robot robot);

    protected abstract Double consumoDeBateria();
}
