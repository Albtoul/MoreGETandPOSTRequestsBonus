package com.example.moregetandpostrequestsbonus

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    lateinit var ed: EditText
    lateinit var bt: Button
    lateinit var bt2: Button
    lateinit var list:ArrayList<String>
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ed=findViewById(R.id.ed)
        bt= findViewById(R.id.button)
        bt2= findViewById(R.id.button2)
        list= arrayListOf()
        recyclerView = findViewById(R.id.RV)

        recyclerView.layoutManager = LinearLayoutManager(this)

        bt.setOnClickListener {

            addUser()
        }
        bt2.setOnClickListener {
            retrifit()}
    }

    private fun retrifit(){

        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)//call api to fetch data
        apiInterface!!.getUser()?.enqueue(object : Callback<ContactNumber?> {
            override fun onResponse(
                call: Call<ContactNumber?>,
                response: Response<ContactNumber?>
            ) {
                for(index in response.body()!!){

                    list.add("${index.name}")
                }
                recyclerView.adapter= RV(list)
                recyclerView.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<ContactNumber?>, t: Throwable) {
                call.cancel()

            }


        } )



    }
    private fun addUser() {

        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        val progressDialog = ProgressDialog(this@MainActivity)
        progressDialog.setMessage("Please wait")
        progressDialog.show()
        if(apiInterface!=null){

            apiInterface.addUser(ContactNumber.ContactNumberItem(ed.text.toString())).enqueue(object :
                Callback<ContactNumber.ContactNumberItem?> {
                override fun onResponse(
                    call: Call<ContactNumber.ContactNumberItem?>,
                    response: Response<ContactNumber.ContactNumberItem?>
                ) {

                    progressDialog.dismiss()

                }

                override fun onFailure(call: Call<ContactNumber.ContactNumberItem?>, t: Throwable) {
                call.cancel()

                }
            })

        }

    }
}