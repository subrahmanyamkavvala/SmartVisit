package smartvisit.com.smartvisit;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import smartvisit.com.smartvisit.model.LoginDAO;
import smartvisit.com.smartvisit.utils.Constants;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener
{

    private static final String TAG = "HomeActivity";


    LoginDAO userdao = null;
    private Button mgetCustomer,mviewvisitor,maddvisitor,mgetemployee,msignup,mgetapartment,msignoutvisitr,mforgotpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

         userdao =  AppController.getInstance().getLoginData();
        Log.d(TAG, "loginDAO:" + userdao.branch_email);



        mgetCustomer = (Button)findViewById(R.id.getCustomer);
        mgetCustomer.setOnClickListener(this);


        mviewvisitor = (Button)findViewById(R.id.viewvisitor);
        mviewvisitor.setOnClickListener(this);



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.getCustomer:

                getCustomer();
                    break;
             case  R.id.addvisitor:
                    break;
            case  R.id.viewvisitor:
                viewVisitor();
                    break;
            case  R.id.getemployee:
                    break;
            case  R.id.signup:
                    break;

            case  R.id.getapartment:
                    break;
            case  R.id.signoutvisitor:
                    break;
            case  R.id.forgotpassword:
                    break;

        }

    }



    //get Customer Fields

    private void getCustomer(){
        Log.d(TAG,"getCustomer");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.Company_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String arg) {
                        Log.d(TAG,"responce :"+arg);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e(TAG, "onErrorResponse :" + error.getMessage());
                    }
                }){
            @Override
            protected Map<String,String> getParams(){


                return getCustomerParams();
            }

        };
        AppController.getInstance().addToRequestQueue(stringRequest, "json_login_req");


    }



    //view list of visitores

    private void viewVisitor(){
        Log.d(TAG,"viewVisitor");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.View_Visitor_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String arg) {
                        Log.d(TAG,"responce :"+arg);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e(TAG, "onErrorResponse :" + error.getMessage());
                    }
                }){
            @Override
            protected Map<String,String> getParams(){


                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
               // dateFormat.f
                //09-03-2016
                Calendar cal = Calendar.getInstance();
                cal.set(2016,02,01);
                System.out.println("Todays' Date" + dateFormat.format(cal.getTime()));
                // datetime.setText(dateFormat.format(cal.getTime()));
                String datein = dateFormat.format(cal.getTime()).toString();

                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                String strDate = sdf.format(cal.getTime());
                // intiming.setText(strDate);
               String outtime = strDate;
                Log.d(TAG,"datein"+datein);
                Log.d(TAG,"outtime"+outtime);

                return viewVisitorParam(datein,outtime);
            }

        };
        AppController.getInstance().addToRequestQueue(stringRequest, "json_login_reqkaasj");


    }




    public Map<String,String> getCustomerParams(){

       /* device_token	(required)
        device_type(as mentioned) (required)
                security_token(required)
        companyId(required)*/

        Map<String, String> params = new HashMap<String, String>();
        params.put("device_type", "1");
        params.put("device_token", AppController.device_token);

        params.put("security_token", userdao.branch_key);
        params.put("companyId", userdao.branch_id);
        Log.e(TAG, "getParams:"+params.toString());
        return  params;

}


    public Map<String,String> viewVisitorParam(String datein,String outtime){



        Map<String, String> params = new HashMap<String, String>();
        params.put("device_type", "1");
        params.put("device_token", AppController.device_token);

        params.put("security_token", userdao.branch_key);
        params.put("companyId", userdao.branch_id);
        params.put("date", datein);
        params.put("time", outtime);
        params.put("type", "checkout");
        Log.e(TAG, "getParams:"+params.toString());
        return  params;

}
}
