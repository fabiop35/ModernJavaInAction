package com.mjia;

@FunctionalInterface
public interface BufferedReaderProcessor {

    String process(java.io.BufferedReader b) throws java.io.IOException;
}
