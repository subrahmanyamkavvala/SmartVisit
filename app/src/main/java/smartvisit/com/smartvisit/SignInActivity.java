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

import butterknife.ButterKnife;
import butterknife.InjectView;

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
                    String Pass = password.getText().toString();
                  //login goe shere
                    username.setText("");
                    password.setText("");
                }
            }
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

        username.setText("subbukavvala");
        password.setText("Subbu@1234");

    }


}
