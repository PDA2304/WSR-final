package com.example.wsr

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.chat.*
import kotlinx.android.synthetic.main.fragment_chat_screen.*


class Chat : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chat)
        val navController = findNavController(R.id.nav_host_fragment)
        nav_view.setupWithNavController(navController)
        val uid = FirebaseAuth.getInstance().uid
        if (uid == null) {
            var intent = Intent(this, SignInScreen::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        FirebaseDatabase.getInstance().getReference("/user")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    var string = ArrayList<String>()
                    snapshot.children.forEach {
                        string.add(it.getValue((User::class.java))!!.name)
                    }


                  var  adapter = ArrayAdapter (
                        this@Chat,
                        android.R.layout.simple_list_item_1, string
                    )

                    list_item.adapter = adapter

                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })

    }
}