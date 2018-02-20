package com.c4q.roomtodolist;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@Dao
public interface TaskDao {

  @Insert
  long addTask(Task task);

  @Query("SELECT * FROM task")
  LiveData<Task[]> getTasks();
}
