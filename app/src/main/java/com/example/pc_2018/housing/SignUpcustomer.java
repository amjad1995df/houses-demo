package com.example.pc_2018.housing;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.KeyEvent;
import android.view.View.OnKeyListener;
import java.util.regex.Pattern;

public class SignUpcustomer extends AppCompatActivity {

    EditText editTextUserName,editTextPassword,editTextConfirmPassword,editTextemail,editTextphone,editTextFirstName,editTextlastName;
    Button btnCreateAccount;
    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_upcustomer);

        // get Instance  of Database Adapter
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();

        // Get Refferences of Views
        editTextUserName=(EditText)findViewById(com.example.pc_2018.housing.R.id.editTextUserName2);
        editTextPassword=(EditText)findViewById(com.example.pc_2018.housing.R.id.editTextPassword2);
        editTextConfirmPassword=(EditText)findViewById(com.example.pc_2018.housing.R.id.editTextConfirmPassword2);


        editTextemail=(EditText)findViewById(com.example.pc_2018.housing.R.id.eeditText52);
        editTextphone=(EditText)findViewById(com.example.pc_2018.housing.R.id.editText72);
        editTextFirstName=(EditText)findViewById(com.example.pc_2018.housing.R.id.editText82);
        editTextlastName=(EditText)findViewById(com.example.pc_2018.housing.R.id.editText);


        btnCreateAccount=(Button)findViewById(com.example.pc_2018.housing.R.id.buttonCreateAccount2);

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub

                String userName = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();
                String confirmPassword = editTextConfirmPassword.getText().toString();

                String email = editTextemail.getText().toString();
                String phone = editTextphone.getText().toString();
                String FirstName = editTextFirstName.getText().toString();
                String lastName = editTextlastName.getText().toString();



                // check if any of the fields are vaccant
                if (userName.equals("") || password.equals("") || confirmPassword.equals("")) {
                    Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
                    return;
                }
                // check if both password matches
                if (!password.equals(confirmPassword)) {
                    Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    // Save the Data in Database
                    //************* email @ condtions
                    if(isValidEmaillId(email.trim())){
                        Toast.makeText(getApplicationContext(), "Valid Email Address.", Toast.LENGTH_SHORT).show();



                        loginDataBaseAdapter.insertEntry(userName, password,email,phone,FirstName,lastName);
                        Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(SignUpcustomer.this,
                                CustomerPage.class));

                    }else{
                        Toast.makeText(getApplicationContext(), "InValid Email Address.", Toast.LENGTH_SHORT).show();
                    }
                    //************



                }
            }
        });





    }



    //************** email validation code****************



    private boolean isValidEmaillId(String email){

        return Pattern.compile("(.+?)"+"@"+"(.+?)"+"."+"com").matcher(email).matches();
    }

    //*************** end email validation code**************
}