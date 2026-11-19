package com.example.tirardados
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Declaramos los elementos de la pantalla
    private lateinit var etNumero: EditText
    private lateinit var btnLanzar: Button
    private lateinit var tvResultado: TextView

    // Controlamos si se está realizando una tirada
    private var jugando = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Cargamos el diseño XML
        setContentView(R.layout.activity_main)

        // Conectamos los elementos del XML
        etNumero = findViewById(R.id.etNumero)
        btnLanzar = findViewById(R.id.btnLanzar)
        tvResultado = findViewById(R.id.tvResultado)

        // Programamos el botón
        btnLanzar.setOnClickListener {
            lanzarDados()
        }
    }

    // Función para lanzar los tres dados
    private fun lanzarDados() {

        if (jugando) {
            return
        }

        // Recogemos el número introducido
        val numeroTexto = etNumero.text.toString()

        if (numeroTexto.isEmpty()) {
            etNumero.error = "Introduce un número"
            return
        }

        val numeroUsuario = numeroTexto.toIntOrNull()

        if (numeroUsuario == null || numeroUsuario !in 3..18) {
            etNumero.error = "Introduce un número entre 3 y 18"
            return
        }

        // Bloqueamos el botón
        jugando = true
        btnLanzar.isEnabled = false

        tvResultado.text = "Lanzando los dados..."

        // Generamos tres números aleatorios
        val dado1 = (1..6).random()
        val dado2 = (1..6).random()
        val dado3 = (1..6).random()

        // Sumamos los tres dados
        val resultado = dado1 + dado2 + dado3

        // Esperamos dos segundos
        btnLanzar.postDelayed({

            tvResultado.text = "Resultado: $resultado"

            // Comprobamos si el usuario ha acertado
            if (resultado == numeroUsuario) {

                val intent = Intent(this, AcertadoActivity::class.java)
                intent.putExtra("resultado", resultado)
                startActivity(intent)
            }

            // Volvemos a permitir jugar
            jugando = false
            btnLanzar.isEnabled = true

        }, 2000)
    }
}