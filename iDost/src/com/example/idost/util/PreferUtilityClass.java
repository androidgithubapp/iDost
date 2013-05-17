package com.example.idost.util;


import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import com.example.idost.R;
import com.example.idost.pojo.ContactBean;

public class PreferUtilityClass {

SharedPreferences shrpref;
	
	public static void StoreContact(Context context, ContactBean conobj) 
	{
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		Editor edit = prefs.edit();
		String phndata=getData(context);
		if(phndata.equals(""))
		{
			edit.putString(context.getString(R.string.CONVAL),conobj.getName()+":"+conobj.getPhn()+";");
		}
		else
		edit.putString(context.getString(R.string.CONVAL),phndata+conobj.getName()+":"+conobj.getPhn()+";");
		edit.commit();
	}
	
	public static void UpdateContact(Context context, ContactBean conobj) 
	{
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		Editor edit = prefs.edit();
		String phndata=getData(context);
		if(phndata.equals(""))
		{
			edit.putString(context.getString(R.string.CONVAL),conobj.getName()+":"+conobj.getPhn());
		}
		else
		edit.putString(context.getString(R.string.CONVAL),phndata+";"+conobj.getName()+":"+conobj.getPhn());
		edit.commit();
	}
	
	public static void UpdateContactDetails(Context context,ArrayList<String> contactList)
	{
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		Editor edit = prefs.edit();
		edit.putString(context.getString(R.string.CONVAL),StoreContactData(contactList));
		edit.commit();
	}
	
	private static String StoreContactData(ArrayList<String> contactList)
	{
		String conListData="";
		for(int i=0;i<contactList.size();i++)
		{
			conListData+=contactList.get(i).split(":")[1]+":"+contactList.get(i).split(":")[0]+";";
		}
		return conListData;
	}
	
	public static String GetContact(Context context) {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(context);
		return prefs.getString(context.getString(R.string.CONVAL), "");
	}
	
	public static String[] GetContactNumber(Context context)
	{
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(context);
		String[] conNum= prefs.getString(context.getString(R.string.CONVAL), "").split(";");
		for(int i=0;i<conNum.length;i++)
		{
			conNum[i]=conNum[i].replaceAll("[A-Za-z:]", "").trim();
		}
		return conNum;
	}
	
	public static String getData(Context context)
	{
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		String data = prefs.getString(context.getString(R.string.CONVAL), null);
		
		if(data==null)
			return "";
		else
		return data;
	}
	
	public static void delData(Context context) 
	{
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		Editor edit = prefs.edit();
		edit.putString(context.getString(R.string.CONVAL),null);
		edit.commit();
	}
}
