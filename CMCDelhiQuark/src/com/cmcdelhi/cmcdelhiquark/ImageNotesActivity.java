package com.cmcdelhi.cmcdelhiquark;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.ActionBar;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ImageNotesActivity extends Activity {

	LinearLayout imagescanner1, imagescanner2;
	FrameLayout frame;

	private Uri imageUri;

	ImageView img;

	LockedColorSingleton lcsI;
	TextView tv1, tv2;

	private static final int IMAGE_CAPTURE = 0;

	Typeface tpf;

	ActionBar ab;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_notes);

		// LockedColorSingleton.getInstance().colorVal

		imagescanner1 = (LinearLayout) findViewById(R.id.imagescan);

		imagescanner1
				.setBackgroundColor(LockedColorSingleton.getInstance().colorVal);

		frame = (FrameLayout) findViewById(R.id.frames);

		frame.setBackgroundColor(LockedColorSingleton.getInstance().colorVal);

		imagescanner2 = (LinearLayout) findViewById(R.id.newlinearlayout);

		imagescanner2
				.setBackgroundColor(LockedColorSingleton.getInstance().colorVal);

		img = (ImageView) findViewById(R.id.imageView1);

		tv1 = (TextView) findViewById(R.id.textView1);

		tv2 = (TextView) findViewById(R.id.textView2);

		tv2.setText("Share your image notes on Dropbox ,\n Evernotes, GDrive, Facebook etc...");

		tv2.setTextColor(Color.WHITE);

		tpf = Typeface.createFromAsset(getAssets(), "fonts/Mathlete-Bulky.otf");

		tv2.setTypeface(tpf);

		tv2.setTextSize(27f);

		tv1.setTypeface(tpf);

		tv1.setTextSize(50f);

		tv1.setTextColor(LockedColorSingleton.getInstance().colorVal);

		ab = getActionBar();

		img.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Toast.makeText(getBaseContext(), "Starting up Camera  ",
						Toast.LENGTH_SHORT).show();

				Log.d("QUARK_ANDRO_CAMERA",
						"Starting camera on the phone for Image Notes ...");
				String fileName = "quarkImageNote.jpg";
				ContentValues values = new ContentValues();
				values.put(MediaStore.Images.Media.TITLE, fileName);
				values.put(MediaStore.Images.Media.DESCRIPTION,
						"Image Notes By Quark");
				values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");

				imageUri = getContentResolver().insert(
						MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
				intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
				startActivityForResult(intent, IMAGE_CAPTURE);

			}
		});

		ab.setIcon(R.drawable.ic_launcher);
		ab.setDisplayHomeAsUpEnabled(true);
		ab.setDisplayShowCustomEnabled(true);
		ab.setTitle("ImageScanner");

		ColorDrawable d = new ColorDrawable(
				LockedColorSingleton.getInstance().colorVal);

		ab.setBackgroundDrawable(d);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == IMAGE_CAPTURE) {
			if (resultCode == RESULT_OK) {
				Log.d("ANDRO_CAMERA", "Picture taken!!!");
				img.setImageURI(imageUri);

			}
		}

	}

}
