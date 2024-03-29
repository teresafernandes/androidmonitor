package ufrn.dimap.se.monitor;

import java.text.DecimalFormat;
import java.util.Vector;

import ufrn.dimap.se.monitor.R.id;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class MonitorActivity extends Activity {

	static int INTERVAL_UPDATE = 1000;
	private TextView batteryTextView, batteryTextViewPercent;
	private MonitorService monitorService;
	private DecimalFormat myFormat = new DecimalFormat("##,###,##0");
	private DecimalFormat myFormatPercent = new DecimalFormat("##0.0");

	/*private Runnable drawRunnable = new Runnable() {
		public void run() {
			try {
				setTextLabel(batteryTextView, batteryTextViewPercent,
						monitorService.getData().getBatteryConsume(),
						monitorService.getData().getBatteryScale());
				Thread.sleep(INTERVAL_UPDATE);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};
	private Thread drawThread = new Thread(drawRunnable, "drawThread");*/

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_monitor);
		monitorService = new MonitorService();
		monitorService.setInterval(1000);
		System.out.println("service =" + monitorService);

		startService(new Intent(this, MonitorService.class));

		System.out.println("supimpa");

		batteryTextView = (TextView) findViewById(id.valueBattery);
		batteryTextViewPercent = (TextView) findViewById(id.percentBattery);

		//drawThread.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_monitor, menu);
		return true;
	}

	private void setTextLabel(TextView textView, TextView textViewPercent,
			long value, long scale) {

		textView.setText(myFormat.format(value) + " Uni");
		textViewPercent.setText(myFormatPercent.format(value * 100
				/ (float) scale)
				+ "%");

	}
}
