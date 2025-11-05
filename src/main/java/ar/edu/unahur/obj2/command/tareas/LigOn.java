package ar.edu.unahur.obj2.command.tareas;

import ar.edu.unahur.obj2.command.Habitacion;
import ar.edu.unahur.obj2.command.Robot;

public class LigOn extends Command {
    private Habitacion habitacion;
    private Boolean estabaEncendida;

    public LigOn(Habitacion habitacion) {
        this.habitacion = habitacion;
        this.estabaEncendida = habitacion.getLuzEncendida();
    }

    @Override
    protected void doEjecutar(Robot robot) {
        habitacion.encenderLuz();
    }

    @Override
    protected Double consumoDeBateria() {
        return estabaEncendida ? 1.0 : 5.0;
    }

    @Override
    public Double getDuracion() {
        return estabaEncendida ? 25.0 : 90.0;
    }
}
