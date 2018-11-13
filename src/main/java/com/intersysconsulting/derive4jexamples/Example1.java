package com.intersysconsulting.derive4jexamples;

import lombok.val;

import java.util.function.Function;

import static com.intersysconsulting.derive4jexamples.PersonNames.personNameShow;
import static fj.Show.stringShow;

public class Example1 {

    public static void main(String[] args) {
        val nameErrorMsg = "A name cannot be only spaces, must not start or end with space";

        val nameStartsWithUppercaseMsg = "Name starts with uppercase letter";
        val nameStartsWithLowercaseMsg = "Name starts with lowercase letter";
        final Function<String, String> msgFromName =
            s -> Character.isUpperCase(s.charAt(0))
                ? nameStartsWithUppercaseMsg
                : nameStartsWithLowercaseMsg;

        val name = PersonName.parseName("Oscar");
        name.foreach(personNameShow()::println);
        val msg = name.option(nameErrorMsg,
            n -> n.match(msgFromName));
        stringShow.println(msg);
    }
}
