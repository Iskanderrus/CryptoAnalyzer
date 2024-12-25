package ru.javarush.chasovskoy.cryptoanalyzer.entity;

import java.nio.file.Path;
import java.util.Objects;

/**
 * Represents the result of an operation with a message and a status code.
 */
public class Result {
    private final String message;
    private final ResultCode resultCode;
    private final Path outputPath;

    /**
     * Constructs a Result object.
     *
     * @param message              the message describing the result.
     * @param resultCode           the status code of the result.
     * @param outputPath           the path for the resulting file
     */
    public Result(String message, ResultCode resultCode, Path outputPath) {
        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException("Message cannot be null or blank");
        }
        if (resultCode == null) {
            throw new IllegalArgumentException("ResultCode cannot be null");
        }
        this.message = message;
        this.resultCode = resultCode;
        this.outputPath = outputPath;
    }

    public String getMessage() {
        return message;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    @Override
    public String toString() {
        return "Result{" +
                "message='" + message + '\'' +
                ", resultCode=" + resultCode +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return message.equals(result.message) && resultCode == result.resultCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, resultCode);
    }

    public String getOutputFilePath() {
        return this.outputPath.toString();
    }
}
