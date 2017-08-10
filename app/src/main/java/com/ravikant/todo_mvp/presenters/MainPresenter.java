package com.ravikant.todo_mvp.presenters;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.ravikant.todo_mvp.interfaces.DashboardView;
import com.ravikant.todo_mvp.models.Board;
import com.ravikant.todo_mvp.views.ToDoApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ravikant on 9/8/17.
 **/

public class MainPresenter {
    private DatabaseReference boardDbReference;
    private DashboardView dashboardView;

    public MainPresenter(DashboardView dashboardView){
        this.dashboardView = dashboardView;
        boardDbReference = ToDoApplication.getBoardsTableDbReference();
    }

    public void addBoard(final String userId, final Board newBoard){

        // Write a message to the database
       /* FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");*/

        final String boardId = boardDbReference.push().getKey();
        boardDbReference.child(userId).child(boardId).setValue(newBoard);

       /* boardDbReference.child(AppConfig.TABLE_BOARDS).child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Board board = dataSnapshot.getValue(Board.class);
                if (board!=null){
                    // boardDbReference.child(boardId).setValue(newBoard);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                databaseError.getMessage();
            }
        });*/
       /* boardDbReference.setValue(newBoard, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                databaseError.getMessage();
            }
        });*/

        /*boardDbReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Board board = dataSnapshot.getValue(Board.class);
                if (board!=null){
                    boardDbReference.child(userId).child(boardId).setValue(newBoard);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                databaseError.getMessage();
            }
        });*/
    }

    public void getAllBoards(final String userId){
        final List<Board> boards = new ArrayList<>();
        boardDbReference.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boards.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Board board = snapshot.getValue(Board.class);
                    if (board!=null){
                        boards.add(board);
                    }
                }
                dashboardView.onGetAllBoards(boards);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                dashboardView.onErrorShow(databaseError.getMessage());
            }
        });
    }
}
