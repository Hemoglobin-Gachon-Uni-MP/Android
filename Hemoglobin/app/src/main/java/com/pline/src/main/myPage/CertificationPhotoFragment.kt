package com.pline.src.main.myPage

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pline.config.ApplicationClass
import com.pline.data.mypage.MyPageRetrofitInterface
import com.pline.data.mypage.model.PostCertReq
import com.pline.data.mypage.model.PostCertResponse
import com.pline.databinding.FragmentCertificationPhotoBinding
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


class CertificationPhotoFragment(val eName: String, val eNum: String, val eDate: String): BottomSheetDialogFragment() {
    private lateinit var binding: FragmentCertificationPhotoBinding
    private lateinit var getResult: ActivityResultLauncher<Intent>
    private lateinit var permissionResultLauncher: ActivityResultLauncher<Array<String>>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCertificationPhotoBinding.inflate(inflater, container, false)

        // registerForActivityResult 초기화
        getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            handleImageResult(result)
        }

        // 권한 요청용 registerForActivityResult 초기화
        permissionResultLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            if (permissions[android.Manifest.permission.READ_EXTERNAL_STORAGE] == true) {
                // 권한이 허용된 경우 갤러리 열기
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = MediaStore.Images.Media.CONTENT_TYPE
                intent.type = "image/*"
                getResult.launch(intent)
            } else {
                // 권한이 거부된 경우 처리
                Toast.makeText(requireContext(), "권한을 거부하셨습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.certificationPhotoImageCl.setOnClickListener {
            initImageViewProfile()
        }

        /** 이전 버튼 클릭 **/
        binding.fragmentCertificationPhotoPreviousBtnTv.setOnClickListener {
            val dialog = CertificationFragment()
            dialog.show(requireActivity().supportFragmentManager, dialog.tag)
            dismiss()
        }

        return binding.root
    }

    private fun initImageViewProfile() {
        // 권한이 허용되어 있는지 확인
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // 이미 권한이 허용된 경우 갤러리 열기
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            getResult.launch(intent)
        } else {
            // 권한이 허용되지 않은 경우 권한 요청
            permissionResultLauncher.launch(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE))
        }
    }

    private fun uploadImage(selectedImageUri: Uri) {
        Log.d("image","uploadImage")
        val imgPath = absolutelyPath(selectedImageUri, requireContext())
        val file = File(imgPath)
        val requestBody = RequestBody.create("image/*".toMediaTypeOrNull(), file)
        val body = MultipartBody.Part.createFormData("image", file.name, requestBody)

        Log.d("seori1116", "imgPath $imgPath")
        Log.d("seori1116", "body $body")
        val req = PostCertReq(eNum, eDate, eName)

        postCert(req, body)
    }
    private fun handleImageResult(result: ActivityResult) {
        Log.d("image","handleImageResult")
        if (result.resultCode == RESULT_OK) {
            val selectedImageUri: Uri? = result.data?.data
            if (selectedImageUri != null) {
                // 이미지 업로드 로직
                uploadImage(selectedImageUri)
            } else {
                Toast.makeText(requireContext(), "사진을 가져오지 못했습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun absolutelyPath(path: Uri?, context : Context): String {
        val proj: Array<String> = arrayOf(MediaStore.Images.Media.DATA)
        val c: Cursor? = context.contentResolver.query(path!!, proj, null, null, null)
        val index = c?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        c?.moveToFirst()

        val result = c?.getString(index!!)

        return result!!
    }

    private fun postCert(req: PostCertReq, body: MultipartBody.Part) {
        val service = ApplicationClass.sRetrofit.create(MyPageRetrofitInterface::class.java)
        // Get jwt, userId from sp
        val jwt = ApplicationClass.sSharedPreferences.getString("jwt", "")
        if (jwt != null) {
            Log.d("seori1116", jwt)
        }

        val jsonObject = JSONObject(
            "{\"certificationNum\":\"${eNum}\"," +
                    "\"date\":\"${eDate}\"," +
                    "\"name\":\"${eName}\"}").toString()
        val jsonBody: RequestBody = RequestBody.create("application/json".toMediaTypeOrNull(), jsonObject)

        // Request my page info through API
        jwt?.let {
            // Jwt is in header, userId is in Path Variable
            service.postMyCert("Bearer $jwt", body, jsonBody).enqueue(object : Callback<PostCertResponse> {
                override fun onResponse(call: Call<PostCertResponse>, response: Response<PostCertResponse>) {
                    Log.d("seori000", response.toString())
                    if (response.isSuccessful) {
                        val body = response.body()
                        when (body?.code) {
                            // If success, fill the data
                            1000 -> {
                                Log.d("seori1116", "사진 전송 성공")
                                val result = body.result
                                binding.run {
                                    //
                                }
                            }
//                             If GET fails, show toast message to user
//                            else -> body?.message?.let { it1
//                                -> showCustomToast(it1)
//                                Log.d("seori111", response.message())
//                            }
                        }
                    } else {
                        // If fail, show toast message to user
                        Log.d("seori1116", response.body().toString())
                    }
                }
                // If fail, show toast message to user
                override fun onFailure(call: Call<PostCertResponse>, t: Throwable) {
                    Log.d("seori1116", "?") // 여기에 걸리다
                }
            })
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)

    }
}