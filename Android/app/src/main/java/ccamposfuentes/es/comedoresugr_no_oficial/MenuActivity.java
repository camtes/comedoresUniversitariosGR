package ccamposfuentes.es.comedoresugr_no_oficial;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;
import android.widget.RelativeLayout;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
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

        // Adaptador
        mAdapter = new MenuAdapter(menu_semanal, this);
        mRecyclerView.setAdapter(mAdapter);

        new GetMenu().execute();
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


    private class GetMenu extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            OkHttpClient client = new OkHttpClient();
            String result = null;

            Request request = new Request.Builder()
                    .url("http://comedoresugr-ccamposfuentes.rhcloud.com/")
                    .build();

            try {
                Response response = client.newCall(request).execute();
                result = response.body().string();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            JSONArray array = null;
            try {
                array = new JSONArray(s);

                for (int i=0; i<array.length(); i++) {
                    JSONObject obj = array.getJSONObject(i);

                    menu_semanal.add(new MenuSemanal(obj.getString("fecha"), obj.getString("plato1"),
                            obj.getString("plato2"), obj.getString("plato3"), obj.getString("plato4")));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            mAdapter.notifyDataSetChanged();
            loadingPanel.setVisibility(View.GONE);
        }
    }
}
