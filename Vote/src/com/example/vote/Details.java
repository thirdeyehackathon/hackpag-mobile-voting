package com.example.vote;

//import android.support.v7.app.ActionBarActivity;
//import com.example.askhelpself.Mymaps;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Details extends Activity {
	int CAMERA_PIC_REQUEST = 2500;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		Intent in=getIntent();
		 Bundle b=in.getExtras();
		 String nameString = b.getString("name");
		 String genString = b.getString("gender");
		 String dobString = b.getString("dob");
		 String stateString = b.getString("state");
		 String localityString = b.getString("locality");
		 String inf="Name      :"+ nameString +"\n"+
		            "gender    :"+genString+"\n"+
				    "DOB       :"+dobString+"\n"+
		            "State     :"+stateString+"\n"+
				    "Locality  :"+localityString;
		 TextView t=(TextView)findViewById(R.id.textView1);
		 t.setText(inf);
		 Toast.makeText(getApplicationContext(), inf, Toast.LENGTH_LONG).show();
//	}
/*	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_PIC_REQUEST) {
              Bitmap image = (Bitmap) data.getExtras().get("data");
              ImageView imageview = (ImageView) findViewById(R.id.ImageView01);
              imageview.setImageBitmap(image);
        }
	}*/
// public void info(View v)
 //{
	/* Intent in=getIntent();
	 Bundle b=in.getExtras();
	 String nameString = b.getString("name");
	 String genString = b.getString("gender");
	 String dobString = b.getString("dob");
	 String stateString = b.getString("state");
	 String localityString = b.getString("locality");
	 String inf="Name      :"+ nameString +"\n"+
	            "gender    :"+genString+"\n"+
			    "DOB       :"+dobString+"\n"+
	            "State     :"+stateString+"\n"+
			    "Locality  :"+localityString;*/
	 try{
//		 Toast.makeText(getApplicationContext(),"jlo", Toast.LENGTH_SHORT).show();
		 Calendar c = Calendar.getInstance();
		 SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy");;
		// Date df1 =new Date(dobString);
		 Date df1 =df.parse(dobString);
		 Date today = Calendar.getInstance().getTime();
		// String formattedDate1 = ((java.text.DateFormat) df1).format(c.getTime());
		 SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		 String folderName = formatter.format(today);
		 
		 //SimpleDateFormat df1 = new SimpleDateFormat("dd-MMM-yyyy");
		/* try
		 {
		// df1=SimpleDateFormat.getDateInstance(DateFormat.DATE).parse(dobString);
		 }
		 catch(ParseException e)
		 {
			 
		 }*/
//		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     //java.util.Date dateOne = (java.util.Date) df.parse(dobString);
 //    java.util.Date dateTwo = (java.util.Date) df.parse(stime2); */  
     //long timeDiff = Math.abs(dateOne.getTime() - dateTwo.getTime());
     long timeDiff = Math.abs(df1.getTime() - today.getTime());
     float y=timeDiff;
     long diffSeconds = timeDiff / 1000;
     float d=y / 1000;
     long diffMinutes = timeDiff / (60 * 1000);
     float d1=y / (60 * 1000);
     float diffHours = (y / (60 * 1000))/60;
     float diffyears=(float) (diffHours*0.000114155);
     String sdu=diffyears+"";
     Toast.makeText(getApplicationContext(),sdu , Toast.LENGTH_LONG).show();
     
     
     
     
     
     
     
	
	 
	
	 }
	 catch(Exception e)
	 {
	Log.e("error", e.toString());	 
	 }
	 t.setClickable(true);
     t.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//  TODO Auto-generated method stub
			//	Intent i=new Intent(getBaseContext(),Mymaps.class);
         	//startActivity(i);
				
				//Intent ii=new Intent(getBaseContext(),OpenCameraDemo.class);
				Intent ii1=new Intent(getBaseContext(),Party.class);
				//startActivity(ii);
				startActivity(ii1);
				
			//	Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            //    startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
			}
     });
 }
 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.details, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		
		return super.onOptionsItemSelected(item);
	}
}
