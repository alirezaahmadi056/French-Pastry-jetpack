package ir.hoseinahmadi.frenchpastry.data.remote

import ir.hoseinahmadi.frenchpastry.data.model.userInfo.UserInfoResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface InfoUserInterFace {


    @GET("v1/auth/heartbeat")
    suspend fun getUserInfo(
        @Header("app-device-uid") deviceUid: String,
        @Header("app-public-key") publicKey: String,
        @Header("app-api-key") apiKey: String,
    ): Response<UserInfoResponse>

    @FormUrlEncoded
    @POST("v1/user/profile")
    suspend fun sendUserInfo(
        @Header("app-device-uid") deviceUid: String,
        @Header("app-public-key") publicKey: String,
        @Header("app-api-key") apiKey: String,
        @Field("fullname") fullName: String,
        @Field("national_code") nationalCode: String,
        @Field("email") email: String,
        @Field("sex") sex: String,
        @Field("birthdate") birthdate: String,
    ): Response<UserInfoResponse>

}