package com.example.saveinfoinfile

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.saveinfoinfile.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val FILENAME = "Note.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_open -> {
                openFile(FILENAME)
                true
            }
            R.id.action_save -> {
                saveFile(FILENAME)
                true
            }
            else -> true
        }
    }

    private fun openFile(fileName: String) {

        val textFromFile =
            File(applicationContext.filesDir, fileName)
                .bufferedReader()
                .use { it.readText(); }
        binding.editText.setText(textFromFile)
    }

    private fun saveFile(fileName: String) {
        applicationContext.openFileOutput(fileName, Context.MODE_PRIVATE).use {
            it.write(binding.editText.text.toString().toByteArray())
        }
        Toast.makeText(applicationContext, "Файл сохранён", Toast.LENGTH_SHORT).show()
    }
}