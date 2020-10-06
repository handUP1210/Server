package com.dev.handup.dto.users;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class UsersListResultData<T> {
    private T data;

    public UsersListResultData(T data) {
        this.data = data;
    }
}
