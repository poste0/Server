package com.notification;

import java.io.FileNotFoundException

/**
 * Describes the model of notifications. It has just one method notifyUser that notify the user.
 */
interface Notification {
    @Throws(FileNotFoundException::class)
    fun notifyUser()
}