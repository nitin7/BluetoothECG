
package your.tutorial.graph;

import java.util.Timer;
import java.util.TimerTask;

import org.achartengine.GraphicalView;






import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class SettingsActivity extends Activity{
	static final int RANGE_DIALOG_ID = 0;
	EditText e2;
	private static GraphicalView view;
	private LineGraph line = new LineGraph();
	private static Thread thread;
	
	protected Dialog onCreateDialog(int id) {
        switch (id) {
        case RANGE_DIALOG_ID:
        	final CharSequence[] items = {"30 min", "1 hr", "1.5hr", "2 hr"};
        	AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Pick a range");
            builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int item) {
                	if (ServiceStarter.serviceOn)
                		SAService.wakeupRange = (item+1)*30;
                }
            });
            AlertDialog alert = builder.create();
            return alert;
        }
        return null;
	}
	
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		Log.v("Inside","InsideSettingsActivity");
		setContentView(R.layout.setting);
		/*thread = new Thread() {
			public void run()
			{	
				//Condition for loop needs to be changed to as long as heartbeat tab is open!
				int i=0;
				for (;;) 
				{
					i++;
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Point p = MockData.getDataFromReceiver(i); // We got new data!
					line.addNewPoints(p); // Add it to our graph
				//	if (i % 50 == 0)
					//	line.delPoints();
					view.repaint();
				}
			}
		};
		thread.start();*/
       // final String[] listItems = {"Set Time Range", "Sleep Simulation", "Heart Rate"};
        
       // setListAdapter(new ArrayAdapter<String>(this, R.layout.setting, listItems));
        String toastText = "Service is not on";
		if (ServiceStarter.serviceOn)
		{
			toastText = "Heart Rate: " + SAService.avgRate + " min avg: " + SAService.minAvg;
		}
		
	    boolean used=false;
		Log.v("ere","rere");
        used=true;
        this.e2 = (EditText) findViewById(R.id.editText1);
	    
		 new CountDownTimer(16000* 1000, 1000) {
	            int x = 5;
	            
	            @Override
	            public void onTick(long millisUntilFinished) {
	            	
	        		Toast.makeText(getApplicationContext(), "Heart Rate: " + SAService.avgRate + " min avg: " + SAService.minAvg, Toast.LENGTH_SHORT).show();
	        		e2.setText("Heart Rate: " + SAService.avgRate);
	         
	            }

	            @Override
	            public void onFinish() 
	            {
	            
		            	Log.v("gotcha","gotcha");
		            
	            	Log.v("work,","work");
	            }
	            
	          
	        }.start();	 

	   
	    
		int n=0;
		Toast.makeText(getApplicationContext(), "Heart Rate: " + SAService.avgRate + " min avg: " + SAService.minAvg, Toast.LENGTH_SHORT).show();
       
      
	}
	/*
	@Override
	protected void onStart() {
		super.onStart();
		view = line.getView(this);
		setContentView(view);
	}*/
}
