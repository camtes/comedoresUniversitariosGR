package ccamposfuentes.es.comedoresugr_no_oficial;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;
import android.widget.RelativeLayout;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MenuAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<MenuSemanal> menu_semanal;
    private RelativeLayout loadingPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        menu_semanal = new ArrayList<>();
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_menu);
        loadingPanel = (RelativeLayout) findViewById(R.id.loadingPanel);

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        String siteUrl = "http://comedoresugr.tcomunica.org/";
        ( new ParseURL() ).execute(new String[]{siteUrl});

        // Adaptador
        mAdapter = new MenuAdapter(menu_semanal, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_about) {
            Intent i = new Intent(this, AboutActivity.class );
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }


    private class ParseURL extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            StringBuffer buffer = new StringBuffer();

            List<String> plato1_ = new ArrayList<>();
            List<String> plato2_ = new ArrayList<>();
            List<String> plato3_ = new ArrayList<>();
            List<String> fecha_ = new ArrayList<>();
            List<String> dia_ = new ArrayList<>();
            try {
                Log.d("JSwa", "Connecting to ["+params[0]+"]");
                Document doc  = Jsoup.connect(params[0]).get();
                Log.d("JSwa", "Connected to ["+params[0]+"]");
                // Get document (HTML page) title
                String title = doc.title();
                Log.d("JSwA", "Title ["+title+"]");
                buffer.append("Title: " + title + "\r\n");

                // Get meta info
                Elements metaElems = doc.select("meta");
                buffer.append("META DATA\r\n");
                for (Element metaElem : metaElems) {
                    String name = metaElem.attr("name");
                    String content = metaElem.attr("content");
                    buffer.append("name ["+name+"] - content ["+content+"] \r\n");
                }

                Elements plato1 = doc.select("#plato1");
                for (Element metaElement : plato1) {
                    String name = metaElement.text();
                    plato1_.add(name);
                }

                Elements plato2 = doc.select("#plato2");
                for (Element metaElement : plato2) {
                    String name = metaElement.text();
                    plato2_.add(name);
                }

                Elements plato3 = doc.select("#plato3");
                for (Element metaElement : plato3) {
                    String name = metaElement.text();
                    plato3_.add(name);
                }

                Elements fecha = doc.select("#fechaplato");
                for (Element metaElement : fecha) {
                    String name = metaElement.text();
                    fecha_.add(name);
                }

                Elements dia = doc.select("div.numero");
                for (Element metaElement : dia) {
                    String name = metaElement.text();
                    dia_.add(name);
                }

                for (int i=0; i<plato1_.size(); i++) {

                    String fecha_calc = fecha_.get(i).replace(dia_.get(i), "");

                    menu_semanal.add(new MenuSemanal(plato1_.get(i), plato2_.get(i), plato3_.get(i),
                            Integer.valueOf(dia_.get(i)), fecha_calc));
                }

            }
            catch(Throwable t) {
                t.printStackTrace();
            }

            return buffer.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mAdapter.notifyDataSetChanged();
            loadingPanel.setVisibility(View.GONE);
        }
    }
}
