package com.pccofvns.ts.service;

import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Map;

@Service
public class PicklistService {
    public Map<String, String> getPickList(String picklistName) {
        return Map.of("ENG", "English");
    }

    public Map<String, String> getPicklistsForPicklistNameAndLocale(String name, Locale english) {
        return Map.of("ENG", "English");
    }
}
