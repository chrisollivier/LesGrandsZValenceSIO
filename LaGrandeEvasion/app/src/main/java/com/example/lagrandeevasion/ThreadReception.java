package com.example.lagrandeevasion;

import com.example.lagrandeevasion.MainActivity;
import com.example.lagrandeevasion.ui.home.HomeFragment;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ThreadReception extends Thread{
    HomeFragment IHM;
    private InputStream In;
    private boolean flagStopThread = false;

    public ThreadReception(HomeFragment _IHM, InputStream _In)
    {
        IHM = _IHM;
        In = _In;
    }

    @Override
    public void run()
    {
        super.run();
        while(flagStopThread == false) {
            byte[] recep = new byte[20];
            try {
                if (In.read(recep) > 0) {
                    String s = new String(recep, StandardCharsets.UTF_8);
                    IHM.setTextViewAlert("" + s);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void close(){
        flagStopThread = true;
    }
}
