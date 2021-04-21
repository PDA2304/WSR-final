package com.example.wsr

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.sign_up_screen.*
import java.util.*


class SignUpScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up_screen)

        btn_sign_in.setOnClickListener {

            if (pass.text.toString().isEmpty() || email.text.toString()
                    .isEmpty() || name.text.toString().isEmpty() || pass_confirm.text.toString()
                    .isEmpty()
            ) {
                Toast.makeText(
                    this@SignUpScreen, "Пусто   ",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            if (pass.text.toString() != pass_confirm.text.toString()) {
                Toast.makeText(
                    this@SignUpScreen, "Пароли не совпадают   ",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            var auth = FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                email.text.toString(),
                pass.text.toString()
            ).addOnCompleteListener(this,
                OnCompleteListener<AuthResult?> { task ->

                    if (task.isSuccessful) {
                        Toast.makeText(
                            this,
                            taskId.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.i("Tag",taskId.toString())
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(
                            this@SignUpScreen, "Ошибка",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
            var database = FirebaseDatabase.getInstance().getReference("user").get()
            var user = User(name.text.toString(), email.text.toString(), pass.text.toString())
                //database.setValue(user)
            var intent = Intent(this, SignInScreen::class.java)
            startActivity(intent)
        }
        btn_cansel.setOnClickListener {
            var intent = Intent(this, SignInScreen::class.java)
            startActivity(intent)
        }
    }
}

data class User(
    var name: String, var email: String, var pass: String
) : Observable()