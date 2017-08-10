package com.ravikant.todo_mvp.interfaces;

import com.ravikant.todo_mvp.models.Board;

import java.util.List;

/**
 * Created by ravikant on 10/8/17.
 **/

public interface DashboardView {
    void onGetAllBoards(List<Board> boards);
    void onErrorShow(String errorMessage);
}
