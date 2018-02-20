package com.c4q.roomtodolist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

  private final ToDoListPresenter presenter;

  public TaskAdapter(ToDoListPresenter presenter) {
    this.presenter = presenter;
  }

  @Override public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.list_item_task, parent, false);
    return new TaskViewHolder(view);
  }

  @Override public void onBindViewHolder(TaskViewHolder holder, int position) {
    presenter.bindView(holder, position);
  }

  @Override public int getItemCount() {
    return presenter.getItemCount();
  }

  public static class TaskViewHolder extends RecyclerView.ViewHolder
      implements ToDoListPresenter.TaskListItem {
    private TextView taskNameTextView;

    public TaskViewHolder(View itemView) {
      super(itemView);

      taskNameTextView = itemView.findViewById(R.id.task_name);
    }

    @Override public void setTaskName(String taskName) {
      taskNameTextView.setText(taskName);
    }
  }
}
