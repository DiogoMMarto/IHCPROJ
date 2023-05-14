package com.example.test

import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.util.concurrent.TimeUnit

class ServerP(private val client: OkHttpClient = OkHttpClient.Builder().readTimeout(0, TimeUnit.SECONDS).connectTimeout(0, TimeUnit.SECONDS).build()) {

    public fun chatWithEmphaty(input:String): String{
        val request = Request.Builder()
            .url("http://192.168.0.105:5000/EmphatyChat")
            .post(FormBody.Builder()
                .add("user_text",input)
                .build())
            .build()
        val response: Response = client.newCall(request).execute()
        return response.body?.string() ?: ""
    }
}