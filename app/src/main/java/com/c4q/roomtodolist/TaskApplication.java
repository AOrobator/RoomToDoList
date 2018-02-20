package com.c4q.roomtodolist;

import android.app.Application;
import android.arch.persistence.room.Room;

public class TaskApplication extends Application {

  private AppDatabase appDatabase;

  @Override public void onCreate() {
    super.onCreate();

    appDatabase = Room.databaseBuilder(this, AppDatabase.class, "task-database")
        .build();
  }

  public AppDatabase getAppDatabase() {
    return appDatabase;
  }
}
