package com.example.jobschedulerexample;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

public class ExampleJobService extends JobService {
    
    private static final String TAG="DEMOJOBSERVICE";
    private boolean jobcancelled=false;
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        System.out.println("====jsobstartr=="+"Start");
        doBackgroundwork(jobParameters);
        return true;
    }

    private void doBackgroundwork(JobParameters jobParameters) {
    new Thread(new Runnable() {
        @Override
        public void run() {
            for(int i=0;i<10;i++){
                System.out.println("====run"+i);
                if(jobcancelled){
                    return;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Job finished");
            jobFinished(jobParameters,false);
        }
    }).start();
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.d(TAG,"job cancelled before completion");
        jobcancelled=true;
        return true;
    }
}
