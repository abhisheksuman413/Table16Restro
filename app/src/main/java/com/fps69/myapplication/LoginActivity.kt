package com.fps69.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.fps69.myapplication.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var auth :FirebaseAuth
    lateinit var binding : ActivityLoginBinding
    private lateinit var googleSigninClint: GoogleSignInClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Initialize Firebase
        auth=Firebase.auth


        //Initialize GoogleSignIn Var
        val GSO = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
        requestIdToken(getString(R.string.default_web_clint_id)).requestEmail().build()
        googleSigninClint= GoogleSignIn.getClient(this@LoginActivity,GSO)



        binding.TVnoaccount.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignActivity::class.java)
            startActivity(intent)

        }
        binding.LoginButtonBTN.setOnClickListener {
//            val intent = Intent(this@LoginActivity, ChooseLocationActivity::class.java)
//            startActivity(intent)

            val Email =binding.EnterEmailLoginET.text.toString().trim()
            val Password = binding.EnterPasswordLoginEt.text.toString().trim()
            if(!(Email.isBlank()||Password.isBlank())){
                loginInAccount(Email,Password)
            }else{
                Toast.makeText(this@LoginActivity," Enter All Details ",Toast.LENGTH_SHORT).show()
            }

        }


        binding.GoogleButtonLoginBTN.setOnClickListener {
            val googleSignInIntent:Intent= googleSigninClint.signInIntent
            launcherr.launch(googleSignInIntent)

        }
    }


    val launcherr = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {result->
        if(result.resultCode==Activity.RESULT_OK){
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            if(task.isSuccessful){
                val account :GoogleSignInAccount? = task.result
                val credential:AuthCredential = GoogleAuthProvider.getCredential(account?.idToken,null)
                auth.signInWithCredential(credential).addOnCompleteListener { authTask->
                    if(authTask.isSuccessful){
                        Toast.makeText(this@LoginActivity,"Login Successful With Google ", Toast.LENGTH_SHORT).show()
                        updateUI()
                        finish()
                    }else{
                        Toast.makeText(this@LoginActivity,"Login Failed With Google ", Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(this@LoginActivity,"Login failed With Google ", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this@LoginActivity,"Login Failed With Google ", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI() {
        startActivity(Intent(this@LoginActivity,MainActivity::class.java))
    }

    private fun loginInAccount(Email: String, Password: String) {
        auth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener { task->
            if(task.isSuccessful){
                Toast.makeText(this@LoginActivity," Login Successful",Toast.LENGTH_SHORT).show()
                updateUI()
            }
            else{
                Toast.makeText(this@LoginActivity," Login Fail ${task.exception}",Toast.LENGTH_SHORT).show()
                Log.d("Login Account", "Login Failed ", task.exception)
            }
        }
    }
}