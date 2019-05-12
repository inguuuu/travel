package com.teamandroid.travelmaker.main

import android.graphics.Bitmap
import android.os.Parcel
import android.os.Parcelable

data class Country(
        var countryData : CountryData,
        var thumbnailbitmap : Bitmap?,
        var detailBitmap : Bitmap?
)