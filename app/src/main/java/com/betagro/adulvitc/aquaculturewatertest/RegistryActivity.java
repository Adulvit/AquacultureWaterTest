package com.betagro.adulvitc.aquaculturewatertest;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.betagro.adulvitc.aquaculturewatertest.utility.MyConstant;
import com.betagro.adulvitc.aquaculturewatertest.utility.MyPostData;

public class RegistryActivity extends AppCompatActivity {

    //    Explicit
    private String nameString,userString, passwordString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry);

        ImageView imageView = (ImageView)findViewById(R.id.imvSave);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //              Get Value from Edit text ( การผูกตัวแปร )
                final EditText nameEditText = (EditText)findViewById(R.id.edtName);
                final EditText userEditText = (EditText)findViewById(R.id.edtUser);
                final EditText passwordEditText = (EditText)findViewById(R.id.edtPassword);


//              Change Type Value to String ( การแปลงตัวแปรจาก EditText เป็น String )
                nameString = nameEditText.getText().toString().trim();
                userString = userEditText.getText().toString().trim();
                passwordString = passwordEditText.getText().toString().trim();

                //              Check Space ( สิ่งที่กรอกมีความว่างเปล่าอยู่หรือไม่ )

                final AlertDialog.Builder ad = new AlertDialog.Builder(RegistryActivity.this);

                if (nameString.equals("") || userString.equals("") || passwordString.equals("")) {
//                    Have Space ( ภาวะที่เป็น True )

                    ad.setTitle("Have Space");
                    ad.setCancelable(false);
                    ad.setIcon(R.drawable.icon_alert);
                    ad.setMessage("Please Fill in The Blank");
                    ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                        }
                    });
                    ad.show();
                    return;





                } else {
//                    No Space ( ภาวะที่เป็น False )
                    try {
                        MyPostData myPostData = new MyPostData(getApplication());
                        myPostData.execute(nameString,
                                userString,
                                passwordString,
                                "http://androidthai.in.th/sky/addDataAdulvitC.php");
                        if (Boolean.parseBoolean(myPostData.get())) {
                            Toast.makeText(getApplication(),"upload new User Success",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(RegistryActivity.this, Login1Activity.class);
                            finish();


                        } else {
                            Toast.makeText(getApplication(),"Cannot Upload New User",Toast.LENGTH_LONG).show();

                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }






                }

            }   //Onclick


        });



    }   //Main Method


}   //Main Class
