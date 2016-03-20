package com.example.vote;

//import android.support.v7.app.ActionBarActivity;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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

//import com.example.sabari.Insert;
//import com.example.sabari.R;





import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.Toast;

public class Updat2 extends Activity {
int count;
ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_updat2);
		Intent in=getIntent();
		 Bundle b=in.getExtras();
		 String str = b.getString("cou");
		 String str1=b.getString("part");
		 count=Integer.parseInt(str);
		 nameValuePairs.add(new BasicNameValuePair("count",str));
		 nameValuePairs.add(new BasicNameValuePair("part",str1));
		 new LongOperation().execute();
	}
	
	class LongOperation  extends AsyncTask<Void, Void, String>
	{
		 List<String> list = new ArrayList<String>();
		 
		   
		    List<String> list2 = new ArrayList<String>();
		   
		 private Spinner spinner1,spinner2;
		 String[] add;
		 private ProgressDialog Dialog = new ProgressDialog(Updat2.this);
		 protected void onPreExecute() {
	        // NOTE: You can call UI Element here.
	         
	        //UI Element
	       // uiUpdate.setText("Output : ");
	        Dialog.setMessage("Voting source party..");
	        Dialog.show();
	    }
		 @Override
		 protected String doInBackground(Void... arg) 
		 {
			  String result=null;
	         InputStream is = null;
	       
	         try{
	                 HttpClient httpclient = new DefaultHttpClient();
	                 //Toast.makeText(getApplicationContext(), "s1",Toast.LENGTH_SHORT).show();
	                 //Toast.makeText(getApplicationContext(), "s1", Toast.LENGTH_SHORT).show();
	                 //HttpPost httppost = new HttpPost("http://kecitians.tk/combo.php");
	                 HttpPost httppost = new HttpPost("http://10.0.2.2/votesrc.php");
	                 httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	                 //Toast.makeText(getApplicationContext(), "s2", Toast.LENGTH_SHORT).show();
	                 HttpResponse response = httpclient.execute(httppost);
	                 //Toast.makeText(getApplicationContext(), "s3", Toast.LENGTH_SHORT).show();
	                 HttpEntity entity = response.getEntity();
	                 is = entity.getContent();

	                 Log.e("log_tag", "connection success ");
	              //   Toast.makeText(getApplicationContext(), "pass", Toast.LENGTH_SHORT).show();
	         }
	      catch(Exception e)
	         {
	                 Log.e("log_tag", "Error in http connection "+e.toString());
	                // Toast.makeText(getApplicationContext(), "Connection fail", Toast.LENGTH_SHORT).show();

	         }
	 // catch(NullPointerException e)
	  //{
	 	 
	  //}
	         //convert response to string
	         try
	         {
	                 BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
	                 StringBuilder sb = new StringBuilder();
	                 String line = null;
	                 while ((line = reader.readLine()) != null)
	                 {
	                         sb.append(line + "\n");
	                       //  Toast.makeText(getApplicationContext(), "Input Reading pass", Toast.LENGTH_SHORT).show();
	                 }
	                 is.close();

	                 result=sb.toString();
	                 //return result;
	                 //Toast.makeText(getApplicationContext(),result, Toast.LENGTH_SHORT).show();

	         }
	         catch(Exception e)
	         {
	       	  
	                Log.e("log_tag", "Error converting result "+e.toString());
	            // Toast.makeText(getApplicationContext(), " Input reading fail", Toast.LENGTH_SHORT).show();

	         }
				return result;
		 }
		
	        
		 
		 

		 protected void onPostExecute(String result) 
	  	 {
			 
			 
			
			 String trans,supply;
	  		 String[] sad=new String[50];
	  		 Dialog.dismiss();
	  		String result1=result;
	  		//spinner1 = (Spinner) findViewById(R.id.spinner1);
	 		// spinner2 = (Spinner) findViewById(R.id.spinner2);
	  		
	  		try{
    			Toast.makeText(getApplicationContext(), result1, Toast.LENGTH_LONG).show();
    			Log.i("out", result);
                 
    			 JSONObject json_data = new JSONObject(result);

                 CharSequence w= (CharSequence) json_data.get("re");
              
                 Toast.makeText(getApplicationContext(),w, Toast.LENGTH_SHORT).show();

      
      }
 catch(JSONException e)
    {
         Log.e("log_tag", "Error parsing data "+e.toString());
         Log.e("JSON Parser", "Error parsing data [" + e.getMessage()+"] ");
       /*  try {
			Log.d("tag", json_data.toString(4));
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
         //e.printStackTrace();
         Toast.makeText(getApplicationContext(), "JsonArray fail", Toast.LENGTH_SHORT).show();
     }
 catch(NullPointerException e)
 {
 	
 }
    		 
	  	 }
	
	

	
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.updat2, menu);
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
