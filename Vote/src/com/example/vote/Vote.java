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
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//import com.example.sabari.Insert;
//import com.example.sabari.Lorry;
//import com.example.sabari.R;


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
import android.widget.Spinner;
import android.widget.Toast;

public class Vote extends Activity {
String name,gender,dob,state,locality;
ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vote);
	}
	public void sign(View v)
	{
		Intent i=new Intent(getBaseContext(),Signin.class);
		startActivity(i);
	}
	public void reg(View v)
	{
		
		Toast.makeText(getApplicationContext(), "reg",Toast.LENGTH_SHORT).show();
		
		EditText e1=(EditText)findViewById(R.id.editText1);
		EditText e2=(EditText)findViewById(R.id.editText2);
		EditText e3=(EditText)findViewById(R.id.editText3);
		EditText e4=(EditText)findViewById(R.id.editText4);
		EditText e5=(EditText)findViewById(R.id.editText5);
		name=e1.getText().toString();
		gender=e2.getText().toString();
		dob=e3.getText().toString();
		state=e4.getText().toString();
		locality=e5.getText().toString();
		nameValuePairs.add(new BasicNameValuePair("namee",name));
		nameValuePairs.add(new BasicNameValuePair("genderr",gender));
		nameValuePairs.add(new BasicNameValuePair("dobb",dob));
		nameValuePairs.add(new BasicNameValuePair("statee",state));
		nameValuePairs.add(new BasicNameValuePair("localityy",locality));
		new LonggOperation().execute();
		
		
	
	
	
	
	
	
	
	
	}
	
	
	class LonggOperation  extends AsyncTask<Void, Void, String>
    {
		String result=null,result1=null;
   	 private ProgressDialog Dialog = new ProgressDialog(Vote.this);
   	 protected void onPreExecute() {
           // NOTE: You can call UI Element here.
            
           //UI Element
          // uiUpdate.setText("Output : ");
           Dialog.setMessage("registering..");
           Dialog.show();
       }
   	 protected String doInBackground(Void... arg) 
   	 {
   		 InputStream is=null;
   		 String result=null;
   		 try{
                HttpClient httpclient = new DefaultHttpClient();
          // Toast.makeText(getApplicationContext(), "step1", Toast.LENGTH_SHORT).show();
                HttpPost httppost = new HttpPost("http://10.0.2.2/insertppp.php");
          //      Toast.makeText(getApplicationContext(), "step2", Toast.LENGTH_SHORT).show();
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
          //      Toast.makeText(getApplicationContext(), "step3", Toast.LENGTH_SHORT).show();
                HttpResponse response1 = httpclient.execute(httppost);
          //      Toast.makeText(getApplicationContext(), "step4", Toast.LENGTH_SHORT).show();
                HttpEntity entity = response1.getEntity();
      //          Toast.makeText(getApplicationContext(), "step5", Toast.LENGTH_SHORT).show();
                is = entity.getContent();

                Log.e("log_tag", "connection success ");
//Toast.makeText(getApplicationContext(), "pass", Toast.LENGTH_SHORT).show();
           }
        catch(HttpHostConnectException g)
        {
        	//Toast.makeText(getApplicationContext(), "Connection fail", Toast.LENGTH_SHORT).show();
        }
       
       
        catch(Exception e)
        {
                Log.e("log_tag", "Error in http connection "+e.toString());
//Toast.makeText(getApplicationContext(), "Connection fail",                                        Toast.LENGTH_SHORT).show();

        }
        //convert response to string
        try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null)
                {
                        sb.append(line + "\n");
 //                         Intent i = new Intent(getBaseContext(),Lorry.class);
 //                       startActivity(i);
                }
                is.close();

                result=sb.toString();
                result1=result;
        }
        catch(Exception e)
       {
               Log.e("log_tag", "Error converting result "+e.toString());
           }
        
return result;
   	 }
   	 protected void onPostExecute(String result) 
     	 {
   		 // json_data = null;
   		 try{
   		CharSequence result1;
			//Toast.makeText(getApplicationContext(), result1, Toast.LENGTH_LONG).show();
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
        //Toast.makeText(getApplicationContext(), "JsonArray fail", Toast.LENGTH_SHORT).show();
    }
catch(NullPointerException e)
{
	
}
   		 
     	 }
   	 
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.vote, menu);
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
