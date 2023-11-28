package com.bidyut.tech.galleryz.log

import com.bidyut.tech.galleryz.di.AppScope
import javax.inject.Inject

@AppScope
class LoggerManager @Inject constructor(
    private val loggers: Set<@JvmSuppressWildcards Logger>
): Logger {
    override fun log(message: String) {
        for (logger in loggers) {
            logger.log(message)
        }
    }
}
