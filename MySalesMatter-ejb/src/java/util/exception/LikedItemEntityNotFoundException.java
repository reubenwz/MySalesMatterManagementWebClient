/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.exception;

/**
 *
 * @author rtan3
 */
public class LikedItemEntityNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>LikedItemEntityNotFoundException</code>
     * without detail message.
     */
    public LikedItemEntityNotFoundException() {
    }

    /**
     * Constructs an instance of <code>LikedItemEntityNotFoundException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public LikedItemEntityNotFoundException(String msg) {
        super(msg);
    }
}
