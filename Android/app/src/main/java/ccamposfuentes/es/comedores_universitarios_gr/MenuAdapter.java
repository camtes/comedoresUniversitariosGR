package ccamposfuentes.es.comedores_universitarios_gr;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ccamposfuentes.es.comedores_universitarios_gr.util.Util;

/**
 * Author: Carlos Campos
 * Email: carlos@ccamposfuentes.es
 * Date: 27/11/15
 * Project: Comedores Android
 */
public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    private List<MenuSemanal> menu_semanal;
    private Context context;
    private String plato1, plato2, plato3;
    private List<List<Boolean>> fav;
    private boolean plato1Fav = false, plato2Fav = false, plato3Fav = false;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView favorite;
        public TextView fecha;

        public TextView plato1;
        public TextView plato2;
        public TextView plato3;
        public TextView plato4;

        public CardView card;
        public LinearLayout platos;

        public ImageButton ib_plato1, ib_plato2, ib_plato3;

        public View separatorP1, separatorP2, separatorP3;

        public ViewHolder(View v) {
            super(v);

            favorite = (ImageView) v.findViewById(R.id.iv_favorite);
            fecha = (TextView) v.findViewById(R.id.tv_fecha);
            plato1 = (TextView) v.findViewById(R.id.tv_plato1);
            plato2 = (TextView) v.findViewById(R.id.tv_plato2);
            plato3 = (TextView) v.findViewById(R.id.tv_plato3);
            plato4 = (TextView) v.findViewById(R.id.tv_plato4);
            card = (CardView) v.findViewById(R.id.cv_menu);
            platos = (LinearLayout) v.findViewById(R.id.ll_platos);
            separatorP1 = v.findViewById(R.id.separatorp1);
            separatorP2 = v.findViewById(R.id.separatorp2);
            separatorP3 = v.findViewById(R.id.separatorp3);
            ib_plato1 = (ImageButton) v.findViewById(R.id.ib_plato1);
            ib_plato2 = (ImageButton) v.findViewById(R.id.ib_plato2);
            ib_plato3 = (ImageButton) v.findViewById(R.id.ib_plato3);
        }
    }

    // Constructor, puedes crear varios según el tipo de contenido.
    public MenuAdapter(List<MenuSemanal> myDataset, Context context) {
        menu_semanal = myDataset;
        this.fav = new ArrayList<>();
        this.context = context;
    }

    @Override
    public MenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {

        View viewRoot = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_menu_item, parent, false);

        return new ViewHolder(viewRoot);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // - Se recupera el elemento del vector con position.
        Date d = new Date();
        CharSequence s  = DateFormat.format("d", d.getTime());
        final MenuSemanal week = menu_semanal.get(position);

        if (week.getFecha().contains(s.toString())) {
            holder.card.setCardBackgroundColor(ContextCompat.getColor(context, R.color.yellow));
            holder.fecha.setBackgroundColor(ContextCompat.getColor(context, R.color.yellow));
            holder.platos.setBackgroundColor(ContextCompat.getColor(context, R.color.yellow));
        }

        holder.fecha.setText(week.getFecha());

        if (week.getPrimer_plato().equals("  CERRADO  ")) {

            // Ocultamos botones
            holder.ib_plato1.setVisibility(View.GONE);
            holder.ib_plato2.setVisibility(View.GONE);
            holder.ib_plato3.setVisibility(View.GONE);

            // Ocultamos separadores
            holder.separatorP1.setVisibility(View.GONE);
            holder.separatorP2.setVisibility(View.GONE);
            holder.separatorP3.setVisibility(View.GONE);

            // "Centramos" texto cerrado
            holder.plato1.setText("");
            holder.plato2.setText(context.getText(R.string.closed));
            holder.plato3.setText("");
            holder.plato2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            holder.card.setCardBackgroundColor(ContextCompat.getColor(context, R.color.grey_light));
        }
        else {
            holder.plato1.setText(week.getPrimer_plato());
            holder.plato2.setText(week.getSegundo_plato());
            holder.plato3.setText(week.getTercer_plato());

            if (Util.isCourseFav(context, holder.plato1.getText().toString())) {
                holder.ib_plato1.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_star_black_24dp));
                week.setPlato1_fav(true);
            }

            if (Util.isCourseFav(context, holder.plato2.getText().toString())) {
                holder.ib_plato2.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_star_black_24dp));
                week.setPlato2_fav(true);
            }

            if (Util.isCourseFav(context, holder.plato3.getText().toString())) {
                holder.ib_plato3.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_star_black_24dp));
                week.setPlato3_fav(true);
            }
        }

        if (!week.getCuarto_plato().equals("null")) {
            holder.plato4.setVisibility(View.VISIBLE);
            holder.ib_plato3.setVisibility(View.VISIBLE);
            if (Util.isCourseFav(context, holder.plato3.getText().toString())) {
                holder.ib_plato3.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_star_black_24dp));
            }
            holder.separatorP3.setVisibility(View.VISIBLE);
            holder.plato4.setText(week.getCuarto_plato());

            holder.ib_plato3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    plato1 = holder.plato1.getText().toString();
                    plato1 = plato1.replace(" ","_");
                    plato2 = holder.plato2.getText().toString();
                    plato2 = plato2.replace(" ","_");
                    plato3 = holder.plato3.getText().toString();
                    plato3 = plato3.replace(" ","_");

                    if (!week.isPlato3_fav()) {
                        holder.favorite.setVisibility(View.VISIBLE);
                        holder.ib_plato3.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_star_black_24dp));
                        Util.saveCourse(context, plato3);
                        week.setPlato3_fav(true);
                    }
                    else {
                        holder.ib_plato3.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_star_border_black_24dp));
                        Util.removeCourse(context, plato3);
                        week.setPlato3_fav(false);

                        if (!Util.isCourseFav(context, plato1) && !Util.isCourseFav(context, plato2)) {
                            holder.favorite.setVisibility(View.INVISIBLE);
                        }
                    }
                }
            });
        }
        
        // suscribirse al canal
        holder.ib_plato1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plato1 = holder.plato1.getText().toString();
                plato1 = plato1.replace(" ","_");
                plato2 = holder.plato2.getText().toString();
                plato2 = plato2.replace(" ","_");

                if (!week.isPlato1_fav()) {
                    holder.favorite.setVisibility(View.VISIBLE);
                    holder.ib_plato1.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_star_black_24dp));
                    Util.saveCourse(context, plato1);
                    week.setPlato1_fav(true);
                }
                else {
                    holder.ib_plato1.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_star_border_black_24dp));
                    Util.removeCourse(context, plato1);
                    week.setPlato1_fav(false);

                    if (!Util.isCourseFav(context, plato2)) {
                        holder.favorite.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });

        holder.ib_plato2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plato1 = holder.plato1.getText().toString();
                plato1 = plato1.replace(" ","_");
                plato2 = holder.plato2.getText().toString();
                plato2 = plato2.replace(" ","_");

                if (!week.isPlato2_fav()) {
                    holder.favorite.setVisibility(View.VISIBLE);
                    holder.ib_plato2.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_star_black_24dp));
                    Util.saveCourse(context, plato2);
                    week.setPlato2_fav(true);
                }
                else {
                    holder.ib_plato2.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_star_border_black_24dp));
                    Util.removeCourse(context, plato2);
                    week.setPlato2_fav(false);

                    if (!Util.isCourseFav(context, plato1)) {
                        holder.favorite.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });


        if (isFavorite(position)) {
            holder.favorite.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return menu_semanal.size();
    }

    public boolean isFavorite(int position) {

        String plato1 = menu_semanal.get(position).getPrimer_plato();
        plato1 = plato1.replace(" ","_");
        String plato2 = menu_semanal.get(position).getSegundo_plato();
        plato2 = plato2.replace(" ","_");

        if (Util.isCourseFav(context, plato1) || Util.isCourseFav(context, plato2)) {
            return true;
        }

        return false;
    }
}