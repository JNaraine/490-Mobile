package com.example.icp10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView = findViewById(R.id.textView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API API = retrofit.create(API.class);


        Call<List<users>> usersCall = API.getData();



        usersCall.enqueue(new Callback<List<users>>() {
            @Override
            public void onResponse(Call<List<users>> call, Response<List<users>> response) {

                if (response.isSuccessful()){

                    List<users> users = response.body();

                    for (users user: users){

                        String data = "";

                        data += "ID: " + user.getId() + "\n";
                        data += " \n";
                        data += "UserName: " + user.getUserName() + "\n";

                        textView.append(data);

                    }

                }
            }

            @Override
            public void onFailure(Call<List<users>> call, Throwable t) {


                Toast.makeText(MainActivity.this, "Data Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }
}