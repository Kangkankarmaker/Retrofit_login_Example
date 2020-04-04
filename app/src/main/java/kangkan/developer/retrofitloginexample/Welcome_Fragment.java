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
import android.widget.TextView;


public class Welcome_Fragment extends Fragment {
    private TextView textView;
    private Button button;

    OnLogoutlistener logoutlistener;

    public interface OnLogoutlistener
    {
        public void logoutPerformed();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_welcome, container, false);
        textView=view.findViewById(R.id.txt_name);

        textView.setText(MainActivity.prefConfig.readName());
        button=view.findViewById(R.id.btn_log_out);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutlistener.logoutPerformed();

            }
        });


        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        Activity activity=(Activity)context;
        logoutlistener=(OnLogoutlistener) activity;
    }
}
