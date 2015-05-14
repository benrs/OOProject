package com.example.astdemo;

import java.util.ArrayList;

public interface AysncResponse {
    void processFinish(ArrayList<String> list);
    void processUpdate(int progress);
}
