package com.coop.pinchangewidget.pinchange.model

import com.google.gson.annotations.SerializedName

class WrapKeyResponse {
/*
    {
        "data": {
        "wrappedKey": "KntbzwJjpY3ye-qb_lCWh3BOZN6a2Q8Kg8dNXJxDio94omAGzExEVtC-XCyNTUbk_IvBdfCyEjdboHnwvTEqaZlDBC6O9VNUHgRSi66zYKbDLU1bKO0XRCUT8KLqQqj4aLWE3IZsZxK0mA8HJY-0R3bwdN0jY7UrmGGYjs07Jzyd2qUykUpI66SY8fp3t8UuuBqDTNzpvxLsuYK-3bOtY0HQz2SprcNS1acRIth6lzXwrUd5iB1lI9MOELvmnUMCWo_OKO0LIgHne7kaIrb1O-bQv4UPThEMYn0tgaSUMbBKIYSzJ90XowqT9lh38jgPhZ03NW-Q1cPTGLgWorXTWw"
    },
        "message": "Successfully wrapped PIN key",
        "returnCode": 200,
        "success": true
    }
*/
    @SerializedName("success")
    var isSuccess: Boolean? = null

    @SerializedName("message")
    var message: String? = null

    @SerializedName("returnCode")
    var returnCode: Integer? = null

    @SerializedName("data")
    var data: WrapperData? = null

    private var error: Throwable ? = null

    constructor(err: Throwable){
        error = err
    }

    fun getError():Throwable?{
        return  error
    }

    fun setError(err:Throwable){
        error = err
    }
}