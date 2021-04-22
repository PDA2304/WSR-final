package com.example.wsr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.sign_in_screen.*

class SignInScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_in_screen)

        val uid = FirebaseAuth.getInstance().uid
        if(uid != null)
        {
            var intent = Intent(this, Chat::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity (intent)
        }

        btn_sign_up.setOnClickListener {
            var intent = Intent(this, SignUpScreen::class.java)
            startActivity (intent)
        }
        btn_sign_in.setOnClickListener {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email.text.toString(),pass.text.toString()).addOnSuccessListener {
                var intent = Intent(this, Chat::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity (intent)
            }
        }

    }

}

//выбор фотки
//var intent = Intent(Intent.ACTION_PICK)
//            intent.type = "image/*"
//            startActivityForResult(intent,0)