package smartvisit.com.smartvisit;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.barcode.Barcode;
import com.j256.ormlite.dao.Dao;

import java.util.Iterator;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import smartvisit.com.smartvisit.db.CompanyInfo;
import smartvisit.com.smartvisit.utils.Utils;

public class SignInActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    @InjectView(R.id.loginUserName)
    protected EditText username;

    @InjectView(R.id.loginPassword)
    protected EditText password;

    @InjectView(R.id.btnSignIn)
    protected Button btnSignIn;

    @InjectView(R.id.btnSignUp)
    protected Button btnSignUp;

    @InjectView(R.id.forgotPassword)
    protected TextView forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginnew);
        ButterKnife.inject(this);

        //for sign in
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((username.getText().toString().equalsIgnoreCase("")) ||
                        (password.getText().toString().equalsIgnoreCase(""))) {
                    ((InputMethodManager) getSystemService(LoginActivity.INPUT_METHOD_SERVICE))
                            .toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
                } else {
                    String user_name = username.getText().toString();
                    String user_pass = password.getText().toString();
                  //login goe shere
                    username.setText("");
                    password.setText("");

                    printCompanyDetails();




                    try {

                        CompanyInfo info = new CompanyInfo();
                        info.com_email = user_name;
                        info.com_password = user_pass;


                        Dao<CompanyInfo, Integer> accountsDao = AppController.getInstance().getHelper().getCompanyDao();

                      //CompanyInfo result =  accountsDao.queryForSameId(info);
                       List<CompanyInfo> result =  accountsDao.queryForMatching(info);
                        Iterator<CompanyInfo> iterator = result.iterator();

                        boolean loginStatus = false;

                        while (iterator.hasNext()){
                           CompanyInfo item = iterator.next();
                           if(item.com_email.equals(user_name)&&item.com_password.equals(user_pass)){

                               AppController.getInstance().setLoginData(item);
                               loginStatus = true;

                              Intent homeactivity = new Intent(SignInActivity.this, HomeActivity.class);
                               homeactivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                               startActivity(homeactivity);
                               finish();

                           }else {
                               loginStatus = false;
                           }

                       }
                        Log.d(TAG,"loginStats "+loginStatus);

                        if(!loginStatus){
                            Toast.makeText(SignInActivity.this,"Invalid Credientils",Toast.LENGTH_SHORT).show();
                        }

                    }catch (Exception e){

                    }
            }}
        });

        //for creating new user
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iSignUp = new Intent(SignInActivity.this, SignUpActivity.class);
               startActivity(iSignUp);
            }
        });

        // username.setText("wptest123");
        // password.setText("Abcde@123");

        username.setText("subbu@gmail.com");
        password.setText("abc1234");

    }





    private void printCompanyDetails(){
        Log.d(TAG,"printCompanyDetails");
    /*   List<CompanyInfo> list =Utils.getCompantData();
        if (list != null) {

            Iterator<CompanyInfo> iterator = list.iterator();

            while (iterator.hasNext()){
                CompanyInfo item = iterator.next();
                Log.d(TAG,""+item.com_email);
                Log.d(TAG,""+item.com_company_name);
                Log.d(TAG,""+item.com_password);
                Log.d(TAG,""+item.com_type);
                Log.d(TAG,""+item.com_mobile);
                Log.d(TAG,""+item.com_joineddate);
            }
        }
        */}



}
