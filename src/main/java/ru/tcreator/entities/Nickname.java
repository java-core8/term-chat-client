package ru.tcreator.entities;

import java.util.Scanner;


/**
 * Сущность пользователя
 * содержит в себе ник
 * singleton
 */
public class Nickname {
    protected static Nickname instance;
    protected volatile String nickname;

    public String getNickname() {
        return nickname;
    }

    public static Nickname getInstance() {
        if(instance == null) {
            instance = new Nickname();
        }
        return instance;
    }

    /**
     * Запускает процесс инициализации пользователя
     */
    public boolean getNickInLine() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите никнейм ^_^");
        String nicknameLine = scanner.nextLine();

        if(nicknameLine != null) {
            // убираем пробелы из ника
            nickname = nicknameLine.trim().split(" ")[0];
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
