package com.wellmember.app.Auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.core.text.isDigitsOnly
import androidx.core.widget.addTextChangedListener
import com.wellmember.app.R

class LoginActivity : AppCompatActivity() {

    lateinit var accountText: EditText
    lateinit var passwordText: EditText
    lateinit var loginBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // init
        findViews();
        var account: String
        var password: String


        // account validation
        accountText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {

                var accountChecker = accountText.text.toString().uppercase()

                if(accountChecker.isEmpty() && "".equals(accountChecker)){

                    //accountText.setError("請輸入會員編號", null)

                }
                else{

                    if((accountChecker.length < 2 && accountChecker.length >= 0) || !accountChecker[0].equals('T') || !accountChecker[1].equals('W')) {

//                        accountText.setError("開頭請輸入TW", null)

                    }else if(accountChecker.length < 9 && accountChecker.length > 2){

//                        accountText.setError("您的會員編號", null)

                    }

                    account = accountChecker

                }
            }
        })

        // password validation
        passwordText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {

                var passwordChecker = passwordText.text.toString().uppercase()

                if(passwordChecker.isEmpty() && "".equals(passwordChecker)){

                    //accountText.setError("請輸入密碼")

                }
                else{

                    if(passwordChecker.length < 10 && passwordChecker.length > 0) {

//                        passwordText.setError("您的身分證字號", null)

                    }

                    password = passwordChecker

                }
            }
        })

        //

    }

    private fun findViews(){

        accountText = findViewById(R.id.account)
        passwordText = findViewById(R.id.password)
        loginBtn = findViewById(R.id.login_btn)

    }

}