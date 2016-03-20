package com.example.vote;

//import android.support.v7.app.ActionBarActivity;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//import com.example.sabari.Choice;
//import com.example.sabari.Inctra;






import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Signin extends Activity {
String s1,s2;
ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signin);
		
	}
	public void submit(View v)
	{
		EditText e1=(EditText)findViewById(R.id.editText1);
		EditText e2=(EditText)findViewById(R.id.editText2);
		s1=e1.getText().toString();
		s2=e2.getText().toString();
		nameValuePairs.add(new BasicNameValuePair("name",s1));
		nameValuePairs.add(new BasicNameValuePair("pass",s2));
		new LongOperation().execute();
		
	}
	class LongOperation  extends AsyncTask<Void, Void, String>
	   {
		
		private ProgressDialog Dialog = new ProgressDialog(Signin.this);
	  	 protected void onPreExecute() {
	           // NOTE: You can call UI Element here.
	            
	           //UI Element
	          // uiUpdate.setText("Output : ");
	           Dialog.setMessage("updating source..");
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
	                    HttpPost httppost = new HttpPost("http://10.0.2.2/inctravote.php");
	          //          httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	                    //Toast.makeText(getApplicationContext(), "s2", Toast.LENGTH_SHORT).show();
	                    HttpResponse response = httpclient.execute(httppost);
	                    //Toast.makeText(getApplicationContext(), "s3", Toast.LENGTH_SHORT).show();
	                    HttpEntity entity = response.getEntity();
	                    is = entity.getContent();

	                    Log.e("log_tag", "connection success ");
	                 //   Toast.makeText(getApplicationContext(), "pass", Toast.LENGTH_SHORT).show();
	            }
	            catch(HttpHostConnectException g)
             {
             	//Toast.makeText(getApplicationContext(), "Connection fail", Toast.LENGTH_SHORT).show();
             }
	         catch(Exception e)
	            {
	                    Log.e("log_tag", "Error in http connection "+e.toString());
	                  //  Toast.makeText(getApplicationContext(), "Connection fail", Toast.LENGTH_SHORT).show();

	            }
	            try{
                 BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
                 StringBuilder sb = new StringBuilder();
                 String line = null;
                 while ((line = reader.readLine()) != null)
                 {
                         sb.append(line + "\n");
  //                          Intent i = new Intent(getBaseContext(),Choice.class);
  //                       startActivity(i);
                 }
                 is.close();
                 
                 result=sb.toString();
                 
         }
         catch(Exception e)
        {
                Log.e("log_tag", "Error converting result "+e.toString());
            }
         
	            
            
	    // catch(NullPointerException e)
	     //{
	    	 
	     //}
	            return result;
	  	 }
	  	 
	  	protected void onPostExecute(String result) 
	  	 {
	  		 String[] sad=new String[50];
	  		 Dialog.dismiss();
	  		 
	  		 
	  		 
	  			 
	  			 try{
	  				Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
	  				String result1=result;
	  	  			 JSONArray jArray = new JSONArray(result1);
	  	  		 String re=jArray.getString(jArray.length()-1);
	  	  		for(int i=0;i<jArray.length();i++)
	                
                {
	  	  		JSONObject json_data = jArray.getJSONObject(i);
	  	  	Log.i("log_tag","lid: "+json_data.getString("name")+
                    ", date: ");
	  	  	
	  	  String retname=String.valueOf(json_data.getString("name"));
	  	String retgen=String.valueOf(json_data.getString("gender"));
	  	String retdob=String.valueOf(json_data.getString("dob"));
	  	String retstate=String.valueOf(json_data.getString("state"));
	  	String retlocality=String.valueOf(json_data.getString("locality"));
	  	//String retpass=String.valueOf(json_data.getString("password"));
		String retaadhar1=String.valueOf(json_data.getString("aadhar_name"));
		String retaadhar2=String.valueOf(json_data.getString("aadhar_number"));
	  	
	  	
	  	if(retaadhar1.equals(s1)&&retaadhar2.equals(s2))
	  	{
	  		Bundle b = new Bundle();
	  		 b.putString("name",retname);
	  		b.putString("gender",retgen);
	  		b.putString("dob",retdob);
	  		b.putString("state",retstate);
	  		b.putString("locality",retlocality);
	  		Intent in=new Intent(getBaseContext(),Details.class);
	  		in.putExtras(b);
	  		startActivity(in);
	  		
	  	}
	  	
	  	
	  	  
                }
	  				//Toast.makeText(getApplicationContext(), "welcome new transporter", Toast.LENGTH_SHORT).show();
	                   
//                  JSONObject json_data = new JSONObject(result);

//                  CharSequence w= (CharSequence) json_data.get("re");
               
                  

       
       }
  catch(JSONException e)
     {
          Log.e("log_tag", "Error parsing data "+e.toString());
        //  Toast.makeText(getApplicationContext(), "JsonArray fail", Toast.LENGTH_SHORT).show();
      }
  catch(NullPointerException e)
  {
  	
  }
	  			
	  	 }
	   }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.signin, menu);
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
