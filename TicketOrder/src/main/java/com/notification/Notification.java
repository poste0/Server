package com.notification;

import java.io.FileNotFoundException;

/**
 * Describes the model of notifications. It has just one method notifyUser that notify the user.
 */
public interface Notification {
	void notifyUser() throws FileNotFoundException;
}