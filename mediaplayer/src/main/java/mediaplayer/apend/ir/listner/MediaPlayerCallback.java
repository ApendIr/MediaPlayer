package mediaplayer.apend.ir.listner;

import android.net.Uri;

import mediaplayer.apend.ir.MediaPlayerLibrary;

/** @author Aidan Follestad (afollestad) */
public interface MediaPlayerCallback {

  void onStarted(MediaPlayerLibrary player);

  void onPaused(MediaPlayerLibrary player);

  void onPreparing(MediaPlayerLibrary player);

  void onPrepared(MediaPlayerLibrary player);

  void onBuffering(int percent);

  void onError(MediaPlayerLibrary player, Exception e);

  void onCompletion(MediaPlayerLibrary player);

  void onRetry(MediaPlayerLibrary player, Uri source);

  void onSubmit(MediaPlayerLibrary player, Uri source);

  void onClickVideoFrame(MediaPlayerLibrary player);
}
