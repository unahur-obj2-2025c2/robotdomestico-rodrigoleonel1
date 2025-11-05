package ar.edu.unahur.obj2.command;

public class Habitacion {
    private Boolean luzEncendida;
    private final Double area;
    private Boolean pisoLimpio;

    public Habitacion(Boolean luzEncendida, Double area) {
        this.luzEncendida = luzEncendida;
        this.area = area;
        this.pisoLimpio = Boolean.FALSE;
    }

    public void encenderLuz() {
        luzEncendida = Boolean.TRUE;
    }

    public void apagarLuz() {
        luzEncendida = Boolean.FALSE;
    }

    public Boolean getLuzEncendida() {
        return luzEncendida;
    }

    public Double getArea() {
        return area;
    }

    public Boolean getPisoLimpio() {
        return pisoLimpio;
    }

    public void limpiarPiso() {
        pisoLimpio = Boolean.TRUE;
    }
}
