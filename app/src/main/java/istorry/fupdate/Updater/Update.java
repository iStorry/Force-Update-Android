package istorry.fupdate.Updater;

import android.os.AsyncTask;
import android.util.Log;

import istorry.fupdate.Activity;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;

/**
 * Created by Jatinjit Singh (iStorry) on 11/15/16.
 */

public class Update extends AsyncTask<String, Void, String> {

    private String url, res;
    private static final String TAG = Activity.class.getSimpleName();
    private Listener result;
    private Service.type type;
    public Update(String url, Service.type type, Listener result) {
        this.url = url;
        this.result = result;
        this.type = type;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        OkHttpClient.Builder b = new OkHttpClient.Builder();
        OkHttpClient client = b.build();
        Request request = new Request.Builder()
                .addHeader("Content-Type", "application/json")
                .addHeader("User-Agent", "fUpdate - v1.0")
                .url(url).build();
        try {
            Response response = client.newCall(request).execute();

            if (!response.isSuccessful()) {
                res = response.toString();
                if (res.equals("") || res.equals("null") || res.length() == 0) {
                    // Util.showToast(context, "Something went wrong. Try later.");
                }
            } else {

            }
            res = response.body().string();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(res.equals("")|| res.equals("null")|| res.length()==0){
            //Util.showToast(context, "Something went wrong. Try later.");
        }
        Log.e("Result", "Type= " + type + " Result= " + s);

        result.getWebResponse(s, type);
    }

    private static String bodyToString(final Request request) {

        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (Exception e) {
            return "did not work";
        }
    }
}
