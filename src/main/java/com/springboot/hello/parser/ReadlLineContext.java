package com.springboot.hello.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadlLineContext<T> {
    private Parser<T> parser;

    public ReadlLineContext(Parser<T> parser) {
        this.parser = parser;
    }

    public List<T> readByLine(String filename) throws IOException {
        List<T> result = new ArrayList<>();
        BufferedReader reader = new BufferedReader(
                new FileReader(filename)
        );
        String str;
        while((str = reader.readLine()) != null) {
            result.add(parser.parse(str));
        }
        reader.close();
        return result;
    }
}
