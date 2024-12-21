package com.example.threadui

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.threadui.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()

        handler = Handler { message ->
            val bundle = message.data
            val msg = bundle.getString("MSG_KEY") ?: "NA"

//            binding.txtTxt.text = "File Downloaded."
//            binding.btnBtn.text = "File Downloaded."

            binding.txtTxt.text = msg
            binding.btnBtn.text = msg

            true
        }

    }

    private fun setListeners() {
        /*PROBLEM*/
        /*binding.btnBtn.setOnClickListener {
            try {
                Thread.sleep(3000)
            } catch (e: Exception) {
                Log.e("TAG", "setListeners: ${e.message}")
            }
            binding.txtTxt.text = "BUTTON IS CLICKED"
            binding.btnBtn.text = "BUTTON IS CLICKED."
        }*/

        /*SOLUTION*/
        binding.btnBtn.setOnClickListener {
            val r = Runnable {
                val objMsg = handler.obtainMessage()
                val objBundle = Bundle()

                try {
                    Thread.sleep(3000)
                } catch (e: Exception) {
                    println(e.message)
                }

                /*TO SEND CUSTOM MESSAGE*/
                objBundle.putString("MSG_KEY", "FILE HAS BEEN DOWNLOADED.")
                objMsg.data = objBundle

                handler.sendMessage(objMsg)

//                handler.sendEmptyMessage(0)     /*USED TO ACKNOWLEDGE THE UI AFTER THREAD TASK COMPLETED. */
            }
            val th = Thread(r)
            th.start()

            binding.txtTxt.text = "Button CLICKED."
            binding.btnBtn.text = "Button CLICKED."
        }
    }

}