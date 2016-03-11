package smartvisit.com.smartvisit;

/**
 * Created by bhaskar on 05-01-2016.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;
import smartvisit.com.smartvisit.db.CompanyInfo;
import smartvisit.com.smartvisit.utils.ICommonUtil;


public class SignUpActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    String TAG = "SignUpActivity";


    @InjectView(R.id.com_fullName)
    protected EditText userFullName;

    @InjectView(R.id.com_companyName)
    protected EditText companyName;

    @InjectView(R.id.com_emailAddress)
    protected EditText emailAddress;

    @InjectView(R.id.com_mobile)
    protected EditText mobile;


    @InjectView(R.id.com_password)
    protected EditText password;

    @InjectView(R.id.com_cnfpassword)
    protected EditText confirmPassword;


    @InjectView(R.id.buttonSignUp)
    protected Button buttonSignUp;


    @InjectView(R.id.com_type)
    protected Spinner companyType;


    private boolean submit_Button = false;

    private static boolean checkEmailFormat = true;
    private ProgressDialog pDialog;


    //for countries
    ArrayList<String> spinner_countries = new ArrayList();

    //for gender
    ArrayList<String> spinner_gender = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        ButterKnife.inject(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        confirmPassword.setText(ICommonUtil.MOBILE_NO_PREFIX);
        Selection.setSelection(confirmPassword.getText(), confirmPassword.getText().length());

        confirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().startsWith(ICommonUtil.MOBILE_NO_PREFIX)) {
                    confirmPassword.setText(ICommonUtil.MOBILE_NO_PREFIX);
                    Selection.setSelection(confirmPassword.getText(), confirmPassword
                            .getText().length());
                }
            }

        });

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptSiginUp();
            }
        });
    }


    private void attemptSiginUp(){

        pDialog = new ProgressDialog(SignUpActivity.this);
        pDialog.setMessage("Fetching friend list...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();

        boolean isValid= false;

        String inpur_userFullName = userFullName.getText().toString();
        String inpur_companyName = companyName.getText().toString();
        String inpur_emailAddress = emailAddress.getText().toString();
        String inpur_mobile = mobile.getText().toString();
        String inpur_password = password.getText().toString();
        String inpur_confirmPassword = confirmPassword.getText().toString();

        int input_type = companyType.getSelectedItemPosition();

        Log.d(TAG,"inpur_userFullName:"+inpur_userFullName);
        Log.d(TAG,"inpur_companyName:"+inpur_companyName);
        Log.d(TAG,"inpur_emailAddress:"+inpur_emailAddress);
        Log.d(TAG,"inpur_mobile:"+inpur_mobile);
        Log.d(TAG,"inpur_password:"+inpur_password);
        Log.d(TAG,"inpur_confirmPassword:"+inpur_confirmPassword);
        Log.d(TAG,"input_type:"+input_type);



        if (inpur_userFullName.isEmpty() || inpur_userFullName.length() < 6 ) {
            userFullName.setError("Minimum Lenght is 6");
            isValid = false;
        } else {
            userFullName.setError(null);
            isValid = true;
        }

        if (inpur_companyName.isEmpty() || inpur_companyName.length() < 6 ) {
            companyName.setError("Minimum Lenght is 6");
            isValid = false;
        } else {
            companyName.setError(null);
            isValid = true;
        }

        if (inpur_emailAddress.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(inpur_emailAddress).matches()) {
            emailAddress.setError("Enter a valid email");
            isValid = false;
        } else {
            emailAddress.setError(null);
            isValid = true;
        }

        if (inpur_mobile.isEmpty() || !Patterns.PHONE.matcher(inpur_mobile).matches()) {
            mobile.setError("Enter a valid phone");
            isValid = false;
        } else {
            mobile.setError(null);
            isValid = true;

        }

        if (inpur_password.isEmpty() || inpur_password.length() < 6) {
            password.setError("Password lenght minimum 6");
            isValid = false;
        } else {
            password.setError(null);
            isValid = true;

        }

        if (!inpur_password.equals(inpur_confirmPassword)) {
            confirmPassword.setError("Conform password does match");
            isValid = false;
        } else {
            confirmPassword.setError(null);
            isValid = true;

        }

        if(isValid){

            CompanyInfo info = new CompanyInfo();
            info.com_name = inpur_userFullName;
            info.com_company_name = inpur_companyName;
            info.com_email = inpur_emailAddress;
            info.com_mobile = inpur_mobile;
            info.com_password = inpur_password;
            info.com_type = getStringType(input_type);
            info.com_status = "active";

            Date date  = new Date();
            info.com_joineddate  = date;

            Log.d(TAG, "go ahead sign up");

            try {
                Thread.sleep(500);
            }catch (InterruptedException e){

            }

            Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
            startActivity(intent);

            pDialog.cancel();
            pDialog = null;
        }else {
            pDialog.cancel();
            pDialog = null;
        }

    }
    private String getStringType(int type){
        if(type==0){
            return "Residential";
        }else return "Commertial";
    }




     @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


    }
}
