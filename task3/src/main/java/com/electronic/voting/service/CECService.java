package com.electronic.voting.service;

import com.electronic.voting.model.CEC;
import java.util.ArrayList;
import java.util.List;

public class CECService {
    private List<CEC> cecList = new ArrayList<>();

    public CEC createCEC(CEC cec) {
        cecList.add(cec);
        return cec;
    }

    public List<CEC> getAllCECs() {
        return new ArrayList<>(cecList);
    }

    public boolean deleteCEC(int cecId) {
        return cecList.removeIf(c -> c.getCecId() == cecId);
    }
}