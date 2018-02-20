package com.c4q.roomtodolist;

import android.util.Log;

public class ToDoListPresenter {
  private final TaskDao taskDao;
  private Task[] tasks = {};
  private ToDoListPresentation presentation;

  public ToDoListPresenter(TaskDao taskDao) {
    this.taskDao = taskDao;
  }

  public void attach(ToDoListPresentation presentation) {
    this.presentation = presentation;
  }

  public void detach() {
    presentation = null;
  }

  public void onAddTaskFabClicked() {
    if (presentation != null) {
      presentation.showAddTaskDialog();
    }
  }

  public void addTask(final String taskName) {
    Log.d("Presenter", "Adding Task: " + taskName);
    new Thread(new Runnable() {
      @Override public void run() {
        taskDao.addTask(new Task(taskName));
      }
    }).start();
  }

  public void bindView(TaskListItem listItem, int position) {
    listItem.setTaskName(tasks[position].taskName);
  }

  public int getItemCount() {
    return tasks.length;
  }

  public void onTasksChanged(Task[] tasks) {
    this.tasks = tasks;

    if (presentation != null) {
      presentation.notifyDataSetChanged();
    }
  }

  public interface ToDoListPresentation {
    void showAddTaskDialog();

    void notifyDataSetChanged();
  }

  public interface TaskListItem {
    void setTaskName(String taskName);
  }
}
