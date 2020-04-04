package kangkan.developer.retrofitloginexample;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements LogIn_Fragment.OnLoginFromActivityListener, Welcome_Fragment.OnLogoutlistener {

    @SuppressLint("StaticFieldLeak")
    public static PrefConfig prefConfig;

    public static ApiIntreface apiIntreface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefConfig=new PrefConfig(this);
        apiIntreface=ApiClient.getApiClient().create(ApiIntreface.class);

        if (findViewById(R.id.fragment_container)!=null)
        {
            if (savedInstanceState!=null)
            {
                return;
            }
            if (prefConfig.readLogInStatus())
            {
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,new Welcome_Fragment()).commit();
            }
            else
            {
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,new LogIn_Fragment()).commit();
            }
        }

    }

    @Override
    public void performRegister() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Registration_Fragment()).addToBackStack(null).commit();
    }

    @Override
    public void perfromLogin(String name) {
        prefConfig.writeName(name);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Welcome_Fragment()).addToBackStack(null).commit();
    }

    @Override
    public void logoutPerformed() {
        prefConfig.writeLoginStatus(false);
        prefConfig.writeName("User");
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new LogIn_Fragment()).addToBackStack(null).commit();
    }
}
