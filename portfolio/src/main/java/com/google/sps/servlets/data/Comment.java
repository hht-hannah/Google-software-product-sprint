package com.google.sps.data;

/** An item of comment entry. */
public final class Comment {

    private final long id;
    private final String comment;
    private final String user;
    private final long timestamp;
    private final String email;

    /**
    * Thia is the constructor for Comment.
    * @param id The id of the comment Entity.
    * @param comment The content of user comment.
    * @param user Username.
    * @param timestamp The timestamp of the Entity.
    * @param email UserEmail.
    */ 
    public Comment(long id, String comment, String user, long timestamp, String email) {
        this.id = id;
        this.comment = comment;
        this.user = user;
        this.timestamp = timestamp;
        this.email = email;
    }
}