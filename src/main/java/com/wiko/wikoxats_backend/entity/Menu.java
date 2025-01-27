package com.wiko.wikoxats_backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    private int menuId;
    private int parentSeq;
    private String menu;
    private String url;
    private String title;
    private String type;
    private int level;
    private boolean hasChild;
}
