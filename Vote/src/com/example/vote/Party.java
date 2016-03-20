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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//import com.example.sabari.Choice;
//import com.example.sabari.Inctra;


//import com.example.watchman.Iron;
//import com.example.watchman.R;
//import com.example.watchman.Updat;
//import com.example.watchman.Iron.LongOperation.CustomOnItemSelectedListenerty;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class Party extends Activity {
	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	private RadioGroup radiovotegroup;
	  private RadioButton radiovotebutton;
	  private Button btnDisplay;
	  String s=null;
	  String ss=null;
	  int i=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_party);
	}
	public void votedo(View v)
	{
		new LongOperation().execute();
		

	  }
	

	class LongOperation  extends AsyncTask<Void, Void, String>
	   {
		String sl;
		List<String> list = new ArrayList<String>();
		private static final int ArrayList = 0;
		private Spinner spinner1,spinner2;
		
		
		// Spinner element
    // Spinner spinner = (Spinner) findViewById(R.id.spinner1);

     // Spinner click listener
     
     
		
    /*  List<String> list = new ArrayList<String>();*/
		 String[] add;
 	 private ProgressDialog Dialog = new ProgressDialog(Party.this);
 	 protected void onPreExecute() {
          // NOTE: You can call UI Element here.
           
          //UI Element
         // uiUpdate.setText("Output : ");
          Dialog.setMessage("Downloading source..");
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
                  // HttpPost httppost = new HttpPost("http://kecitians.tk/sel2.php");
                   HttpPost httppost = new HttpPost("http://10.0.2.2/partycombo.php");
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
                   //Toast.makeText(getApplicationContext(), "Connection fail", Toast.LENGTH_SHORT).show();

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
 		 //spinner.setOnItemSelectedListener(Coall.this);
 	   //     int lt;
			//	int List = lt;Object String = get(); int categories = ArrayList&lt;String&get();();
 		 String[] sad=new String[50];
 		 
 		 spinner1 = (Spinner) findViewById(R.id.spinner1);
 		 Dialog.dismiss();
 		 
 		 try
 		 {
 			
 	     String result1=result;
 			 JSONArray jArray = new JSONArray(result1);
 		//	 JSONObject object = new JSONObject(result);
 			// String ch=object.getString("re");
// 			 Toast.makeText(getApplicationContext(), ch, Toast.LENGTH_SHORT).show();
 			// Toast.makeText(getApplicationContext(), result1, Toast.LENGTH_LONG).show();
 			// JSONObject hj=jArray.getJSONObject(result);
 			 String re=jArray.getString(jArray.length()-1);
 			 //Toast.makeText(getApplicationContext(),"STEP2", Toast.LENGTH_LONG).show();
          int flag=1;
     list.add("Party");
       for(int i=0;i<jArray.length();i++)
             
               {
                   
                   
                   
                   
       
                         
                     
                         
                           JSONObject json_data = jArray.getJSONObject(i);
                //           Toast.makeText(getApplicationContext(),"STEP3", Toast.LENGTH_LONG).show();
                           Log.i("log_tag","lid: "+json_data.getString("parties")
                                    );
                  
                         //  Toast.makeText(getApplicationContext(),"STEP4", Toast.LENGTH_LONG).show();
                     
                           String party=String.valueOf(json_data.getString("parties"));
                           
                           //Toast.makeText(getApplicationContext(),stime, Toast.LENGTH_LONG).show();
                      // String stime=String.valueOf(json_data.getInt("lid"));
                  // pass=String.valueOf(json_data.getInt("f1"));
              
                /*       String stime1=json_data.getString("date");
                  //     Toast.makeText(getApplicationContext(),stime1, Toast.LENGTH_LONG).show();
                      String stime2=json_data.getString("supplier");
                    //  Toast.makeText(getApplicationContext(),"STEP7", Toast.LENGTH_LONG).show();
                      String stime3=json_data.getString("lrydri");
                      //Toast.makeText(getApplicationContext(),"STEP8", Toast.LENGTH_LONG).show();
                      String stime4=json_data.getString("lryno");
                     // Toast.makeText(getApplicationContext(),"STEP LRY", Toast.LENGTH_LONG).show();
                      String stimep=String.valueOf(json_data.getInt("mobile"));
                    //  Toast.makeText(getApplicationContext(),"STEP9", Toast.LENGTH_LONG).show();
                      // String stime5=String.valueOf(json_data.getInt("mobile"));
                      String stime6=json_data.getString("raw");
                    //  Toast.makeText(getApplicationContext(),"STEP RA", Toast.LENGTH_LONG).show();
                      String stime7=json_data.getString("weight");
                    //  Toast.makeText(getApplicationContext(),"STEP WE", Toast.LENGTH_LONG).show();
                      String stime8=json_data.getString("dispatch");
                      String sy=json_data.getString("status");*/
                    //  Toast.makeText(getApplicationContext(),"STEP DI", Toast.LENGTH_LONG).show();
                      //String stime9=String.valueOf(json_data.getInt("f3"));
                      //String stime10=String.valueOf(json_data.getInt("f3"));
                       // add[i]=stime+"  "+stime1+"  "+stime2+" "+stime3+" "+stime4+" "+stimep+" "+stime6+" "
               	//	  +stime7+" "+stime8;
                     // sad[i]="sad";
                    //  add[0]=stime;
         //            
         //             List<String> list = new ArrayList<String>();
                     // list.add(stime4);
                      //if(sy.equalsIgnoreCase("pending"))
                     // {
                      list.add(party);
                      
                     // }
                     // categories.add(stime4);
                     // list.add(stime4);
                    //  list.add("Sup4");
                    //  list.add("Sup5");
  /*                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                      (this, android.R.layout.simple_spinner_item,list);
                       
              dataAdapter.setDropDownViewResource
                      (android.R.layout.simple_spinner_dropdown_item);
                       
              spinner1.setAdapter(dataAdapter);

              //Spinner item selection Listener  
              addListenerOnSpinnerItemSelection1();

                   //arrayset(); */ 
                      
                      
	   }
      
      // public void addListenerOnSpinnerItemSelection1(){
	         
	       //  spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
//	}
        arrayset();
 		 }
 		 
 		 catch(JSONException e)
          {
                  Log.e("log_tag", "Error parsing data "+e.toString());
                  Toast.makeText(getApplicationContext(), "JsonArray fail", Toast.LENGTH_SHORT).show();
          }
          catch(NullPointerException e)
          {
          	
          }
 	/*	 public void arrayset()
          {
        	  spinner1 = (Spinner) findViewById(R.id.spinner1);
        	  ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
              (this, android.R.layout.simple_spinner_item,list);
               
      dataAdapter.setDropDownViewResource
              (android.R.layout.simple_spinner_dropdown_item);
               
      spinner1.setAdapter(dataAdapter);

      //Spinner item selection Listener  
      addListenerOnSpinnerItemSelection1();

          }*/
 		
 		 
 	 }
 	 public void arrayset() {
			// TODO Auto-generated method stub
 		 
 		 spinner1 = (Spinner) findViewById(R.id.spinner1);
        	  ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
              (Party.this, android.R.layout.simple_spinner_item,list);
               
      dataAdapter.setDropDownViewResource
              (android.R.layout.simple_spinner_dropdown_item);
               
      spinner1.setAdapter(dataAdapter);

      //Spinner item selection Listener  
      addListenerOnSpinnerItemSelection1();
 		 
 		 
/*   		   int lt;
			int ArrayAdapter = lt;Object gt;
			Object String = gt; int dataAdapter = ArrayAdapter&lt;String&gt;Object categories;
			(this, android.R.layout.simple_spinner_item, categories);
 		   
 	        // Drop down layout style - list view with radio button
 	        ((Object) dataAdapter).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 	 
 	        // attaching data adapter to spinner
 	        spinner.setAdapter(dataAdapter);*/

      
      Button b1=(Button)findViewById(R.id.button1);
		 b1.setOnClickListener(new View.OnClickListener()
      {
             public void onClick(View view)
               {
             	//String s1=spinner1.getSelectedItem().toString();
             	//Toast.makeText(getApplicationContext(), "try", Toast.LENGTH_LONG).show();
             	 Intent i = new Intent(getBaseContext(),Updat.class);
             	 Bundle extras = new Bundle();
                   extras.putString("status",ss);
            
                   // 4. add bundle to intent
                   i.putExtras(extras);
                  startActivity(i);
               }
      });
 	 }
	 public void addListenerOnSpinnerItemSelection1(){
	        // Toast.makeText(getApplicationContext(), "sad", Toast.LENGTH_LONG).show();
	          ss=spinner1.getSelectedItem().toString();
	         // Toast.makeText(getApplicationContext(), ss, Toast.LENGTH_LONG).show();
	         spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListenerty());
	}
	public class CustomOnItemSelectedListenerty implements OnItemSelectedListener {
	 
	    public void onItemSelected(AdapterView<?> parent, View view, int pos,
	            long id) {
	         
	        Toast.makeText(parent.getContext(), 
	                "On Item Select : \n" + parent.getItemAtPosition(pos).toString(),
	                Toast.LENGTH_LONG).show();
	         ss=parent.getItemAtPosition(pos).toString();
	        //parent.getItemAtPosition(pos).toString();
	  /*      Intent i = new Intent(getBaseContext(),Update.class);
	        Bundle extras = new Bundle();
	        extras.putString("status","you");
	 
	        // 4. add bundle to intent
	        i.putExtras(extras);
	       startActivity(i);         */
	    }
	 
	    @Override
	    public void onNothingSelected(AdapterView<?> arg0) {
	        // TODO Auto-generated method stub
	 
	    }
	 
	}
/*  	 public void pass(View v)
 	 {
 		 Bundle bundlee = getIntent().getExtras();
 			
 		 String status = bundlee.getString("status");
 		 Toast.makeText(getApplicationContext(), status + "vava", Toast.LENGTH_LONG).show();
 		//new Longoperation().execute();
 		 
 	 }
*/

	   }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.party, menu);
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
