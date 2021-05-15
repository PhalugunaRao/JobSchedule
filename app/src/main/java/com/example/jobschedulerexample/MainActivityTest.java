package com.example.jobschedulerexample;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivityTest extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.start_job).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ComponentName componentName=new ComponentName(MainActivityTest.this,ExampleJobService.class);

                JobInfo info= new JobInfo.Builder(123,componentName)
                        .setRequiresCharging(true)
                        .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                        .setPersisted(true)
                        .setPeriodic(15*60*1000)
                        .build();

                JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
                int resultCode=scheduler.schedule(info);
                if(resultCode==JobScheduler.RESULT_SUCCESS){
                    System.out.println("MainActivityTest.onClick==="+"jobsucess");
                }else{
                    System.out.println("MainActivityTest.onClick==="+"job fails");
                }

            }
        });

        findViewById(R.id.stop_job).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JobScheduler scheduler=(JobScheduler)getSystemService(JOB_SCHEDULER_SERVICE);
                scheduler.cancel(123);

            }
        });

    }
}
