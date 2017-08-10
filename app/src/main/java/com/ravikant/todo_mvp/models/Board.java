package com.ravikant.todo_mvp.models;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Ravikant on 3/8/17.
 **/

@IgnoreExtraProperties
public class Board {

    public String boardName;
    public String boardId;
    public String createdDate;
    public String modifiedDate;
    public boolean isActive;

    public Board(String boardName, String createdDate, String modifiedDate, boolean isActive){
        this.boardName = boardName;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.isActive = isActive;
    }

    public Board(){

    }
}
