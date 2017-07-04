package com.sollyu.android.libsuperuser;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * 作者：Sollyu
 * 日期：2017/7/4
 * 说明：
 */

public class Shell {
    private static final String TAG = "Shell";

    public static class Result {
        private Integer            exitCode    = Integer.MIN_VALUE;
        private LinkedList<String> output      = new LinkedList<>();
        private LinkedList<String> errorOutput = new LinkedList<>();

        public Integer getExitCode() {
            return exitCode;
        }

        public LinkedList<String> getOutput() {
            return output;
        }

        public LinkedList<String> getErrorOutput() {
            return errorOutput;
        }
    }

    public static class Sh {


        public synchronized static Result run(String command) throws IOException, InterruptedException {
            Result result = new Result();
            Process shProcess = Runtime.getRuntime().exec(command);


            BufferedReader successResult = new BufferedReader(new InputStreamReader(shProcess.getInputStream()));
            BufferedReader errorResult   = new BufferedReader(new InputStreamReader(shProcess.getErrorStream()));

            String outputLine;
            while ((outputLine = successResult.readLine()) != null) {
                //Log.d(TAG, outputLine);
                result.getOutput().add(outputLine);
            }

            while ((outputLine = errorResult.readLine()) != null) {
                //Log.e(TAG, outputLine);
                result.getErrorOutput().add(outputLine);
            }

            shProcess.waitFor();
            result.exitCode = shProcess.exitValue();
            return result;
        }
    }

    public static class Su {

        private static Process          suProcess = null;
        private static DataOutputStream STDIN     = null;

        public synchronized static Result run(String command) throws IOException {
            Result result = new Result();

            if (suProcess == null) {
                suProcess = Runtime.getRuntime().exec("su");
                STDIN = new DataOutputStream(suProcess.getOutputStream());
            }

            STDIN.write((command + "\n").getBytes("UTF-8"));
            STDIN.write(("echo 1DCCADFED7BCBB036C56A4AFB97E906F $?\n").getBytes("UTF-8"));

            BufferedReader successResult = new BufferedReader(new InputStreamReader(suProcess.getInputStream()));
            BufferedReader errorResult   = new BufferedReader(new InputStreamReader(suProcess.getErrorStream()));

            String outputLine;
            while ((outputLine = successResult.readLine()) != null) {
                //Log.d(TAG, outputLine);
                result.getOutput().add(outputLine);
                if (outputLine.startsWith("1DCCADFED7BCBB036C56A4AFB97E906F ")) {
                    result.exitCode = Integer.valueOf(outputLine.substring("1DCCADFED7BCBB036C56A4AFB97E906F ".length()));
                    break;
                }
            }

            STDIN.write(("echo 1DCCADFED7BCBB036C56A4AFB97E906F $? >&2\n").getBytes("UTF-8"));
            while ((outputLine = errorResult.readLine()) != null) {
                //Log.e(TAG, outputLine);
                result.getErrorOutput().add(outputLine);
                if (outputLine.startsWith("1DCCADFED7BCBB036C56A4AFB97E906F ")) {
                    break;
                }
            }

            return result;
        }
    }
}

