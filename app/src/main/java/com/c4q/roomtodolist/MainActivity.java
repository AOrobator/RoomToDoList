package com.c4q.roomtodolist;

import android.arch.lifecycle.LiveData;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity
    implements ToDoListPresenter.ToDoListPresentation {

  protected ToDoListPresenter presenter;
  private RecyclerView tasksRecyclerView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    AppDatabase db = ((TaskApplication) getApplication()).getAppDatabase();
    TaskDao taskDao = db.taskDao();

    presenter = new ToDoListPresenter(taskDao);

    tasksRecyclerView = findViewById(R.id.tasks_recycler_view);
    tasksRecyclerView.setAdapter(new TaskAdapter(presenter));

    LiveData<Task[]> tasks = taskDao.getTasks();
    tasks.observe(this, new TasksObserver(presenter));

    FloatingActionButton addTaskFab = findViewById(R.id.add_task_fab);
    addTaskFab.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        presenter.onAddTaskFabClicked();
      }
    });
  }

  @Override protected void onStart() {
    super.onStart();

    presenter.attach(this);
  }

  @Override protected void onStop() {
    super.onStop();

    presenter.detach();
  }

  @Override public void showAddTaskDialog() {
    AddTaskDialogFragment fragment = new AddTaskDialogFragment();
    fragment.show(getSupportFragmentManager(), "Add Task");
  }

  @Override public void notifyDataSetChanged() {
    Log.d("MainActivity", "notifyDatasetChanged()");

    tasksRecyclerView.getAdapter().notifyDataSetChanged();
  }
}
