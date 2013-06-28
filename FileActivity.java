package com.example.trial;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class TrialActivity extends Activity {
  
	ListView lv;
	Button b1,b2,b3;
	final ArrayList<HashMap<String,String>> list1 = new ArrayList<HashMap<String,String>>();
	final ArrayList<HashMap<String,String>> names = new ArrayList<HashMap<String,String>>();
	final ArrayList<HashMap<String,String>> sizes = new ArrayList<HashMap<String,String>>();
	final ArrayList<HashMap<String,String>> extns = new ArrayList<HashMap<String,String>>();
	int a=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_trial);
		lv=(ListView)findViewById(R.id.listView);
		b1=(Button)findViewById(R.id.button1);
		b2=(Button)findViewById(R.id.button2);
		b3=(Button)findViewById(R.id.button3);
		SimpleAdapter adapter = new SimpleAdapter(this,list1,R.layout.row_layout,
				new String[] {"name","size","extn"},
				new int[] {R.id.textView1,R.id.textView2, R.id.textView3}
				);
		populateList();
		lv.setAdapter(adapter);
		final details[] det=new details[a];
		
		int i,j;
		for(i=0;i<a;i++)
		{
			det[i]=new details();
			det[i].name=list1.get(i).get("name");
			det[i].extn=list1.get(i).get("extn");
			det[i].size=Integer.parseInt(list1.get(i).get("size"));
		}
		b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				int i,j;
				for(i=0;i<a;i++){
					for(j=0;j<a-i-1;j++){
						String name1=det[j].name;
						String name2=det[j+1].name;
						if(name1.compareToIgnoreCase(name2)>0){
							details tempo=det[j];
							det[j]=det[j+1];
							det[j+1]=tempo;
							
						}
					}
				}
				
				for(i=0;i<a;i++){
					HashMap<String,String> temp1 = new HashMap<String,String>();
					temp1.put("name",det[i].name);
					temp1.put("size",String.valueOf(det[i].size));
					temp1.put("extn",det[i].extn);
					names.add(temp1);
				}
				Disp();
			}
		});
		
		b2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int i,j;
				for(i=0;i<a;i++){
					for(j=0;j<a-i-1;j++){
						int num1=det[j].size;
						int num2=det[j+1].size;
						if(num1>num2){
							details tempo=det[j];
							det[j]=det[j+1];
							det[j+1]=tempo;
						}
					}
				}
				
				for(i=0;i<a;i++){
					HashMap<String,String> temp1 = new HashMap<String,String>();
					temp1.put("name",det[i].name);
					temp1.put("size",String.valueOf(det[i].size));
					temp1.put("extn",det[i].extn);
					sizes.add(temp1);
				}
				Disp1();	
			}
		});
	
		b3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int i,j;
				for(i=0;i<a;i++){
					for(j=0;j<a-i-1;j++){
						String name1=det[j].extn;
						String name2=det[j+1].extn;
						if(name1.compareToIgnoreCase(name2)>0){
							details tempo=det[j];
							det[j]=det[j+1];
							det[j+1]=tempo;
							
						}
					}
				}
				
				for(i=0;i<a;i++){
					HashMap<String,String> temp1 = new HashMap<String,String>();
					temp1.put("name",det[i].name);
					temp1.put("size",String.valueOf(det[i].size));
					temp1.put("extn",det[i].extn);
					extns.add(temp1);
				}
				Disp2();
			}
		});
	}
			
	private void Disp(){
		
			SimpleAdapter adapter1 = new SimpleAdapter(this,names,R.layout.row_layout,
					new String[] {"name","size","extn"},
				new int[] {R.id.textView1,R.id.textView2, R.id.textView3}
				);
		lv.setAdapter(adapter1);
   }
	
	private void Disp1(){
		
		SimpleAdapter adapter1 = new SimpleAdapter(this,sizes,R.layout.row_layout,
				new String[] {"name","size","extn"},
			new int[] {R.id.textView1,R.id.textView2, R.id.textView3}
			);
	lv.setAdapter(adapter1);
   }
	
	private void Disp2(){
		
		SimpleAdapter adapter1 = new SimpleAdapter(this,extns,R.layout.row_layout,
				new String[] {"name","size","extn"},
			new int[] {R.id.textView1,R.id.textView2, R.id.textView3}
			);
	lv.setAdapter(adapter1);
	}
	private void populateList(){
		String Fname,Fextn,temp;
		int j;
		long size;
		File mfile=new File("/"); // For files in the internal memory
		//File mfile=new File(""/sdcard/"); // Comment previous line and uncomment this line to display files in the sd card 
		final File[] list=mfile.listFiles();
		for(int i=0;i<list.length;i++){
			
			temp=list[i].getName();
			j=temp.lastIndexOf('.');
			if(j>0){
				Fname=temp.substring(0,j);
				}
			else
				Fname=temp;
			temp=list[i].toString();
			j=temp.lastIndexOf('.');
			if(j>0)
			{
				Fextn=temp.substring(j+1);
			}
			else
				Fextn="Folder";
			size=list[i].length();
			
			HashMap<String,String> temp1 = new HashMap<String,String>();
			temp1.put("name",Fname);
			temp1.put("size",String.valueOf(size));
			temp1.put("extn",Fextn);
			list1.add(temp1); a++;
			
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.trial, menu);
		return true;
	}

}
