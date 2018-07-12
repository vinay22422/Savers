package com.vinay.savers;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;


public class

Login extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;
    private Button btnSignUp, btnLogin;

    private ProgressDialog PD;

    @Override    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        //FirebaseMessaging.getInstance().subscribeToTopic("test");
        FirebaseInstanceId.getInstance().getToken();
         Log.d("TOKEN", FirebaseInstanceId.getInstance().getToken());
        PD = new ProgressDialog(this);
        PD.setMessage("Loading...");
        PD.setCancelable(true);
        PD.setCanceledOnTouchOutside(false);
        auth = FirebaseAuth.getInstance();


        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        btnLogin = (Button) findViewById(R.id.sign_in_button);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override            public void onClick(View view) {
                final String email = inputEmail.getText().toString().trim();
                final String password = inputPassword.getText().toString().trim();

                try {

                    if (password.length() > 0 && email.length() > 0) {
                        PD.show();

                        auth.signInWithEmailAndPassword(email,password).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Login.this, "Auth failed", Toast.LENGTH_SHORT).show();

                            }
                        });
                        auth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {

                                            Intent intent = new Intent(Login.this, Navigation.class);
                                            startActivity(intent);
                                            finish();
                                        } else {

                                            try
                                            {
                                                throw task.getException();
                                            }
                                            catch (FirebaseAuthWeakPasswordException e)
                                            {
                                                Toast.makeText(
                                                        Login.this,
                                                        "Weak Passwoed(6 Characters)",
                                                        Toast.LENGTH_LONG).show();
                                            }
                                            catch (FirebaseAuthInvalidCredentialsException e)
                                            {
                                                Toast.makeText(
                                                        Login.this,
                                                        "Invalid Credentials",
                                                        Toast.LENGTH_LONG).show();
                                            }
                                            catch (FirebaseAuthUserCollisionException e)
                                            {
                                                Toast.makeText(
                                                        Login.this,
                                                        "Fill All Fields",
                                                        Toast.LENGTH_LONG).show();
                                            }
                                            catch(Exception e)
                                            {
                                                Toast.makeText(
                                                        Login.this,
                                                        "Fill All Fields",
                                                        Toast.LENGTH_LONG).show();
                                            }

                                        }
                                        PD.dismiss();
                                    }
                                });
                    } else {
                        Toast.makeText(
                                Login.this,
                                "Fill All Fields",
                                Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e)
                {
                    Toast.makeText(Login.this, "invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.forget_password_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ForgetPassword.class).putExtra("Mode", 0));
            }
        });

    }

    @Override    protected void onResume() {
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(Login.this,Navigation.class));
            finish();
        }
        super.onResume();
    }


}
