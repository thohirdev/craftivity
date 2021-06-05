package com.garbage.craftivity.ui.cam

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.garbage.craftivity.R
import com.garbage.craftivity.databinding.FragmentCamBinding
import com.garbage.craftivity.databinding.FragmentCraftBinding
import com.garbage.craftivity.ml.ModelQuantTl


import com.garbage.craftivity.ui.craft.CraftAdapter
import com.garbage.craftivity.ui.craft.CraftViewModel
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer

class CamFragment : Fragment() {

    private lateinit var binding: FragmentCamBinding
    lateinit var open_camera_button : Button
    lateinit var select_image_button : Button
    lateinit var make_prediction : Button
    lateinit var img_view : ImageView
    lateinit var text_view : TextView
    private lateinit var bitmap: Bitmap
    private val REQUEST_IMAGE_CAPTURE = 1
    private val REQUEST_PICK_IMAGE = 2
    private val REQUEST_PERMISSION = 100

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCamBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        open_camera_button = binding.buttonCamera
        select_image_button = binding.button
        make_prediction = binding.button2
        img_view = binding.imageView2
        text_view = binding.textView

        val labels = context?.assets?.open("labels.txt")?.bufferedReader().use { it?.readText() }
            ?.split("\n")

        open_camera_button.setOnClickListener(View.OnClickListener {
            Log.d("mssg", "button camera pressed")

            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { intent ->
                context?.let { it1 ->
                    intent.resolveActivity(it1.packageManager)?.also {
                        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
                    }
                }
            }
        })

        select_image_button.setOnClickListener(View.OnClickListener {
            Log.d("mssg", "button pressed")
            var intent : Intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"

            startActivityForResult(intent, REQUEST_PICK_IMAGE)
        })

        make_prediction.setOnClickListener(View.OnClickListener {

            var resized = Bitmap.createScaledBitmap(bitmap, 224, 224, true)
            val model = ModelQuantTl.newInstance(this.requireContext())

            var tbuffer = TensorImage.fromBitmap(resized)
            var byteBuffer = tbuffer.buffer

// Creates inputs for reference.
            val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.UINT8)
            inputFeature0.loadBuffer(byteBuffer)

// Runs model inference and gets result.
            val outputs = model.process(inputFeature0)
            val outputFeature0 = outputs.outputFeature0AsTensorBuffer

            var max = getMax(outputFeature0.floatArray)

            text_view.setText(labels?.get(max))

// Releases model resources if no longer used.
            model.close()
        })

    }

    override fun onResume() {
        super.onResume()
        checkCameraPermission()
    }

    private fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this.requireContext(), Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this.requireActivity(),
                arrayOf(Manifest.permission.CAMERA),
                REQUEST_PERMISSION)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == AppCompatActivity.RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                bitmap = data?.extras?.get("data") as Bitmap
                img_view.setImageBitmap(bitmap)

                //bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, bitmap)
            }
            else if (requestCode == REQUEST_PICK_IMAGE) {

                var uri : Uri?= data?.data
                img_view.setImageURI(uri)
                val contentResolver = requireActivity().contentResolver
                bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)

            }
        }

    }

    fun getMax(arr:FloatArray) : Int{
        var ind = 0;
        var min = 0.0f;

        for(i in 0..5)
        {
            if(arr[i] > min)
            {
                min = arr[i]
                ind = i;
            }
        }
        return ind
    }
}