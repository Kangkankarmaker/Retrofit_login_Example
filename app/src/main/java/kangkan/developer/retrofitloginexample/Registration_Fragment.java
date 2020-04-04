package kangkan.developer.retrofitloginexample;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Registration_Fragment extends Fragment {

    private EditText Name,Username,UserPassword;
    private Button buttonRegister;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_registration, container, false);

        Name=view.findViewById(R.id.extName);
        Username=view.findViewById(R.id.etUsername);
        UserPassword=view.findViewById(R.id.etPassword);
        buttonRegister=view.findViewById(R.id.btnRegister);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performRegistration();

            }
        });


        return view;
    }

    public void performRegistration()
    {
        String name=Name.getText().toString();
        String username=Username.getText().toString();
        String userpassword=UserPassword.getText().toString();

        Call<User>call=MainActivity.apiIntreface.PerformRegistration(name,username,userpassword);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (response.body().getResponse().equals("Ok"))
                {
                    MainActivity.prefConfig.displayToast("Succedd");
                }
                else if (response.body().getResponse().equals("exist"))
                {
                    MainActivity.prefConfig.displayToast("user already exist");
                }
                else if (response.body().getResponse().equals("error"))
                {
                    MainActivity.prefConfig.displayToast("some thing went wrong...");
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {


            }
        });

    }
}
