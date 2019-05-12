package com.teamandroid.travelmaker.get

import com.teamandroid.travelmaker.Application
import com.teamandroid.travelmaker.Expert
import com.teamandroid.travelmaker.main.CountryData

data class GetCountryDetail (
        var message : String,
        var country_info : ArrayList<CountryData>,
        var expert_info : ArrayList<Expert>,
        var board_info : ArrayList<Application>
)