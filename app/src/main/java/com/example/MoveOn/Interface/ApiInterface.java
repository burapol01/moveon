package com.example.MoveOn.Interface;

import com.example.MoveOn.Model.ApiResponse;
import com.example.MoveOn.Model.BmiModel;
import com.example.MoveOn.Model.DataExercise;
import com.example.MoveOn.Model.DataFoods;
import com.example.MoveOn.Model.DataNews;
import com.example.MoveOn.Model.Datapersonal;
import com.example.MoveOn.Model.Datauser;
import com.example.MoveOn.Model.History;
import com.example.MoveOn.Model.SettingModel;
import com.example.MoveOn.Model.UserModel;
import com.example.MoveOn.Model.Videofile;
import com.example.MoveOn.Model.WorkoutDay;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("get/getLogin.php")
    Call<ApiResponse<UserModel>> login(@Query("email") String email,
                                       @Query("user_password") String userPassword);

    @FormUrlEncoded
    @POST("set/setUser.php")
    Call<ApiResponse<UserModel>> regis(@Field("email") String email,
                                       @Field("user_username") String username,
                                       @Field("user_password") String password,
                                       @Field("picture") String picture);

    @GET("get/getdatapn.php")
    Call<ApiResponse<List<UserModel>>> datapn();

    @GET("set/datapersonal.php")
    Call<ApiResponse<Datapersonal>> data(@Query("name") String name,
                                         @Query("lastname") String lastname);

    @GET("set/setWorkoutDay.php")
    Call<ApiResponse<WorkoutDay>> daydone(@Query("Day") String day,
                                          @Query("category_id") int categoryId,
                                          @Query("user_id") String userId);

    @GET("get/get_profile.php")
    Call<ApiResponse<Datauser>> datauser(@Query("user_id") int userId);

    @GET("get/getProfile.php")
    Call<ApiResponse<List<UserModel>>> getProfile(@Query("user_id") int userId);

    @FormUrlEncoded
    @POST("set/Updateuser.php")
    Call<ApiResponse<UserModel>> updateUser(@Field("user_id") int userId,
                                            @Field("email") String email,
                                            @Field("user_username") String username,
                                            @Field("user_password") String password,
                                            @Field("picture") String picture);

    @FormUrlEncoded
    @POST("set/setDatapersonal.php")
    Call<ApiResponse<Datapersonal>> insertdatapn(@Field("titlename_id") int titleNameId,
                                                 @Field("name") String name,
                                                 @Field("lastname") String lastname,
                                                 @Field("sex_id") int sexId,
                                                 @Field("stat_lev_id") int statusLevelId,
                                                 @Field("weight") String weight,
                                                 @Field("height") String height,
                                                 @Field("birth") String birth,
                                                 @Field("address") String address,
                                                 @Field("user_id") int userId);

    @FormUrlEncoded
    @POST("set/setEditDatapersonal.php")
    Call<ApiResponse<Datapersonal>> Editdatapn(@Field("titlename_id") int titleNameId,
                                               @Field("name") String name,
                                               @Field("lastname") String lastname,
                                               @Field("sex_id") int sexId,
                                               @Field("stat_lev_id") int statusLevelId,
                                               @Field("weight") String weight,
                                               @Field("height") String height,
                                               @Field("birth") String birth,
                                               @Field("address") String address,
                                               @Field("user_id") int userId);

    @FormUrlEncoded
    @POST("set/setDeleteDatapersonal.php")
    Call<ApiResponse<Datapersonal>> Deletedatapn(@Field("user_id") int userId);

    @GET("get/getshowdatapn.php")
    Call<ApiResponse<List<Datapersonal>>> datapnsh(@Query("user_id") int userId);

    @GET("get/getWorkoutday.php")
    Call<ApiResponse<List<History>>> getWorkoutday(@Query("user_id") int userId);

    @GET("get/getWorkoutCalendar.php")
    Call<ApiResponse<List<String>>> getWorkoutCalendar(@Query("user_id") int userId);

    @GET("get/getSettingMode.php")
    Call<ApiResponse<SettingModel>> getSettingMode(@Query("user_id") int userId);

    @FormUrlEncoded
    @POST("set/setSettingMode.php")
    Call<ApiResponse<SettingModel>> saveSettingMode(@Field("user_id") int userId,
                                                    @Field("mode") int mode);

    @GET("get/getDataNews.php")
    Call<ApiResponse<List<DataNews>>> getNews();

    @GET("get/getDatareadnews.php")
    Call<ApiResponse<List<DataNews>>> getReadnews(@Query("datanews_id") String dataNewsId);

    @GET("set/setDatareadnews.php")
    Call<ApiResponse<DataNews>> setReadnews(@Query("datanews_id") String dataNewsId,
                                            @Query("user_id") int userId);

    @FormUrlEncoded
    @POST("set/setBmi.php")
    Call<ApiResponse<BmiModel>> bmiuser(@Field("bmiresult") float bmiValue,
                                        @Field("description_bmi") String bmiResult,
                                        @Field("user_id") int userId);

    @GET("get/getHomepage.php")
    Call<ApiResponse<List<DataNews>>> datahome();

    @GET("get/getFoodsPage.php")
    Call<ApiResponse<List<DataFoods>>> foodspage();

    @GET("set/setDatareadFoods.php")
    Call<ApiResponse<DataFoods>> setReadfoods(@Query("datafood_id") String dataFoodId,
                                              @Query("user_id") int userId);

    @GET("get/getDatareadFoods.php")
    Call<ApiResponse<List<DataFoods>>> getReadfoods(@Query("datafood_id") String dataFoodId);

    @GET("get/getDataEx.php")
    Call<ApiResponse<List<DataExercise>>> dataex();

    @GET("set/setDatareadEx.php")
    Call<ApiResponse<DataExercise>> setReadex(@Query("dataex_id") String dataExId,
                                              @Query("user_id") int userId);

    @GET("get/getDataReadEx.php")
    Call<ApiResponse<List<DataExercise>>> getReadex(@Query("dataex_id") String dataExId);

    @GET("get/getDataYoga.php")
    Call<ApiResponse<List<DataExercise>>> datayoga();

    @GET("get/getVideo.php")
    Call<ApiResponse<List<Videofile>>> videofile();

    @GET("set/setViewVideo.php")
    Call<ApiResponse<Videofile>> setviewvideo(@Query("video_id") String videoId,
                                              @Query("user_id") int userId);

    @GET("get/getDataVideoEx.php")
    Call<ApiResponse<List<Videofile>>> getviewvideex(@Query("video_id") String videoId);

    @GET("get/getVideoPage.php")
    Call<ApiResponse<List<Videofile>>> videopage();
}
