package smartvisit.com.smartvisit;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import smartvisit.com.smartvisit.db.CompanyInfo;
import smartvisit.com.smartvisit.db.VisitorsCheckIn;
import smartvisit.com.smartvisit.utils.Utils;

public class Appoinment extends AppCompatActivity {

    private DatePicker datePicker;
    private TextView dateView;
    private ProgressDialog pDialog;


    Button bt_checkin_date,bt_checkin_time,bt_checkou_date,bt_checckout_time;
    EditText et_name,et_tomeet,et_companyname,et_email,et_mobile;

    ImageView app_profile_image;
    String profile_selectedpath = null;

    String TAG = "Appoinment";


    private Date mCheckinDate,mCheckoutDate;



    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day

            Log.e(TAG,"year "+arg1);
            Log.e(TAG,"year "+arg2);
            Log.e(TAG,"year "+arg3);


      mCheckinDate.setYear(arg1);
      mCheckinDate.setMonth(arg2);
      mCheckinDate.setDate(arg3);
            bt_checkin_date.setText(Utils.getSimpleDateFormat(mCheckinDate));
        }
    };

    private DatePickerDialog.OnDateSetListener myDateListener1 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day

            mCheckoutDate.setYear(arg1);
            mCheckoutDate.setMonth(arg2);
            mCheckoutDate.setDate(arg3);
            bt_checkou_date.setText(Utils.getSimpleDateFormat(mCheckoutDate));


        }
    };


    private TimePickerDialog.OnTimeSetListener myTimerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

          /*  bt_checkin_time.setText(new StringBuilder().append(hourOfDay).append("/")
                    .append(minute));*/

            mCheckinDate.setHours(hourOfDay);
            mCheckinDate.setMinutes(minute);

            bt_checkin_time.setText(Utils.getSimpleTimeFormat(mCheckinDate));

        }
    };
    private TimePickerDialog.OnTimeSetListener myTimerListener1 = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            mCheckoutDate.setHours(hourOfDay);
            mCheckoutDate.setMinutes(minute);

            bt_checckout_time.setText(Utils.getSimpleTimeFormat(mCheckoutDate));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoinment);


        et_name = (EditText)findViewById(R.id.app_fullname);
        et_companyname = (EditText)findViewById(R.id.app_companyName);
        et_email = (EditText)findViewById(R.id.app_email);
        et_tomeet = (EditText)findViewById(R.id.app_tomeet);
        et_mobile= (EditText)findViewById(R.id.app_mobile);

        bt_checkin_date  =  (Button)findViewById(R.id.app_checkindate);
        bt_checkin_time  =  (Button)findViewById(R.id.app_chickintime);
        bt_checkou_date  =  (Button)findViewById(R.id.app_checkoutdate);
        bt_checckout_time  =  (Button)findViewById(R.id.app_checkouttime);

        app_profile_image  =  (ImageView)findViewById(R.id.app_profile_image);



        mCheckinDate = new Date();
        mCheckoutDate = new Date();



        bt_checkin_date.setText(Utils.getSimpleDateFormat(mCheckinDate));
        bt_checkou_date.setText(Utils.getSimpleDateFormat(mCheckoutDate));

        bt_checkin_time.setText(Utils.getSimpleTimeFormat(mCheckinDate));
        bt_checckout_time.setText(Utils.getSimpleTimeFormat(mCheckoutDate));


       /* bt_checkou_date.setText(new StringBuilder().append(year).append("/")
                .append(month+1).append("/").append(day));
*/


       /* bt_checckout_time.setText(new StringBuilder().append(hour).append("/")
                .append(mins));
*/

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_appoinment, menu);
        return true;
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        Log.d("appoinment", "id :" + id);
        if (id == R.id.action_done) {
            Toast.makeText(this,"done",Toast.LENGTH_SHORT).show();
            setAppoinment();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    protected Dialog onCreateDialog(int id) {

        if (id == 100) {
            DatePickerDialog dialog = new DatePickerDialog(this, myDateListener, mCheckinDate.getYear(), mCheckinDate.getMonth(), mCheckinDate.getDay());
            dialog.setTitle(R.string.st_checkin_date);
            return dialog;
        }


            if (id == 200) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(this, myTimerListener, mCheckinDate.getHours(), mCheckinDate.getMinutes(), false);
                timePickerDialog.setTitle(R.string.st_checkin_time);
                return timePickerDialog;

            }

            if (id == 300) {
                DatePickerDialog dialog1 = new DatePickerDialog(this, myDateListener1, mCheckoutDate.getYear(), mCheckoutDate.getMonth(), mCheckoutDate.getDay());
                dialog1.setTitle(R.string.st_checkout_date);

                return dialog1;
            }

            if (id == 400) {
                TimePickerDialog timePickerDialog1 = new TimePickerDialog(this, myTimerListener1, mCheckoutDate.getHours(), mCheckoutDate.getMinutes(), false);
                timePickerDialog1.setTitle(R.string.st_checkout_time);
                return  timePickerDialog1;

            }


            return null;
        }





    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));


    }

    public void viewClicked(View view) {

        switch (view.getId()) {

            case R.id.app_checkindate:
                showDialog(100);
                break;

            case R.id.app_chickintime:
                showDialog(200);
                break;

            case R.id.app_checkoutdate:
                showDialog(300);
                break;

            case R.id.app_checkouttime:
                showDialog(400);
                break;

            case R.id.app_profile_browse:
                getImage();
                break;
        }

    }

    private void getImage(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");

        //intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(Intent.createChooser(intent,
                "Select Picture"), 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 100) {


                Uri selectedImageUri = data.getData();
                String path = data.getDataString();
                String selectedImagePath = getPath(selectedImageUri);
                Log.d(TAG, "selectedImagePath" + selectedImagePath);



                Log.d(TAG,"dataString"+path);

                if(selectedImagePath!=null){
                    StringBuilder appendpath = new StringBuilder("file://");
                    appendpath.append(selectedImagePath);
                    String image_uri=  appendpath.toString();
                    Log.d(TAG,"image_uri"+image_uri);
                    profile_selectedpath = image_uri;

                    ImageSize targetSize = new ImageSize(80, 50); // result Bitmap will be fit to this size
                    ImageLoader imageLoader = ImageLoader.getInstance();
                    imageLoader.displayImage(image_uri,app_profile_image);

                }
            }
        }
    }

    /**
     * helper to retrieve the path of an image URI
     */
    public String getPath(Uri uri) {
        // just some safety built in
        if( uri == null ) {
            // TODO perform some logging or show user feedback
            return null;
        }
        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if( cursor != null ){
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        // this is our fallback here
        return uri.getPath();
    }



    private void setAppoinment(){

        String l_fullName = et_name.getText().toString();
        String l_company = et_companyname.getText().toString();
        String l_tomeet = et_tomeet.getText().toString();
        String l_email = et_email.getText().toString();
        String l_mobile = et_mobile.getText().toString();




        Log.d(TAG,"l_fullName :"+l_fullName);
        Log.d(TAG, "l_company :" + l_company);
        Log.d(TAG, "l_tomeet :" + l_tomeet);
        Log.d(TAG, "l_email :" + l_email);
        Log.d(TAG, "l_mobile :" + l_mobile);



        Log.d(TAG,"check in data :"+mCheckinDate.toString());
        Log.d(TAG,"check out date :"+mCheckoutDate.toString());


        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Fetching friend list...");
        pDialog.setCancelable(true);
        pDialog.show();

        boolean isValid= false;

        if (l_fullName.isEmpty() || l_fullName.length() < 6 ) {
            et_name.setError("Minimum Lenght is 6");
            isValid = false;
        } else {
            et_name.setError(null);
            isValid = true;
        }

        if (l_company.isEmpty() || l_company.length() < 6 ) {
            et_companyname.setError("Minimum Lenght is 6");
            isValid = false;
        } else {
            et_companyname.setError(null);
            isValid = true;
        }

        if (l_tomeet.isEmpty() || l_tomeet.length() < 6 ) {
            et_tomeet.setError("Minimum Lenght is 6");
            isValid = false;
        } else {
            et_tomeet.setError(null);
            isValid = true;
        }


        if (l_email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(l_email).matches()) {
            et_email.setError("Enter a valid email");
            isValid = false;
        } else {
            et_email.setError(null);
            isValid = true;
        }

        if (l_mobile.isEmpty() || !Patterns.PHONE.matcher(l_mobile).matches()) {
            et_mobile.setError("Enter a valid phone");
            isValid = false;
        } else {
            et_mobile.setError(null);
            isValid = true;

        }




        if(isValid){

            try {
                VisitorsCheckIn checkIn = new VisitorsCheckIn();

                    checkIn.vis_fullname = l_fullName;
                    checkIn.vis_company = l_company;
                    checkIn.vis_email = l_email;
                    checkIn.vis_tomeet = l_tomeet;
                    checkIn.vis_mobile = l_mobile;
                    checkIn.vis_indate = mCheckinDate;
                    checkIn.vis_outdate = mCheckoutDate;
                    checkIn.vis_url_image  = profile_selectedpath;

                Log.e(TAG,"profile_selectedpath :"+profile_selectedpath);

                    Dao<VisitorsCheckIn,Integer> dao =AppController.getInstance().getHelper().getVisitorsDao();
                    dao.create(checkIn);

                    CompanyInfo companyInfo = AppController.getInstance().getLoginData();

                String smsMessage =String.format(" SMART VISIT\n From %s\n Hi %s\n Your Appoinment with %s is Conformed\n Reporting Time on%s ",
                        companyInfo.com_company_name,l_fullName,l_tomeet,Utils.getSimpleDateFormat(mCheckinDate)+" "+Utils.getSimpleTimeFormat(mCheckinDate));

                Log.d(TAG,"SMS MEssage :"+smsMessage);
                sendLongSMS(l_mobile, smsMessage);

                Thread.sleep(2000);

                finish();

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto",l_email, null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "SMART VISIT Appoinmet");
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, smsMessage);
                startActivity(Intent.createChooser(emailIntent, "Send email..."));


            }catch (InterruptedException    e){

            }catch (SQLException e){

            }


            pDialog.cancel();
            pDialog = null;
        }else {
            pDialog.cancel();
            pDialog = null;
        }

    }

    public void sendLongSMS(String phone,String smsmessage) {

        /*String phoneNumber = "0123456789";
        String message = "Hello World! Now we are going to demonstrate " +
                "how to send a message with more than 160 characters from your Android application.";
*/
        SmsManager smsManager = SmsManager.getDefault();
        ArrayList<String> parts = smsManager.divideMessage(smsmessage);
        smsManager.sendMultipartTextMessage(phone, null, parts, null, null);
    }

}
