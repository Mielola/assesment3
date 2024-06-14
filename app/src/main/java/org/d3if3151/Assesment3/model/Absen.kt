package org.d3if3151.Assesment3.model

import com.squareup.moshi.Json

data class Absen(
    @Json(name = "id")
    val id: Long,
    @Json(name = "namaPegawai")
    val namaPegawai: String,
    @Json(name = "statusPegawai")
    val statusPegawai: String,
    @Json(name = "imageId")
    val imageId: String,
    @Json(name = "mine")
    val mine: String
)
