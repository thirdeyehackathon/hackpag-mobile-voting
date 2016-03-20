package com.example.vote;

//package com.example.watchman;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Time;
import java.sql.Timestamp;
//import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//import com.example.sabari.Retr;

//import com.example.sabari.Lorry;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.telephony.SmsManager;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Toast;

public class Updat extends Activity {
	String stime1,stime2,stime3,stime4,stime5,stime6,stime7,stime8;
	int sp;
String status,ad;
int cp=0;
String result = null;
InputStream is = null;
ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_updat);
		Bundle bundlee = getIntent().getExtras();
		
		 status = bundlee.getString("status");
		 Toast.makeText(getApplicationContext(), status , Toast.LENGTH_LONG).show();
		 nameValuePairs.add(new BasicNameValuePair("partie",status));
		 new Longoperation().execute();
		 
     //  Toast.makeText(getApplicationContext(), "aa", Toast.LENGTH_LONG).show();
	}     
		class Longoperation  extends AsyncTask<Void, Void, String>
		   {
			
			String[] add;
		  	 private ProgressDialog Dialog = new ProgressDialog(Updat.this);
		  	 protected void onPreExecute() {
		           // NOTE: You can call UI Element here.
		            
		           //UI Element
		          // uiUpdate.setText("Output : ");
		           Dialog.setMessage("Choosing source party..");
		           Dialog.show();
		       }
	
		  	@Override
		  	 protected String doInBackground(Void... arg) 
		  	 {
		  		  String result=null;
		            InputStream is = null;
		          
		            
		 try{
			// String s=null;
			// Bundle bundlee1 = getIntent().getExtras();
			 //s=bundlee1.getString("status");
			 //nameValuePairs.add(new BasicNameValuePair("lryno",s));
             HttpClient httpclient = new DefaultHttpClient();
             //HttpPost httppost = new HttpPost("http://kecitians.tk/ulionel.php");
             HttpPost httppost = new HttpPost("http://10.0.2.2/choose.php");
             httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
             HttpResponse response = httpclient.execute(httppost);
             HttpEntity entity = response.getEntity();
             is = entity.getContent();

             Log.e("log_tag", "connection success ");
             Log.e("log_tag", "connection success ");
             Log.e("log_tag", "connection success ");
            // Toast.makeText(getApplicationContext(), "pass", Toast.LENGTH_SHORT).show();
     }
     catch(Exception e)
     {
             Log.e("log_tag", "Error in http connection "+e.toString());
            // Toast.makeText(getApplicationContext(), "Connection fail", Toast.LENGTH_SHORT).show();

     }
     //convert response to string
     try
     {
    	
             BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
             StringBuilder sb = new StringBuilder();
             String line = null;
             while ((line = reader.readLine()) != null)
             {
                     sb.append(line + "\n");
                     //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                     //Intent i = new Intent(getBaseContext(),Lorry.class);
                    // startActivity(i);
             }
             is.close();
             result=sb.toString();
            // Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
            
     }
     catch(Exception e1)
     {
            Log.e("log_tag", "Error converting result "+e1.toString());
        // Toast.makeText(getApplicationContext(), " record passing fail", Toast.LENGTH_SHORT).show();

     }
return result;
		  	 }

   
     //parse json data
		  	 protected void onPostExecute(String result) 
		  	 {
		  		 String[] sad=new String[50];
		  		 Dialog.dismiss();
		  		 
		  		Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();
     try{
    	 JSONArray jArray = new JSONArray(result);
    	 //String re=jArray.getString(jArray.length()-1);
    	 for(int i=0;i<jArray.length();i++)
             
         {
             
            JSONObject json_data1 = jArray.getJSONObject(i);
             sp=json_data1.getInt("count");/*party*/
             stime1=json_data1.getString("party");/*count*/
            /* stime2=json_data1.getString("updated_at");
             stime3=json_data1.getString("rawt");
             stime4=json_data1.getString("raw");
             stime5=json_data1.getString("lrydri");
             stime6=json_data1.getString("lryno");
             stime7=json_data1.getString("mobile");
             //stime8=json_data1.getString("lrydri");*/
         }
    	 cp=sp+1;
    	 String f=String.valueOf(cp);
    	 Bundle b=new Bundle();
    	 b.putString("cou",f);
    	 b.putString("part",stime1);
    	 Intent iuu=new Intent(getBaseContext(),Updat2.class);
    	 iuu.putExtras(b);
    	 
    	 startActivity(iuu);
    	//JSONObject json_data1 = jArray.getJSONObject(0);
    // 	String w1=json_data1.getString("created_at");
    // 	String e=json_data1.getString("updated_at");
    	 
    	 
       
        // JSONObject json_data = new JSONObject(result);
         
        // CharSequence w= (CharSequence) json_data.getString("re");
        // CharSequence w1= (CharSequence) json_data.getString("ret");
        // Toast.makeText(getApplicationContext(), w, Toast.LENGTH_SHORT).show();
        // Toast.makeText(getApplicationContext(),"qop"+re, Toast.LENGTH_SHORT).show();
 //        Toast.makeText(getApplicationContext(),stime1, Toast.LENGTH_SHORT).show();
 //        Toast.makeText(getApplicationContext(),stime2, Toast.LENGTH_SHORT).show();
//         Toast.makeText(getApplicationContext(),stime3, Toast.LENGTH_SHORT).show();
         
       //  Toast.makeText(getApplicationContext(), w1, Toast.LENGTH_SHORT).show();
         //Toast.makeText(getApplicationContext(), w1, Toast.LENGTH_SHORT).show();
  /*      try{
//			 Toast.makeText(getApplicationContext(),"jlo", Toast.LENGTH_SHORT).show();
			 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         java.util.Date dateOne = (java.util.Date) df.parse(stime1);
         java.util.Date dateTwo = (java.util.Date) df.parse(stime2);   
         long timeDiff = Math.abs(dateOne.getTime() - dateTwo.getTime());
         float y=timeDiff;
         long diffSeconds = timeDiff / 1000;
         float d=y / 1000;
         long diffMinutes = timeDiff / (60 * 1000);
         float d1=y / (60 * 1000);
         float diffHours = (y / (60 * 1000))/60;
         String sdu=diffHours+"";
         String tmp = diffMinutes + " dk. " + (diffSeconds - diffMinutes * 60)
             + " sn " + (timeDiff - (diffSeconds * 1000)) + " ms.";
         String sd=diffHours + "hr";
//         Toast.makeText(getApplicationContext(),tmp, Toast.LENGTH_SHORT).show();
//         Toast.makeText(getApplicationContext(),sd, Toast.LENGTH_SHORT).show();
         String st1="Identity   :"+sp+
        		 "\n"+
        		    "Departure  :"+stime1+
        		    "\n"+
        		    "Reached    :"+stime2+
        		    "\n"+
        		    "Raw        :"+stime3+
        		    "\n"+
        		    "Supplier   :"+stime4+
        		    "\n"+
        		    "lorry driver :"+stime5+
        		    "\n"+
        		    "Lorry no   :"+stime6+
        		    "\n"+
        		    "Mobile      :"+stime7+
        		    "\n"+
        		    stime8+
        		    "\n"+
        		    "Duration   "+tmp+
        		    "\n"+
        		    "Time in HOURS :"+sd;
         if(st1!=null)
         {
        	 EditText rep=(EditText)findViewById(R.id.editText1);
        	 rep.setText("thank you"+"\n"+"response received");
        	 rep.setEnabled(false);
         }
         if(diffHours>8 && stime3.equalsIgnoreCase("coal") )
         {
        	 
        	 Toast.makeText(getApplicationContext(),"coal time exceed", Toast.LENGTH_SHORT).show();
        	 SmsManager smsManager = SmsManager.getDefault();
     		smsManager.sendTextMessage("9677779325", null, st1, null, null);
         }
         else if(diffHours<8 && stime3.equalsIgnoreCase("coal"))
         {
        	 Toast.makeText(getApplicationContext(),"coal reached puctually", Toast.LENGTH_SHORT).show();
        	 
         }
         else if(diffHours>10 && stime3.equalsIgnoreCase("iron"))
         {
        	 Toast.makeText(getApplicationContext(),"iron time exceed", Toast.LENGTH_SHORT).show();
        	 SmsManager smsManager = SmsManager.getDefault();
      		smsManager.sendTextMessage("9677779325", null, st1, null, null);
        	 
         }
         else if(diffHours<10 && stime3.equalsIgnoreCase("iron"))
        		 {
        	 Toast.makeText(getApplicationContext(),"iron reached punctually", Toast.LENGTH_SHORT).show();
        		 }
         else
         {
        	 Toast.makeText(getApplicationContext(),"no comments", Toast.LENGTH_SHORT).show();
         }
         //long timeDiff1=timeDiff/(60 * 1000);
        // Seconds s;time
  //       Timestamp t=new Timestamp(timeDiff);
         
       //  String jh=String.valueOf(timeDiff);
         
        // Time t=Time.valueOf(jh);
        // String sfp=t.toString();
       //  String ret=String.valueOf(t);
         
         //System.out.println(t);   
         //String s=String.valueOf(t);
        // Toast.makeText(getApplicationContext(),tmp, Toast.LENGTH_SHORT).show();
        //.makeText(getApplicationContext(),sd, Toast.LENGTH_SHORT).show();
		 }*/
	/*	 catch(Exception e)
		 {
			 Log.e("log_tag", "Error parsing data "+e.toString());
		 }*/
            // difference: 0
       /* java.util.Date pdate = new java.util.Date(stime1);
         java.util.Date cdate = new java.util.Date(stime2);

         long difference = cdate .getTime() - pdate .getTime();
         String jh=String.valueOf(difference);
         Toast.makeText(getApplicationContext(),jh, Toast.LENGTH_SHORT).show();*/
            }
     catch(JSONException e11)
     {
             Object el1 = null;
			Log.e("log_tag", "Error parsing data "+el1.toString());
             Toast.makeText(getApplicationContext(), "JsonArray fail", Toast.LENGTH_SHORT).show();
     }
     catch(Exception e11)
     {
             Object el1 = null;
			Log.e("log_tag", "Error parsing data "+el1.toString());
             Toast.makeText(getApplicationContext(), "JsonArray fail", Toast.LENGTH_SHORT).show();
     }
     
		  	 }

   
	 
		   }
	   
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.updat, menu);
		return true;
	}
	

}

