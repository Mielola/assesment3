package org.d3if3151.Assesment3.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.d3if3151.Assesment3.model.Absen
import org.d3if3151.Assesment3.model.OpStatus
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

private const val BASE_URL = "https://unspoken.my.id/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()
interface absenApiServices {
    @GET("pegawai.php")
    suspend fun getAbsen(
        @Header("Authorization") userId: String
    ): List<Absen>

    @Multipart
    @POST("pegawai.php")
    suspend fun postAbsen(
        @Header("Authorization") userId: String,
        @Part("namaPegawai") namaPegawai: RequestBody,
        @Part("statusPegawai") statusPegawai: RequestBody,
        @Part image: MultipartBody.Part
    ): OpStatus

    @DELETE("pegawai.php")
    suspend fun deleteAbsen(
        @Header("Authorization") userId: String,
        @Query("id") id: Long
    ): OpStatus
}

object absenApi {
    val service: absenApiServices by lazy {
        retrofit.create(absenApiServices::class.java)
    }

    fun getAbsenUrl(imageId: String): String{
        return "${BASE_URL}image.php?id=$imageId"
    }
}
enum class ApiStatus { LOADING, SUCCESS , FAILED}