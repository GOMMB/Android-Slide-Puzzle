package com.gommb.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int empty = 8;
    Button blist[] = new Button[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView t = (TextView)findViewById(R.id.textView);
        setTitle("Puzzle");
        blist[0] = (Button)findViewById(R.id.b1);
        blist[1] = (Button)findViewById(R.id.b2);
        blist[2] = (Button)findViewById(R.id.b3);
        blist[3] = (Button)findViewById(R.id.b4);
        blist[4] = (Button)findViewById(R.id.b5);
        blist[5] = (Button)findViewById(R.id.b6);
        blist[6] = (Button)findViewById(R.id.b7);
        blist[7] = (Button)findViewById(R.id.b8);
        blist[8] = (Button)findViewById(R.id.b9);

        for (Button b : blist) {
            b.setOnClickListener(
                    new View.OnClickListener(){
                        public void onClick(View v) {
                            click(v);
                        }
                    }
            );
        }
        t.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v) {
                        random();
                    }
                }
        );
        random();

    }

    private void random() {
        Random ran = new Random();
        for (int i = 0; i < (3000); i++) {
            int rand_ = ran.nextInt((8));
            if (click(blist[rand_])) {
                continue;
            }
            else
                i--;
        }
    }

    private boolean click(View v) {
        Button b = (Button)v;
        Integer loc = Arrays.asList(blist).indexOf(b);
        if (loc == empty) {
            return false;
        }
        int[] bc = coord(loc);
        int[] ec = coord(empty);
        if ((bc[1] == ec[1] && (bc[0] == ec[0] + 1 || bc[0] == ec[0] - 1)) || (bc[0] == ec[0] && (bc[1] == ec[1] + 1 || bc[1] == ec[1] - 1))) {
            blist[empty].setText(b.getText());
            b.setText("");
            empty = loc;
            return true;
        } else {
            return false;
        }

    }

    private int[] coord(int x) {
        x++;
        int[] coords = new int[2];
        for (int i3 = 1, b = 1, e = 3; i3 <= 3; i3++) {
            if (x <= e && x >= b) {
                coords[1] = i3;
                break;
            }
            b += 3;
            e += 3;
        }
        end:
        for (int i = 1; i <= 3; i++){
            for (int i2 = i; i2 <= 9; i2 += 3) {
                if (x == i2) {
                    coords[0] = i;
                    break end;
                }
            }
        }
        return coords;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
