package com.c4q.roomtodolist;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.util.Log;

public class TasksObserver implements Observer<Task[]> {
  private final ToDoListPresenter presenter;

  public TasksObserver(ToDoListPresenter presenter) {
    this.presenter = presenter;
  }

  @Override public void onChanged(@Nullable Task[] tasks) {
    Log.d("TasksObserver", "onChanged(numTasks =" + tasks.length + " )");
    presenter.onTasksChanged(tasks);
  }
}
