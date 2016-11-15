package istorry.fupdate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONObject;

import istorry.fupdate.Updater.Listener;
import istorry.fupdate.Updater.Service;
import istorry.fupdate.Updater.Update;

public class Activity extends AppCompatActivity implements Listener {


    private String url = "http://example.com/example.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);


        /*
            App Updater
         */

        Update update = new Update(url, Service.type.update, this);
        update.execute();
    }

    @Override
    public void getWebResponse(String result, Service.type type) {
        switch (type) {

            case update:
                try {
                    JSONObject object = new JSONObject();
                    int version = object.getInt("versioncode");
                    int versionCode = BuildConfig.VERSION_CODE;
                    if (version > versionCode){
                        Log.e("TAG", "Update Required");
                    } else {
                        Log.e("TAG", "Application is Up-to-date");
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }

                break;

        }
    }
}
