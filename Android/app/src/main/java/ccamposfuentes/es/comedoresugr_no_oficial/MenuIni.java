package ccamposfuentes.es.comedoresugr_no_oficial;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;

/**
 * Created by ccamposfuentes on 12/12/15.
 */
public class MenuIni extends Application {

    @Override
    public void onCreate() {
        Parse.initialize(this, "ekIbyOnMYiQxTXFmcHpNDybqOcJ0JBWSpEjHmRUF", "C2aiRYjYp5blG7Dc7ajaFGVpb0wVFhI7vY8F4dj9");
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }

}
