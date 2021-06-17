package testform;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class FillingOutTheFormTest {
    @BeforeAll
    static void setStartConfig() {
        Configuration.startMaximized = true;

    }

    String firstName = "Roman",
            lastName = "Kudinov",
            email = "auto@auto.ru",
            gender = "Male",
            mobile = "9169999999",
            monthOfBirth = "April",
            yearOfBirth = "1990",
            dayOfBirth = "30",
            subject1 = "Physics",
            hobby1 = "Music",
            hobby2 = "Sports",
            picture = "1.png",
            currentAddress = "Earth st.",
            state = "Uttar Pradesh",
            city = "Merrut";

    @Test
    void TeachingSubmitTest() {
        open("https://demoqa.com/automation-practice-form");

        //проверка открывшейся страницы
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));

        //Data entry
        $("#firstName").val(firstName);
        $("#lastName").val(lastName);
        $("#userEmail").val(email);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").val(mobile);
        //ввод даты в календаре
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(monthOfBirth);
        $(".react-datepicker__year-select").selectOption(yearOfBirth);
        $(String.format(".react-datepicker__day--0%s:not(.react-datepicker__day--outside-month)",
                dayOfBirth)).click();

        $("#subjectsInput").val(subject1).pressEnter();
        $("#hobbiesWrapper").$(byText(hobby1)).click();
        $("#hobbiesWrapper").$(byText(hobby2)).click();
        //загрузка картинки
        $("#uploadPicture").uploadFromClasspath("img/" + picture);

        $("#currentAddress").val(currentAddress);
        $("#state").click();
        $("#stateCity-wrapper").$(byText(state)).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();
        $("#submit").click();

        // Result

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $x("//td[text()='Student Name']").parent().shouldHave(text(firstName + " " + lastName));
        $x("//td[text()='Student Email']").parent().shouldHave(text(email));
        $x("//td[text()='Gender']").parent().shouldHave(text(gender));
        $x("//td[text()='Mobile']").parent().shouldHave(text(mobile));
        $x("//td[text()='Date of Birth']").parent().shouldHave(text(dayOfBirth + " " + monthOfBirth + "," + yearOfBirth));
        $x("//td[text()='Subjects']").parent().shouldHave(text(subject1));
        $x("//td[text()='Hobbies']").parent().shouldHave(text(hobby1 + ", " + hobby2));
        $x("//td[text()='Picture']").parent().shouldHave(text(picture));
        $x("//td[text()='Address']").parent().shouldHave(text(currentAddress));
        $x("//td[text()='State and City']").parent().shouldHave(text(state + " " + city));
    }

}
