package ar.edu.unahur.obj2.command.tareas;

import ar.edu.unahur.obj2.command.Robot;

public class Carge extends Command {
    private final Double MINUTOS;
    private Double cargaRestante = 0.0;

    public Carge(Double minutos) {
        this.MINUTOS = minutos;
    }

    @Override
    protected void doEjecutar(Robot robot) {
        cargaRestante = robot.getNivelDeCarga();
        robot.cargar(cantidadDeBateriaACargar());
    }

    private Double segundosNecesariosParaCargaCompleta() {
        Double bateriaFaltante = Robot.getCargaMaxima() - cargaRestante;
        Double cargaCompletaEnSegundos = Robot.getMinutosParaCargaCompleta() * 60;
        return bateriaFaltante * cargaCompletaEnSegundos / Robot.getCargaMaxima();
    }

    private Double cantidadDeBateriaACargar() {
        return MINUTOS * Robot.getCargaMaxima() / Robot.getMinutosParaCargaCompleta();
    }

    @Override
    protected Double consumoDeBateria() {
        return 0.0;
    }

    @Override
    public Double getDuracion() {
        return Double.min(MINUTOS * 60, segundosNecesariosParaCargaCompleta());
    }
}
