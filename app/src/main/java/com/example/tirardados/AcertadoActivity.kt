package com.example.tirardados

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AcertadoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.acertado_activity)

        val resultado = intent.getIntExtra("resultado", 0)

        val tvMensaje = findViewById<TextView>(R.id.tvMensaje)

        tvMensaje.text = "¡ENHORABUENA!\n\n¡Has acertado!\n\nEl resultado es $resultado"
    }
}
