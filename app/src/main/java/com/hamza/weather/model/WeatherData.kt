package com.hamza.weather.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable



data class WeatherData(

        @SerializedName("dt")  var dt: Long,
        @SerializedName("main") var main: Main,
        @SerializedName("weather") var weather: List<Weather>,
        @SerializedName("clouds") var clouds: Clouds,
        @SerializedName("wind") var wind: Wind,
        @SerializedName("visibility") var visibility: Long,
        @SerializedName("pop") var pop: Double,
        @SerializedName("sys") var sys: Sys,
        @SerializedName("dt_txt") var dtTxt: String
)

data class Clouds(

        @SerializedName("all") var all: Long
)


@Entity(indices = [Index(value = ["uid"], unique = true)])
data class Main(

        @PrimaryKey (autoGenerate = true) val uid: Int,
        @SerializedName("temp") @ColumnInfo(name = "temp") var temp: Double,
        @SerializedName("feels_like") @ColumnInfo(name = "feels_like") var feelsLike: Double,
        @SerializedName("temp_min") @ColumnInfo(name = "temp_min") var tempMin: Double,
        @SerializedName("temp_max") @ColumnInfo(name = "temp_max") var tempMax: Double,
        @SerializedName("pressure") @ColumnInfo(name = "pressure") var pressure: Long,
        @SerializedName("sea_level") @ColumnInfo(name = "sea_level") var seaLevel: Long,
        @SerializedName("grnd_level") @ColumnInfo(name = "grnd_level") var grndLevel: Long,
        @SerializedName("humidity") @ColumnInfo(name = "humidity") var humidity: Long,
        @SerializedName("temp_kf") @ColumnInfo(name = "temp_kf") var tempKf: Double
)

data class Sys(

        @SerializedName("pod") var pod: String
)

data class Weather(

        @SerializedName("id") var id: Long,
        @SerializedName("main") var main: String,
        @SerializedName("description") var description: String,
        @SerializedName("icon") var icon: String
)

data class Wind(

        @SerializedName("speed") var speed: Double,
        @SerializedName("deg") var deg: Long
)


