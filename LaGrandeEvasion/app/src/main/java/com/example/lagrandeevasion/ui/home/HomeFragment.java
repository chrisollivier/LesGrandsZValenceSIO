package com.example.lagrandeevasion.ui.home;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.lagrandeevasion.Client_Web;
import com.example.lagrandeevasion.R;

public class HomeFragment extends Fragment implements View.OnClickListener {


    private TextView mTextViewAlert;
    private static Button mBtnConnexion;
    private static TextView mTextViewInstruction;
    private Client_Web monClient;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here
        }
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        mTextViewAlert = root.findViewById(R.id.TextViewAlert);
        mTextViewInstruction = root.findViewById(R.id.instructionConnexion);

        mBtnConnexion = root.findViewById(R.id.btnConnexion);
        mBtnConnexion.setOnClickListener(this);

        return root;
    }
    public void setTextViewAlert(String chaine)
    {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTextViewAlert.setText(chaine);
            }
        });

    }

    public void setTextInstructions(String chaine)
    {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTextViewInstruction.setText(chaine);
            }
        });

    }

    @Override
    public void onClick(View v) {
        if(v == mBtnConnexion)
        {
            if(mBtnConnexion.getText().equals("Connexion"))
            {
                monClient = new Client_Web(this, "172.29.10.230", 1000);
                mBtnConnexion.setText("Deconnexion");
            }
            else
            {
                monClient.Fin_Communication();
                mBtnConnexion.setText("Connexion");
            }

        }
    }
}