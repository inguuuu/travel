package com.teamandroid.travelmaker.travelplanread

import android.os.Parcel
import android.os.Parcelable

class GetPlace (
        var place_idx : Int,
        var place_day : Int,
        var place_count : Int,
        var place_name : String,
        var place_comment : String,
        var place_latitude : Double,
        var place_longitude : Double,
        var place_budget : Int,
        var place_budget_comment : String?,
        var place_img : String?,
        var board_idx : Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readDouble(),
            parcel.readDouble(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(place_idx)
        parcel.writeInt(place_day)
        parcel.writeInt(place_count)
        parcel.writeString(place_name)
        parcel.writeString(place_comment)
        parcel.writeDouble(place_latitude)
        parcel.writeDouble(place_longitude)
        parcel.writeInt(place_budget)
        parcel.writeString(place_budget_comment)
        parcel.writeString(place_img)
        parcel.writeInt(board_idx)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GetPlace> {
        override fun createFromParcel(parcel: Parcel): GetPlace {
            return GetPlace(parcel)
        }

        override fun newArray(size: Int): Array<GetPlace?> {
            return arrayOfNulls(size)
        }
    }
}