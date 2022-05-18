package com.example.mdthomeassignment.data.network
import com.example.mdthomeassignment.data.model.PayeeResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface TransferApi {

    @GET("/payees")
    suspend fun getPayees(): PayeeResponse

    @FormUrlEncoded
    @POST("/transfer")
    suspend  fun transfer(
        @Field("receipientAccountNo") receipientAccountNo: String,
        @Field("amount") amount: Int,
        @Field("description") description: String
    ): Unit
}