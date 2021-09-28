package com.nikhil.shaadi_demo_app

import android.os.Parcelable


/***********************************************
 *     Created By Nikhil.Borkar on 27/09/21           *
 ***********************************************/

enum class StatusEnum(val type: Int) {
    IDLE(0),
    ACCEPTED(1),
    REJECTED(2)
}