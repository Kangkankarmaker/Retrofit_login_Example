package kangkan.developer.retrofitloginexample;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LogIn_Fragment extends Fragment {

    private TextView RegText;
    private EditText Username;
    private EditText UserPassword;
    private Button login;
    OnLoginFromActivityListener LoginFromActivityListener;

    public interface OnLoginFromActivityListener
    {
        public void performRegister();
        public void perfromLogin(String name);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_log_in, container, false);
        RegText=view.findViewById(R.id.txt_register);
        Username=view.findViewById(R.id.etLoginUsername);
        UserPassword=view.findViewById(R.id.etLoginPassword);
        login=view.findViewById(R.id.btnLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogIn();

            }
        });


        RegText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginFromActivityListener.performRegister();
            }
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Activity activity=(Activity) context;
        LoginFromActivityListener=(OnLoginFromActivityListener)activity;
    }

    private void performLogIn()
    {
        String username=Username.getText().toString();
        String userpassword=UserPassword.getText().toString();

        Call<User>call=MainActivity.apiIntreface.PerformUserLogin(username,userpassword);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body().getResponse().equals("ok"))
                {
                    MainActivity.prefConfig.writeLoginStatus(true);
                    LoginFromActivityListener.perfromLogin(response.body().getName());
                }
                else if (response.body().getResponse().equals("failed"))
                {
                    MainActivity.prefConfig.displayToast("Failed");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

}
