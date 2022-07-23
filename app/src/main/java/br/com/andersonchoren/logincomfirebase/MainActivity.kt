package br.com.andersonchoren.logincomfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.andersonchoren.logincomfirebase.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding
    private lateinit var user:FirebaseUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        binding.btnRegister.setOnClickListener {
            view ->
            val email = binding.edtLogin.text.toString()
            val password = binding.edtPassword.text.toString()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this,"Usuário cadastrado com sucesso!!!",Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this,"Não foi possível registrar o usuário!!!",Toast.LENGTH_SHORT).show()
                    }
                }
        }

        binding.btnSign.setOnClickListener {
                view ->
            val email = binding.edtLogin.text.toString()
            val password = binding.edtPassword.text.toString()
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        user = auth.currentUser!!
                        Toast.makeText(this,"Bem-vindo ao sistema!!!",Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this,"Não foi possível localizar o usuário!!!",Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            Toast.makeText(this,"Você já está autenticado!!!",Toast.LENGTH_SHORT).show()
        }
    }
}