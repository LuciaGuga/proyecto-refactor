package com.example.refactor;

import com.example.refactor.service.SongProcessor;

public class Solution {
    public static void main(String... args) {
        /* Si bien ya está implementado, en esta parte
         * identificamos el patrón de diseño estructural Facade
         * que se aplica al proporcionar una interfaz simplificada
         * como puerta de entrada a toda la lógica, mediante el
         * método processSongs()
         */
        SongProcessor songProcessor = new SongProcessor();
        songProcessor.processSongs();
    }
}
