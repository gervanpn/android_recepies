package com.example.recipe.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.widget.ImageView
import java.net.URL

class DownloadImage(var imageView: ImageView) : AsyncTask<String?, Void?, Bitmap?>() {

    protected override fun doInBackground(vararg params: String?): Bitmap? {
        val urlOfImage = params[0]
        var logo: Bitmap? = null
        try {
            val `is` = URL(urlOfImage).openStream()
            logo = BitmapFactory.decodeStream(`is`)
        } catch (e: Exception) { // Catch the download exception
            e.printStackTrace()
        }
        return logo
    }
    
    override fun onPostExecute(result: Bitmap?) {
        imageView.setImageBitmap(result)
    }
}
