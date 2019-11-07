package mk.ukim.finki.students.lab_intents.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import java.util.logging.Logger;

public class LoggingService extends IntentService {
    public LoggingService() {
        super("LoggingService");
    }
    private static Logger logger = Logger.getLogger("LoggingService");

    @Override
    protected void onHandleIntent(Intent intent) {
        logger.info("Im logging something in my console");
    }

}
