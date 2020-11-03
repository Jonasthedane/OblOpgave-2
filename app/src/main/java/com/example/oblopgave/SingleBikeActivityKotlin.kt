package com.example.oblopgave

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_single_bike_kotlin.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SingleBikeActivityKotlin : AppCompatActivity() {
    private var currentBicycle: Bicycle? = null
    private var currentUser: String? = null

    companion object{
        const val BICYCLE = "BICYCLE"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_bike_kotlin)

        val intent = intent
        currentBicycle = intent.getSerializableExtra(SingleBikeActivity.BICYCLE) as Bicycle

        infoId.text = currentBicycle?.id.toString();
        infoFrameNumber.text = currentBicycle?.frameNumber;
        infoKindOfBicycle.text = currentBicycle?.kindOfBicycle;
        infoBrand.text = currentBicycle?.brand;
        infoColors.text = currentBicycle?.colors;
        infoPlace.text = currentBicycle?.place;
        infoDate.text = currentBicycle?.date;
        infoUserId.text = currentBicycle?.userId.toString();
        infoMissingFound.text = currentBicycle?.missingFound;

        currentUser = FirebaseAuth.getInstance().currentUser!!.uid

        if (currentUser == currentBicycle!!.firebaseUserId) {
            DeleteButton.setVisibility(View.VISIBLE)
        } else {
            DeleteButton.setVisibility(View.GONE)
        }

    }

    fun DeleteBicycleKotlin(view: View) {
        val callDeleteBicycles = ApiUtils.getBicycleService().deleteBike(currentBicycle!!.id)
        callDeleteBicycles.enqueue(object : Callback<String?> {
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@SingleBikeActivityKotlin, "Deleted", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@SingleBikeActivityKotlin, "Failed delete", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<String?>, t: Throwable) {}
        })
    }
}