package com.belajar.toko;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.belajar.toko.profile.Login;
import com.belajar.toko.profile.Profile;


import org.json.JSONException;
import org.json.JSONObject;

public class Register  extends AppCompatActivity {

    Intent intent;
    EditText username, regPass, regConfPass, email, fullname, phone, address ;
    Button btnReg;
    int success;
    TextView textlogin;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        username = findViewById(R.id.emailregister);
        regPass = findViewById(R.id.password);
        regConfPass = findViewById(R.id.confirmpassword);
        email = findViewById(R.id.email);
        fullname = findViewById(R.id.fullname);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.address);
//        textlogin = findViewById(R.id.textlogin);
        btnReg = findViewById(R.id.btndaftar);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                runningReg(username, regPass, regConfPass);

            }
        });

//        textlogin.setOnClickListener(new Button.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Register.this, Login.class);
//                startActivity(intent);
//            }
//        });

//        textlogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Register.this, Login.class);
//                startActivity(intent);
//            }
//        });

    }

    private void runningReg(final EditText username, final EditText regPass, final EditText regConfPass) {
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Register ...");
        showDialog();
        AndroidNetworking.post("http://192.168.18.16/ecommerce/api/register.php")
                .addBodyParameter("id", "") //id bersifat Auto_Increment tidak perlu diisi/(diisi NULL) cek create.php
                .addBodyParameter("user_username", username.getText().toString()) //mengirimkan data nama yang akan diisi dengan varibel nama
                .addBodyParameter("user_password", regPass.getText().toString()) //mengirimkan data agama yang akan diisi dengan varibel agama
                .addBodyParameter("confirm_password", regConfPass.getText().toString())
                .addBodyParameter("user_email", email.getText().toString())
                .addBodyParameter("user_fullname", fullname.getText().toString())
                .addBodyParameter("user_phone", phone.getText().toString())
                .addBodyParameter("user_address", address.getText().toString())
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {

                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            success = response.getInt("success");
                            if (success ==1){
                                Toast.makeText(getApplicationContext(),
                                        response.getString("message"), Toast.LENGTH_LONG).show();

                                //action kalo sudah berhasil register isian di kolom akan hilang
                                username.setText("");
                                regPass.setText("");
                                regConfPass.setText("");
                                email.setText("");
                                fullname.setText("");
                                phone.setText("");
                                address.setText("");
                            }else{
                                Toast.makeText(getApplicationContext(),
                                        response.getString("message"), Toast.LENGTH_LONG).show();
                            }
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        hideDialog();
                    }
                });
    }

    private void showMessage(String message) {
        Toast.makeText(Register.this, message, Toast.LENGTH_LONG).show();
    }

    public void login(View view) {
        intent = new Intent(Register.this, Login.class);
        finish();
        startActivity(intent);
    }
    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}
