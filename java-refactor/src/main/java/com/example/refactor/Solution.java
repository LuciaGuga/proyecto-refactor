package com.example.refactor;

import com.example.refactor.service.SongProcessor;

public class Solution {
    public static void main(String... args) {
        var songProcessor = new SongProcessor();  //Uso de inferencia de variables
        songProcessor.processSongs();
    }
}
