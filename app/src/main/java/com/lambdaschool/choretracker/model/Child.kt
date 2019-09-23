package com.lambdaschool.choretracker.model

object ChildList {
    val childList = mutableListOf<Child>()
}

class Child (
    val name: String,
    val labelColorHex: String, // "#FFFFFF"
    val earnedPoints: Int,
    val photo_id: String, // "${child_id}_${timestamp}" where timestamp = 'MM-DD-YYYY_HHMMSS' in 24h format
    val child_id: Int
)
