package mediaplayer.apend.ir.listner;

/** @author Aidan Follestad (afollestad) */
public interface MediaPlayerProgressCallback {

  void onVideoProgressUpdate(int position, int duration);
}
