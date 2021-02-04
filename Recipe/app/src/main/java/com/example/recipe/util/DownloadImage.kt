package com.example.recipe.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.widget.ImageView
import java.net.URL

internal class DownloadImage(var imageView: ImageView) : AsyncTask<String?, Void?, Bitmap?>() {
    /*
        doInBackground(Params... params)
            Override this method to perform a computation on a background thread.
     */
    protected override fun doInBackground(vararg params: String?): Bitmap? {
        val urlOfImage = params[0]
        var logo: Bitmap? = null
        try {
            val `is` = URL(urlOfImage).openStream()
            /*
                    decodeStream(InputStream is)
                        Decode an input stream into a bitmap.
                 */logo = BitmapFactory.decodeStream(`is`)
        } catch (e: Exception) { // Catch the download exception
            e.printStackTrace()
        }
        return logo
    }

    /*
        onPostExecute(Result result)
            Runs on the UI thread after doInBackground(Params...).
     */
    override fun onPostExecute(result: Bitmap?) {
        imageView.setImageBitmap(result)
    }
}