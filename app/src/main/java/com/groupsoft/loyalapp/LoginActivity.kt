package com.groupsoft.loyalapp

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.content.pm.PackageManager
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.app.LoaderManager.LoaderCallbacks
import android.content.CursorLoader
import android.content.Loader
import android.database.Cursor
import android.net.Uri
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.TextView

import android.Manifest.permission.READ_CONTACTS
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.GoogleApiClient

import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject
import java.util.*

class LoginActivity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener {

    private var callbackManager: CallbackManager? = null
    private val RC_SIGN_IN = 9001

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val gso: GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        val googleApiClient = GoogleApiClient
                .Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()

        var btnLogin = findViewById<View>(R.id.sign_in_button)
        btnLogin.setOnClickListener {

            val intent : Intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient)
            startActivityForResult(intent, RC_SIGN_IN)
        }


        var btnLoginFacebook = findViewById<LinearLayout>(R.id.facebook_in_button)

        btnLoginFacebook.setOnClickListener(View.OnClickListener {
            // Login
            callbackManager = CallbackManager.Factory.create()
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"))
            LoginManager.getInstance().registerCallback(callbackManager,
                    object : FacebookCallback<LoginResult> {
                        override fun onSuccess(loginResult: LoginResult) {
                            Log.d("MainActivity", "Facebook token: " + loginResult.accessToken.token)
                            getUserDetails(loginResult)

                        }

                        override fun onCancel() {
                            Log.d("MainActivity", "Facebook onCancel.")

                        }

                        override fun onError(error: FacebookException) {
                            Log.d("MainActivity", "Facebook onError.")

                        }
                    })
        })
    }



    private fun getUserDetails(loginresult: LoginResult){

        val request : GraphRequest = GraphRequest.newMeRequest(loginresult.accessToken, GraphRequest.GraphJSONObjectCallback {
           obj: JSONObject, response: GraphResponse ->

            println(obj.toString())
            val image_url : String = "http://graph.facebook.com/" + loginresult.accessToken.userId + "/picture?type=large"
            Glide.with(applicationContext)
                    .load(image_url)
                    .into(imageView)

        })
        request.executeAsync()



    }


 /*
   val request: GraphRequest = GraphRequest.newMeRequest(result.accessToken, GraphRequest.GraphJSONObjectCallback {
        obj: JSONObject, response: GraphResponse ->
        println(obj.toString())
        println(obj.getString("email"))})
    request.executeAsync()


 protected void getUserDetails(LoginResult loginResult) {
        GraphRequest data_request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(
                    JSONObject json_object,
                    GraphResponse response) {
                Intent intent = new Intent(MainActivity.this, UserProfile.class);
                intent.putExtra("userProfile", json_object.toString());
                startActivity(intent);
            }

        });
        Bundle permission_param = new Bundle();
        permission_param.putString("fields", "id,name,email,picture.width(120).height(120)");
        data_request.setParameters(permission_param);
        data_request.executeAsync();

    }
 */


    override fun onStart() {
        super.onStart()
        val acct = GoogleSignIn.getLastSignedInAccount(applicationContext)
        if (acct != null) {
            val menuActivity = Intent(this, MainActivity::class.java)
            menuActivity.putExtra("Account",acct)
            menuActivity.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK and Intent.FLAG_ACTIVITY_NO_ANIMATION
            startActivity(menuActivity)
            finish()
        }
    }

    override fun onConnectionFailed(p0: ConnectionResult) {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val result: GoogleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            handleSignInResult(result)
        }

        callbackManager?.onActivityResult(requestCode, resultCode, data)
    }

    private fun handleSignInResult(signInResult: GoogleSignInResult) {
        if (signInResult.isSuccess) {
            // Authenticated
            val account: GoogleSignInAccount? = signInResult.signInAccount
            val menuActivity = Intent(this, MainActivity::class.java)
            menuActivity.putExtra("Account",account)
            menuActivity.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(menuActivity)
            finish()

        } else {
            // Failed
        }
    }

}
