package com.fps69.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.fps69.myapplication.DataClass.UserData
import com.fps69.myapplication.databinding.ActivityLoginBinding
import com.fps69.myapplication.databinding.ActivitySignBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SignActivity : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    lateinit var binding: ActivitySignBinding
    private lateinit var googleSigninClint: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Initialize Firebase
        auth = FirebaseAuth.getInstance()
        database = Firebase.database.reference

        // Initialize GoogleSignIn Var
        val GSO = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_clint_id)).requestEmail().build()
        googleSigninClint = GoogleSignIn.getClient(this@SignActivity, GSO)

        binding.Tvalreadyhaveaccount.setOnClickListener {
            val intent = Intent(this@SignActivity, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.CreateAccountButoonBTN.setOnClickListener {
            val Name = binding.EnterNameET.text.toString().trim()
            val Email = binding.EnterEmailET.text.toString().trim()
            val Password = binding.EnterPasswordET.text.toString().trim()

            if (!(Name.isBlank() || Email.isBlank() || Password.isBlank())) {
                createAccount(Name, Email, Password)
            } else {
                Toast.makeText(this@SignActivity, " Please Enter All Details ", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        binding.GoogleButtonBTN.setOnClickListener {
            val googleSignInIntent: Intent = googleSigninClint.signInIntent
            launcherr.launch(googleSignInIntent)
        }
    }


    private val launcherr =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            //Check If result is ok or not
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                if (task.isSuccessful) {
                    val account: GoogleSignInAccount? = task.result
                    val credential: AuthCredential =
                        GoogleAuthProvider.getCredential(account?.idToken, null)
                    auth.signInWithCredential(credential).addOnCompleteListener { authTask ->
                        if (authTask.isSuccessful) {
                            Toast.makeText(
                                this@SignActivity,
                                " Login Successful With Google ",
                                Toast.LENGTH_LONG
                            ).show()
                            updateUI()
                        } else {
                            Toast.makeText(
                                this@SignActivity,
                                " Login Failed With Google ",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                } else {
                    Toast.makeText(
                        this@SignActivity,
                        " Login Successful With Google ",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } else {
                Toast.makeText(
                    this@SignActivity,
                    " Login Successful With Google In result  ",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    private fun updateUI() {
        val intent = Intent(this@SignActivity, ChooseLocationActivity::class.java)
        startActivity(intent)
    }

    private fun createAccount(Name: String, Email: String, Password: String) {
        auth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(
                    this@SignActivity,
                    " Account Created Successfully ",
                    Toast.LENGTH_SHORT
                ).show()
                saveUserData(Name, Email, Password)
                startActivity(Intent(this@SignActivity, LoginActivity::class.java))
                finish()
            } else {
                Toast.makeText(this@SignActivity, " Account creation Fail ", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun saveUserData(Name: String, Email: String, Password: String) {

        val user = UserData(Name, Email, Password)
        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        // Save data to firebase
        database.child("customer_user").child(userId).setValue(user)
    }
}