package com.teamandroid.travelmaker.travelplanread

import android.os.Parcel
import android.os.Parcelable

class GetTrans (
        var trans_idx : Int,
        var trans_name : Int,
        var trans_budget : Int,
        var trans_day : Int,
        var trans_dep_time : String,
        var trans_arr_time : String,
        var trans_content : String?,
        var board_idx : Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt())
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(trans_idx)
        parcel.writeInt(trans_name)
        parcel.writeInt(trans_budget)
        parcel.writeInt(trans_day)
        parcel.writeString(trans_dep_time)
        parcel.writeString(trans_arr_time)
        parcel.writeString(trans_content)
        parcel.writeInt(board_idx)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GetTrans> {
        override fun createFromParcel(parcel: Parcel): GetTrans {
            return GetTrans(parcel)
        }

        override fun newArray(size: Int): Array<GetTrans?> {
            return arrayOfNulls(size)
        }
    }
}