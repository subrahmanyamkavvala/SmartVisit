package smartvisit.com.smartvisit;

/**
 * Created by subbu on 4/3/16.
 */

import android.app.Application;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import smartvisit.com.smartvisit.db.DatabaseHelper;
import smartvisit.com.smartvisit.model.LoginDAO;
import smartvisit.com.smartvisit.utils.LruBitmapCache;

public class AppController extends Application {

    public static final String TAG = AppController.class
            .getSimpleName();

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;


    private LoginDAO login_data;
    public static String device_token= null;
    private static AppController mInstance;

    private DatabaseHelper databaseHelper = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        device_token = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue,
                    new LruBitmapCache());
        }
        return this.mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils
                .isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public LoginDAO getLoginData(){
        return login_data;

    }
    public void setLoginData(LoginDAO data){
        login_data = data;
    }
    public DatabaseHelper getHelper() {

        if (databaseHelper == null) {
            Log.d(TAG, "creating databasehelper !");
            databaseHelper =
                    OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return databaseHelper;
    }
}