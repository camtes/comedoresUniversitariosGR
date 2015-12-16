package ccamposfuentes.es.comedoresugr_no_oficial;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseInstallation;
import com.parse.ParsePush;

import java.util.Date;
import java.util.List;

/**
 * Created by ccamposfuentes on 27/11/15.
 */
public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    private List<MenuSemanal> menu_semanal;
    private Context context;


    public static class ViewHolder extends RecyclerView.ViewHolder {

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
        this.context = context;
    }

    @Override
    public MenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {

        View viewRoot = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_menu_item, parent, false);
        ViewHolder vh = new ViewHolder(viewRoot);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // - Se recupera el elemento del vector con position.
        Date d = new Date();
        CharSequence s  = DateFormat.format("d", d.getTime());
        ParseInstallation.getCurrentInstallation().saveInBackground();


        if (menu_semanal.get(position).getFecha().contains(s.toString())) {
            holder.card.setCardBackgroundColor(context.getResources().getColor(R.color.grey));
            holder.fecha.setBackgroundColor(context.getResources().getColor(R.color.grey));
            holder.platos.setBackgroundColor(context.getResources().getColor(R.color.grey));

            holder.fecha.setTextColor(context.getResources().getColor(R.color.cardview_light_background));
            holder.plato1.setTextColor(context.getResources().getColor(R.color.cardview_light_background));
            holder.plato2.setTextColor(context.getResources().getColor(R.color.cardview_light_background));
            holder.plato3.setTextColor(context.getResources().getColor(R.color.cardview_light_background));
            holder.plato4.setTextColor(context.getResources().getColor(R.color.cardview_light_background));

            holder.separatorP1.setBackgroundColor(context.getResources().getColor(R.color.cardview_light_background));
            holder.separatorP2.setBackgroundColor(context.getResources().getColor(R.color.cardview_light_background));
            holder.separatorP3.setBackgroundColor(context.getResources().getColor(R.color.cardview_light_background));
        }

        holder.fecha.setText(menu_semanal.get(position).getFecha());
        holder.plato1.setText(menu_semanal.get(position).getPrimer_plato());
        holder.plato2.setText(menu_semanal.get(position).getSegundo_plato());
        holder.plato3.setText(menu_semanal.get(position).getTercer_plato());
        if (!menu_semanal.get(position).getCuarto_plato().equals("null")) {
            holder.plato4.setVisibility(View.VISIBLE);
            holder.ib_plato3.setVisibility(View.VISIBLE);
            holder.separatorP3.setVisibility(View.VISIBLE);
            holder.plato4.setText(menu_semanal.get(position).getCuarto_plato());
        }

        if (holder.plato1.getText().toString().equals("CERRADO")) {
            holder.ib_plato1.setVisibility(View.GONE);
            holder.ib_plato2.setVisibility(View.GONE);
            holder.ib_plato3.setVisibility(View.GONE);
        }
        
        // suscribirse al canal
        holder.ib_plato1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Te avisaremos cuando vuelva haber '"+
                        holder.plato1.getText().toString()+"'.", Toast.LENGTH_LONG).show();

                String plato = holder.plato1.getText().toString();
                plato = plato.replace(" ","_");
                ParsePush.subscribeInBackground(plato);
            }
        });

        holder.ib_plato2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Te avisaremos cuando vuelva haber '"+
                        holder.plato2.getText().toString()+"'.", Toast.LENGTH_LONG).show();

                String plato = holder.plato2.getText().toString();
                plato = plato.replace(" ","_");
                ParsePush.subscribeInBackground(plato);
            }
        });
    }

    @Override
    public int getItemCount() {
        return menu_semanal.size();
    }
}