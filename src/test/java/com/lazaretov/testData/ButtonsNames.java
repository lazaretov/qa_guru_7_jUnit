package com.lazaretov.testData;

public enum ButtonsNames {

    POPULAR("Популярное"),
    NEW("Свежее"),
    COMPANY("Компании"),
    JOB("Вакансии"),
    EVENT("Мероприятия"),
    SUBS("Подписки");

    private final String buttonName;
    ButtonsNames(String buttonName) {
        this.buttonName = buttonName;
    }
    public String getButtonName() {
        return buttonName;
    }
}

