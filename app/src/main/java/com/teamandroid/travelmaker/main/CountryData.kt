package com.teamandroid.travelmaker.main

import android.os.Parcel
import android.os.Parcelable

class CountryData (
        var country_idx : Int,
        var country_name : String,
        var country_continent : String?,
        var country_currency : String?,
        var country_exchange : Double?,
        var country_time_difference : Int?,
        var country_climate : Int?,
        var country_language : String?,
        var country_img : String?) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Double::class.java.classLoader) as? Double,
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(country_idx)
        parcel.writeString(country_name)
        parcel.writeString(country_continent)
        parcel.writeString(country_currency)
        parcel.writeValue(country_exchange)
        parcel.writeValue(country_time_difference)
        parcel.writeValue(country_climate)
        parcel.writeString(country_language)
        parcel.writeString(country_img)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CountryData> {
        override fun createFromParcel(parcel: Parcel): CountryData {
            return CountryData(parcel)
        }

        override fun newArray(size: Int): Array<CountryData?> {
            return arrayOfNulls(size)
        }
    }
}