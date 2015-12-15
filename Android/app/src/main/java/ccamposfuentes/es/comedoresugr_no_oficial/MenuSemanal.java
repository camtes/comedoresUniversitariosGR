package ccamposfuentes.es.comedoresugr_no_oficial;

/**
 * Created by ccamposfuentes on 27/11/15.
 */
public class MenuSemanal {

    String primer_plato;
    String segundo_plato;
    String tercer_plato;

    int dia;
    String fecha;

    public MenuSemanal(String primer_plato, String segundo_plato, String tercer_plato, int dia,
                       String fecha) {

        this.primer_plato = primer_plato;
        this.segundo_plato = segundo_plato;
        this.tercer_plato = tercer_plato;
        this.dia = dia;
        this.fecha = fecha;
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

    public int getDia() {
        return dia;
    }

    public String getFecha() {
        return fecha;
    }
}
