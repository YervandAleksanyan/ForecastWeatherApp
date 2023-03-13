package com.weatherapi.domain.util

abstract class UseCase<P, R> {
    abstract suspend fun execute(param: P): R
}
