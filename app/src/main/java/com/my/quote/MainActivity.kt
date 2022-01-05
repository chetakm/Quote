package com.my.quote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel

    private val quoteText:TextView
    get()=findViewById(R.id.quoteText)

    private val authorText:TextView
        get()=findViewById(R.id.quoteAuthor)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel=ViewModelProvider(this,MainViewModelFactory(application)).get(MainViewModel::class.java)
        setQuote(mainViewModel.getQuote())

    }

    private fun setQuote(quote:Quote) {
        quoteText.text=quote.text
        authorText.text=quote.author
    }



    fun onPrevious(view: android.view.View) {
        if (mainViewModel.index>0) {
            setQuote(mainViewModel.previousQuote())
        }else{
            Toast.makeText(this,"It is Starting Position",Toast.LENGTH_SHORT).show()
        }
    }
    fun onNext(view: android.view.View) {
        setQuote(mainViewModel.nextQuote())
    }

    fun onShare(view: android.view.View) {
        val intent= Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT,mainViewModel.getQuote().text)
        startActivity(intent)
    }
}