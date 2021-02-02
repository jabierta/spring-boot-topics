package com.spring_boot_schedule.scheduling;

import java.util.Date;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RunnableTask implements Runnable {
  private final String message;
  private final Date date;

  @Override
  public void run() {
    Date dateBeforeSleep = new Date(System.currentTimeMillis());

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Date dateAfterSleep = new Date(System.currentTimeMillis());
    System.out.println(
        "\n"
            + "Date issued: "
            + date
            + "\n"
            + "Date before sleep: "
            + dateBeforeSleep
            + "\n"
            + "Date after sleep: "
            + dateAfterSleep
            + "\n"
            + "Runnable Task with "
            + message
            + " on thread "
            + Thread.currentThread().getName()
            + "\n");
  }
}
