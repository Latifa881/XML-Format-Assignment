package com.example.xmlformatassignment

import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.InputStream

class MyXmlPullParserHandler {
    private val students_Details = ArrayList<studentsDetails>()
    private var text: String? = null

    private var name = ""
    private var ID = 0
    private var marks=0
    fun parse(inputStream: InputStream): List<studentsDetails> {
        try {
            val factory = XmlPullParserFactory.newInstance()
            val parser = factory.newPullParser()
            parser.setInput(inputStream, null)
            var eventType = parser.eventType
            while (eventType != XmlPullParser.END_DOCUMENT) {
                val tagName = parser.name
                when (eventType) {
                    XmlPullParser.TEXT -> text = parser.text
                    XmlPullParser.END_TAG -> when {
                        tagName.equals("id", ignoreCase = true) -> {
                            ID = text!!.toInt()
                        }
                        tagName.equals("name", ignoreCase = true) -> {
                            name = text.toString()
                        }
                        tagName.equals("marks", ignoreCase = true) -> {
                            marks = text!!.toInt()
                        }

                        else -> students_Details.add(studentsDetails( name,marks))
                    }

                    else -> {
                    }
                }
                eventType = parser.next()
            }

        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return students_Details
    }

}
