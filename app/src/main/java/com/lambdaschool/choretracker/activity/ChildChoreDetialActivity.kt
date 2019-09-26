package com.lambdaschool.choretracker.activity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.lambdaschool.choretracker.model.Chore
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_child_chore_detial.*
import android.os.Environment.DIRECTORY_PICTURES
import android.os.Environment.getExternalStoragePublicDirectory
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.lambdaschool.choretracker.viewmodel.ChildChoreDetailActivityViewModel
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class ChildChoreDetialActivity : AppCompatActivity() {

    companion object {
        const val IMAGE_CAPTURE_CODE = 7366
        const val PERMISSION_REQUEST_CODE = 8210
    }

    private lateinit var viewModel: ChildChoreDetailActivityViewModel
    var filePath = ""
    var file: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.lambdaschool.choretracker.R.layout.activity_child_chore_detial)

        viewModel = ViewModelProviders.of(this).get(ChildChoreDetailActivityViewModel::class.java)

        val data = intent.getSerializableExtra(ChildMainActivity.CHILD_CHORE_DETAIL_KEY) as Chore
        tv_chore_detail_title.text = data.title
        tv_chore_detail_points.text = data.pointValue.toString()
        tv_chore_detail_description.text = data.description

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) !=
            PackageManager.PERMISSION_GRANTED) {

            btn_chore_detail_add_photo.isEnabled = false
            ActivityCompat.requestPermissions(
                this,
                Array(2) {Manifest.permission.CAMERA; Manifest.permission.WRITE_EXTERNAL_STORAGE},
                PERMISSION_REQUEST_CODE
            )
        }

        if (data.photoFilePath != "") {
            Picasso.get().load(data.photoFilePath).into(iv_chore_detail_image)
        }

        btn_chore_detail_add_photo.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            file = Uri.fromFile(getOutputMediaFile())
            intent.putExtra(MediaStore.EXTRA_OUTPUT, file)
            startActivityForResult(intent, IMAGE_CAPTURE_CODE)
        }

        btn_chore_detail_submit.setOnClickListener {
            simulateNetworkCall()
            viewModel.updateChore(
                Chore(
                    data.title,
                    data.description,
                    data.pointValue,
                    true,
                    filePath,
                    data.parent_id,
                    data.child_id
                )
            )
            Toast.makeText(this, "Chore submitted!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun simulateNetworkCall() {
        pb_child_chore_detail.visibility = View.VISIBLE
        val handler = Handler()
        handler.postDelayed({
            pb_child_chore_detail.visibility = View.INVISIBLE
        }, 1500)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_CAPTURE_CODE && resultCode == Activity.RESULT_OK) {
            Picasso.get().load(file).into(iv_chore_detail_image)
        }
    }

    private fun getOutputMediaFile(): File? {
        val mediaStorageDir = File(
            getExternalStoragePublicDirectory(
                DIRECTORY_PICTURES
            ), "ChoreTracker"
        )

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null
            }
        }

        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())

        filePath = mediaStorageDir.getPath() + File.separator +
                "IMG_" + timeStamp + ".jpg"

        return File(filePath)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                grantResults[1] == PackageManager.PERMISSION_GRANTED
            ) {
                btn_chore_detail_add_photo.isEnabled = true
            }
        }
    }
}
