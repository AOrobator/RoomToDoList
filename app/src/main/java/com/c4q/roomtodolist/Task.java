package com.c4q.roomtodolist;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Task {

  @PrimaryKey(autoGenerate = true)
  public int id;

  public String taskName;

  public Task(String taskName) {
    this.taskName = taskName;
  }
}
