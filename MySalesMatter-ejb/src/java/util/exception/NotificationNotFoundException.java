/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.exception;

/**
 *
 * @author Yuki
 */
public class NotificationNotFoundException extends Exception {

    public NotificationNotFoundException() {
    }

    public NotificationNotFoundException(String msg) {
        super(msg);
    }
}
