package com.uml.android.benchmark;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class UML_Android_Benchmark extends Activity {
	private Handler mHandler = new Handler();
	private TextView strCPU, strMEM, strIO, strGraphics;
    private ProgressBar cpuProgress, memProgress, ioProgress, graphicsProgress;
    private int stageProgress = 0;
	
	private int testStage = 0;
	private boolean isTesting = false;
	
	//==============================================================================
    // Called when the activity is first created.
	//==============================================================================
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        strCPU = (TextView)findViewById(R.id.cpu_string);
        strMEM = (TextView)findViewById(R.id.mem_string);
        strIO = (TextView)findViewById(R.id.io_string);
        strGraphics = (TextView)findViewById(R.id.graphics_string);
        
        mHandler.removeCallbacks(mUpdateTimeTask);
        mHandler.postDelayed(mUpdateTimeTask, 100);
        
        ShowSplashScreen();
        
        cpuProgress = (ProgressBar) findViewById(R.id.cpu_progress);
        memProgress = (ProgressBar) findViewById(R.id.mem_progress);
        ioProgress = (ProgressBar) findViewById(R.id.io_progress);
        graphicsProgress = (ProgressBar) findViewById(R.id.graphics_progress);
    }
    
    //==============================================================================
    private void ShowSplashScreen()
    {
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.splash)
               .setCancelable(false)
               .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            	   public void onClick(DialogInterface dialog, int id) {
                       dialog.cancel();
                       RunBenchmark();
                  }
               })
               .setNegativeButton("No", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   UML_Android_Benchmark.this.finish();
                   }
               });
        AlertDialog alert = builder.create();
        alert.show();
    }
    
    //==============================================================================
    private void RunBenchmark()
    {
    	testStage = 0;
    	
    	cpuProgress.setProgress(0);
    	memProgress.setProgress(0);
    	ioProgress.setProgress(0);
    	graphicsProgress.setProgress(0);
    	
    	isTesting = true; 	
    }
        
    //==============================================================================
    private Runnable mUpdateTimeTask = new Runnable() {
	   public void run() {
	     
		   if (isTesting)
		   {
			   if (testStage == 0)
			   {
				   strCPU.setText("CPU: N/A");
				   cpuProgress.setProgress(stageProgress);
				   stageProgress += 2;
				   
				   if (stageProgress >= 100)
				   {
					   cpuProgress.setProgress(100);
					   stageProgress = 0;
					   testStage = 1;
				   }
			   }
			   else if (testStage == 1)
			   {
				   strMEM.setText("MEM: N/A");
				   memProgress.setProgress(stageProgress);
				   stageProgress += 3;
				   
				   if (stageProgress >= 100)
				   {
					   memProgress.setProgress(100);
					   stageProgress = 0;
					   testStage = 2;
				   }
			   }
			   else if (testStage == 2)
			   {
				   strIO.setText("IO: N/A");
				   ioProgress.setProgress(stageProgress);
				   stageProgress += 2;
				   
				   if (stageProgress >= 100)
				   {
					   ioProgress.setProgress(100);
					   stageProgress = 0;
					   testStage = 3;
				   }
			   }
			   else if (testStage == 3)
			   {
				   strGraphics.setText("Graphics: N/A");
				   graphicsProgress.setProgress(stageProgress);
				   stageProgress += 6;
				   
				   if (stageProgress >= 100)
				   {
					   graphicsProgress.setProgress(100);
					   stageProgress = 0;
					   isTesting = false;
				   }
			   }
		   }
		   		   		   
	       mHandler.postDelayed(mUpdateTimeTask, 100);
	   }
    };
}