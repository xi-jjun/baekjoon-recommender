package com.khk.backjoonrecommender.common;

import com.khk.backjoonrecommender.service.SystemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
//@Component
public class ScheduledTasks {

        private final SystemService systemService;

        /* 스케쥴 task 정의 */
//        @Scheduled(fixedDelay = 5000)
        @Scheduled(cron = "0 0 0 1/1 * ? *")
        public void dailyResetScheduler() {
                log.info("reset daily reload count");
                systemService.resetDailyReloadCount();
        }
}
