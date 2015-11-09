package com.andylahs.sqlitedbplus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

  Boolean option = false;
  Button allTasks;
  Button doneTasks;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    allTasks = (Button) findViewById(R.id.alltasks_button);
    allTasks.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        option = false;
        Intent intent = new Intent(MainActivity.this, TaskListActivity.class);
        intent.putExtra("option", option);
        startActivity(intent);
      }
    });

    doneTasks = (Button) findViewById(R.id.donetasks_button);
    doneTasks.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        option = true;
        Intent intent = new Intent(MainActivity.this, TaskListActivity.class);
        intent.putExtra("option", option);
        startActivity(intent);
      }
    });
  }
}
