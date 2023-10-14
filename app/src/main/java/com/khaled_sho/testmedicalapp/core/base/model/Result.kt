package com.khaled_sho.testmedicalapp.core.base.model


open class Result<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {
        fun <T> success(data: T?): Result<T> = Result(Status.SUCCESS, data, null)

        fun <T> error(msg: String, data: T? = null): Result<T> = Result(Status.ERROR, data, msg)

        fun <T> authorize(): Result<T> = Result(Status.AUTHORIZATION, null, null)

        fun <T> loading(): Result<T> = Result(Status.LOADING, null, null)
    }
}

enum class Status { LOADING, ERROR, SUCCESS, AUTHORIZATION }


open class ResultModel<T>(status: Status, data: BaseModel<T>?, message: String = "") :
    Result<BaseModel<T>>(status, data, message) {
    constructor(result: Result<BaseModel<T>>) : this(
        result.status,
        result.data,
        result.message ?: ""
    )

    companion object {
        fun <T> success(data: BaseModel<T>?) = ResultModel(Status.SUCCESS, data)

        fun <T> error(msg: String, data: BaseModel<T>? = null) =
            ResultModel(Status.ERROR, data, msg)

        fun <T> authorize() = ResultModel<T>(Status.AUTHORIZATION, null)

        fun <T> loading() = ResultModel<T>(Status.LOADING, null)
    }
}