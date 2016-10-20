package com.tom.atm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private static final int REQUEST_LOGIN = 50;
    private static final String TAG = "MainActivity";
    boolean logon = false;
    int[] icons = {R.drawable.func_balance,
                R.drawable.func_history,
                R.drawable.func_news,
                R.drawable.func_finance,
                R.drawable.func_exit};
//    String[] func = {"AAA", "BBB", "CCC", "DDD", "EEE"};
    String[] func;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        func = getResources().getStringArray(R.array.functions);
//        forListView();
//        forSpinner();
//        GridView grid = (GridView) findViewById(R.id.grid);
        ListView list = (ListView) findViewById(R.id.list2);

//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
//                this, R.array.functions, android.R.layout.simple_list_item_1);
        IconAdapter adapter = new IconAdapter(R.layout.func_item_horiz);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
//        grid.setAdapter(adapter);
//        grid.setOnItemClickListener(this);

        if (!logon){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent, REQUEST_LOGIN);
//            startActivity(new Intent(this, LoginActivity.class));
        }

    }

    private void forSpinner() {
        spinner = (Spinner) findViewById(R.id.spinner);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1,
//                getResources().getStringArray(R.array.notify));
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.notify, android.R.layout.simple_list_item_1);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        //被選擇的當下
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, position+"");
                Log.d(TAG, (String) adapter.getItem(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void forListView() {
        ListView list = (ListView) findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, func);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Log.d(TAG, position+"/"+id);
                switch ((int)id){
                    case 4:
                        finish();
                        break;
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_LOGIN){
            if (resultCode == RESULT_OK){
                Toast.makeText(this, "Success123", Toast.LENGTH_LONG).show();
            }else{
                finish();
            }
        }
    }

    public void getData(View v){
        String data = spinner.getSelectedItem().toString();
        TextView tv = (TextView) findViewById(R.id.dataText);
        tv.setText(data);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch ((int) id){
            case R.drawable.func_balance:
                break;
            case R.drawable.func_history:
                startActivity(new Intent(this, TransActivity.class));
                break;
            case R.drawable.func_finance:
                startActivity(new Intent(this, FinanceActivity.class));
                break;
            case R.drawable.func_news:
                startActivity(new Intent(this, StockActivity.class));
                break;
            case R.drawable.func_exit:
                finish();
                break;
        }
    }
    class IconAdapter extends BaseAdapter{
        int layout;

        public IconAdapter(int layoutRes){
            this.layout = layoutRes;
        }
        @Override
        public int getCount() {
            return icons.length;
        }

        @Override
        public Object getItem(int position) {
            return func[position];
        }

        @Override
        public long getItemId(int position) {
            return icons[position];
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                View view =
                        getLayoutInflater().inflate(layout, null);
                TextView tv = (TextView) view.findViewById(R.id.title);
                ImageView iv = (ImageView) view.findViewById(R.id.icon);
                tv.setText(func[position]);
                iv.setImageResource(icons[position]);
                convertView = view;
            }
            return convertView;
        }
    }
}
