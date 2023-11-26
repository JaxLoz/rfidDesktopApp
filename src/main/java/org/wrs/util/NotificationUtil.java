package org.wrs.util;

import raven.toast.Notifications;

/**
 *
 * @author C.Mateo
 */
public class NotificationUtil {

    public static void show(Notifications.Type type, String message) {
        Notifications
                .getInstance()
                .show(type,Notifications.Location.TOP_CENTER,message);
    }
}
