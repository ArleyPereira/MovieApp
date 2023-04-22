package br.com.hellodev.movieapp.presenter.auth.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.hellodev.movieapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
    }

}