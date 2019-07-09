package com.harris.ta
import com.harris.ta.BuildConfig
import com.harris.ta.RootDataModel
import retrofit.http.GET
import retrofit.http.Query
import rx.Observable

interface ApiService {

    //    http://informatika.upgris.ac.id/mobile/data/index.php/api/example/detil_jadwal?key=3fb468b6e1a339f85d398d15be4b76&npm=16670085
    @GET("detil_jadwal?key=" + BuildConfig.API_KEY + "&npm=" + BuildConfig.NPM)
    fun getJadwalKuliah(
    ): Observable<RootDataModel>

    @GET("detil_pribadi?key=" + BuildConfig.API_KEY + "&npm=" + BuildConfig.NPM)
    fun getProfil(
    ): Observable<RootDataModel>
}