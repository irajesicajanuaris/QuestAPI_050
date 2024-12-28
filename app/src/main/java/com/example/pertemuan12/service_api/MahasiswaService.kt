package com.example.pertemuan12.service_api

import com.example.pertemuan12.model.Mahasiswa
import com.example.pertemuan12.repository.MahasiswaRepository
import okio.IOException
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface MahasiswaService {

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @POST("insertmahasiswa.php")
    suspend fun insertMahasiswa(@Body mahasiswa: Mahasiswa)

    @GET("bacamahasiswa.php")
    suspend fun getAllMahasiswa():List<Mahasiswa>

    @GET("baca1mahasiswa.php/{nim}")
    suspend fun getMahasiswabyNim(@Query("nim") nim:String):Mahasiswa

    @PUT("editmahasiswa.php/{nim}")
    suspend fun updateMahasiswa(@Query("nim")nim:String, @Body mahasiswa: Mahasiswa)

    @DELETE("hapusmahasiswa.php/{nim}")
    suspend fun deleteMahasiswa(@Query("nim")nim:String) : Response<Void>

    class NetworkKontakRepository(
        private val kontakApiService: MahasiswaService
    ): MahasiswaRepository {
        override suspend fun insertMahasiswa(mahasiswa: Mahasiswa) {
            kontakApiService.insertMahasiswa(mahasiswa)
        }

        override suspend fun updateMahasiswa(nim: String, mahasiswa: Mahasiswa) {
            kontakApiService.updateMahasiswa(nim, mahasiswa)
        }

        override suspend fun deleteMahasiswa(nim: String) {
            try {
                val response = kontakApiService.deleteMahasiswa(nim)
                if (!response.isSuccessful){
                    throw IOException("Failed to delete kontak. HTTP Status code:" +
                    "${response.code()}")
                } else {
                    response.message()
                    println(response.message())
                }
            } catch (e:Exception){
                throw e
            }
        }

        override suspend fun getMahasiswa(): List<Mahasiswa> =
            kontakApiService.getAllMahasiswa()

        override suspend fun getMahasiswabyNim(nim: String): Mahasiswa {
           return kontakApiService.getMahasiswabyNim(nim)
        }
    }
}