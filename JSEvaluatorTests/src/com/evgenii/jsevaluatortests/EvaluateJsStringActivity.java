package com.evgenii.jsevaluatortests;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.evgenii.jsevaluator.JsEvaluator;
import com.evgenii.jsevaluator.interfaces.JsCallback;

public class EvaluateJsStringActivity extends Activity {
	JsEvaluator mJsEvaluator;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_evaluate_js_string);

		// Make sure we're running on Honeycomb or higher to use ActionBar APIs
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			// Show the Up button in the action bar.
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}

		mJsEvaluator = new JsEvaluator(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.evaluate_js_string, menu);
		return true;
	}

	public void onEvaluateClicked(View view) {
		final EditText editText = (EditText) findViewById(R.id.edit_java_script);
		mJsEvaluator.evaluate(editText.getText().toString(), new JsCallback() {
			@Override
			public void onResult(final String resultValue) {
				final TextView jsResultTextView = (TextView) findViewById(R.id.js_result_text_view);
				jsResultTextView.setText(String.format("Result: %s",
						resultValue));
			}
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
