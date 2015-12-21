package ccamposfuentes.es.comedores_universitarios_gr;

/**
 * Created by ccamposfuentes on 27/11/15.
 */
public class MenuSemanal {

    String primer_plato;
    String segundo_plato;
    String tercer_plato;
    String cuarto_plato;

    String fecha;

    public MenuSemanal(String fecha, String primer_plato, String segundo_plato, String tercer_plato, String cuarto_plato) {
        this.fecha = fecha;
        this.primer_plato = primer_plato;
        this.segundo_plato = segundo_plato;
        this.tercer_plato = tercer_plato;
        this.cuarto_plato = cuarto_plato;
    }

    public String getPrimer_plato() {
        return primer_plato;
    }

    public String getSegundo_plato() {
        return segundo_plato;
    }

    public String getTercer_plato() {
        return tercer_plato;
    }

    public String getCuarto_plato() {
        return cuarto_plato;
    }

    public String getFecha() {
        return fecha;
    }
}
