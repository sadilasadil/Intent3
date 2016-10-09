package id.sch.smktelkom_mlg.learn.intent3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && requestCode == RESULT_OK) {
            Bitmap bitmap = data.getParcelableExtra("data");
            ImageView iv = (ImageView) findViewById(R.id.imageViewCamera);
            iv.setImageBitmap(bitmap);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.imageViewPhone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialPhoneNumber("0341712500");
            }

            private void dialPhoneNumber(String s) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + s));
                if (intent.resolveActivity(getPackageManager()) != null)
                    startActivity(intent);
            }
        });

        findViewById(R.id.imageViewSMS).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                composeSmsMessage("Pesan dari SMK Telkom Malang");
            }

            private void composeSmsMessage(String s) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra("sms_body", s);
                if (intent.resolveActivity(getPackageManager()) != null)
                    startActivity(intent);
            }
        });

        findViewById(R.id.imageViewBrowser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebPage("http://www.smktelkom-mlg.sch.id/");
            }

            private void openWebPage(String s) {
                Uri webpage = Uri.parse(s);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                if (intent.resolveActivity(getPackageManager()) != null)
                    startActivity(intent);
            }
        });

        findViewById(R.id.imageViewCamera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capturePhoto();
            }

            private void capturePhoto() {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null)
                    startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
            }
        });


    }
}
