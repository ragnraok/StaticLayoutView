package com.ragnarok.staticlayoutview.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.TypedValue;
import android.view.*;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.ragnarok.staticlayouttest.app.R;
import com.ragnarok.staticlayoutview.util.TestSpan;
import com.ragnarok.staticlayoutview.util.Util;

public class NormalLayoutUI extends ActionBarActivity {

    private ListView listView;
    
    private NormalListAdapter adapter;
    
    private AutoScrollHandler autoScrollHandler;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_layout_ui);
        
        listView = (ListView) findViewById(R.id.test_list);
        
        adapter = new NormalListAdapter(this);
        
        listView.setAdapter(adapter);
        
        autoScrollHandler = new AutoScrollHandler(listView, Util.TEST_LIST_ITEM_COUNT);

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
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_normal_layout_ui, menu);
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
    
    private static class NormalListAdapter extends BaseAdapter {
        
        private Context context;
        
        public NormalListAdapter(Context context) {
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
                convertView = LayoutInflater.from(context).inflate(R.layout.normal_list_item, parent, false);

                ViewHolder viewHolder = new ViewHolder();
                viewHolder.textView = (TextView) convertView.findViewById(R.id.normal_text);

                convertView.setTag(viewHolder);
            }

            ViewHolder holder = (ViewHolder) convertView.getTag();
            holder.textView.setText(TestSpan.getSpanString(position));
            holder.textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, Util.fromDPtoPix(context, Util.TEXT_SIZE_DP));

            return convertView;
        }

        private class ViewHolder {
            TextView textView;
        }
    }
}
