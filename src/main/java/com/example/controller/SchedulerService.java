package com.example.controller;

import com.example.com.example.util.ApplicationContextProvider;
import com.example.dao.FileStoreDao;
import com.example.service.EmailService;
import com.example.service.FileData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * A scheduler service which searches for all the files added within last 1 hour to the file store.
 * If there are files added, then the service sends an email with the content of the found files.
 */
@Component
@EnableAutoConfiguration
@ComponentScan
public class SchedulerService {

    private EmailService emailService = (EmailService) ApplicationContextProvider.applicationContext.getBean("emailServiceImpl");
    private FileStoreDao fileStoreDao = (FileStoreDao) ApplicationContextProvider.applicationContext.getBean("fileStoreDaoImpl");

    private static final Logger LOG = LoggerFactory.getLogger(SchedulerService.class);
    private static final int repeatInterval = 3600000;

    //1 hour = [0 0 0/1 1/1 * ?]
    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void runJob() {
        String methodName = "runJob() : Entry";
        LOG.info(methodName);

        List<FileData> output = fileStoreDao.getNewAddedFile(System.currentTimeMillis(), repeatInterval);
        if (output.size() != 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (FileData fileData : output) {
                stringBuilder.append(fileData.getFileData());
            }
            emailService.sendEmail(stringBuilder.toString());
        }
    }
}
