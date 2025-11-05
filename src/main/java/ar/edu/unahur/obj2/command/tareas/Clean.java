package ar.edu.unahur.obj2.command.tareas;

import ar.edu.unahur.obj2.command.Habitacion;
import ar.edu.unahur.obj2.command.Robot;

public class Clean extends Command {
    private Habitacion habitacion;

    public Clean(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    @Override
    protected void doEjecutar(Robot robot) {
        habitacion.limpiarPiso();
    }

    @Override
    protected Double consumoDeBateria() {
        return 5 * habitacion.getArea();
    }

    @Override
    public Double getDuracion() {
        return 180 * habitacion.getArea();
    }
}
