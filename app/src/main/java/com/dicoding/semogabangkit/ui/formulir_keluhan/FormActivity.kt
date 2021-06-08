package com.dicoding.semogabangkit.ui.formulir_keluhan

import android.R.attr
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.semogabangkit.databinding.ActivityFormBinding
import com.dicoding.semogabangkit.viewmodel.ViewModelFactory
import java.io.ByteArrayOutputStream


class FormActivity : AppCompatActivity() {

    companion object {
        //image pick code
        private const val IMAGE_REQUEST_CODE = 1000;

        //Permission code
        private const val PERMISSION_CODE = 1001;
    }

    private lateinit var binding: ActivityFormBinding
    private lateinit var viewModel: FormViewModel

    private lateinit var judul: String
    private lateinit var deskripsi: String
    private lateinit var encodedImage: String

    private var imageIsChanged = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this, factory)[FormViewModel::class.java]

        binding.apply {
            fab.setOnClickListener {
                setJudulAndDeskripsi()
                if (imageIsChanged) {
                    uploadReport(judul, deskripsi, encodedImage)
                } else {
                    showToast("Tolong Pilih Gambar dari Galeri")
                }
            }
            btnImgChooseImage.setOnClickListener {
                pickImageFromGallery()
            }
        }

    }

    override fun onResume() {
        super.onResume()
        supportActionBar?.title = "Kirim Laporan"
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {

            val selectedfile: Uri = data?.data as Uri//The uri with the location of the file
            val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, selectedfile)
            setEncodedImage(bitmap)

            binding.btnImgChooseImage.setImageURI(data?.data)
        }
    }

    private fun setEncodedImage(bitmap: Bitmap) {
        encodedImage = convertBitmapToBase64(bitmap)
        imageIsChanged = true
    }

    private fun convertBitmapToBase64(bitmap: Bitmap): String {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 60, stream)
        val image = stream.toByteArray()
        return Base64.encodeToString(image, Base64.DEFAULT)
    }

    private fun setJudulAndDeskripsi() {
        judul = binding.etTitle.text.toString()
        deskripsi = binding.etDescription.text.toString()
    }

    private fun uploadReport(judul: String, deskripsi: String, encodedImage: String) {
        viewModel.uploadReport(judul, deskripsi, encodedImage).observe(this, { response ->
            Log.d("form activity", response.toString())
        })
        Log.d("form activity", judul)
        Log.d("form activity", deskripsi)
        Log.d("form activity", encodedImage)
    }

    private fun showToast(text: String) {
        Toast.makeText(this@FormActivity, text, Toast.LENGTH_SHORT).show()
    }

}
