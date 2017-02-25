/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material,
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose.
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
 ***/
package iloveyouboss;

import java.io.*;

import org.json.simple.parser.*;
import org.junit.*;
import org.junit.jupiter.api.Test;
import util.*;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;


public class AddressRetrieverTest {
    @Test
    public void answersAppropriateAddressForValidCoordinates()
            throws IOException, ParseException {
        Http http = (String url) -> {
            if (!url.contains("lat=38.000000&lon=-104.000000")) fail("URL " + url + " に正しいパラメーターが含まれていません");
            return
                    "{\"address\":{"
                            + "\"house_number\":\"324\","
                            + "\"road\":\"North Tejon Street\","
                            + "\"city\":\"Colorado Springs\","
                            + "\"state\":\"Colorado\","
                            + "\"postcode\":\"80903\","
                            + "\"country_code\":\"us\"}"
                            + "}";
        };
        AddressRetriever retriever = new AddressRetriever(http);

        Address address = retriever.retrieve(38.0, -104.0);

        assertThat(address.houseNumber).isEqualTo("324");
        assertThat(address.road).isEqualTo("North Tejon Street");
        assertThat(address.city).isEqualTo("Colorado Springs");
        assertThat(address.state).isEqualTo("Colorado");
        assertThat(address.zip).isEqualTo("80903");
    }

    @Test
    public void returnsAppropriateAddressForValidCoordinates()
            throws IOException, ParseException {
        Http http = new Http() {
            @Override
            public String get(String url) throws IOException {
                return "{\"address\":{"
                        + "\"house_number\":\"324\","
                        + "\"road\":\"North Tejon Street\","
                        // ...
                        + "\"city\":\"Colorado Springs\","
                        + "\"state\":\"Colorado\","
                        + "\"postcode\":\"80903\","
                        + "\"country_code\":\"us\"}"
                        + "}";
            }
        };
        AddressRetriever retriever = new AddressRetriever(http);

        Address address = retriever.retrieve(38.0, -104.0);

        assertThat(address.houseNumber).isEqualTo("324");
        assertThat(address.road).isEqualTo("North Tejon Street");
        assertThat(address.city).isEqualTo("Colorado Springs");
        assertThat(address.state).isEqualTo("Colorado");
        assertThat(address.zip).isEqualTo("80903");
    }
}
