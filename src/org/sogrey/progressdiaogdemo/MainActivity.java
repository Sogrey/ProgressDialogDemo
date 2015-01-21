/**
 * 
 */
package org.sogrey.progressdiaogdemo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.ant.liao.GifView;

/**
 * @author Administrator
 *
 */
public class MainActivity extends Activity {

	ProgressDialog progress ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.main);
		super.onCreate(savedInstanceState);
		
		findViewById(R.id.but_menu).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
//				progress= ProgressDialog.show(MainActivity.this, "��ʾ", "���ڵ�¼�����Ժ�...");//ϵͳ��
				progress = new ProgressDialog(MainActivity.this);//�Զ���
				progress.setCancelable(false);
				progress.show();
				progress.setContentView(R.layout.dialog_commit_gif);
				GifView gif = (GifView)progress.findViewById(R.id.gif_commit_loading);
				gif.setGifImage(R.drawable.loading);
				new Thread() {
					@Override
					public void run() {
						Message logMsg = handler.obtainMessage();
						try {
							Thread.sleep(10000);
							logMsg.what = 1001;
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						logMsg.sendToTarget();
					}
				}.start();
				
			}
		});
	}
	
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if (progress != null) {
				progress.cancel();
			}
			Toast t = Toast.makeText(MainActivity.this, "ʱ�䵽��������", 1000);
			t.setGravity(Gravity.BOTTOM, 0, 0);
			t.show();
		}
	};
}
