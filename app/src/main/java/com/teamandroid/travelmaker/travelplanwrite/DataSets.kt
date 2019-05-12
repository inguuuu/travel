package com.teamandroid.travelmaker

import java.util.ArrayList
import java.util.HashMap

class DataSets {
    internal var hashTitleMap: HashMap<String, ArrayList<String>> = HashMap()

    constructor(s: String, title: String) {
        if (hashTitleMap.containsKey(s)) {
            var temp = ArrayList<String>()
            temp = hashTitleMap[s]!!
            temp.add(title)
            hashTitleMap[s] = temp
        } else {
            val temp = ArrayList<String>()
            temp.add(title)
            hashTitleMap[s] = temp
        }
    }

    constructor() {

    }
}