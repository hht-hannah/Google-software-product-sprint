package com.google.sps.data;

/** An item on a todo list. */
public final class Comment {

  private final long id;
  private final String comment;
  private final String user;
  private final long timestamp;

  public Comment(long id, String comment, String user, long timestamp) {
    this.id = id;
    this.comment = comment;
    this.user = user;
    this.timestamp = timestamp;
  }
}