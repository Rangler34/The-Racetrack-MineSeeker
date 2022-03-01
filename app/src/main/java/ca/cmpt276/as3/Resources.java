package ca.cmpt276.as3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

/**
 * Resource class stores all the resources used
 * includes hyperlinks
 */
public class Resources extends AppCompatActivity {


    TextView link1,link2,link3,link4,link5,
    link6,link7,link8,link9,link10,link11, link12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);


        link1 = findViewById(R.id.r1);
        link1.setMovementMethod(LinkMovementMethod.getInstance());

        link2 = findViewById(R.id.r2);
        link2.setMovementMethod(LinkMovementMethod.getInstance());

        link3 = findViewById(R.id.r3);
        link3.setMovementMethod(LinkMovementMethod.getInstance());

        link4 = findViewById(R.id.r4);
        link4.setMovementMethod(LinkMovementMethod.getInstance());

        link5 = findViewById(R.id.r5);
        link5.setMovementMethod(LinkMovementMethod.getInstance());

        link6 = findViewById(R.id.r6);
        link6.setMovementMethod(LinkMovementMethod.getInstance());

        link7 = findViewById(R.id.r7);
        link7.setMovementMethod(LinkMovementMethod.getInstance());

        link8 = findViewById(R.id.r8);
        link8.setMovementMethod(LinkMovementMethod.getInstance());

        link9 = findViewById(R.id.r9);
        link9.setMovementMethod(LinkMovementMethod.getInstance());

        link10 = findViewById(R.id.r10);
        link10.setMovementMethod(LinkMovementMethod.getInstance());

        link11 = findViewById(R.id.r11);
        link11.setMovementMethod(LinkMovementMethod.getInstance());

        link12 = findViewById(R.id.r12);
        link12.setMovementMethod(LinkMovementMethod.getInstance());
    }
}