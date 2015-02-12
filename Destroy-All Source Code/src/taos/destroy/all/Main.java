package taos.destroy.all;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import taos.destroy.all.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

public class Main extends Activity {

	String numero;
	boolean preferenciasGuardadas;
	private InterstitialAd interstitial;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// AdView
		AdView adView = (AdView) this.findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);

		// Intersetial.
		interstitial = new InterstitialAd(this);
		interstitial.setAdUnitId("ca-app-pub-4116840717941176/3245817244");
		AdRequest adRequestInterstitial = new AdRequest.Builder().build();
		interstitial.loadAd(adRequestInterstitial);

	}

	public void onStart(View view) {
		Intent intent = new Intent(this, BaseActivity.class);
		startActivity(intent);
		this.finish();
	}

	public void onMore(View view) {
		Intent intent = new Intent(this, More.class);
		startActivity(intent);
		this.finish();
	}

	public void onExit(View view) {
		displayInterstitial();
		this.finish();
	}

	@Override
	protected void onStart() {
		super.onStart();
		cargarPreferencias();
		TextView textNumber = (TextView) findViewById(R.id.recordId);
		if (this.preferenciasGuardadas) {
			textNumber.setText("Record: " + numero + "%");
		} else {
			textNumber.setText("Ningun record establecido");
		}
	}

	public void cargarPreferencias() {
		SharedPreferences prefs = getSharedPreferences("recordsMarcianos",
				Context.MODE_PRIVATE);
		numero = prefs.getString("numero", "valor por defecto");
		preferenciasGuardadas = prefs
				.getBoolean("preferenciasGuardadas", false);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			displayInterstitial();
			this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	public void displayInterstitial() {
		if (interstitial.isLoaded()) {
			interstitial.show();
		}
	}
}
