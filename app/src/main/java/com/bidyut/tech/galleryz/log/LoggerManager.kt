package com.bidyut.tech.galleryz.log

import me.tatarka.inject.annotations.Inject

class LoggerManager @Inject constructor(
    private val loggers: Set<Logger>
): Logger {
    override fun log(message: String) {
        for (logger in loggers) {
            logger.log(message)
        }
    }
}
