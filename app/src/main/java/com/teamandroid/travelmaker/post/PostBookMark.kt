package com.teamandroid.travelmaker.post

import com.teamandroid.travelmaker.CountryIdx
import com.teamandroid.travelmaker.Expert

data class PostBookMark (
        var message : String,
        var country : ArrayList<CountryIdx>,
        var expert : ArrayList<Expert>
)