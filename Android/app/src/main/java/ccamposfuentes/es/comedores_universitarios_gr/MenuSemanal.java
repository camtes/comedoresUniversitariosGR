package ccamposfuentes.es.comedores_universitarios_gr;

/**
 * Created by ccamposfuentes on 27/11/15.
 */
public class MenuSemanal {

    String primer_plato;
    String segundo_plato;
    String tercer_plato;
    String cuarto_plato;
    boolean plato1_fav, plato2_fav, plato3_fav;

    String fecha;

    public MenuSemanal(String fecha, String primer_plato, String segundo_plato, String tercer_plato, String cuarto_plato) {
        this.fecha = fecha;
        this.primer_plato = primer_plato;
        this.segundo_plato = segundo_plato;
        this.tercer_plato = tercer_plato;
        this.cuarto_plato = cuarto_plato;
        this.plato1_fav = false;
        this.plato2_fav = false;
        this.plato3_fav = false;
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

    public boolean isPlato1_fav() {
        return plato1_fav;
    }

    public void setPlato1_fav(boolean plato1_fav) {
        this.plato1_fav = plato1_fav;
    }

    public boolean isPlato2_fav() {
        return plato2_fav;
    }

    public void setPlato2_fav(boolean plato2_fav) {
        this.plato2_fav = plato2_fav;
    }

    public boolean isPlato3_fav() {
        return plato3_fav;
    }

    public void setPlato3_fav(boolean plato3_fav) {
        this.plato3_fav = plato3_fav;
    }
}
