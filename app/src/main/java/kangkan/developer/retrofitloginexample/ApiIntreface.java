package kangkan.developer.retrofitloginexample;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiIntreface {



    @GET("register.php")
    Call<User> PerformRegistration(

            @Query("name") String Name,
            @Query("user_name") String UserName,
            @Query("user_password") String UserPassword
    );


    @GET("login.php")
    Call<User> PerformUserLogin(

            @Query("user_name") String UserName,
            @Query("user_password") String UserPassword);


}
