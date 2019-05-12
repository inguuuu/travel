package com.teamandroid.travelmaker.travelplanwrite.transdata

import java.io.File

data class TmPost (
        var board_idx : Int,
        var plan : ArrayList<Plan>,
        var place_img : ArrayList<File>
)