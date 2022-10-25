package com.lazaretov;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.conditions.CssClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.CollectionCondition.textsInAnyOrder;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class WebTests {

    @BeforeAll
    static void configure(){
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
    }

    @Disabled
    @Test
    void vcSearchTest() {
        open ("https://vc.ru");
        $(".v-text-input__input").setValue("Илон Маск").pressEnter();
        $$(".content-container").first().shouldHave(text("Илон Маск"));
    }

    @Disabled
    @ValueSource(strings = {"Илон Маск", "Ozon"})
    @ParameterizedTest(name = "Соответствие поисковых результатов для запроса {0}")
    void vcParamSearchTest(String testData) {
        open ("https://vc.ru");
        $(".v-text-input__input").setValue(testData).pressEnter();
        $$(".content-container").first().shouldHave(text(testData));
    }

    @Disabled
    @CsvSource(value = {
            "Илон Маск | должен будет оплатить $1 млрд, если сделка не состоится. В мае",
            "Ozon | оказалось, в Астрахани есть несколько производств: завод резиновой обуви"},
            delimiter = '|'
    )
    @ParameterizedTest(name = "Соответствие поисковых результатов для запроса {0}")
    void vcParamSearchResultTest(String searchQuery, String expectedText) {
        open ("https://vc.ru");
        $(".v-text-input__input").setValue(searchQuery).pressEnter();
        $$(".content-container").first()
                .shouldHave(text(searchQuery))
                .shouldHave(text(expectedText));
    }


    static Stream<Arguments> vcParamButtonText() {
        return Stream.of(
                Arguments.of(List.of("Популярное", "Свежее", "Компании", "Вакансии", "Мероприятия", "Подписки", "Личный опыт", "Маркетинг", "Дизайн", "Техника", "Карьера"), "https://vc.ru/popular"),
                Arguments.of(List.of("Популярное", "Свежее", "Компании", "Вакансии", "Мероприятия", "Подписки", "Личный опыт", "Маркетинг", "Дизайн", "Техника", "Карьера"), "https://vc.ru/new"),
                Arguments.of(List.of("Популярное", "Свежее", "Компании", "Вакансии", "Мероприятия", "Подписки", "Личный опыт", "Маркетинг", "Дизайн", "Техника", "Карьера"), "https://vc.ru/companies"),
                Arguments.of(List.of("Популярное", "Свежее", "Компании", "Вакансии", "Мероприятия", "Подписки", "Личный опыт", "Маркетинг", "Дизайн", "Техника", "Карьера"), "https://vc.ru/job"),
                Arguments.of(List.of("Популярное", "Свежее", "Компании", "Вакансии", "Мероприятия", "Подписки", "Личный опыт", "Маркетинг", "Дизайн", "Техника", "Карьера"), "https://vc.ru/events"),
                Arguments.of(List.of("Популярное", "Свежее", "Компании", "Вакансии", "Мероприятия", "Подписки", "Личный опыт", "Маркетинг", "Дизайн", "Техника", "Карьера"), "https://vc.ru/subs")
        );
    }
    @MethodSource("vcParamButtonText")
    @ParameterizedTest(name = "Наличие кнопок меню на странице {1}")
    void vcParamButtonText(List<String> buttonName, String Link) {
        open (Link);
        $$(".sidebar-tree-list-item a").filter(visible)
                .excludeWith(text("Где реклама?"))
                .shouldHave(CollectionCondition.texts(buttonName));
    }











}