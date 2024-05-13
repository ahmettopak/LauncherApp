package com.ahmet.launcherapp;

import androidx.annotation.NonNull;

/**
 * @author Ahmet TOPAK
 * @version 1.0
 * @since 4/19/2024
 */

public class AdbCommandResult {


    private String output;
    private String errorOutput;
    private int exitCode;

    public AdbCommandResult(String output, String errorOutput, int exitCode) {
        this.output = output;
        this.errorOutput = errorOutput;
        this.exitCode = exitCode;
    }

    public AdbCommandResult() {

    }
    public void setOutput(String output) {
        this.output = output;
    }

    public void setErrorOutput(String errorOutput) {
        this.errorOutput = errorOutput;
    }

    public void setExitCode(int exitCode) {
        this.exitCode = exitCode;
    }

    public String getOutput() {
        return output;
    }

    public String getErrorOutput() {
        return errorOutput;
    }

    public int getExitCode() {
        return exitCode;
    }

    @NonNull
    @Override
    public String toString() {
        return "AdbCommandResult{" +
                "output='" + output + '\'' +
                ", errorOutput='" + errorOutput + '\'' +
                ", exitCode=" + exitCode +
                '}';
    }
}