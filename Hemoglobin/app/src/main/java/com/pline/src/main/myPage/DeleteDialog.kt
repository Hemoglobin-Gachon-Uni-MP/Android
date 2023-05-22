package com.pline.src.main.myPage

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.fragment.app.DialogFragment
import com.pline.config.ApplicationClass
import com.pline.data.mypage.MyPageRetrofitInterface
import com.pline.data.mypage.model.MyAccountDeleteResponse
import com.pline.databinding.DialogDeleteAccountBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// Dialog for deleting account
class DeleteDialog : DialogFragment() {

    private lateinit var binding: DialogDeleteAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        binding = DialogDeleteAccountBinding.inflate(inflater, container, false)
        setDialog()
        return binding.root
    }

    // Set custom dialog
    private fun setDialog() = with(binding) {
        // Transparent background for visible corner radius
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // Set click event of delete button
        btnDelete.setOnClickListener {
            // Request delete account
            deleteMyAccount()
        }
        // Set click event of cancel button
        btnCancel.setOnClickListener {
            dismiss()
        }
    }

    // Delete my account through api
    private fun deleteMyAccount() {
        val service = ApplicationClass.sRetrofit.create(MyPageRetrofitInterface::class.java)
        // Get jwt, userId from sp
        val jwt = ApplicationClass.sSharedPreferences.getString("jwt", "")
        val userId = ApplicationClass.sSharedPreferences.getInt("userId", 0)
        // Request delete account through API
        jwt?.let {
            // Jwt is in header, userId is in Path Variable
            service.deleteMyAccount(jwt, userId)
                .enqueue(object : Callback<MyAccountDeleteResponse> {
                    override fun onResponse(
                        call: Call<MyAccountDeleteResponse>,
                        response: Response<MyAccountDeleteResponse>
                    ) {
                        if (response.isSuccessful) {
                            val body = response.body()
                            when (body?.code) {
                                // If success, show delete complete message
                                1000 -> {
                                    Toast.makeText(activity, "탈퇴가 완료되었습니다", Toast.LENGTH_SHORT)
                                        .show()
                                    // Clear all shared preferences
                                    ApplicationClass.sSharedPreferences.edit().clear().commit()
                                    // Remove all pages
                                    activity?.let { it1 -> finishAffinity(it1) }
                                }
                                // If fail, show toast message to user
                                else -> Toast.makeText(activity, body?.message, Toast.LENGTH_SHORT)
                                    .show()
                            }
                        } else {
                            // If fail, show toast message to user
                            Toast.makeText(activity, "네트워크 연결에 실패했습니다", Toast.LENGTH_SHORT).show()
                        }
                    }

                    // If fail, show toast message to user
                    override fun onFailure(call: Call<MyAccountDeleteResponse>, t: Throwable) {
                        Toast.makeText(activity, "네트워크 연결에 실패했습니다", Toast.LENGTH_SHORT).show()
                    }
                })
        }
    }
}