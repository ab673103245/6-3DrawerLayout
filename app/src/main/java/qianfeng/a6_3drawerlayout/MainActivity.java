package qianfeng.a6_3drawerlayout;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = ((DrawerLayout) findViewById(R.id.drawerLayout));
        lv = ((ListView) findViewById(R.id.lv));

        final List<String> list = new ArrayList<>();
        list.add("手机");
        list.add("相册");
        list.add("钱包");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,list.get(position),Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(Gravity.LEFT);
            }
        });

        //出现ActionBar的 后退键
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //关联呢？将ActionBar和DrawerLayout关联起来
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,0,0);

        // 让toggle 监听drawerLayout的打开状态
        drawerLayout.setDrawerListener(toggle);

        // 让ActionBar和DrawerLayout状态同步
        toggle.syncState();





    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                if(drawerLayout.isDrawerOpen(Gravity.LEFT))
                {
                    drawerLayout.closeDrawer(Gravity.LEFT);
                }else
                {
                    drawerLayout.openDrawer(Gravity.LEFT);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void openLeft(View view) { // 打开左边的侧拉菜单
        drawerLayout.openDrawer(Gravity.LEFT); // 注意这里面的参数也可以是一个View，就是左边菜单的那个view(即左边的LinearLayout)
    }

    public void openRight(View view) { // 打开右边的侧拉菜单

        drawerLayout.openDrawer(Gravity.RIGHT);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(Gravity.LEFT))
        {
            drawerLayout.closeDrawer(Gravity.LEFT);
        }else
        {
            super.onBackPressed();
        }
    }
}
