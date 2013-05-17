package com.example.idost.service;

import android.app.IntentService;
import android.content.Intent;
import android.telephony.SmsManager;

import com.example.idost.util.PreferUtilityClass;

public class MessagingService extends IntentService {

	
	public MessagingService() {
		super("MessagingService");
	}


	private static final int DELAY = 30000; //time in milli sec
	private static final String TAG = "MessagingService";
	boolean running = false;
	
	

	
	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		
		
		
		String[] contactNos = PreferUtilityClass.GetContactNumber(this);

		String currentAddress = CurAddPolAddiDostService.appCommonBean.currentAddressBean.addressLine + "\n" +  
				CurAddPolAddiDostService.appCommonBean.currentAddressBean.locality;
		
		String nearestPolInfo = "Police station address : \n" + 
				CurAddPolAddiDostService.appCommonBean.nearestPoliceInfoBean.policeNm + " \n" +
				CurAddPolAddiDostService.appCommonBean.nearestPoliceInfoBean.policeVicinity + "\n\n" +
				"Contact Number : \n" +
				CurAddPolAddiDostService.appCommonBean.nearestPoliceInfoBean.policeIntFrmattedPhNo;
		
		final String msgContent = "I am in deep trouble. Please help! I am currently at the following location : " + 
									currentAddress + "\n" + 
									"The following are the details of the nearest Police Station : \n" + 
									nearestPolInfo;
		
		int SmsRcvrCount = 0;
		if(contactNos!=null)
		{
			
			if(contactNos.length>0)
			{
				if(contactNos.length<=5)
					SmsRcvrCount = contactNos.length;
				else
					SmsRcvrCount = 5;
				
			
				for(int i=0;i<SmsRcvrCount;i++)
				{
					try{
					SmsManager smsManager = SmsManager.getDefault();
					smsManager.sendTextMessage(contactNos[i], null, msgContent, null, null);
					}catch(Exception e)
					{
						e.printStackTrace();
					}catch(Error err)
					{
						err.printStackTrace();
					}
					
				}
				//return "message sent to five of your closest people!";
			}
			else
			{
				/*return "Oops! seems like you are emergency list is not configured." +
						"Please go to menu and add contacts to the emergency list";*/
			}
		}
				//new SendSms().execute(contactNos);
		}
	
	@Override
	  public void onDestroy() {
	    super.onDestroy();
	  }

}
