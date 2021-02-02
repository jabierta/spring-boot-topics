package com.spring_boot_schedule.scheduling;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@EnableAsync
@Configuration
@EnableScheduling
public class ThreadExecutorsConfiguration {
  @Value("${thread.pool.size}")
  private Integer threadPoolSize;
  @Bean
  public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
    ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
    threadPoolTaskScheduler.setPoolSize(threadPoolSize);
    threadPoolTaskScheduler.setThreadNamePrefix("TaskScheduler-");

    return threadPoolTaskScheduler;
  }

  // array list of schedluers!
}
