package com.spring_boot_schedule.scheduling;

import java.util.Date;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
  private final ThreadPoolTaskScheduler threadPoolTaskScheduler;

  @GetMapping("/schedule")
  public String scheduleJobs() {
    Long currentTime = System.currentTimeMillis();

    System.out.println(new Date(currentTime));

    Date threeSec = new Date(currentTime + 3000);
    Date fourSec = new Date(currentTime + 4000);
    Date fiveSec = new Date(currentTime + 5000);
    Date sixSec = new Date(currentTime + 6000);
    Date sevenSec = new Date(currentTime + 7000);
    Date tenSec = new Date(currentTime + 10000);

    threadPoolTaskScheduler.schedule(
        new RunnableTask("Specific time, 3 Seconds from now", threeSec), threeSec);

    threadPoolTaskScheduler.schedule(
        new RunnableTask("Specific time, 4 Seconds from now", fourSec), fourSec);

    threadPoolTaskScheduler.schedule(
        new RunnableTask("Specific time, 5 Seconds from now", fiveSec), fiveSec);

    threadPoolTaskScheduler.schedule(
        new RunnableTask("Specific time, 3 Seconds from now (2)", threeSec), threeSec);

    threadPoolTaskScheduler.schedule(
        new RunnableTask("Specific time, 3 Seconds from now(3)", threeSec), threeSec);

    threadPoolTaskScheduler.schedule(
        new RunnableTask("Specific time, 4 Seconds from now(2)", fourSec), fourSec);

    threadPoolTaskScheduler.schedule(
        new RunnableTask("Specific time, 6 Seconds from now", sixSec), sixSec);

    threadPoolTaskScheduler.schedule(
        new RunnableTask("Specific time, 7 Seconds from now", sevenSec), sevenSec);

    threadPoolTaskScheduler.schedule(
        new RunnableTask("Specific time, 3 Seconds from now(4)", threeSec), threeSec);

    threadPoolTaskScheduler.schedule(
        new RunnableTask("Specific time, 5 Seconds from now(2)", fiveSec), fiveSec);

    threadPoolTaskScheduler.schedule(
        new RunnableTask("Specific time, 10 Seconds from now", tenSec), tenSec);

    return "Completed Schedule set up";
  }

  @GetMapping("/schedule/{NumberOfTask}")
  public String scheduleJobs(@PathVariable("NumberOfTask") Integer numberOfTask) {
    int initialSizeOfThread = numberOfTask;

    Long currentTime = System.currentTimeMillis();

    System.out.println(new Date(currentTime));

    Random random = new Random();

    do {
      int randomNumber = random.nextInt(10) + 1;

      Date date = new Date(currentTime + (randomNumber * 1000));

      int currentThreadNum = (initialSizeOfThread - numberOfTask) + 1;

      threadPoolTaskScheduler.schedule(
          new RunnableTask(
              "Thread #"
                  + currentThreadNum
                  + " at specific time: "
                  + randomNumber
                  + " Seconds from now!",
              date),
          date);

      numberOfTask--;

    } while (numberOfTask > 0);

    return "Completed Schedule set up";
  }
}
