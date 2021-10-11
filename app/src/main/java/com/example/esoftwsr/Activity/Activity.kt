package com.example.esoftwsr.Activity

import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
val  connect = URL("http://cfif31.ru:8080/events?agent_id=7&from=1&to=999999999999999999").openConnection() as HttpURLConnection
class Activity() {
    lateinit var date: Date
    var lasting: Int? = null
    lateinit var activityType: String
    var comment: String? = null

    constructor(item: JSONObject) : this() {
        date = SimpleDateFormat("yyyy-MM-dd", Locale.UK).parse(item.getString("datetime"))
        lasting = item.getInt("duration")
        activityType = item.getString("type")
        comment = item.getString("comment")

    }
    fun SendActivity(activity: Activity){
        connect.requestMethod ="POST"
        connect.setRequestProperty("Content-Type", "application/json; charset=UTF-8")
        val writer = BufferedWriter(OutputStreamWriter(connect.outputStream, "UTF-8"))
        writer.write(getPalmString(activity))
        writer.flush()
        connect.connect()
    }

    fun getPalmString(activity:Activity):String{
        return "agent_id=7&datetime=${activity.date}&type=${activity.activityType}&duration=${activity.lasting}&comment=${activity.comment}"
    }
    companion object {
        fun GetActivity(): MutableList<Activity> {
            var activities= mutableListOf<Activity>()
            connect.requestMethod ="GET"
            val array =JSONArray(connect.inputStream.bufferedReader().readText())
            for( i in 0 until array.length()){
            activities.add(Activity(array.getJSONObject(i)))
            }
return activities
        }

    }
}
