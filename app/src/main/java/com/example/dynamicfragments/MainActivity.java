package com.example.dynamicfragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

// contains dynamic frag + backstack
// of frags + data passing in frags
public class MainActivity extends AppCompatActivity {

    String Root_Frag = "root_fagment";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnMessages, btnStatus, btnCalls;

        btnMessages = findViewById(R.id.btnMessages);
        btnStatus = findViewById(R.id.btnStatus);
        btnCalls = findViewById(R.id.btnCalls);

        // default frag
        loadFrag(new MessagesFragment(), 0);

        btnMessages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                loadFrag(new MessagesFragment(), 0);
            }
        });

        btnStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                loadFrag(new StatusFragment(), 1);
            }
        });

        btnCalls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                loadFrag(new CallsFragment(), 1);
            }
        });
    }

    // flag 0 for add, 1 for replace
    public void loadFrag(Fragment fragment_name, int flag)
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        if (flag == 0) {
            ft.add(R.id.FL, fragment_name);

            fm.popBackStack(Root_Frag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            ft.addToBackStack(Root_Frag);
        }
        else {
            ft.replace(R.id.FL, fragment_name);
            ft.addToBackStack(null);
        }

        ft.commit();
    }
}
