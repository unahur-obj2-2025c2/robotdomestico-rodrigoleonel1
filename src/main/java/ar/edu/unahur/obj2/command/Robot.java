package ar.edu.unahur.obj2.command;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.command.tareas.Tarea;

public class Robot {
    private Double nivelDeCarga;
    private static final Double MINUTOS_PARA_CARGA_COMPLETA = 125.0; // o 7500 segundos
    private static final Double CARGA_MAXIMA = 100.0;
    private List<Tarea> tareasEjecutadas = new ArrayList<>();

    public Robot(Double nivelDeCarga) {
        this.nivelDeCarga = nivelDeCarga;
    }

    public void disminuirBateria(Double cantidad) {
        nivelDeCarga -= cantidad;
    }

    public Double getNivelDeCarga() {
        return Math.round(nivelDeCarga * 100.0) / 100.0;
    }

    public void cargar(Double valor) {
        nivelDeCarga = Double.min(CARGA_MAXIMA, nivelDeCarga + valor);
    }

    public static Double getMinutosParaCargaCompleta() {
        return MINUTOS_PARA_CARGA_COMPLETA;
    }

    public static Double getCargaMaxima() {
        return CARGA_MAXIMA;
    }

    public List<Tarea> getTareasEjecutadas() {
        return tareasEjecutadas;
    }

    public void ejecutar(List<Tarea> tareas) {
        tareas.forEach(t -> {
            t.ejecutar(this);
            tareasEjecutadas.add(t);
        });
    }
}
