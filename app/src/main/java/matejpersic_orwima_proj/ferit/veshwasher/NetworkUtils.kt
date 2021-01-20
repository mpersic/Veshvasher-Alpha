package matejpersic_orwima_proj.ferit.veshwasher

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkUtils {
    private lateinit var apiInterface:ApiInterface
    private val BASE_API = "https://makeup-api.herokuapp.com/api/v1/"
    fun getApiInterface():ApiInterface {
        if (apiInterface == null)
        {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            apiInterface = retrofit.create(ApiInterface::class.java)
        }
        return apiInterface
    }
}