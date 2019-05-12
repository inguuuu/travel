package com.teamandroid.travelmaker

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.util.Log.d
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import com.bumptech.glide.Glide
import com.teamandroid.travelmaker.travelplanwrite.Specific.SpecificActivity
import com.teamandroid.travelmaker.travelplanwrite.dataSets.Data
import com.teamandroid.travelmaker.travelplanwrite.dataSets.Memo
import com.teamandroid.travelmaker.travelplanwrite.dataSets.TotalData
import kotlinx.android.synthetic.main.activity_memo.*
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.InputStream

class MemoActivity : AppCompatActivity(), View.OnClickListener {
    private val MEMO_REQUEST = 100
    var data : Uri = Uri.EMPTY
    var memo_title : String = ""
    var curDay : Int = 0
    var position : Int = 0
    override fun onClick(v: View?) {
        when(v){
            confirm_memo_btn->{
//                val returnIntent = Intent()
//                returnIntent.putExtra("memo_return", "memo_return")
//
//                setResult(Activity.RESULT_OK, returnIntent)
                if(memo_title == "" || this.data.equals(Uri.EMPTY)){
                    Toast.makeText(this, "빈 칸 없이 입력해주세요", Toast.LENGTH_LONG).show()
                }

                intent = Intent(this, SpecificActivity::class.java)
                intent.putExtra("memo",curDay)
                startActivity(intent)
                finish()
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memo)
        confirm_memo_btn.setOnClickListener(this)

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = Color.parseColor("#14DAC6")
        }

        add_image_btn.setOnClickListener {
            changeImage()
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == MEMO_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    //if(ApplicationController.getInstance().is)
                    this.data = data!!.data
                    Log.v("이미지", this.data.toString())

                    val options = BitmapFactory.Options()

                    var input: InputStream? = null // here, you need to get your context.
                    try {
                        input = contentResolver.openInputStream(this.data)
                    } catch (e: FileNotFoundException) {
                        e.printStackTrace()
                    }

                    val bitmap = BitmapFactory.decodeStream(input, null, options) // InputStream 으로부터 Bitmap 을 만들어 준다.
                    val baos = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos)

                    ///RequestBody photoBody = RequestBody.create(MediaType.parse("image/jpg"), baos.toByteArray());
                    // MultipartBody.Part 실제 파일의 이름을 보내기 위해 사용!!

                    //body = MultipartBody.Part.createFormData("image", photo.getName(), profile_pic);

                    Glide.with(this)
                            .load(data.data)
                            .centerCrop()
                            .into(write_image)

                    var memoEditText : EditText = findViewById(R.id.board_write_title)
                    memo_title = memoEditText.text.toString()

                    curDay = intent.getIntegerArrayListExtra("memo")[0]
                    position = intent.getIntegerArrayListExtra("memo")[1]

                    var arrayList : ArrayList<Data> = TotalData.totalData.get(curDay)!!
                    var tempMemo : Memo = Memo(memo_title, this.data)
                    arrayList.get(position).memo = tempMemo
                    TotalData.totalData.put(curDay, arrayList)

                    Log.d("확인", "오브젝트에 잘 들어가있는지 확인하기")
                    Log.d("확인", TotalData.totalData.get(curDay)!![position].memo!!.memo_title)
                    Log.d("확인", TotalData.totalData.get(curDay)!![position].memo!!.memo_img.toString())

                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }

    }

    fun changeImage(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = android.provider.MediaStore.Images.Media.CONTENT_TYPE
        intent.data = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        startActivityForResult(intent, MEMO_REQUEST)
    }
}