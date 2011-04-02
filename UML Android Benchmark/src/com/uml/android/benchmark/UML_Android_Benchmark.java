package com.uml.android.benchmark;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class UML_Android_Benchmark extends Activity {
	private Handler mHandler = new Handler();
	TextView strCPU; int cpuProgress;
	TextView strMEM; int memProgress;
	TextView strIO; int ioProgress;
	TextView strGraphics; int graphicsProgress;
	
	int testStage = 0;
	boolean isTesting = false;
	
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
    	
    	cpuProgress = 0;
    	memProgress = 0;
    	ioProgress = 0;
    	graphicsProgress = 0;
    	
    	isTesting = true; 	
    }
        
    //==============================================================================
    private Runnable mUpdateTimeTask = new Runnable() {
	   public void run() {
	     
		   if (isTesting)
		   {
			   if (testStage == 0)
			   {
				   strCPU.setText("CPU: " + cpuProgress + "%");
				   cpuProgress += 4;
				   
				   if (cpuProgress >= 100)
				   {
					   strCPU.setText("CPU: 100%");
					   testStage = 1;
				   }
			   }
			   else if (testStage == 1)
			   {
				   strMEM.setText("MEM: " + memProgress + "%");
				   memProgress += 3;
				   
				   if (memProgress >= 100)
				   {
					   strMEM.setText("MEM: 100%");
					   testStage = 2;
				   }
			   }
			   else if (testStage == 2)
			   {
				   strIO.setText("IO: " + ioProgress + "%");
				   ioProgress += 2;
				   
				   if (ioProgress >= 100)
				   {
					   strIO.setText("IO: 100%");
					   testStage = 3;
				   }
			   }
			   else if (testStage == 3)
			   {
				   strGraphics.setText("Graphics: " + graphicsProgress + "%");
				   graphicsProgress += 6;
				   
				   if (graphicsProgress >= 100)
				   {
					   strGraphics.setText("Graphics: 100%");
					   isTesting = false;
				   }
			   }
		   }
		   		   		   
	       mHandler.postDelayed(mUpdateTimeTask, 100);
	   }
    };
}