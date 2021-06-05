package com.sk.contextualmenucontactdeatails;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    String[] names = {"sheetal", "sonali", "Harshali", "Shivani"};
    String[] phone = {"849451151", "87846155", "4664845132", "6456465"};
    int Index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = findViewById(R.id.listview);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);

        listView.setAdapter(arrayAdapter);

        registerForContextMenu(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Index = i;
            }
        });
        registerForContextMenu(listView);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        menu.setHeaderTitle("Selected action");
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.mainmenu, menu);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit:
                Toast.makeText(getApplicationContext(), "Clicked on edit", Toast.LENGTH_SHORT).show();
                break;

            case R.id.remove:
                Toast.makeText(getApplicationContext(), "Clicked on delete", Toast.LENGTH_SHORT).show();
                break;

            case R.id.view:
                Toast.makeText(getApplicationContext(), "Clicked on view", Toast.LENGTH_SHORT).show();
                break;

            case R.id.call:
                Intent calling = new Intent(Intent.ACTION_CALL);
                calling.setData(Uri.parse("tel:" + phone[Index]));
                startActivity(calling);

                Toast.makeText(getApplicationContext(), "Clicked on call", Toast.LENGTH_SHORT).show();
                break;
            default:
                return super.onContextItemSelected(item);
        }
        return true;
    }
}