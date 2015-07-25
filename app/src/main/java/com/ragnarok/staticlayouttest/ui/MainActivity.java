package com.ragnarok.staticlayouttest.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.ragnarok.staticlayouttest.app.R;
import com.ragnarok.staticlayouttest.util.FpsCalculator;
import com.ragnarok.staticlayouttest.util.GhostThread;
import com.ragnarok.staticlayouttest.util.TestSpan;


public class MainActivity extends ActionBarActivity {
    
    private Button staticLayoutBtn;
    private Button normalLayoutBtn;
    private Button staticLongStringBtn;
    private Button normalLongStringBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        staticLayoutBtn = (Button) findViewById(R.id.static_layout_test);
        normalLayoutBtn = (Button) findViewById(R.id.normal_layout_test);
        staticLongStringBtn = (Button) findViewById(R.id.static_long_string);
        normalLongStringBtn = (Button) findViewById(R.id.normal_long_string);
        
        GhostThread.start();
        
        staticLayoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StaticLayoutUI.class);
                startActivity(intent);
            }
        });
        
        normalLayoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NormalLayoutUI.class);
                startActivity(intent);
            }
        });
        
        staticLongStringBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StaticLongStringUI.class);
                startActivity(intent);
            }
        });
        
        normalLongStringBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NormalLongStringUI.class);
                startActivity(intent);
            }
        });
        
        FpsCalculator.instance().start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                TestSpan.init(MainActivity.this);
                StaticLayoutManager.getInstance().initLayout(MainActivity.this, TestSpan.getSpanString(), TestSpan.getLongSpanString());


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "init layout and span finish", Toast.LENGTH_LONG).show();
                    }
                });
            }
        }).start();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        FpsCalculator.instance().stop();
        GhostThread.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
