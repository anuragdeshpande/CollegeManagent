package com.collegeprojects.ankur.collegemanagent;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login = findViewById(R.id.id_button_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText et_username = findViewById(R.id.id_et_username);
                EditText et_password  = findViewById(R.id.id_et_password);



                if(!et_username.getText().toString().trim().equals("")  && !et_password.getText().toString().trim().equals("")){

                    // finding the given username and password
                    String username = et_username.getText().toString().trim();
                    String password = et_password.getText().toString().trim();

                    // building webservice call
                    String domain = getString(R.string.serviceDomain);
                    String serviceURL = "https://"+domain+".localtunnel.me/user";
                    RestOperation restOperation = new RestOperation();
                    restOperation.setParameters(view.getContext(), username+"||"+password);
                    restOperation.execute(serviceURL);
                } else {
                    Toast.makeText(view.getContext(), "Username and Password are required", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }


    private static class RestOperation extends AsyncTask<String, Void, Void> {

        final HttpClient httpClient = new DefaultHttpClient();
        private Context context;
        String content;
        String error;
        ProgressDialog progressDialog;
        String data;
        static String loginDetails;

        private RestOperation() {
        }

        void setParameters(Context context, String data) {
            this.context = context;
            progressDialog = new ProgressDialog(context);
            loginDetails = data;
        }


        @Override
        protected Void doInBackground(String... params) {
            URL url;
            BufferedReader bufferedReader = null;

            try{
                url = new URL(params[0]);
                URLConnection connection = url.openConnection();
                connection.setDoOutput(true);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
                outputStreamWriter.write(data);
                outputStreamWriter.flush();

                bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder stringBuffer = new StringBuilder();
                String line = null;

                while ((line = bufferedReader.readLine())!=null){
                    stringBuffer.append(line);
                    stringBuffer.append(System.getProperty("line.separator"));
                }

                content = stringBuffer.toString();
            }catch (MalformedURLException e){
                error = e.getMessage();
                e.printStackTrace();
            } catch (IOException io){
                error = io.getMessage();
                io.printStackTrace();
            } finally {
                try {
                    bufferedReader.close();
                } catch (IOException io){
                    io.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setTitle("Logging In");
            progressDialog.show();

            try {
                data += "&"+ URLEncoder.encode("data", "UTF-8") + "="+ loginDetails;
            } catch (UnsupportedEncodingException e ){
                // do nothing
            }
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            progressDialog.dismiss();

            if(error != null){
                System.out.println(error);
            } else {
                System.out.println(content);
                if(content.trim().equals("true")){
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        }

        boolean isLoginSuccessful(){
            return content.equals("true");
        }
    }





}
