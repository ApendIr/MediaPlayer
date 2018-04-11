package ir.apend.mediaplayer;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import java.io.File;

import mediaplayer.apend.ir.MediaPlayerLibrary;
import mediaplayer.apend.ir.listner.MediaPlayerCallback;

public class MainActivity extends AppCompatActivity implements MediaPlayerCallback {

    private MediaPlayerLibrary player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player = (MediaPlayerLibrary) findViewById(R.id.player);
        assert player != null;
        player.setCallback(this);

        if(checkStoragePermissionGranted(this)) {
            // player.setSource(Uri.parse("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"),2);
            player.setSource(Uri.fromFile(new File(Environment.getExternalStorageDirectory() +
                    File.separator + "Step", "test1.mp3")));
            // player.setSource(Uri.parse("http://dl.pop-music.ir/music/1397/Farvardin/Mohammad%20Motamedi%20-%20Koobaar%20(128).mp3"));
        }else
            requestStoragePermission(this);
        // All further configuration is done from the XML layout.
    }
    @Override
    protected void onPause() {
        super.onPause();
        player.pause();
    }

    @Override
    public void onStarted(MediaPlayerLibrary player) {}

    @Override
    public void onPaused(MediaPlayerLibrary player) {}

    @Override
    public void onPreparing(MediaPlayerLibrary player) {
        Log.d("EVP-Sample", "onPreparing()");
    }

    @Override
    public void onPrepared(MediaPlayerLibrary player) {
        Log.d("EVP-Sample", "onPrepared()");
    }

    @Override
    public void onBuffering(int percent) {
        Log.d("EVP-Sample", "onBuffering(): " + percent + "%");
    }

    @Override
    public void onError(MediaPlayerLibrary player, Exception e) {
        Log.d("EVP-Sample", "onError(): " + e.getMessage());
        new MaterialDialog.Builder(this)
                .title(R.string.error)
                .content(e.getMessage())
                .positiveText(android.R.string.ok)
                .show();
    }

    @Override
    public void onCompletion(MediaPlayerLibrary player) {
        Log.d("EVP-Sample", "onCompletion()");
    }

    @Override
    public void onRetry(MediaPlayerLibrary player, Uri source) {
        Toast.makeText(this, "Retry", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSubmit(MediaPlayerLibrary player, Uri source) {
        Toast.makeText(this, "Submit", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickVideoFrame(MediaPlayerLibrary player) {
        Toast.makeText(this, "Click video frame.", Toast.LENGTH_SHORT).show();
    }


    public static final int WRITE_PERMISSION_REQUEST_CODE = 1584;

    public static boolean checkStoragePermissionGranted(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            return ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        else
            return true;
    }

    public static void requestStoragePermission(Activity activity) {
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, WRITE_PERMISSION_REQUEST_CODE);
    }
}

