package com.example.pertemuan12.repository

import com.example.pertemuan12.model.Mahasiswa

interface MahasiswaRepository {
    suspend fun insertMahasiswa(mahasiswa: Mahasiswa)

    suspend fun getMahasiswa(): List<Mahasiswa>

    suspend fun updateMahasiswa(nim: String, mahasiswa: Mahasiswa)

    suspend fun deleteMahasiswa(nim: String)

    suspend fun getMahasiswabyNim(nim: String): Mahasiswa


}