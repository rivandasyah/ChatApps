package com.rivaphy.chatapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.rivaphy.chatapps.adapter.ChatAdapter
import com.rivaphy.chatapps.model.Chat
import com.rivaphy.chatapps.model.Users
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.activity_visit_profile.*
import kotlinx.android.synthetic.main.keft_chat_item.*

class ChatActivity : AppCompatActivity() {

    var userIdVisit: String = ""
    var firebaseUser: FirebaseUser? = null
    var chatAdapter: ChatAdapter? = null
    lateinit var recycler_view: RecyclerView
    var chatList: List<Chat>? = null
    var reference: DatabaseReference? = null

    var notify = false
    var api : ApiService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        setSupportActionBar(toolbar_chat)
        supportActionBar!!.title
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar_chat.setNavigationOnClickListener {
            finish()
        }

        intent = intent
        userIdVisit = intent.getStringExtra("visit_id")
        firebaseUser = FirebaseAuth.getInstance().currentUser

        recycler_view = findViewById(R.id.rv_profile)
        recycler_view.setHasFixedSize(true)
        var linearLayoutManager = LinearLayoutManager(applicationContext)
        linearLayoutManager.stackFromEnd = true

        recycler_view.layoutManager = linearLayoutManager
        reference = FirebaseDatabase.getInstance().reference
            .child("Users").child(userIdVisit)
        reference!!.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val user : Users? = snapshot.getValue(Users::class.java)
                tv_username_profile.text = user!!.getUsername()
                Picasso.get().load(user.getProfile()).into(iv_image_chat)
                retrieveMessage(firebaseUser!!.uid, userIdVisit, user.getProfile())
            }

        })

        //imageView2 = yang buat send message di chat xml
        imageView2.setOnClickListener {
            notify = true
            val message = edt_username_profile.text.toString()
            if (message == "") {
                Toast.makeText(this, getString(R.string.message_notif),
                    Toast.LENGTH_LONG).show()
            } else {
                sendMessage(firebaseUser!!.uid, userIdVisit, message)
            }
            edt_username_profile.setText("")
        }
        iv_attach_file_profile.setOnClickListener { 
            notify = true
            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "image/*"
            startActivityForResult(Intent.createChooser(intent, 
                "Pick Image"), 438)
        }
        seenMessage(userIdVisit)
    }

    private fun seenMessage(userIdVisit: String?) {

    }

    private fun sendMessage(uid: String, userIdVisit: String?, message: String) {

    }

    private fun retrieveMessage(senderId: String, receiverId: String?, profile: String?) {
        chatList = ArrayList()
        val reference = FirebaseDatabase.getInstance().reference.child("Chats")
        reference.addValueEventListener( object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshots: DataSnapshot) {
                (chatList as ArrayList<Chat>).clear()
                for (snapshot in snapshots.children) {
                    val chat = snapshot.getValue(Chat::class.java)
                    if (chat!!.getReceiver().equals(senderId) && chat.getSender().equals(receiverId) ||
                            chat.getReceiver().equals(receiverId) && chat.getSender().equals(senderId)) {
                        (chatList as ArrayList<Chat>).add(chat)
                    }

                    chatAdapter = ChatAdapter(this@ChatActivity,
                        (chatList as ArrayList<Chat>), profile!!)
                    recycler_view.adapter = chatAdapter
                }
            }
        })
    }
}
