package ir.hoseinahmadi.frenchpastry.data.remote

import ir.hoseinahmadi.frenchpastry.data.model.addres.addredResponse
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface AddressApiInterface {

    @GET("v1/user/address")
    suspend fun getAllAddress(
        @Header("app-device-uid") deviceUid: String,
        @Header("app-public-key") publicKey: String,
        @Header("app-api-key") apiKey: String,
    ): Response<addredResponse>

    @FormUrlEncoded
    @POST("v1/user/address")
    suspend fun addAddress(
        @Header("app-device-uid") deviceUid: String,
        @Header("app-public-key") publicKey: String,
        @Header("app-api-key") apiKey: String,
        @Field("address") address: String,
        @Field("receiver") receiver: String,
        @Field("phone") phone: String,
    ): Response<addredResponse>

    @DELETE("v1/user/address/{id}")
    suspend fun deleteAddress(
        @Header("app-device-uid") deviceUid: String,
        @Header("app-public-key") publicKey: String,
        @Header("app-api-key") apiKey: String,
        @Path("id") id: String
    ):Response<addredResponse>

    @FormUrlEncoded
    @POST("v1/user/address/{id}")
    suspend fun editAddress(
        @Header("app-device-uid") deviceUid: String,
        @Header("app-public-key") publicKey: String,
        @Header("app-api-key") apiKey: String,
        @Path("id")id:String,
        @Field("address") address: String,
        @Field("receiver") receiver: String,
        @Field("phone") phone: String,
    ): Response<addredResponse>
}