package com.ragnarok.staticlayouttest.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.*;
import android.widget.BaseAdapter;
import android.widget.ListView;
import com.ragnarok.staticlayouttest.app.R;
import com.ragnarok.staticlayouttest.util.Util;
import com.ragnarok.staticlayouttest.util.FpsCalculator;

public class StaticLayoutUI extends ActionBarActivity {
    
    private static final String TAG = "StaticLayoutUI";
    
    private ListView listview;
    
    private StaticListAdapter adapter;
    
    private AutoScrollHandler autoScrollHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_layout_ui);
        
        listview = (ListView) findViewById(R.id.test_list);
        
        adapter = new StaticListAdapter(this);
        
        listview.setAdapter(adapter);
        
        autoScrollHandler = new AutoScrollHandler(listview, Util.TEST_LIST_ITEM_COUNT);
        
        findViewById(R.id.scroll_down_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoScrollHandler.startAutoScrollDown();
            }
        });

        findViewById(R.id.scroll_up_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoScrollHandler.startAutoScrollUp();
            }
        });


        FpsCalculator.instance().start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        FpsCalculator.instance().stop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_static_layout_ui, menu);
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
    
    private static class StaticListAdapter extends BaseAdapter {
        
        private Context context;
        
        public StaticListAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return Util.TEST_LIST_ITEM_COUNT;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.static_list_item, parent, false);
                
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.staticLayoutView = (StaticLayoutView) convertView.findViewById(R.id.static_layout_view);
                
                convertView.setTag(viewHolder);
            }
            
            ViewHolder holder = (ViewHolder) convertView.getTag();
            holder.staticLayoutView.setLayout(StaticLayoutManager.getInstance().getLayout(position));
            holder.staticLayoutView.invalidate();
            
            return convertView;
        }
        
        private class ViewHolder {
            StaticLayoutView staticLayoutView;
        }
    }
}
