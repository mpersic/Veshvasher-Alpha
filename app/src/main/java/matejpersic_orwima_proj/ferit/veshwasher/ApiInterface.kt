package matejpersic_orwima_proj.ferit.veshwasher

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("employees")
    fun getEmployees(): Call<List<Employee>>
}