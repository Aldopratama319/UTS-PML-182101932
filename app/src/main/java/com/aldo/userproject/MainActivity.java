package com.aldo.userproject;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.widget.TextView;

import com.aldo.model.user;
import com.aldo.service.GetService;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView tvData ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvData = findViewById(R.id.tv_data);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final GetService getService = retrofit.create(GetService.class);

        Call<List<user>> call = getService.getUserList();

        call.enqueue(new Callback<List<user>>() {
            @Override
            public void onResponse(Call<List<user>> call, Response<List<user>> response) {


                if (response.isSuccessful()){

                    List<user> users = response.body();

                    for (user user : users ){
                        String isidata = ""+ "\n";
                        isidata += "Id : "+ user.getId()+ "\n";
                        isidata += "UserName : "+ user.getUsername()+ "\n";
                        isidata += "Name : "+ user.getName()+ "\n";
                        isidata += "Email : "+ user.getEmail()+ "\n";

                        tvData.append(isidata);
                    }

                    return;
                }
            }

            @Override
            public void onFailure(Call<List<user>> call, Throwable t) {
                tvData.setText(t.getMessage());

            }
        });




    }
}