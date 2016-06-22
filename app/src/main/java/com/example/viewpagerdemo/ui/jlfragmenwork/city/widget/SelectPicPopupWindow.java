package com.example.viewpagerdemo.ui.jlfragmenwork.city.widget;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.xingkesi.foodapp.R;


public class SelectPicPopupWindow extends PopupWindow implements OnClickListener, OnWheelChangedListener{
	
	private WheelView mViewProvince;
	private WheelView mViewCity;
	private WheelView mViewDistrict;
	 private View mMenuView;
	 Activity context;
	 Handler han;
	 /**
		 * ����ʡ
		 */
		protected String[] mProvinceDatas;
		/**
		 * key - ʡ value - ��
		 */
		protected Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();
		/**
		 * key - �� values - ��
		 */
		protected Map<String, String[]> mDistrictDatasMap = new HashMap<String, String[]>();
		
		/**
		 * key - �� values - �ʱ�
		 */
		protected Map<String, String> mZipcodeDatasMap = new HashMap<String, String>(); 

		/**
		 * ��ǰʡ������
		 */
		protected String mCurrentProviceName;//mCurrentProviceName  mCurrentCityName mCurrentDistrictName mCurrentZipCode
		/**
		 * ��ǰ�е�����
		 */
		protected String mCurrentCityName;
		/**
		 * ��ǰ��������
		 */
		protected String mCurrentDistrictName ="";
		
		/**
		 * ��ǰ������������
		 */
		protected String mCurrentZipCode ="";
		
	 
		TextView ok,cancel;
		
	 public SelectPicPopupWindow(Activity context,Handler han) {  
	        super(context);  
	        this.context=context;
	        this.han=han;
	        LayoutInflater inflater = (LayoutInflater) context  
	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
	        mMenuView = inflater.inflate(R.layout.jlcitylayout, null);
	        
	        mViewProvince = (WheelView) mMenuView.findViewById(R.id.id_province);
			mViewCity = (WheelView) mMenuView.findViewById(R.id.id_city);
			mViewDistrict = (WheelView) mMenuView.findViewById(R.id.id_district);
			ok = (TextView) mMenuView.findViewById(R.id.tv_ok);
			cancel = (TextView) mMenuView.findViewById(R.id.tv_cancel);
			
			
			setUpListener();
			setUpData();
	        
	      /*  btn_take_photo = (Button) mMenuView.findViewById(R.id.btn_take_photo);  
	        btn_pick_photo = (Button) mMenuView.findViewById(R.id.btn_pick_photo);  
	        btn_cancel = (Button) mMenuView.findViewById(R.id.btn_cancel);  
	        //ȡ����ť  
	        btn_cancel.setOnClickListener(new OnClickListener() {  
	  
	            public void onClick(View v) {  
	                //���ٵ�����  
	                dismiss();  
	            }  
	        });  
	        //���ð�ť����  
	        btn_pick_photo.setOnClickListener(itemsOnClick);  
	        btn_take_photo.setOnClickListener(itemsOnClick);*/  
	        //����SelectPicPopupWindow��View  
	        this.setContentView(mMenuView);  
	        //����SelectPicPopupWindow��������Ŀ�  
	        this.setWidth(LayoutParams.MATCH_PARENT);  
	        //����SelectPicPopupWindow��������ĸ�  
	        this.setHeight(LayoutParams.WRAP_CONTENT);  
	        //����SelectPicPopupWindow��������ɵ��  
	        this.setFocusable(true);  
	        //����SelectPicPopupWindow�������嶯��Ч��  
	        //this.setAnimationStyle(R.style.AnimBottom);  
	        //ʵ����һ��ColorDrawable��ɫΪ��͸��  
	        ColorDrawable dw = new ColorDrawable(0xb0000000);  
	        //����SelectPicPopupWindow��������ı���  
	        this.setBackgroundDrawable(dw);  
	        //mMenuView���OnTouchListener�����жϻ�ȡ����λ�������ѡ������������ٵ�����  
	        mMenuView.setOnTouchListener(new OnTouchListener() {  
	              
	            public boolean onTouch(View v, MotionEvent event) {  
	                  
	               /* int height = mMenuView.findViewById(R.id.pop_layout).getTop();  
	                int y=(int) event.getY();  
	                if(event.getAction()==MotionEvent.ACTION_UP){  
	                    if(y<height){  
	                        dismiss();  
	                    }  
	                }    */             
	                return true;  
	            }  
	        });  
	  
	    }

	 private void setUpListener() {
	    	// ���change�¼�
	    	mViewProvince.addChangingListener(this);
	    	// ���change�¼�
	    	mViewCity.addChangingListener(this);
	    	// ���change�¼�
	    	mViewDistrict.addChangingListener(this);
	    	// ���onclick�¼�
	    	ok.setOnClickListener(this);
	    	cancel.setOnClickListener(this);
	    }
		
		private void setUpData() {
			initProvinceDatas();
			mViewProvince.setViewAdapter(new ArrayWheelAdapter<String>(context, mProvinceDatas));
			// ���ÿɼ���Ŀ����
			mViewProvince.setVisibleItems(7);
			mViewCity.setVisibleItems(7);
			mViewDistrict.setVisibleItems(7);
			updateCities();
			updateAreas();
		}

		@Override
		public void onChanged(WheelView wheel, int oldValue, int newValue) {
			// TODO Auto-generated method stub
			if (wheel == mViewProvince) {
				updateCities();
				mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[0];
				mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);
				Log.d("LD","������ʡ��"+newValue);
			} else if (wheel == mViewCity) {
				updateAreas();

			} else if (wheel == mViewDistrict) {
				Log.d("LD","����������"+newValue);
				mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[newValue];
				mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);
			}
		}

		/**
		 * ���ݵ�ǰ���У�������WheelView����Ϣ
		 */
		private void updateAreas() {
			int pCurrent = mViewCity.getCurrentItem();
			Log.d("LD","������shi��"+pCurrent);
			mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
			String[] areas = mDistrictDatasMap.get(mCurrentCityName);

			if (areas == null) {
				areas = new String[] { "" };
			}
			mViewDistrict.setViewAdapter(new ArrayWheelAdapter<String>(context, areas));
			mViewDistrict.setCurrentItem(0);
			if(areas.length>0){
				mCurrentDistrictName=areas[0];
			}
		}

		/**
		 * ���ݵ�ǰ��ʡ��������WheelView����Ϣ
		 */
		private void updateCities() {
			int pCurrent = mViewProvince.getCurrentItem();
			mCurrentProviceName = mProvinceDatas[pCurrent];
			String[] cities = mCitisDatasMap.get(mCurrentProviceName);
			if (cities == null) {
				cities = new String[] { "" };
			}
			mViewCity.setViewAdapter(new ArrayWheelAdapter<String>(context, cities));
			mViewCity.setCurrentItem(0);
			updateAreas();
		}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.tv_cancel:
			dismiss();
			break;
			
		case R.id.tv_ok:
			Log.d("LD","�����");
			Message msg =han.obtainMessage();
			msg.what=1;
			Bundle data =new  Bundle();
			//mCurrentProviceName  mCurrentCityName mCurrentDistrictName mCurrentZipCode
			if(mCurrentProviceName!=null && !mCurrentProviceName.equals("")){
				data.putString("pro", mCurrentProviceName);
			}
			if(mCurrentCityName!=null && !mCurrentCityName.equals("")){
				data.putString("city", mCurrentCityName);
			}
			if(mCurrentDistrictName!=null && !mCurrentDistrictName.equals("")){
				data.putString("dro", mCurrentDistrictName);
			}
			
			
			
			msg.setData(data);
			msg.sendToTarget();
			dismiss();
			
			break;

		default:
			break;
		}
		
	} 
	
	
	/**
	 * ����ʡ������XML����
	 */
	
    protected void initProvinceDatas()
	{
		List<ProvinceModel> provinceList = null;
    	AssetManager asset = context.getAssets();
        try {
            InputStream input = asset.open("province_data.xml");
            // ����һ������xml�Ĺ�������
			SAXParserFactory spf = SAXParserFactory.newInstance();
			// ����xml
			SAXParser parser = spf.newSAXParser();
			XmlParserHandler handler = new XmlParserHandler();
			parser.parse(input, handler);
			input.close();
			// ��ȡ��������������
			provinceList = handler.getDataList();
			//*/ ��ʼ��Ĭ��ѡ�е�ʡ���С���
			if (provinceList!= null && !provinceList.isEmpty()) {
				mCurrentProviceName = provinceList.get(0).getName();
				List<CityModel> cityList = provinceList.get(0).getCityList();
				if (cityList!= null && !cityList.isEmpty()) {
					mCurrentCityName = cityList.get(0).getName();
					List<DistrictModel> districtList = cityList.get(0).getDistrictList();
					mCurrentDistrictName = districtList.get(0).getName();
					mCurrentZipCode = districtList.get(0).getZipcode();
				}
			}
			//*/
			mProvinceDatas = new String[provinceList.size()];
        	for (int i=0; i< provinceList.size(); i++) {
        		// ��������ʡ������
        		mProvinceDatas[i] = provinceList.get(i).getName();
        		List<CityModel> cityList = provinceList.get(i).getCityList();
        		String[] cityNames = new String[cityList.size()];
        		for (int j=0; j< cityList.size(); j++) {
        			// ����ʡ����������е�����
        			cityNames[j] = cityList.get(j).getName();
        			List<DistrictModel> districtList = cityList.get(j).getDistrictList();
        			String[] distrinctNameArray = new String[districtList.size()];
        			DistrictModel[] distrinctArray = new DistrictModel[districtList.size()];
        			for (int k=0; k<districtList.size(); k++) {
        				// ����������������/�ص�����
        				DistrictModel districtModel = new DistrictModel(districtList.get(k).getName(), districtList.get(k).getZipcode());
        				// ��/�ض��ڵ��ʱ࣬���浽mZipcodeDatasMap
        				mZipcodeDatasMap.put(districtList.get(k).getName(), districtList.get(k).getZipcode());
        				distrinctArray[k] = districtModel;
        				distrinctNameArray[k] = districtModel.getName();
        			}
        			// ��-��/�ص����ݣ����浽mDistrictDatasMap
        			mDistrictDatasMap.put(cityNames[j], distrinctNameArray);
        		}
        		// ʡ-�е����ݣ����浽mCitisDatasMap
        		mCitisDatasMap.put(provinceList.get(i).getName(), cityNames);
        	}
        } catch (Throwable e) {  
            e.printStackTrace();  
        } finally {
        	
        } 
	}

}
