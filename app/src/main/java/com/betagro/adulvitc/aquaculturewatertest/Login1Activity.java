package com.betagro.adulvitc.aquaculturewatertest;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.betagro.adulvitc.aquaculturewatertest.utility.MyGetAllData;

import org.json.JSONArray;
import org.json.JSONObject;

public class Login1Activity extends AppCompatActivity {

    private EditText userEditText, passEditText;
    private TextView registryTextView;
    private Button loginButton;
    private String userString, passwordString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);


        TextView registryTextView = (TextView) findViewById(R.id.tvRegisterLink);
        Button loginButton = (Button) findViewById(R.id.bSignIn);



        registryTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login1Activity.this, RegistryActivity.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final EditText userEditText = (EditText) findViewById(R.id.edtUsername);
                final EditText passEditText = (EditText) findViewById(R.id.edtPassword);

                userString = userEditText.getText().toString().trim();
                passwordString = passEditText.getText().toString().trim();

                final AlertDialog.Builder ad = new AlertDialog.Builder(Login1Activity.this);

                if (userString.equals("") || passwordString.equals("")) {
//                  Have Space
                    ad.setTitle("Have Space");
                    ad.setCancelable(false);
                    ad.setIcon(R.drawable.icon_alert);
                    ad.setNegativeButton("Close", null);
                    ad.setMessage("Please Fill All");
                    /*ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                        }
                    }); */
                    ad.show();
                    return;

                } else {
//                  No Space
                    checkUserAndPass();

                }


            }


        });





    }// Main Method

    private void checkUserAndPass() {

        final AlertDialog.Builder ad1 = new AlertDialog.Builder(Login1Activity.this);

        try {

            boolean status = true;  // true ---> user False
            String truePasswordString = null,nameString = null;

            MyGetAllData myGetAllData = new MyGetAllData(getApplication());
            myGetAllData.execute("http://androidthai.in.th/sky/getAllDataAdulvitC.php");
            String jsonString = myGetAllData.get();

            Log.d("AdulvitC", "JSON ==>" + jsonString);

            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i=0; i<jsonArray.length(); i+=1) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (userString.equals(jsonObject.getString("User"))) {
                    status = false;
                    truePasswordString = jsonObject.getString("Password");
                    nameString = jsonObject.getString("Name");
                }
            }
            if (status) {
                ad1.setTitle("User False");
                ad1.setCancelable(false);
                ad1.setIcon(R.drawable.icon_alert);
                ad1.setNegativeButton("Close", null);
                ad1.setMessage("No this User in my Database");
                ad1.show();
                return;


            } else if (passwordString.equals(truePasswordString)) {
                Toast.makeText(getApplication(), "Welcome" + nameString, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Login1Activity.this, MainActivity.class);
                startActivity(intent);

            } else {
                ad1.setTitle("Password False");
                ad1.setCancelable(false);
                ad1.setIcon(R.drawable.icon_alert);
                ad1.setNegativeButton("Close", null);
                ad1.setMessage("Please Try again Password False");
                ad1.show();
                return;


            }



        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}// Main Class
